<%--
  Created by IntelliJ IDEA.
  User: users1234
  Date: 10/5/2014
  Time: 5:08 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Editar Noticia</title>
    <meta name="layout" content="upbolsa"/>
</head>

<body>
    <h1>Editar Noticia</h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>

    <g:hasErrors bean="${noticiaInstance}">
        <ul class="errors" role="alert">
            <g:eachError bean="${noticiaInstance}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
            </g:eachError>
        </ul>
    </g:hasErrors>

    <g:form action="update" method="POST" enctype="multipart/form-data" class="form">
        <p>
            <g:render template="form"/>
        </p>
        <g:submitButton name="Guardar" class="button" value="Guardar" />
    </g:form>

</body>
</html>