package edu.upb.upbBolsa

import Registration.User

class Broker {
    int userslimit
    static hasMany = [users:User]
    User user
    static constraints = {
        user unique: true, nullable: false
    }
}
