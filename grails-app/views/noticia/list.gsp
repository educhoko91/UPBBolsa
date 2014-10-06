<%--
  Created by IntelliJ IDEA.
  User: users1234
  Date: 10/6/2014
  Time: 6:15 AM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Lista de Noticias</title>
    <meta name="layout" content="upbolsa"/>
</head>

<body>
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
                <g:link action="show" id="${noticia.id}">${fieldValue(bean: noticia, field: "titulo")}</g:link>
            </td>

        </tr>
    </g:each>
    </tbody>
</table>
</p>
</body>
</html>