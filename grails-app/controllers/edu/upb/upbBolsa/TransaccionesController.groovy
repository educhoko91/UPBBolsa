package edu.upb.upbBolsa

import Registration.User
import groovy.sql.Sql
import upbbolsa.SyncEngineService
import java.text.DecimalFormat

import javax.validation.constraints.Null

class TransaccionesController {
    private static double precioGlobal
    def springSecurityService
    def dataSource
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
         User user = springSecurityService.currentUser;
         def trans = new Transacciones();
        // trans.id = id;
         trans.usuario = user;
         trans.broker = null;
         trans.empresa = Company.findByName(params.empresas);
         trans.montounitario = precioGlobal;
         trans.montototal = precioGlobal*Double.parseDouble(params.cantidadAcciones);
         trans.periodo=SyncEngineService.ciclo as int;
         trans.tipo = "compra";
         trans.cantidadacciones = params.cantidadAcciones as int;
        user.capital -= trans.montototal;
         if(!trans.save() && !user.save()){

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

        Company empresa = Company.findByName(params.empresa)
        User usuario = springSecurityService.currentUser
        int numeroAcciones = 0
        if(params.empresa == "null"){
            flash.message = "Seleccione una empresa"
            redirect(controller: 'transacciones', action: 'venta')
            return
        } else if(Integer.parseInt(params.cantidad) < 0){
            flash.message = "Debe vender mas de una accion"
            redirect(controller: 'transacciones', action: 'venta')
            return
        }
        for(Transacciones trans : Transacciones.findAllByUsuario(usuario)){
            numeroAcciones += trans.cantidadacciones
        }
        if(Integer.parseInt(params.cantidad) > numeroAcciones){
            print "No tiene suficientes acciones"
            flash.message = "No tiene suficientes acciones"
            redirect(controller: 'transacciones', action: 'venta')
            return
        }
        def trans = new Transacciones();
        // trans.id = id;
        trans.usuario = springSecurityService.currentUser;
        trans.broker = null;
        trans.empresa = Company.findByName(params.empresa);
        trans.montounitario = precioGlobal;
        trans.montototal = precioGlobal*Double.parseDouble(params.cantidad);
        trans.periodo=SyncEngineService.ciclo as int;
        trans.tipo = "venta";
        trans.cantidadacciones = params.cantidad as int;
        usuario.capital += trans.montototal;
        if(!trans.save() && !usuario.save()){
            render "NO SE PUDO REALIZAR LA Venta"
        }else{
            render "venta exitosa"
        }
    }

    def resumen(){
        User user = springSecurityService.currentUser;
        Company [] companies = Company.findAll();
        Transacciones [] trans = Transacciones.findAllByUsuario(user);

        Map empresaCantidad = new HashMap<String,Integer>()
        for (Company c : companies ){
            empresaCantidad.put(c.name,0)
        }


        for(Transacciones t : trans ){
            String compName = t.empresa.name
            if(t.tipo.equals("venta")) {
                empresaCantidad.put(compName, empresaCantidad.get(compName) - t.cantidadacciones)
            }else if(t.tipo.equals("compra")){
                empresaCantidad.put(compName,empresaCantidad.get(compName) + t.cantidadacciones)
            }

        }
        [transacciones : trans, user: user, companies: empresaCantidad]
    }

    def brokerMove(){
        if(params.select_user != '' && params.select_company != '' && Integer.parseInt(params.quantity_capital) > 0 && params.quantity_capital != null) {
            if (params.select_option == 'Comprar') {
                redirect(controller: 'transacciones', action: 'brokerComprar', params: params)
            } else if (params.select_option == 'Vender') {
                redirect(controller: 'transacciones', action: 'brokerVender', params: params)
            }
        } else {
            redirect(controller: 'brokerFunctions', action: 'adminUsers', params: params)
        }
    }

