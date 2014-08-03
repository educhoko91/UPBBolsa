package edu.upb.upbBolsa

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.grails.plugins.excelimport.*
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile
import upbbolsa.SyncEngineService;

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
        print "entro";
        def comp  = new Company(params);
        print params.file.getOriginalFilename();
        print comp.name
        print comp.code
        def serie = new Serie()
        serie.name = params.file.getOriginalFilename();
        comp.serie = serie;
        print comp.validate();
        if(comp.validate()) {
            print "paso primer if"

            serie.save(flush:true);


            if (comp.save(flush:true)) {
                print "paso el if"

                Map SERIES_DATA = [
                        sheet    : 'Sheet1',
                        startRow : 0,
                        columnMap: [
                                'A': 'period',
                                'B': 'price',
                        ]
                ]


                MultipartHttpServletRequest mpr = (MultipartHttpServletRequest) request;
                CommonsMultipartFile file = (CommonsMultipartFile) mpr.getFile("file");

                Workbook workbook = WorkbookFactory.create(file.inputStream)

                List<Map> serieList = new ExcelImportService().columns(workbook, SERIES_DATA)
                if(VariablesSistema.findByNombre("cicloFin")==null) {
                    new VariablesSistema(nombre: 'cicloFin',value: serieList.size()).save(failOnError: true)
                }
                for (s in serieList) {
                    def datos = new DatoSerie()
                    datos.serie = serie
                    datos.period = s.period
                    datos.price = s.price
                    datos.save()
                }

            }

        }else{

            comp.errors.each {
                print it
            }

        }
        redirect(action: "create", params: params)

    }
}
