package edu.upb.upbBolsa

import Registration.User
import edu.upb.upbBolsa.Cajero
import grails.converters.*

class CajeroController {

    def index() {
        render view: 'Habiltaciones', params: params
    }



    def springSecurityService
    def save(){
        User habilitado = User.findByUsername(params.username);

        print("numero habilitaciones " + VariablesSistema.findByNombre('numHabilit').getValue())
        if (habilitado.numeroHabilitaciones > VariablesSistema.findByNombre('numHabilit').getValue() ){
            flash.message = "Ya no tiene mas habilitaciones disponibles"
            redirect(controller: 'cajero',action: 'index')
        } else {
            Habilitaciones habilitaciones = new Habilitaciones();
            habilitaciones.cajero = springSecurityService.currentUser;
            params.cajeroname = habilitaciones.cajero;
            habilitaciones.habilitado = habilitado;
            habilitaciones.save()
            habilitado.numeroHabilitaciones += 1
            habilitado.capital = Double.parseDouble(VariablesSistema.findByNombre('capInicio').getValue())
            habilitado.save()
        }
        render view: "Habiltaciones.gsp"
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
