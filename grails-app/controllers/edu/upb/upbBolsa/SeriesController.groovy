package edu.upb.upbBolsa

import upbbolsa.SyncEngineService

class SeriesController {

    def id = 0;

    def index() {
        sleep(200);
        if (SyncEngineService.getCiclo()==-2) {
            redirect(action: "noConfig");
        } else if (SyncEngineService.getCiclo()==-1) {
            redirect(action: "waitingStart");
        } else {
            def companies = Company.findAll();
            String compTable = "";
            boolean newRow = true;
            for (def c : companies) {
                if (newRow) {
                    compTable += "<tr>";
                }
                compTable += "<td> ${c.name} (${c.code}) <br/> <div id=\"container${c.serie.id}\" style=\"width:300px\"></div> </td>";
                if (!newRow) {
                    compTable += "</tr>";
                }
                newRow = !newRow;

            }


            def ciclo = -1;
            if (SyncEngineService.getCiclo() != null) {
                print("ciclo =" + ciclo);
                ciclo = SyncEngineService.getCiclo();
            }

            //def charsData = charsData(companies);
            //[compTable: compTable, companies: companies, charsData:charsData];
            [compTable: compTable, companies: companies, ciclo: ciclo, inter: SyncEngineService.getInter()];
        }
    }

    def updateSeries(long id) {
        if(SyncEngineService.getCiclo() >=0) {
            def company = Company.findById(id);
            def datos = DatoSerie.find {
                serie == company.serie && period == SyncEngineService.getCiclo();
            }
            render(contentType: 'text/json') {
                [
                        'time'  : datos.time,
                        'price' : datos.price,
                        'inter' : SyncEngineService.getInter(),
                        'succes': true

                ]
            }
        }
        else {
            render(contentType: 'text/json') {
                [
                        'succes': false,
                        'inter' : SyncEngineService.getInter(),
                ]
            }
        }
    }

    def noConfig() {

    }

    def waitingStart() {
        ['time':SyncEngineService.getInter()];
    }


}
