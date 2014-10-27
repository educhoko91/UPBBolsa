package edu.upb.upbBolsa

import Registration.Role
import Registration.User
import Registration.UserRole
import edu.upb.upbBolsa.Cajero
import grails.converters.*
import grails.plugins.springsecurity.SpringSecurityService
import groovy.sql.Sql
import org.springframework.dao.DataIntegrityViolationException

class CajeroController {

    def springSecurityService

    static scaffold = true

    def dataSource

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)

        [cajeroInstanceList: Cajero.list(params), cajeroInstanceTotal: Cajero.count()]
    }

    def save2(){
        def userRole = Role.findByAuthority('ROLE_CAJA')
        if (request.method == 'POST') {
            def db = new Sql(dataSource)
            String val = String.valueOf(params.user.id)
            def result = db.rows("SELECT id FROM user WHERE username = ?;", [val])
            int trueValueOfUserId = Integer.parseInt(String.valueOf(result).substring(5,String.valueOf(result).length()-2))
            params.user.id = trueValueOfUserId
            params."user.id" = trueValueOfUserId
            def cajero = new Cajero(params)
            if (!cajero.save()) {
                flash.message = 'No se pudo registrar cajero'
                redirect(controller:'cajero', action: 'list')
                return [cajero:cajero]
            } else {
                UserRole.create(cajero.user,userRole)
                flash.message = 'Cajero registrado'
                redirect(controller:'cajero', action: 'list')
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
        render view: "/Cajero/habiltaciones"
    }

    def ajaxUsers = {
        def usersFound = User.withCriteria {
            ilike 'username', params.term + '%'
        }
        render (usersFound.username as JSON)
    }
    def cajareporte (){
        User usuarioac = springSecurityService.currentUser;
    }


}
