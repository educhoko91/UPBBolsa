package edu.upb.upbBolsa

import Registration.User

class Broker {
    int userslimit
    User user
    static constraints = {
        user unique: true, nullable: false
    }
}
