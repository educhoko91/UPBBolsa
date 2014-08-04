package edu.upb.upbBolsa

import Registration.User
import edu.upb.upbBolsa.Cajero

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
        print(habilitaciones.cajero)
    }
}
