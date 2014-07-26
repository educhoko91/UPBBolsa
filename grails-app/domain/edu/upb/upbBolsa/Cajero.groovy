package edu.upb.upbBolsa

import Registration.User

class Cajero {

    User user
    static constraints = {
        user unique: true, nullable: false, blank: false
    }
}
