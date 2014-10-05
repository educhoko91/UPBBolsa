<%--
  Created by IntelliJ IDEA.
  User: Omar
  Date: 05/10/2014
  Time: 17:42
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Venta</title>
    <meta name="layout" content="upbolsa">
</head>

<body>
    <div class="info-venta">
        Dinero en mi cuenta: ${user.capital}
    </div>
    <div class="data-venta">
        <span>Empresa:</span> <g:select name="empresa" from="${edu.upb.upbBolsa.Company.findAll().name}">Empresa</g:select>
        <span>Cantidad:</span> <input type="text" name="cantidad">
    </div>
    <div class="data-simulacion">
        <span>Precio de la accion: </span>
        <span>Precio total: 200</span>
    </div>
    <div class="buttons-venta">
        <g:link controller="transacciones" action="venta"><button type="button">Vender</button></g:link>
    </div>
</body>
</html>