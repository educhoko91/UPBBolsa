package edu.upb.upbBolsa

import Registration.User

class Acciones {

    int cantidad_ac
    Company company_ac
    User user
    static belongsTo = User
    static constraints = {
        cantidad_ac()
        company_ac()
    }
}
