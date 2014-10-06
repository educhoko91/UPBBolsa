package edu.upb.upbBolsa

import Registration.User
import upbbolsa.SyncEngineService

import javax.validation.constraints.Null

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

    def listDetail(String company) {
        Company companyInstance = Company.findByName(company);
        print(companyInstance)
        Serie serie = Serie.findByCompany(companyInstance)

            DatoSerie datoserieInstance = DatoSerie.findByPeriodAndSerie(new BigInteger(SyncEngineService.getCiclo() + ""), serie);
            if(!(datoserieInstance instanceof Null)){
                print(datoserieInstance)
                BigDecimal precio = datoserieInstance.price;

                render(contentType: 'text/json'){
                    [
                            'precio' : precio,
                    ]

                }
            } else{
                redirect(controller: "company", action: "create", params: params)
            }



 }



    def comprar() {
         Transacciones trans = new Transacciones();
         trans.cantidadacciones = params.cantidadAcciones;
         trans.empresa = params.empresas;
         trans.
        }

    def venta(){
        User user = springSecurityService.currentUser
        int numeroSerie = SyncEngineService.ciclo

        [user: user, precio: 0]
    }
    def actualizarValores(String nombre){
        print("Valor company")
        print(nombre)
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
