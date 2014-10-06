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
        function valuecompany(){
            var valcompany = $(".empresa").val();
            console.log("valcompany",valcompany);
            $(".ejemplo")[0].innerHTML+=valcompany;
            $.getJSON("${createLink(controller: 'Transacciones', action: 'listDetail')}",{'company':valcompany}, function(data) {
            console.log("precioser",data)
            $(".precioaccion")[0].innerHTML+=data.datosserie;
                    });
        }
    </g:javascript>
</head>

<body>
<h1>COMPRA DE ACCIONES</h1>
<g:form action="comprar" class="cssform">
    <div class="info-compra">
                <span>Su usuario: ${user.username}</span><br/>
                <span>Su capital: ${user.capital}</span> <br/>
                <span>Seleccionar una empresa: </span><g:select class="empresa" name="empresas" from="${edu.upb.upbBolsa.Company.findAll().name}" onchange="valuecompany()"></g:select> <br/>
                <span class="ejemplo">Empresa seleccionada:  </span>
    </div>
    <div class="info-empresa">
        <span class="precioaccion">Precio por acción: ${params.datosserie}</span> <br/>
        <span>Costo de transferencia: ${edu.upb.upbBolsa.VariablesSistema.findByNombre('costoTransfer').value}</span>
        %{--<span>Su usuario: ${user.username}</span>--}%
        %{--<span>Su usuario: ${user.username}</span>--}%
        %{--<span>Su usuario: ${user.username}</span>--}%

    </div>
</g:form>


</body>
</html>