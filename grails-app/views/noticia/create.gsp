<%--
  Created by IntelliJ IDEA.
  User: Alvaro
  Date: 7/19/2014
  Time: 1:19 AM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Noticia Creation</title>
    <meta name="layout" content="upbolsa"/>
</head>

<body>
<div id="create-category" class="content scaffold-create" role="main">
    <h1>Crear Noticia</h1>
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

    <g:form action="save" method="POST" enctype="multipart/form-data" class="form">
        <p>
            <g:render template="form"/>
        </p>
        <g:submitButton name="create" class="button" value="${message(code: 'default.button.create.label', default: 'Create')}" />
    </g:form>

    <p>
        <table>
            <thead>
            <tr>

                <g:sortableColumn property="titulo" title="Titulo"/>


                <td></td>

            </tr>
            </thead>
            <tbody>
                <g:each in="${noticias}" status="i" var="noticia">
                    <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                        <td>
                            <g:link action="edit" id="${noticia.id}">${fieldValue(bean: noticia, field: "titulo")}</g:link>
                        </td>

                        <td>
                            <g:link action="delete" id="${noticia.id}"><button type="button">${message(code: 'delete.label', default: 'Delete')}</button></g:link>
                        </td>

                    </tr>
                </g:each>
            </tbody>
        </table>
    </p>
    <g:link controller="simulation" action="start">Inicial Simulacion</g:link>
</div>
</body>
</html>