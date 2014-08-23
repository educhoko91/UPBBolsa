<%--
  Created by IntelliJ IDEA.
  User: Omar
  Date: 13/07/2014
  Time: 11:30 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>UPBBolsa</title>
    <link rel="stylesheet" type="text/css" href="${resource(dir: 'css/theme', file: 'style.css')}" media="screen" title="style (screen)" />

    <g:javascript library="jquery-ui" />

    <g:layoutHead/>
    <r:layoutResources />
    <script type="text/javascript" src="${resource(dir:'js/highstock', file: 'highstock.js')}"></script>
</head>

<body>
<div id="head">
    <div class="wrap">
        <h1>out of the box</h1>
        <h2>inspired by <a href="http://zenhabits.net">LOS SISTEMATICOS</a></h2>
        <div id="nav">
            <ul>
                <li><a href="#">home</a></li>
                <li><a href="#">about</a></li>
                <li><a href="#">blog</a></li>
                <li><a href="#">contact</a></li>
                <li><a href="#">about</a></li>
                <li><a href="#">blog</a></li>
                <li><a href="#">contact</a></li>
                <li><a href="http://localhost:8080/UPBBolsa/login">Log-in</a></li>
                <li><a href="http://localhost:8080/UPBBolsa/logout">Log-out</a></li>
            </ul>
        </div>
    </div>
</div>

<div class="wrap">
    <div id="right">
        <h1>Basic Menu</h1>
        <ul>
            <li><a href="">Seriast</a></li>
            <li><a href="">Noticias</a></li>
            <li><a href="">Vender</a></li>
            <li><a href="">Comprar</a></li>
            <li><a href="">Ver</a></li>
            <br/>
        </ul>
    <sec:ifAllGranted roles="ROLE_ADMIN">
        <h1>Menu Admin</h1>
        <ul>
            <li><a href="">Config Sistema</a></li>
            <li><a href="">Crear Empresa</a></li>
            <li><a href="">Crear Noticia</a></li>
            <li><a href="">Config brocker</a></li>
            <li><a href="">Config Sistema</a></li>
            <li><a href="">Habilitar Usuario</a></li>
            <li><a href="">Asignar Cajero</a></li>
            <li><a href="">Caja</a></li>
            <li><a href="">Reiniciar Sistema</a></li>
        </ul>
    </sec:ifAllGranted>
    </div>
    <div id="content">
        <g:layoutBody/>
    </div>
    <br class="clear" />
</div>
<div id="footer"><div class="wrap">

    <div class="clearer">&nbsp;</div><br /><br />
    <p class="foot">
        Template by MOROCHO

    </p>
</div></div>
</body>
<r:layoutResources />
</html>