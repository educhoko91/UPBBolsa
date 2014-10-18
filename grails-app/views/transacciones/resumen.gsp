<%--
  Created by IntelliJ IDEA.
  User: Miguel
  Date: 06/10/2014
  Time: 02:14 AM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>RESUMEN USUARIO</title>
    <meta name="layout" content="upbolsa">
</head>

<body>
    <h1>Resumen </h1>
    <br/><br/>
    <h2>Estado actual acciones</h2>
    <table>
        <tr>
            <td>Empresa</td><td>Acciones</td>
        </tr>
        <g:each in="${companies}" var="comp">
            <tr>
                <td>${comp.key.encodeAsHTML()} </td><td> ${comp.value.encodeAsHTML()}</td>
            </tr>
        </g:each>

    </table>
    <br/><br/>
    <h2>Historial de Transacciones</h2>
    <table>
        <tr><td>Empresa</td><td>Acciones</td><td>Tipo</td></tr>
        <g:each in="${transacciones}" var="trans">
            <tr>
                <td> ${trans.empresa.name}</td><td>${trans.cantidadacciones} </td><td>${trans.tipo}</td>
            </tr>
        </g:each>
    </table>

</body>
</html>