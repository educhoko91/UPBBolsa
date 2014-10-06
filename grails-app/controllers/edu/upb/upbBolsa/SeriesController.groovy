package edu.upb.upbBolsa

import upbbolsa.SyncEngineService

import java.text.NumberFormat

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
                String link = "<a href=\"${createLink(action: "singleSerie",id: c.id)}\">${c.name} (${c.code})</a>"
                compTable += "<td> ${link} <br/> <div id=\"container${c.serie.id}\" style=\"width:300px\"></div> </td>";
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
                        'stats' : calcStatsData(id),
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

    def singleSerie(long id) {
        if (SyncEngineService.getCiclo()==-2) {
            redirect(action: "noConfig");
        } else if (SyncEngineService.getCiclo()==-1) {
            redirect(action: "waitingStart");
        } else {
            def companie = Company.findById(id);


            def ciclo = -1;
            if (SyncEngineService.getCiclo() != null) {
                print("ciclo =" + ciclo);
                ciclo = SyncEngineService.getCiclo();
            }
            def noticias = Noticia.getAll();
            //def charsData = charsData(companies);
            //[compTable: compTable, companies: companies, charsData:charsData];
            [companie: companie, ciclo: ciclo, inter: SyncEngineService.getInter(), stats: calcStatsData(id),noticias:noticias];
        }
    }

    private def calcStatsData(long id) {
       // Load Data
        def company = Company.findById(id);
        def serie = company.serie.datos;

        double sum = 0;
        double min = 99999999;
        double max = 0;
        int calcCiclo = 0

        for(def s: serie) {
            if(s.period < SyncEngineService.getCiclo()){
                calcCiclo = SyncEngineService.getCiclo();
                sum += s.price;
                if(s.price > max)
                    max = s.price;
                if(s.price < min)
                    min = s.price;
            }
        }

        double mean = sum/calcCiclo;
        return [mean: mean, min: min, max: max];

    }


}
