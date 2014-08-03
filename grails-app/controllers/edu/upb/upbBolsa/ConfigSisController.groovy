package edu.upb.upbBolsa

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
        for(param in request.parameterMap) {
            def varSis = VariablesSistema.findByNombre(param.key)
            varSis.value = param.value[0]
            if(varSis.save(true)) {
                isSave = true
            } else {
                isSave = false
                break;
            }
        }
        if(isSave) {
            redirect(controller: "company", action: "create")
        } else {
            redirect(action: "index")
        }

    }

    private def initParametros() {
        this.parametros = VariablesSistema.findAll();
    }
}
