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

    </h2>
    <h1>${user.fullname}</h1>
    <table>
        <TR><td>CANTIDAD DE ACCIONES</td><td>EMPRESA</td>    </TR>
        <g:each in="${transacciones}" var="trans">
            <tr>
                <td>${trans.cantidadacciones} </td><td> ${trans.empresa.name}</td>
            </tr>
        </g:each>
    </table>

</body>
</html>