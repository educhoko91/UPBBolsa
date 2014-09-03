<%@ page import="org.apache.tomcat.jni.User; edu.upb.upbBolsa.Broker" %>
<html>
<head>
    <meta name="layout" content="upbolsa">
    <g:javascript library="jquery"/>
    <g:javascript library="jquery-ui"/>
    <r:layoutResources/>
    <script type="text/javascript" >
    jQuery(function() {
        var data = "${Registration.User.executeQuery("select username from User")}".substring(1,"${Registration.User.executeQuery("select username from User")}".length-1).split(", ");
        jQuery("#user").autocomplete({
            source: data
        });
    });
    </script>
</head>

<body>

<div class="fieldcontain ${hasErrors(bean: brokerInstance, field: 'user', 'error')} required">
	<label for="user">
		<g:message code="broker.user.label" default="User" />
		<span class="required-indicator">*</span>
	</label>
	<g:field id="user" name="user.id" from="${Registration.User.list()}" optionKey="id" required="" value="${brokerInstance?.user?.id}" class="many-to-one" type="text"/>
</div>

<div class="fieldcontain ${hasErrors(bean: brokerInstance, field: 'userslimit', 'error')} required">
	<label for="userslimit">
		<g:message code="broker.userslimit.label" default="Userslimit" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="userslimit" type="number" value="${brokerInstance.userslimit}" required=""/>
</div>

</body>
</html>