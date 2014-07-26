package edu.upb.upbBolsa

class Serie {

    String name;
    static belongsTo = [company:Company]
    static hasMany = [datos:DatoSerie]

    static constraints = {
    }
}
