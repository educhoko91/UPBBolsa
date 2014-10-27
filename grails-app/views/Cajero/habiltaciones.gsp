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

<g:form action="save" class="cssform">
    <g:if test="${flash.message}">
        <div style="color: red" class="message" role="status">${flash.message}</div>
    </g:if>
    <h1>Resumen de Caja</h1>
    <span>Username:</span><g:select name="username" from="${Registration.User.findAll().username}"></g:select>
    %{--Correo: <g:field type="text" name="correo"/><br>--}%
    <g:submitButton name="submit" value="Habilitar" />

    <div>

        Costo de habilitacion real: <em><span>${VariablesSistema.findByNombre("costoHabilitacion").getValue()} Bs.</span> </em><br />
        Monto de habilitacion ficticio: <em><span>${VariablesSistema.findByNombre("capInicio").getValue()} $</span></em>  <br />

        Cantidad de habilitaciones realizadas en la simulacion:  <%
        try{ %> <span>${
        edu.upb.upbBolsa.Habilitaciones.findByCajero(params.cajeroname).count()
    }</span>
        <%} catch(Exception e){%>
        0 <%}
        %>    <br />

      Plata total real:

      <%
        try{ %> <span> ${Double.parseDouble((VariablesSistema.findByNombre("costoHabilitacion").getValue()))*  edu.upb.upbBolsa.Habilitaciones.findByCajero(params.cajeroname).count()

     }</span>
        <%} catch(Exception e){%>
        0 <%}
        %>

    </div>


</g:form>

</body>
</html>