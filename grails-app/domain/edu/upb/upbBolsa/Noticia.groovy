package edu.upb.upbBolsa

class Noticia {
    int periodo
    String titulo
    String descripcion
    static constraints = {
        descripcion(nullable: false,blank: false)
        periodo(nullable: false,blank: false)
        titulo(nullable: false, blank: false)
    }

    @Override
    public String toString(){
        String ans="Titulo: "+this.titulo+"\n"+"Descripcion: "+this.descripcion+"\n"+"companies: \n";

        return ans;
    }


}
