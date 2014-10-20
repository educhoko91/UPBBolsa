package edu.upb.upbBolsa

import grails.plugins.springsecurity.Secured
import org.springframework.dao.DataIntegrityViolationException
import upbbolsa.SyncEngineService

class NoticiaController {

    @Secured(["hasRole('ROLE_ADMIN')"])
    def index() {
        redirect(action: "create", params: params)
    }

    @Secured(["hasRole('ROLE_ADMIN')"])
    def create(){
        def noticias = Noticia.getAll();
        [noticiaInstance:new Noticia(params), noticias:noticias]
    }

    @Secured(["hasRole('ROLE_ADMIN')"])
    def save(){
        def noticia = new Noticia(params);
//        print "paso new noticia"
        if(noticia.validate()){
            noticia.save(flush: true);
            for(String n : params.companies){
                Long i = Long.parseLong(n);
                def auxCompany = Company.findById(i);
                def rel = new NoticiaCompRel(noticiaId: noticia.id, companyId: auxCompany.id);
                rel.save();
            }
        }
        else {
            flash.message = "La noticia no pudo ser guardada en base de datos, revise que los campos obligatorios hayan sido llenados correctamente"
        }
        print (params)


//        print(noticia);


        redirect(action: "create")
    }

    @Secured(["hasRole('ROLE_ADMIN')"])
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
            print "no se pudo editar"
        }
    }

    @Secured(["hasRole('ROLE_ADMIN')"])
    def update(){
        print params
        def noticia = Noticia.get(params.id)
        noticia.setTitulo(params.titulo);
        noticia.setDescripcion(params.descripcion)
        noticia.setPeriodo(Integer.parseInt(params.periodo))
        noticia.save();
        def noticiasRelToDel = NoticiaCompRel.findAllByNoticiaId(noticia.id)

        for(NoticiaCompRel n: noticiasRelToDel){
            try{
                n.delete(flush: true)
            }catch (DataIntegrityViolationException e){
                print "no se pudo borrar"
            }
        }
//        HashSet<Company> companies = new HashSet<>()
        for(String n : params.companies){
            Long i = Long.parseLong(n);
            def auxCompany = Company.findById(i);
            def rel = new NoticiaCompRel(noticiaId: noticia.id, companyId: auxCompany.id);
            rel.save();
        }
//        noticia.setCompanies(companies)
        redirect(action: "create")
    }

    def list(){
        def noticias = Noticia.findAllByPeriodoLessThanEquals(SyncEngineService.getCiclo());
        [noticias:noticias]
    }
    def show(){
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
            print "no se pudo mostrar"
        }
    }

    @Secured(["hasRole('ROLE_ADMIN')"])
    def delete(){
        try{
            def noticia = Noticia.get(params.id);
            def noticiasRelToDel = NoticiaCompRel.findAllByNoticiaId(noticia.id)

            for(NoticiaCompRel n: noticiasRelToDel){
                try{
                    n.delete(flush: true)
                }catch (DataIntegrityViolationException e){
                    print "no se pudo borrar"
                }
            }
            noticia.delete(flush: true)
        }catch (DataIntegrityViolationException e){
            print "no se pudo borrar"
        }
        redirect(action: "create", params: params)
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
            def aux = NoticiaCompRel.findAllByNoticiaIdAndCompanyId(n.id,company.id);
            if(aux.size()>0){
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
                        'change':true,
                        'title':company.lastNoticiaDisplayed.titulo,
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
