package edu.upb.upbBolsa

import grails.plugins.springsecurity.Secured
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.grails.plugins.excelimport.*
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile
import upbbolsa.SyncEngineService;

@Secured(['IS_AUTHENTICATED_REMEMBERED',"hasRole('ROLE_ADMIN')"])
class CompanyController {

    def index() {
        redirect(action: "create", params: params)
    }

    def create(){
        def companies = Company.getAll();
        print companies;
        [companyInstance:new Company(params),companies:companies ]
    }


    def delete(){
        print params
        try{
            def company = Company.get(params.id);
            company.delete(flush: true)
        }catch (DataIntegrityViolationException e){
            print "no se pudo borrar"
        }
        redirect(action: "create", params: params)
    }

    def save(){
//        print "entro";
        def comp  = new Company(params);
//        print params.file.getOriginalFilename();
//        print comp.name
//        print comp.code
        def serie = new Serie()
        serie.name = params.file.getOriginalFilename();
        comp.serie = serie;
//        print comp.validate();
        if(comp.validate()) {
            MultipartHttpServletRequest mpr = (MultipartHttpServletRequest) request;
            CommonsMultipartFile file = (CommonsMultipartFile) mpr.getFile("file");
            Workbook workbook;
            List<Map> serieList;
            try {
                 workbook = WorkbookFactory.create(file.inputStream)
            } catch (Exception e){
                flash.message = "No se ingreso ninguna serie, por favor ingrese una serie"
                redirect(action: 'create')
                return
            }

            try {
                Map SERIES_DATA = [
                        sheet    : 'Sheet1',
                        startRow : 0,
                        columnMap: [
                                'A': 'period',
                                'B': 'price',
                        ]
                ]
                serieList = new ExcelImportService().columns(workbook, SERIES_DATA)
                if(serieList.size()==0) {
                    SERIES_DATA = [
                            sheet    : 'Hoja1',
                            startRow : 0,
                            columnMap: [
                                    'A': 'period',
                                    'B': 'price',
                            ]
                    ]
                    serieList = new ExcelImportService().columns(workbook, SERIES_DATA);
                }
            }   catch (MissingPropertyException e1){
                flash.message = 'El archivo excel no se encuentra en el formato establecido.'
                redirect(action: 'create')
                return
            }
            serie.save(flush:true);


            if (comp.save(flush:true)) {
                print "paso el if"

                if(VariablesSistema.findByNombre("cicloFin")==null) {
                    new VariablesSistema(nombre: 'cicloFin',value: serieList.size()).save(failOnError: true)
                }
                for (s in serieList) {
                    def datos = new DatoSerie()
                    datos.serie = serie
                    datos.period = s.period
                    datos.price = s.price
                    datos.time = calcTime(s.period);
                    datos.save(failOnError: true);
                }

                def cicloFin = VariablesSistema.findByNombre("cicloFin");
                cicloFin.value  = Math.min(serieList.size(),Long.parseLong(cicloFin.value));
                cicloFin.save(failOnError: true, flush: true);

            }

        }else{

            comp.errors.each {
                print it
            }

        }
        redirect(action: "create", params: params)

    }

    public static def calcTime(def period) {
        def puntoInicial = Integer.parseInt(VariablesSistema.findByNombre("puntoInicial").value);
        def interTiempo = Integer.parseInt(VariablesSistema.findByNombre("interTiempo").value);
        def horaInicio = new Date().parse("HH:mm",VariablesSistema.findByNombre("horaInicio").value);
        def currentPeriod = (int)period;
        def calcPeriod = (currentPeriod -puntoInicial)*interTiempo;
        def time = Calendar.getInstance();
        time.setTime(horaInicio);
        time.add(Calendar.SECOND,calcPeriod);
        return time.format("HH:mm:ss").toString();

    }
}
