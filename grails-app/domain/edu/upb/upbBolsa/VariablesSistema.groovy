package edu.upb.upbBolsa

class VariablesSistema {

    String nombre;
    String value;

    static constraints = {
        nombre{blank:false}
        value{blank: false}
    }
}
