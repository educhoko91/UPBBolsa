<%--
  Created by IntelliJ IDEA.
  User: Dani
  Date: 7/26/2014
  Time: 7:58 AM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Habilitaciones</title>
    <meta name="layout" content="upbolsa">
</head>

<body>

<g:form action="muestra">
    Nombre y Apellido: <g:field type="text" name="nombre"/><br>
    Correo: <g:field type="text" name="correo"/><br>
    <g:submitButton name="submit" value="Enviar datos"/>

    <div>
        Monto de habilitacion real: <span>number</span> <%--  ${parametros[4].nombre} --%>
        Monto de habilitacion ficticio: <span>monto</span>      <%--  ${parametros[7].nombre} --%>
    </div>

</g:form>

</body>
</html>