package edu.upb.upbBolsa

class TransaccionesController {

    def index() {
        render view: 'transacciones'
    }

    def compra(){
        render view: 'compra'
    }

    def venta(){
        render view: 'venta'
    }
}
