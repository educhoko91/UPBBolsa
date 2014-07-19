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
        Map SERIES_DATA = [
                sheet:'Sheet1',
                startRow: 1,
                columnMap:  [
                        //Col, Map-Key
                        'A':'price',
                ]
        ]


        MultipartHttpServletRequest mpr = (MultipartHttpServletRequest)request;
        CommonsMultipartFile file = (CommonsMultipartFile) mpr.getFile("file");

        Workbook workbook = WorkbookFactory.create(file.inputStream)

        List<Map> bookList = excelImportService.columns(workbook, SERIES_DATA)


    }
}
