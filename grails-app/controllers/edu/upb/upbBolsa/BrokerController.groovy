package edu.upb.upbBolsa

import Registration.Role
import Registration.User
import Registration.UserRole
import org.springframework.dao.DataIntegrityViolationException
import groovy.sql.Sql
import grails.plugins.springsecurity.Secured

@Secured(['IS_AUTHENTICATED_REMEMBERED',"hasRole('ROLE_ADMIN')"])

class BrokerController {

    static scaffold = true

    def dataSource

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)

        [brokerInstanceList: Broker.list(params), brokerInstanceTotal: Broker.count()]
    }

    def save(){
        def userRole = Role.findByAuthority('ROLE_BROK')
        if (request.method == 'POST') {
            def db = new Sql(dataSource)
            String val = String.valueOf(params.user.id)
            def result = db.rows("SELECT id FROM user WHERE username = ?;", [val])
            int trueValueOfUserId = Integer.parseInt(String.valueOf(result).substring(5,String.valueOf(result).length()-2))
            params.user.id = trueValueOfUserId
            params."user.id" = trueValueOfUserId
            def broker = new Broker(params)
            if (!broker.save()) {
               flash.message = 'No se pudo registrar broker'
                redirect(controller:'Broker', action: 'list')
                return [broker:broker]
            } else {
                UserRole.create(broker.user,userRole)
                flash.message = 'Broker registrado'
                redirect(controller:'broker', action: 'list')
            }
        }
    }

    def delete (){
        def brokerInstance = Broker.get(params.id)
        def brokerRole = Role.findByAuthority('ROLE_BROK')
        if (!brokerInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'broker.label', default: 'Broker'), params.id])
            redirect(action: "list")
            return
        }

        try {
            UserRole.remove(brokerInstance.user,brokerRole)
            brokerInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'broker.label', default: 'Broker'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'category.label', default: 'Category'), params.id])
            redirect(action: "show", id: params.id)
        }
    }

}
