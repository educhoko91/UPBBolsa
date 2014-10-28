<%@ page import="org.apache.tomcat.jni.User; edu.upb.upbBolsa.Cajero" %>
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

<div class="fieldcontain ${hasErrors(bean: cajeroInstance, field: 'user', 'error')} required">
    <label for="user">
        <g:message code="broker.user.label" default="Usuario" />
        <span class="required-indicator">*</span>
    </label>
    <g:field id="user" name="user.id" from="${Registration.User.list()}" optionKey="id" required="" value="${cajeroInstance?.user?.id}" class="many-to-one" type="text"/>
</div>

</body>
</html>