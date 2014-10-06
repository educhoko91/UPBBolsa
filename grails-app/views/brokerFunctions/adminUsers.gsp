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