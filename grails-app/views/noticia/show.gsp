<%--
  Created by IntelliJ IDEA.
  User: users1234
  Date: 10/6/2014
  Time: 6:18 AM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Mostrar Noticia</title>
    <meta name="layout" content="upbolsa"/>
</head>

<body>
<div>
    <span id="title" style="font-size: 32; text-align: center">${noticiaInstance.titulo}</span>
    <br />
    <br />

    <span id="contenido" style="font-size: 20; text-align: left">${noticiaInstance.descripcion}</span>
</div>

</body>
</html>