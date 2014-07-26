<%@ page import="edu.upb.upbBolsa.Broker" %>



<div class="fieldcontain ${hasErrors(bean: brokerInstance, field: 'user', 'error')} required">
	<label for="user">
		<g:message code="broker.user.label" default="User" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="user" name="user.id" from="${Registration.User.list()}" optionValue="username" optionKey="id" required="" value="${brokerInstance?.user?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: brokerInstance, field: 'userslimit', 'error')} required">
	<label for="userslimit">
		<g:message code="broker.userslimit.label" default="Userslimit" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="userslimit" type="number" value="${brokerInstance.userslimit}" required=""/>
</div>

