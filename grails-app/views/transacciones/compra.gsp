<%--
  Created by IntelliJ IDEA.
  User: Omar
  Date: 05/10/2014
  Time: 17:42
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Compra</title>
    <meta name="layout" content="upbolsa">
    <g:javascript >
        var precioaccom
        function valuecompany(){
            var valcompany = $(".empresa").val();
            console.log("valcompany",valcompany);
            $(".empresasel").html(valcompany);
            $.getJSON("${createLink(controller: 'transacciones', action: 'listDetail')}",{'company':valcompany}, function(data) {
            precioaccom = data.precio;
            $(".precioaccion").html(precioaccom);

                    });

        }

        function calccosto(){
            var cantidad = $(".cantidad").val();
            var capitaluser = $(".capital").val();
            console.log("pre",precioaccom);
            var costtrans = $(".costoTransfer").val();
            var total = (cantidad*precioaccom)+costtrans;
            var saldo = capitaluser-total;
            console.log("costo",total);
            console.log("capital",capitaluser);
            if(parseInt(capitaluser)<parseInt(total)){
                $(".mostrarsaldo").html("SALDO INSUFICIENTE");
                 $(".subbutton").hide();
            }else{
                $(".mostrarsaldo").html(total);
                $(".subbutton").show();
            }
            $(".saldo").html(saldo);


        }
    </g:javascript>
</head>

<body>
<h1>COMPRA DE ACCIONES</h1>
<g:form action="comprar" class="cssform">
    <div class="info-compra">
                <span>Su usuario: ${user.username}</span><br/>
                <span>Su capital: </span><output class="capital">${user.capital}</output> <br/>
                <span>Seleccionar una empresa: </span><g:select class="empresa" name="empresas" from="${edu.upb.upbBolsa.Company.findAll().name}" onclick="valuecompany()"></g:select> <br/>
                <span>Empresa seleccionada:  </span><span class="empresasel"></span><br/>

    </div>
    <div class="info-empresa">
        <g:hiddenField name="precioAccion" class="precioaccion"></g:hiddenField>
        <g:hiddenField name="costoTransfer" value="${edu.upb.upbBolsa.VariablesSistema.findByNombre('costoTransfer').value}" ></g:hiddenField>
        <span>Precio por acción:</span><output class="precioaccion"></output> <br/>
        <span >Costo de transferencia: </span><output class="costoTransfer">${edu.upb.upbBolsa.VariablesSistema.findByNombre('costoTransfer').value}</output> <br/>
        <span>Numero de acciones que desea comprar: </span><input class="cantidad" type="number" name="cantidadAcciones" onchange="calccosto()">   <br/>
        <span>Costo total:</span><span class="mostrarsaldo"></span> <br/>
        <span>Saldo: </span><output class="saldo"></output>        <br/>
            <g:submitButton class="subbutton" name="comprar">COMPRAR</g:submitButton>


    </div>
</g:form>


</body>
</html>