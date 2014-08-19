package edu.upb.upbBolsa

import upbbolsa.SyncEngineService

class SeriesController {


    def index() {
        def companies = Company.findAll();
        String compTable = "";
        boolean newRow=true;
        for(def c: companies) {
            if(newRow){
                compTable += "<tr>";
            }
            compTable += "<td> ${c.name} (${c.code}) <br/> <div id=\"container${c.serie.id}\" style=\"width:300px\"/> </td>";
            if(!newRow) {
                compTable += "</tr>";
            }
            newRow = !newRow;

        }


        def ciclo = -1;
        if(SyncEngineService.getCiclo() != null) {
            print(ciclo);
            ciclo = SyncEngineService.getCiclo();
        }


        //def charsData = charsData(companies);
        //[compTable: compTable, companies: companies, charsData:charsData];
        [compTable: compTable, companies: companies, ciclo:ciclo];
    }

    def updateSeries(def id) {

    }


}
