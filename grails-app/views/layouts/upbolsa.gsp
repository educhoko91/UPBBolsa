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
    <script type="text/javascript" src="${resource(dir:'js/highstock', file: 'highstock.js')}"></script>
    <g:layoutHead/>
    <r:layoutResources />

</head>

<body>
<div id="head">
    <div class="wrap">
        <h1>UPB Bolsa</h1>
        <h2>inspired by <a href="">LOS SISTEMATICOS</a></h2>
        <div id="nav">
            <ul>
                <li><a href="#">home</a></li>
                <li><a href="#">about</a></li>
                <li><a href="#">blog</a></li>
                <li><a href="#">contact</a></li>
                <sec:ifNotLoggedIn>
                    <li><a href="http://localhost:8080/UPBBolsa/login">Log-in</a></li>
                </sec:ifNotLoggedIn>
                <sec:ifLoggedIn>
                    <li><a href="http://localhost:8080/UPBBolsa/logout">Log-out</a></li>
                </sec:ifLoggedIn>
            </ul>
        </div>
    </div>
</div>

<div class="wrap">
    <div id="right">
        <h1>Menu Basico</h1>
        <ul>
            <li><a href="">Seriast</a></li>
            <li><a href="">Noticias</a></li>
            <li><g:link controller="transacciones" action="venta">Venta</g:link></li>
            <li><g:link controller="transacciones" action="compra">Compra</g:link> </li>
            <li><g:link controller="transacciones" action="index">Ver</g:link></li>
            <br/>
        </ul>
    <sec:ifAllGranted roles="ROLE_ADMIN">
        <h1>Menu Admin</h1>
        <ul>
            <li><a href="">Config Sistema</a></li>
            <li> <g:link controller="company" action="create"> Crear Empresa</g:link> </li>
            <li><a href="">Crear Noticia</a></li>
            <li><a href="">Config brocker</a></li>
            <li><a href="">Config Sistema</a></li>
            <li> <g:link controller="cajero" action="index">Habilitar Usuario</g:link> </li>
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