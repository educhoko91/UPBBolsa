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
    <link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'transacciones.css')}" media="screen" title="style (screen)" />

    <g:javascript library="jquery-ui" />

    <g:layoutHead/>
    <r:layoutResources />
    <script type="text/javascript" src="${resource(dir:'js/highstock', file: 'highstock.js')}"></script>

</head>

<body>
<div id="head">
    <div class="wrap">
        <h1>UPB Bolsa</h1>
        <img  src="${resource(dir: 'images', file: 'logoupb.png')}" style="position: absolute; right: 100px; background: #ffffff; top: 50px" />
        <h2>inspired by <a href="">LOS SISTEMATICOS</a></h2>
    </div>
</div>

<div class="wrap">
    <div id="right">
        <sec:ifLoggedIn>

            <h1>Menu Basico</h1>
            <ul>
                <li><g:link controller="series" action="index" title="Aqui podra visualizar todas las empresas">Serie</g:link></li>
                <li><g:link controller="noticia" action="list" title="Aqui podra ver todas las noticias referentes a las empresas">Noticias</g:link></li>
                <li><g:link controller="transacciones" action="venta" title="Aqui podra vender sus acciones">Venta</g:link></li>
                <li><g:link controller="transacciones" action="compra" title="Aqui podra comprar acciones de la empresa que desea">Compra</g:link> </li>
                <li><g:link controller="transacciones" action="resumen" title="Aqui podrá visualizar el resumen de su cuenta">Resumen</g:link></li>
                <li><g:link controller="user" action="brokerMessages" title="Aqui podra soliciar un Broker">Solicitar Broker</g:link></li>
                <sec:ifNotLoggedIn>
                    <li><g:link controller="login" action="auth">Iniciar Sesion</g:link> </li>
                </sec:ifNotLoggedIn>
                <sec:ifLoggedIn>
                    <li><g:link controller="logout" action="index">Cerrar Sesion</g:link></li>
                </sec:ifLoggedIn>
                <br/>
            </ul>
            
        </sec:ifLoggedIn>
    <sec:ifAllGranted roles="ROLE_ADMIN">
        <h1>Menu Admin</h1>
        <ul>
            <li><g:link controller="configSis" action="index" title="Aqui podra configurar su sistema para la simulación">Config Sistema</g:link> </li>
            <li><g:link controller="company" action="create" title="Aqui podra crear empresas para la simulación"> Crear Empresa</g:link> </li>
            <li><g:link controller="noticia" action="create" title="Aqui podra crear noticias para las empresas">Crear Noticia</g:link></li>
            <li><g:link controller="Broker" action="list" title="Aqui podra crear brokers">Config brocker</g:link></li>
            <li><g:link controller="cajero" action="index" title="Aqui podra habilitar dinero ficticio para los jugadores">Habilitar Usuario</g:link> </li>
            <li><g:link controller="cajero" action="list" title="Aqui podra crear cajeros">Asignar Cajero</g:link></li>
            <li><a href="">Caja</a></li>
            <g:if test="${upbbolsa.SyncEngineService.isRunning()}">
                <li> <g:link controller="simulation" action="stop">Parar Simulacion</g:link> </li>
            </g:if>
        </ul>
    </sec:ifAllGranted>
        <sec:ifAllGranted roles="ROLE_BROK">
            <h1>Menu Broker</h1>
            <ul>
                <li><g:link controller="brokerFunctions" action="adminUsers">Administrar Usuarios</g:link> </li>
            </ul>
        </sec:ifAllGranted>
    <sec:ifAllGranted roles="ROLE_CAJA">
        <li> <g:link controller="cajero" action="index">Habilitar Usuario</g:link> </li>
        <sec:ifNotLoggedIn>
            <li><g:link controller="login" action="auth">Iniciar Sesion</g:link> </li>
        </sec:ifNotLoggedIn>
        <sec:ifLoggedIn>
            <li><g:link controller="logout" action="index">Cerrar Sesion</g:link></li>
        </sec:ifLoggedIn>
    </sec:ifAllGranted>
    </div>
    <div id="content">
        <g:layoutBody/>
    </div>
    <br class="clear" />
</div>
<div id="footer">

    <div class="clearer">&nbsp;</div><br /><br />
    <p class="foot">
        Powered by LOS SISTEMATICOS:
            Alvaro Bilbao,
            Omar Cespedes,
            Nestor Chavarria,
            Miguel Ferrufino,
            Eduardo Liendo,
            Daniel Sagarnaga.

    </p>
</div>
</body>
<r:layoutResources />
</html>