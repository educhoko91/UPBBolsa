<%--
  Created by IntelliJ IDEA.
  User: eliendojr
  Date: 7/19/14
  Time: 1:04 AM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="upbolsa"/>

    <title></title>
</head>

<body>

<r:script>
    $("#fechaInicio").datepicker({ dateFormat: "dd/mm/yy" });


</r:script>


<g:form controller="configSis" action="save" class="cssform">
    <h2>Configuracion Sistema</h2>
    <p>
        <label>Fecha Inicio</label>
        <g:textField name="${parametros[0].nombre}" value="${parametros[0].value.toString()}" class="text"></g:textField>
        <label>Hora Inicio</label>
        <g:textField name="${parametros[1].nombre}" value="${parametros[1].value}" class="text"></g:textField>
        <label>Intervalo de Tiempo (Segundos)</label>
        <g:textField name="${parametros[2].nombre}" value="${parametros[2].value}" class="text"></g:textField>
        <label>Punto Inicial</label>
        <g:textField name="${parametros[3].nombre}" value="${parametros[3].value}" class="text"></g:textField>
    </p>

    <h2>Configuracion de Usuarios</h2>
    <p>
        <label>Capital por Habilitacion</label>
        <g:textField name="${parametros[4].nombre}" value="${parametros[4].value}" class="text"></g:textField>
        <label>Numero de Habilitaciones</label>
        <g:textField name="${parametros[5].nombre}" value="${parametros[5].value}" class="text"></g:textField>
        <label>Costo Trasferencia ($)</label>
        <g:textField name="${parametros[6].nombre}" value="${parametros[6].value}" class="text"></g:textField>
        <label>Costo Por Habilitacion (Bs.)</label>
        <g:textField name="${parametros[7].nombre}" value="${parametros[7].value}" class="text"></g:textField>
    </p>
    <input type="submit" value="Guardar" class="button">
</g:form>
</body>
</html>