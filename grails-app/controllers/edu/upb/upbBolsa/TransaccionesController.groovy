package edu.upb.upbBolsa

import Registration.User
import upbbolsa.SyncEngineService

import javax.validation.constraints.Null

class TransaccionesController {
    private static double precioGlobal
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
                precioGlobal = precio.toDouble();
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

        print(precioGlobal)

        int id = 0;
         def trans = new Transacciones();
        // trans.id = id;
         trans.usuario = springSecurityService.currentUser;
         trans.broker = null;
         trans.empresa = Company.findByName(params.empresas);
         trans.montounitario = precioGlobal;
         trans.montototal = precioGlobal*Double.parseDouble(params.cantidadAcciones);
         trans.periodo=SyncEngineService.ciclo as int;
         trans.tipo = "compra";
         trans.cantidadacciones = params.cantidadAcciones as int;

         if(!trans.save()){
             render "NO SE PUDO REALIZAR LA COMPRA"
         }else{
             render "compra exitosa"
         }

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
