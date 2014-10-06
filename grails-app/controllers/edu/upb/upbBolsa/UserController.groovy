package edu.upb.upbBolsa

import Registration.User

class UserController {


    def springSecurityService

    def index() {
        redirect action: 'brokerMessages', params: params
    }

    def brokerMessages(){

    }

    def sendBrokerMessages(){
        if (request.method == 'POST') {
            def user = User.findByUsername (params.search_broker)
            def broker = Broker.findByUser(user)
            User user2 = springSecurityService.currentUser;
            if(broker.userslimit > broker.users.size() && user2.broker == null) {
                broker.addToUsers(springSecurityService.currentUser);
                user2.broker = broker;
            }
            else{
                flash.message = 'Este broker ya no puede aceptar mas usuarios o este broker ya te tiene registrado'
            }
            redirect(controller: 'user', action: 'brokerMessages')
        }
    }

}
