package edu.upb.upbBolsa

import Registration.User
import edu.upb.upbBolsa.Cajero
import grails.converters.*
import grails.plugins.springsecurity.SpringSecurityService

class CajeroController {

    def springSecurityService
    def index() {
        User cajero = springSecurityService.currentUser;
        params.cajeroname = cajero;
        render (view: "/Cajero/habiltaciones", params: params)

    }


    def save(){
        User habilitado = User.findByUsername(params.username);

        print("numero habilitaciones " + VariablesSistema.findByNombre('numHabilit').getValue())
        if (habilitado.numeroHabilitaciones > Integer.parseInt(VariablesSistema.findByNombre('numHabilit').getValue()) ){
            User cajero = springSecurityService.currentUser;
            params.cajeroname = cajero;
            flash.message = "Ya no tiene mas habilitaciones disponibles"
            redirect(controller: 'cajero',action: 'index')
        } else {
            Habilitaciones habilitaciones = new Habilitaciones();
            habilitaciones.cajero = springSecurityService.currentUser;
            params.cajeroname = habilitaciones.cajero;
            habilitaciones.habilitado = habilitado;
            habilitaciones.save()
            habilitado.numeroHabilitaciones += 1
            habilitado.capital += Double.parseDouble(VariablesSistema.findByNombre('capInicio').getValue())
            habilitado.save()
        }
        print(params)
        render view: "/Cajero/habiltaciones"
    }

    def ajaxUsers = {
        def usersFound = User.withCriteria {
            ilike 'username', params.term + '%'
        }
        print(usersFound.username)
        render (usersFound.username as JSON)
    }
    def cajareporte (){
        User usuarioac = springSecurityService.currentUser;
    }
}
