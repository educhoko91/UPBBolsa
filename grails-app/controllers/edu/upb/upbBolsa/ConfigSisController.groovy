package edu.upb.upbBolsa

import edu.upb.upbBolsa.VariablesSistema


class ConfigSisController {

    private def parametros;

    def index() {
        initParametros()
        [parametros: parametros]
    }

    private def initParametros() {
        this.parametros = VariablesSistema.findAll();
    }
}
