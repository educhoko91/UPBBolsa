package edu.upb.upbBolsa

import Registration.User

class TransaccionesController {

    def springSecurityService
    def index() {
        render view: 'transacciones'

    }

    def compra(){
        User user = springSecurityService.currentUser
        [user:user]

    }

    def venta(){
        User user = springSecurityService.currentUser
        [user: user]
    }
}
