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
            <br />
            <g:submitButton name="create" class="button" value="${message(code: 'default.button.create.label', default: 'Create')}" />
            <br />
        </p>
        <p>
            <table class="gridtable">
                <thead>
                <tr>

                    <g:sortableColumn property="name" title="${message(code: 'company.label', default: 'Name')}"/>

                    <g:sortableColumn property="serie" title="${message(code: 'serie.label', default: 'Serie')}"/>

                    <td></td>

                </tr>
                </thead>
                <tbody>
                <g:each in="${companies}" status="i" var="company">
                    <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">



                        <td>${fieldValue(bean: company, field: "name")}</td>

                        <td>${fieldValue(bean: company, field: "code")}</td>

                        <td>

                            <g:link action="delete" id="${company.id}"><button type="button">${message(code: 'delete.label', default: 'Delete')}</button></g:link>
                        </td>

                    </tr>
                </g:each>
                </tbody>
            </table>

        </p>

    </g:form>

    <g:link name="Continuar" controller="noticia" action="create">Continuar</g:link>
</div>
</body>
</html>