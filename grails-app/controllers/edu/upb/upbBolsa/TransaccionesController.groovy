package edu.upb.upbBolsa

import Registration.User
import upbbolsa.SyncEngineService

class TransaccionesController {

    static allowedMethods = [comprar: "POST", listDetail: "GET"]

    def springSecurityService
    def index() {
        render view: 'transacciones'

    }

    def compra(){
        User user = springSecurityService.currentUser
        [user:user]

    }

    def listDetail(String company){
        Company companyInstance = Company.findByName(company);
        print(companyInstance)
        Serie serie = Serie.findByCompany(companyInstance)
        DatoSerie datoserieInstance = DatoSerie.findByPeriodAndSerie(new BigInteger(SyncEngineService.getCiclo()+""),serie);
        print(datoserieInstance)
        [datosserie:datoserieInstance]
    }

    def comprar() {
        def transaccionInstance = new Transacciones(params)

        if (!transaccionInstance.save(flush: true)) {
            render(view: "compra", model: [transaccionInstance: transaccionInstance])
            return
        } else {


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


        render(contentType: 'text/json') {
            [
                'precio'  : precio,
            ]
        }

    }
}
