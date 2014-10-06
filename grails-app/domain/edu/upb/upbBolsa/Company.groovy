package edu.upb.upbBolsa

class Company{

    String name;
    String code;
    static hasOne = [serie:Serie];
    Noticia lastNoticiaDisplayed;
    static hasMany = [noticias:Noticia]

    static constraints = {
        name(blank: false,maxSize: 50, nullable:false, unique: true)
        code(blank: false,maxSize: 50, nullable:false, unique: true)
        lastNoticiaDisplayed(nullable: true)
    }

    public boolean equals(Company c){
        if(this.id ==c.id){
            print "llamo equals true"
            return true;
        }
        else{
            print "llamo equals false"
            return false;
        }
    }
}
