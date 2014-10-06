package edu.upb.upbBolsa

class DatoSerie {

    static belongsTo = [serie:Serie]
    BigDecimal price;
    BigInteger period;
    String time;

    static constraints = {
    }

}
