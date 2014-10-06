package edu.upb.upbBolsa

import grails.plugins.springsecurity.Secured

import javax.servlet.Registration

import Registration.User;

@Secured(['IS_AUTHENTICATED_REMEMBERED',"hasRole('ROLE_BROK')"])

class BrokerFunctionsController {

    static springSecurityService

    def index() {
        redirect(controller: 'brokerFunctions' , action: 'adminUsers')
    }

    def adminUsers(){

    }

    def messageToUsers(){
        User user = springSecurityService.currentUser
        Broker broker = Broker.findByUser(user);
        for(User i: broker.users){
            i.mensaje = params.send_message
        }
        redirect(controller: 'brokerFunctions' , action: 'adminUsers')
    }
}
