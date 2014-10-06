package edu.upb.upbBolsa

import org.springframework.dao.DataIntegrityViolationException
import upbbolsa.SyncEngineService

class NoticiaController {

    def index() {
        redirect(action: "create", params: params)
    }

    def create(){
        def noticias = Noticia.getAll();
        [noticiaInstance:new Noticia(params), noticias:noticias]
    }

    def save(){
        def noticia = new Noticia(params);
//        print "paso new noticia"
        print (params)
        for(String n : params.companies){
            Integer i = Integer.parseInt(n);
            def auxCompany = Company.get(i);
            noticia.addToCompanies(auxCompany);
            auxCompany.addToNoticias(noticia);
            auxCompany.save(flush: true);
        }

        print(noticia);

        if(noticia.validate()){
            noticia.save(flush: true);

        }
        else {
            flash.message = "La noticia no pudo ser guardada en base de datos, revise que los campos obligatorios hayan sido llenados correctamente"
        }
        redirect(action: "create")
    }

    def edit(){
        try{
            def noticia = Noticia.get(params.id);
            if(noticia){
                [noticiaInstance: noticia]
            }
            else{
                flash.message = "selecciono una instancia de noticia que ya no existe"
                redirect(action: "create")
            }
        }catch (DataIntegrityViolationException e){
            print "no se pudo borrar"
        }
    }
    def update(){
        print params
        def noticia = Noticia.get(params.id)
        noticia.setTitulo(params.titulo);
        noticia.setDescripcion(params.descripcion)
        noticia.setPeriodo(Integer.parseInt(params.periodo))
        HashSet<Company> companies = new HashSet<>()
        for(String n : params.companies){
            companies.add(Company.get(Integer.parseInt(n)))
        }
        noticia.setCompanies(companies)
        redirect(action: "create")
    }

    def getNoticiasBySerieAndPeriod(long serieId){
        print "ciclo "+SyncEngineService.getCiclo();
        Serie s = Serie.get(serieId)
        Company company = s.company;
        List<Noticia> lnoticias = Noticia.findAllByPeriodo(SyncEngineService.getCiclo());
//        print lnoticias;
        print(company);
        Noticia displayedNotice = null;
        for(Noticia n : lnoticias){
            print("ncompanies"+ n.companies);
            print n.companies.contains(company)
            if(n.companies.contains(company)){
                displayedNotice = n;
                company.setLastNoticiaDisplayed(n);
                break;
            }
        }
        if(displayedNotice !=null){
            render(contentType: 'text/json') {
                [
                        'change': true,
                        'title': displayedNotice.titulo,
                        'contenido' : displayedNotice.descripcion,
                ]
            }

        }
        else if(company.lastNoticiaDisplayed != null){
            render(contentType: 'text/json') {
                [
                        'change':false,
                        'title':company.lastNoticiaDisplayed.getTitulo(),
                        'contenido':company.lastNoticiaDisplayed.descripcion,
                ]
            }
        }
        else{
            render(contentType: 'text/json') {
                [
                        'change':false,
                        'title':"",
                        'contenido':"",
                ]
            }
        }
    }
}
