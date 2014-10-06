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
        User user = springSecurityService.currentUser
        Broker broker = Broker.findByUser(user);
        ArrayList <String> list_users = new ArrayList<String>()
        for (User i:broker.users){
            if(i.capital > 0){
                list_users.add(i.username)
            }
        }
        params.broker_users_available = list_users
        params.companies_availables = Company.getAll().name
        params.broker_all_users = broker.users
        render view: 'adminUsers.gsp'
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
