<%--
  Created by IntelliJ IDEA.
  User: Nestor
  Date: 10/5/2014
  Time: 7:50 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="upbolsa">
    <title>Administrar Usuarios</title>
</head>
<body>
<div>
    <g:if test='${flash.message}'>
        <div>${flash.message}</div>
    </g:if>
    <p>
    <h2>Usuarios</h2>
    <g:form class="cssform" url="[controller: 'transacciones', action: 'brokerMove']" >
        <p>
            <label for="select_user" class="text">Usuario</label>
            <g:select id="select_user" name="select_user" from="${params.broker_users_available}" noSelection="['':'-Escoge un Usuario-']"/>
            <label for="select_option" class="text">Transaccion</label>
            <g:select id="select_option" name="select_option" from="${['Comprar','Vender']}"/>
            <label for="select_company" class="text">Empresa</label>
            <g:select id="select_company" name="select_company" from="${params.companies_availables}" noSelection="['':'-Escoge una empresa-']"/>
            <label for="quantity_capital" class="text">Acciones</label>
            <g:field type="number" name="quantity_capital" id="quantity_capital" required="" value="0"/>
            </br></br>
            <g:submitButton class="button" name="submitButton" value="Realizar Transaccion"/>
        </p>
    </g:form>
    </p>
    <table class="gridtable">
        <tr>
            <td>
                <table class="gridtable">
                    <g:each in="${params.broker_all_users}">
                        <tr>
                            <td>
                                <h5>Usuario: ${it.username}</h5></td><td><h5>Capital: <g:formatNumber number="${it.capital}" format="\$#.##"/></h5>
                            </td>
                                <g:each in="${it.acciones}">
                                    <td>
                                        <h5>Acciones ${it.company_ac.name} : ${it.cantidad_ac} </h5>
                                    </td>
                                </g:each>
                        </tr>
                    </g:each>
                </table>
            </td>
            <td>
            </td>
        </tr>
    </table>
    <p>
    <h2>Mandar mensaje a Usuarios</h2>
    <g:form class="cssform" url="[controller: 'brokerFunctions', action: 'messageToUsers']" >
        <label for="send_message">
            <g:message default="Mensaje" />
        </label>
        <g:textArea name="send_message" id="send_message" rows="5" cols="40"/>
        <g:submitButton class="button" name="submitButton" value="Mandar Mensaje"/>
    </g:form>
    </p>
</div>
</body>
</html>