<%--
  Created by IntelliJ IDEA.
  User: Dani
  Date: 7/26/2014
  Time: 7:58 AM
--%>


<%@ page import="edu.upb.upbBolsa.VariablesSistema" contentType="text/html;charset=UTF-8" %>
<%@ page import="edu.upb.upbBolsa.Habilitaciones" contentType="text/html;charset=UTF-8" %>

<%--<%@ page import="edu.upb.upbBolsa.User" contentType="text/html;charset=UTF-8" %>    --%>

<html>
<head>
    <title>Habilitaciones</title>
    <meta name="layout" content="upbolsa">
    <link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'transacciones.css')}" media="screen" title="style (screen)" />
        <g:javascript>
        $(document).ready(function() {
            $('.inputUserCajero').autocomplete({
               // console.log('este es el JSON', source);
                source: '<g:createLink controller='cajero' action='ajaxUsers'/>'
            });
        });
    </g:javascript>



</head>

<body>
<h1 style="text-align: center">Funciones Cajero</h1>
<br/>
<h2>Habilitar Usuario</h2>
<g:form action="save" class="cssform">
    <g:if test="${flash.message}">
        <div style="color: red" class="message" role="status">${flash.message}</div>
    </g:if>

    <span class="data-venta">Username:</span><g:select name="username" class="data-venta" from="${Registration.User.findAll().username}"></g:select>
    %{--Correo: <g:field type="text" name="correo"/><br>--}%


    <div class="data-venta">
        Costo de habilitacion real: <em><span class="data-simulacion">${VariablesSistema.findByNombre("costoHabilitacion").getValue()} Bs.</span> </em><br />
        Monto de habilitacion ficticio: <em><span class="data-simulacion">${VariablesSistema.findByNombre("capInicio").getValue()} $</span></em>  <br />
    </div>
    <br/>
    <g:submitButton name="submit" value="Habilitar" />
</g:form>
<br/><br/>
    <h2>Resumen de Caja</h2>
    <div class="data-venta">
        Cantidad de habilitaciones realizadas en la simulacion:  <%
        try{ %> <span class="data-simulacion">${
        edu.upb.upbBolsa.Habilitaciones.findByCajero(params.cajeroname).count()
    }</span>
        <%} catch(Exception e){%>
        0 <%}
        %>    <br />

      Plata total real:

      <%
        try{ %> <span class="data-simulacion"> ${Double.parseDouble((VariablesSistema.findByNombre("costoHabilitacion").getValue()))*  edu.upb.upbBolsa.Habilitaciones.findByCajero(params.cajeroname).count()

     }</span>
        <%} catch(Exception e){%>
        0 <%}
        %>
    </div>





</body>
</html>