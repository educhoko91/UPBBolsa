package edu.upb.upbBolsa

class Company {

    String name;
    String code;
    static hasOne = [serie:Serie];

    static constraints = {
        name(blank: false,maxSize: 50, nullable:false)
    }
}
