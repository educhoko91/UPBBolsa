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
        Transacciones transaccion = new Transacciones()
        Company empresa = Company.findByName(params.empresa)
        User usuario = springSecurityService.currentUser
        transaccion.usuario = usuario
        transaccion.cantidadacciones = Integer.parseInt(params.cantidad)
        transaccion.montounitario = precioGlobal
        transaccion.montototal = precioGlobal * Integer.parseInt(params.cantidad)
        transaccion.periodo = SyncEngineService.ciclo
        transaccion.tipo = "Venta"
        transaccion.empresa = empresa
        transaccion.broker = null
        transaccion.save()
//        render(view: 'venta',  user: usuario, precio: 0)
    }
}