    def brokerComprar(){
        def trans = new Transacciones();
        boolean needAccion = true;
        User broker = springSecurityService.currentUser;
        def user_selected = User.findByUsername(params.select_user)
        trans.usuario = user_selected
        trans.broker = Broker.findByUser(broker)
        trans.empresa = Company.findByName(params.select_company)
        def company_selected = Company.findByName(params.select_company)
        for(Acciones ac : user_selected.acciones){
            if (ac.company_ac == company_selected){
                needAccion = false
                break;
            }
        }

        if (needAccion == true){
            def nueva_ac = new Acciones()
            nueva_ac.company_ac = company_selected
            nueva_ac.user = user_selected
            nueva_ac.cantidad_ac = (params.quantity_capital as int);
            nueva_ac.save()
        }
        print(user_selected.acciones)

        def serie_selected = Serie.findByCompany(company_selected)
        def db = new Sql(dataSource)
        int val = SyncEngineService.ciclo as int;
        int val2 = serie_selected.id as int;
        def result = db.rows("SELECT price FROM dato_serie WHERE period = ? AND serie_id = ?;", [val,val2])
        if (String.valueOf(result) == null || String.valueOf(result) == "" || String.valueOf(result) == "[]"){
            flash.message = "No se inicio la simulacion"
            redirect(controller: 'brokerFunctions', action: 'adminUsers')
            return
        }
        double trueValueOfPrice = Double.parseDouble(String.valueOf(result).substring(8, String.valueOf(result).length() - 2))

        trans.montounitario = trueValueOfPrice;
        DecimalFormat df = new DecimalFormat("0.00");
        trans.montototal = df.format(trueValueOfPrice*Double.parseDouble(params.quantity_capital)) as double;
        trans.periodo=SyncEngineService.ciclo as int;
        trans.tipo = "compra";
        trans.cantidadacciones = params.quantity_capital as int;
        def user = User.findByUsername(params.select_user)

        def result2 = db.rows("SELECT value FROM variables_sistema WHERE nombre = 'costoTransfer';")
        double costTrans = Double.parseDouble(String.valueOf(result2).substring(8, String.valueOf(result2).length() - 2))

        if(user.capital < trans.montototal + costTrans){
            flash.message = "No posee suficiente Capital"
            redirect(controller: 'brokerFunctions', action: 'adminUsers')
            return
        }
        user.capital = user.capital - trans.montototal - costTrans

        for(Acciones ac : user_selected.acciones){
            if (ac.company_ac == company_selected){
                ac.cantidad_ac = ac.cantidad_ac + (params.quantity_capital as int)
                break;
            }
        }
        if(!trans.save() && !user.save()){
            flash.message = "Compra Fallida"
            redirect(controller: 'brokerFunctions', action: 'adminUsers')
        }else{
            flash.message = "Compra Exitosa"
            redirect(controller: 'brokerFunctions', action: 'adminUsers')
        }
    }





    def brokerVender(){
        def trans = new Transacciones();
        User broker = springSecurityService.currentUser;
        def user_selected = User.findByUsername(params.select_user)
        trans.usuario = user_selected
        trans.broker = Broker.findByUser(broker)
        trans.empresa = Company.findByName(params.select_company)

        def company_selected = Company.findByName(params.select_company)
        def serie_selected = Serie.findByCompany(company_selected)
        def db = new Sql(dataSource)
        int val = SyncEngineService.ciclo as int;
        int val2 = serie_selected.id as int;
        def result = db.rows("SELECT price FROM dato_serie WHERE period = ? AND serie_id = ?;", [val,val2])
        if (String.valueOf(result) == null || String.valueOf(result) == "" || String.valueOf(result) == "[]") {
            flash.message = "No se inicio la simulacion"
            redirect(controller: 'brokerFunctions', action: 'adminUsers')
            return
        }
        double trueValueOfPrice = Double.parseDouble(String.valueOf(result).substring(8, String.valueOf(result).length() - 2))
        trans.montounitario = trueValueOfPrice;
        DecimalFormat df = new DecimalFormat("0.00");
        trans.montototal = df.format(trueValueOfPrice*Double.parseDouble(params.quantity_capital)) as double;
        trans.periodo=SyncEngineService.ciclo as int;
        trans.tipo = "venta";
        trans.cantidadacciones = params.quantity_capital as int;
        def user = User.findByUsername(params.select_user)

        def result2 = db.rows("SELECT value FROM variables_sistema WHERE nombre = 'costoTransfer';")
        double costTrans = Double.parseDouble(String.valueOf(result2).substring(8, String.valueOf(result2).length() - 2))

        if(user.capital < trans.cantidadacciones + costTrans){
            flash.message = "No posee suficiente Capital"
            redirect(controller: 'brokerFunctions', action: 'adminUsers')
            return
        }

        for(Acciones ac : user_selected.acciones){
            if (ac.company_ac == company_selected){
                if (ac.cantidad_ac >= (params.quantity_capital as int)) {
                    ac.cantidad_ac = ac.cantidad_ac - (params.quantity_capital as int)
                    break;
                }
                else{
                    flash.message = "No posee suficiente Acciones de esta empresa"
                    redirect(controller: 'brokerFunctions', action: 'adminUsers')
                    return
                }
            }
        }

        user.capital = user.capital + trans.montototal - costTrans
        if(!trans.save() && !user.save()){
            render "Venta Fallida"
            redirect(controller: 'brokerFunctions', action: 'adminUsers')
        }else{
            render "Venta Exitosa"
            redirect(controller: 'brokerFunctions', action: 'adminUsers')

        }

    }
}
