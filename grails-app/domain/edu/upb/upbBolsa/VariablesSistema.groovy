package edu.upb.upbBolsa

import org.springframework.transaction.annotation.Transactional

@Transactional
class VariablesSistema {

    String nombre;
    String value;

    static constraints = {
        nombre{blank:false}
        value{blank: false}
    }
}
