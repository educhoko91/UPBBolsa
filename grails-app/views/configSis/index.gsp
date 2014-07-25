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

<g:each in="${parametros}">
    ${it.nombre}->${it.value},
</g:each>

</body>
</html>