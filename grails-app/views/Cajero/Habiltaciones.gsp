<%--
  Created by IntelliJ IDEA.
  User: Dani
  Date: 7/26/2014
  Time: 7:58 AM
--%>

<%@ page import="edu.upb.upbBolsa.VariablesSistema" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Habilitaciones</title>
    <meta name="layout" content="upbolsa">
</head>

<body>

<g:form action="save">
    Nombre y Apellido: <g:field type="text" name="nombre"/><br>
    Correo: <g:field type="text" name="correo"/><br>
    <g:submitButton name="submit" value="Habilitar" />

    <div>
        Monto de habilitacion real: <em><span>${VariablesSistema.findByNombre("costoHabilitacion").getValue()}</span> </em><br />
        Monto de habilitacion ficticio: <em><span>${VariablesSistema.findByNombre("capInicio").getValue()}</span></em>
    </div>

</g:form>

</body>
</html>