<%--
  Created by IntelliJ IDEA.
  User: Omar
  Date: 05/10/2014
  Time: 17:42
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Compra</title>
    <meta name="layout" content="upbolsa">

</head>

<body>
    <div class="info-compra">
                <span>User: ${user.username}</span>
                <span>Capital:</span>
                <span>Empresa: </span><g:select name="empresas" from="${edu.upb.upbBolsa.Company.findAll().name}"></g:select>
    </div>
</body>
</html>