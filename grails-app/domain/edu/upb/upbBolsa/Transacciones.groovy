package edu.upb.upbBolsa

import Registration.User

class Transacciones {
    int id
    User usuario
    Broker broker
    Company empresa
    int montounitario
    int montototal
    int periodo
    String tipo
    int cantidadacciones
    static constraints = {
        usuario(nullable: false, blank: false)

    }
}
