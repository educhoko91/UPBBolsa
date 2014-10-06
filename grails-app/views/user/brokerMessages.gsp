<%--
  Created by IntelliJ IDEA.
  User: Nestor
  Date: 9/17/2014
  Time: 4:33 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="upbolsa">
<g:javascript library="jquery"/>
<g:javascript library="jquery-ui"/>
<r:layoutResources/>
<script type="text/javascript" >
    jQuery(function() {
        var data = "${Registration.UserRole.executeQuery("select user.username from UserRole where role.id = 4")}".substring(1,"${Registration.UserRole.executeQuery("select user.username from UserRole where role.id = 4")}".length-1).split(", ");
        jQuery("#search_broker").autocomplete({
            source: data
        });
    });
</script>
</head>
<body>
<div>
    <g:if test='${flash.message}'>
        <div>${flash.message}</div>
    </g:if>
    <table>
        <tr>
            <td>
                <p>
                    <h2>Mandar solicitud al Broker</h2>
                    <g:form class="cssform" url="[controller: 'user', action: 'sendBrokerMessages']" >
                        <label for="search_broker">
                            <g:message default="Buscar Broker" />
                        </label>
                        <g:field id="search_broker" name="search_broker" type="text"/>
                        <g:submitButton class="button" name="submitButton" value="Mandar Solicitud"/>
                    </g:form>
                </p>
            </td>
        </tr>
        <tr>
            <td>
                <p>
                    <h3>Mensaje del Broker</h3>
                    <h5>${params.params_message_sent}</h5>
                </p>
            </td>
        </tr>
    </table>
</div>
</body>
</html>