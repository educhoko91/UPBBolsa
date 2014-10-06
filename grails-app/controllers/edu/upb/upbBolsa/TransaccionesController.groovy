package edu.upb.upbBolsa

import Registration.User
import upbbolsa.SyncEngineService

class TransaccionesController {
    private double precioGlobal
    def springSecurityService
    def index() {
        render view: 'transacciones'

    }

    def compra(){
        User user = springSecurityService.currentUser
        [user:user]

    }

    def venta(){
        User user = springSecurityService.currentUser
        int numeroSerie = SyncEngineService.ciclo
        [user: user, precio: 0]
    }
    def actualizarValores(String nombre){
        Company company = Company.findByName(nombre)
        int ciclo = SyncEngineService.ciclo
        BigInteger cicloSerie = new BigInteger(ciclo+"")
        Serie serieEmpresa = company.serie
        DatoSerie datoSerie = DatoSerie.findByPeriodAndSerie(cicloSerie, serieEmpresa)
        BigDecimal precio = datoSerie.price
        precioGlobal = precio.toDouble()

        render(contentType: 'text/json') {
            [
                'precio'  : precio,
            ]
        }

    }

    def ventaAccion(){
        def transaccion = new Transacciones()
        Company empresa = Company.findByName(params.empresa)
        User usuario = springSecurityService.currentUser
        int numeroAcciones = 0
        for(Transacciones trans : Transacciones.findAllByUsuario(usuario)){
            numeroAcciones += trans.cantidadacciones
        }
//        if(Integer.parseInt(params.cantidad) > numeroAcciones){
//            print "No tiene suficientes acciones"
//            flash.message = "No tiene suficientes acciones"
//            return
//        }
        print "pase el if"
        transaccion.usuario = usuario
        transaccion.cantidadacciones = Integer.parseInt(params.cantidad)
        transaccion.montounitario = precioGlobal
        transaccion.montototal = precioGlobal * Double.parseDouble(params.cantidad)
        transaccion.periodo = SyncEngineService.ciclo as int
        transaccion.tipo = "venta"
        transaccion.empresa = empresa
        transaccion.broker = null
        if(!transaccion.save()){
            render "Han ocurrido errores"
        }

//        render(view: 'venta',  user: usuario, precio: 0)
    }
}
