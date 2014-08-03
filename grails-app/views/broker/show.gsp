
<%@ page import="edu.upb.upbBolsa.Broker" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="upbolsa">
		<g:set var="entityName" value="${message(code: 'broker.label', default: 'Broker')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-broker" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
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
			
				<g:if test="${brokerInstance?.user}">
				<li class="fieldcontain">
					<span id="user-label" class="property-label"><g:message code="broker.user.label" default="User: " /></span>
					
						<span class="property-value" aria-labelledby="user-label"><g:link controller="user" action="show" id="${brokerInstance?.user?.id}">${brokerInstance?.user?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>

                <g:if test="${brokerInstance?.user?.username}">
                    <li class="fieldcontain">
                        <span id="username-label" class="property-label"><g:message code="broker.user.username.label" default="Email: " /></span>

                        <span class="property-value" aria-labelledby="username-label"><g:link controller="user" action="show" id="${brokerInstance?.user?.id}">${brokerInstance?.user?.username.encodeAsHTML()}</g:link></span>

                    </li>
                </g:if>

                <g:if test="${brokerInstance?.user?.fullname}">
                    <li class="fieldcontain">
                        <span id="userfullname-label" class="property-label"><g:message code="broker.user.fullname.label" default="Fullname: " /></span>

                        <span class="property-value" aria-labelledby="userfullname-label"><g:fieldValue bean="${brokerInstance}" field="user.fullname"/></span>

                    </li>
                </g:if>

				<g:if test="${brokerInstance?.userslimit}">
				<li class="fieldcontain">
					<span id="userslimit-label" class="property-label"><g:message code="broker.userslimit.label" default="Userslimit: " /></span>
					
						<span class="property-value" aria-labelledby="userslimit-label"><g:fieldValue bean="${brokerInstance}" field="userslimit"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${brokerInstance?.id}" />
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
