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
        [user: user]
    }
}
