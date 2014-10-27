
<%@ page import="edu.upb.upbBolsa.Broker" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="upbolsa">
    <g:set var="entityName" value="${message(code: 'cajero.label', default: 'Cajero')}" />
    <title><g:message code="default.list.label" args="[entityName]" /></title>
</head>
<body>
<div class="nav" role="navigation">
    <ul >
        <h3><li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        </h3>
        <h3><li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
        </h3>
    </ul>
</div>
<div id="list-cajero" class="content scaffold-list" role="main">
    <h1><g:message code="default.list.label" args="[entityName]" /></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>


    <table class="gridtable">
        <thead>
        <tr>

            <th><g:message code="cajero.userusername.label" default="User" /></th>
            <th ><g:message code="cajero.userfullname.label" default="Fullname" /></th>



        </tr>
        </thead>
        <tbody>
        <g:each in="${cajeroInstanceList}" status="i" var="cajeroInstance">
            <tr>
                <td ><g:link action="show" id="${cajeroInstance?.id}">${fieldValue(bean: cajeroInstance, field: "user.username")}</g:link></td>
                <td >${fieldValue(bean: cajeroInstance, field: "user.fullname")}</td>
               </tr>
        </g:each>
        </tbody>
    </table>


    <div class="pagination">
        <g:paginate total="${cajeroInstanceTotal}" />
    </div>
</div>
</body>
</html>
