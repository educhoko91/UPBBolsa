package edu.upb.upbBolsa

import Registration.User
import edu.upb.upbBolsa.Cajero
import grails.converters.*

class CajeroController {

    def index() {
//        redirect(action: "Habilitaciones", params: params)
        render view: 'Habiltaciones', params: params
    }
    def springSecurityService
    def save(){
        Habilitaciones habilitaciones = new Habilitaciones();
        habilitaciones.cajero = springSecurityService.currentUser;
//        habilitaciones.cajero = new User();
//        habilitaciones.save()
//        int selfId = this.
        print(habilitaciones.cajero.id)
    }

    def ajaxUsers = {
        def usersFound = User.withCriteria {
            ilike 'username', params.term + '%'
        }
        print(usersFound.username)
        render (usersFound.username as JSON)
    }
}
