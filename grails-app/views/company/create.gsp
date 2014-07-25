<%--
  Created by IntelliJ IDEA.
  User: Alvaro
  Date: 7/19/2014
  Time: 1:19 AM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Company Creation</title>
    <meta name="layout" content="upbolsa"/>
</head>

<body>
<div id="create-category" class="content scaffold-create" role="main">
    <h1>Crear Empresas</h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>

    <g:hasErrors bean="${companyInstance}">
        <ul class="errors" role="alert">
            <g:eachError bean="${companyInstance}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
            </g:eachError>
        </ul>
    </g:hasErrors>

    <g:form action="save" method="POST" enctype="multipart/form-data" class="form">
        <p>
            <g:render template="form"/>
        </p>
            <g:submitButton name="create" class="button" value="${message(code: 'default.button.create.label', default: 'Create')}" />

    </g:form>
</div>
</body>
</html>