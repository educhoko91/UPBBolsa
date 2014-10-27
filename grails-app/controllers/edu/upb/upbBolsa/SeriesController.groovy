package edu.upb.upbBolsa

import grails.plugins.springsecurity.Secured
import upbbolsa.SyncEngineService

import java.text.NumberFormat
@Secured('IS_AUTHENTICATED_REMEMBERED')
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

            //def charsData = charsData(companies);
            //[compTable: compTable, companies: companies, charsData:charsData];
            [companie: companie, ciclo: ciclo, inter: SyncEngineService.getInter(), stats: calcStatsData(id),lastNoticiaData:getNoticiaData(id)];
        }
    }

    private def getNoticiaData(long id){
        def company = Company.findById(id);

        def noticiasRel = NoticiaCompRel.findAllByCompanyId(id);
        ArrayList<Long> noticias_ids = new ArrayList<>();
        for(NoticiaCompRel n: noticiasRel){
            noticias_ids.add(n.noticiaId);
        }

        def noticias = Noticia.findAllByIdInListAndPeriodoLessThanEquals(noticias_ids,SyncEngineService.getCiclo());

        Collections.sort(noticias,new Comparator<Noticia>() {
            @Override
            int compare(Noticia o1, Noticia o2) {
                return o2.periodo - o1.periodo;
            }
        })

        noticias[0] ? company.setLastNoticiaDisplayed(noticias[0]):null;

        [title:noticias[0]?.titulo? noticias[0].titulo:"", descripcion:noticias[0]?.descripcion ? noticias[0].descripcion:""];

    }

    private def calcStatsData(long id) {
       // Load Data
        def company = Company.findById(id);
        def serie = company.serie.datos;

        double sum = 0;
        double min = 99999999;
        double max = 0;
        int calcCiclo = 0;
        double price = 0.0;

        for(def s: serie) {
            if(s.period < SyncEngineService.getCiclo()){
                calcCiclo = SyncEngineService.getCiclo();
                sum += s.price;
                if(s.price > max)
                    max = s.price;
                if(s.price < min)
                    min = s.price;
            }

            if(s.period == SyncEngineService.getCiclo()){
                price = s.price;
            }
        }
        double mean = sum/calcCiclo;
        return [mean: mean, min: min, max: max, price:price];

    }


}
