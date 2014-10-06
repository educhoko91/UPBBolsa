
<%@ page import="edu.upb.upbBolsa.Broker" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="upbolsa">
		<g:set var="entityName" value="${message(code: 'broker.label', default: 'Broker')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-broker" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list broker">

                <g:if test="${brokerInstance?.user?.username}">
                    <li class="fieldcontain">
                        <span id="username-label" class="property-label"><g:message code="broker.user.username.label" default="Usuario: " /></span>

                        <span class="property-value" aria-labelledby="username-label"><g:fieldValue bean="${brokerInstance}" field="user.username"/></span>

                    </li>
                </g:if>

                <g:if test="${brokerInstance?.user?.fullname}">
                    <li class="fieldcontain">
                        <span id="userfullname-label" class="property-label"><g:message code="broker.user.fullname.label" default="Nombre: " /></span>

                        <span class="property-value" aria-labelledby="userfullname-label"><g:fieldValue bean="${brokerInstance}" field="user.fullname"/></span>

                    </li>
                </g:if>

				<g:if test="${brokerInstance?.userslimit}">
				<li class="fieldcontain">
					<span id="userslimit-label" class="property-label"><g:message code="broker.userslimit.label" default="Limite de Usuarios: " /></span>
					
						<span class="property-value" aria-labelledby="userslimit-label"><g:fieldValue bean="${brokerInstance}" field="userslimit"/></span>
					
				</li>
				</g:if>
                <g:if test="${brokerInstance?.users}">
                    <li class="fieldcontain">
                        <span id="users-label" class="property-label"><g:message code="broker.userslimit.label" default="Usuarios: " /></span>

                        <span class="property-value" aria-labelledby="users-label"><g:fieldValue bean="${brokerInstance}" field="users.username"/></span>
                    </li>
                </g:if>
			
			</ol>
		</div>
	</body>
</html>
