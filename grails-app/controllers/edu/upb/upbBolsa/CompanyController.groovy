package edu.upb.upbBolsa

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.grails.plugins.excelimport.*
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

class CompanyController {

    def index() {

    }

    def create(){
        [companyInstance:new Company(params)]
    }

    def save(){

        def comp  = new Company(params);
        if(comp.validate()) {
            def serie = new Serie()
            comp.serie = serie;

            if (comp.save(true)) {


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
                print serieList
                for (s in serieList) {
                    def datos = new DatoSerie()
                    datos.serie = serie
                    datos.period = s.period
                    datos.price = s.price
                    datos.save()
                }

            }
        }


    }
}
