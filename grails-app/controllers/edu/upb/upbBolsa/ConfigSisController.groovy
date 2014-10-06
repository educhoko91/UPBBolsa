package edu.upb.upbBolsa

import Registration.UserRole
import edu.upb.upbBolsa.VariablesSistema


class ConfigSisController {

    private def parametros;

    def index() {
        initParametros()
       /* for (VariablesSistema var:parametros) {
            print var.nombre+"->"+var.value
        }*/
        [parametros: parametros]
    }

    def save() {
       def isSave = false;
        def accion = null;
        for(param in request.parameterMap) {
            if(param.key.equals("nuevaSimulacion")||param.key.equals("reiniciar")) {
                accion = param.key;
            }
            else {
                def varSis = VariablesSistema.findByNombre(param.key)
                varSis.value = param.value[0]
                if (varSis.save(true)) {
                    isSave = true
                } else {
                    isSave = false
                    break;
                }
            }
        }
        if(isSave) {
            if(accion==null) {
                redirect(action: "index")
            }
            else if(accion.equals("nuevaSimulacion")) {
                clearDatabase();
                redirect(controller: "company", action: "create")

            }
            else {
                resetDatabase();
                redirect(controller: "simulation", action: "start");
            }
        } else {
            redirect(action: "index")
        }

    }

    private def initParametros()
    {
        this.parametros = VariablesSistema.findAll();
    }


    private def clearDatabase() {
        if(Transacciones.count()>0) {
            def transacciones = Transacciones.findAll();
            Transacciones.deleteAll(transacciones);
        }
        if(Noticia.count()>0) {
            def noticias =  Noticia.findAll();
            Noticia.deleteAll(noticias);
        }
        if(Pagos.count()>0) {
            def pagos =  Pagos.findAll();
            Pagos.deleteAll(pagos);
        }
        if(Habilitaciones.count()>0) {
            Habilitaciones.deleteAll();
        }
        if(DatoSerie.count()>0) {
            def datoSerie = DatoSerie.findAll()
            DatoSerie.deleteAll(datoSerie);
        }
        if(Serie.count()>0){
            def serie = Serie.findAll();
            Serie.deleteAll(serie);
        }
        if(Company.count()>0) {
            def company =  Company.findAll();
            Company.deleteAll(company);
        }
        if(Cajero.count()>0) {
            def cajero = Cajero.findAll()
            Cajero.deleteAll(cajero);
        }
        if(Broker.count()>0) {
            def brokers = Broker.findAll()
            Broker.deleteAll(brokers);
            
        }
        /*if(UserRole.count()>0) {
            def userRoles = UserRole.findAllWhere([role: "ROLE_BROK"]);
            UserRole.removeAll(userRoles);
            userRoles = UserRole.findAllWhere([role: "ROLE_CAJA"]);
            UserRole.removeAll(userRoles);
        }*/


    }

    private def resetDatabase() {

        if(Transacciones.count()>0) {
            Transacciones.deleteAll();
        }
        if(Pagos.count()>0) {
            Pagos.deleteAll();
        }
        if(Habilitaciones.count()>0) {
            Habilitaciones.deleteAll();
        }

        for(DatoSerie s: DatoSerie.findAll()) {
            s.time = CompanyController.calcTime(s.period);
        }
    }
}
