package edu.upb.upbBolsa

class Serie {

    static belongsTo = [company:Company]
    static hasMany = [datos:DatoSerie]

    static constraints = {
    }
}
