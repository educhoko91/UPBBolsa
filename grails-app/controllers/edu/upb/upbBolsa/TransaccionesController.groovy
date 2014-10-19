package edu.upb.upbBolsa

import Registration.User
import groovy.sql.Sql
import upbbolsa.SyncEngineService

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
        //sacamos solo las empresas de las cuales tiene acciones
        Map empresaCantidad = new HashMap<String,Integer>()
        Company [] companies = Company.findAll();
        Transacciones [] trans = Transacciones.findAllByUsuario(user);
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
        for (Map.Entry<String, Integer> entry : empresaCantidad.entrySet())
        {
            if(entry.getValue() <= 0){
                empresaCantidad.remove(entry.getKey())
            }
        }

        [user: user, precio: 0, empresas:empresaCantidad]
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
        } else if(Integer.parseInt(params.cantidad) <= 0){
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
}
