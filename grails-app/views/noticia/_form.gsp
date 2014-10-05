<%@ page import="edu.upb.upbBolsa.Noticia" %>

<table width="100%" valign="top">
    <tr>
        <td width="40%"  class="cssform" valign="top">
            <div>
                <label>Empresas</label>
            </div>
            <table >
                <g:each in="${edu.upb.upbBolsa.Company.list()}" var="company">
                    <tr>
                        <td><g:checkBox name="companies" value="${company.id}" id="${company.id}"/></td>
                        <td><label for="${company.id}">${company.name}</label></td>
                    </tr>
                </g:each>
            </table>

        </td>
        <td width="10%"></td>
        <td width="50%"  class="cssform">
            <div>
                Titulo:<br />
                <g:textField name="titulo" value="${noticiaInstance.titulo}" /><br />
                Periodo:<br />
                <g:textField name="periodo" value="${noticiaInstance.periodo}" /><br /><br />
                <g:textArea name="descripcion" value="${noticiaInstance.descripcion}" rows="10" cols="10"/>
                <g:if test="${noticiaInstance}">
                    <g:hiddenField name="id" value="${noticiaInstance.id}"></g:hiddenField>
                </g:if>
            </div>
        </td>
    </tr>
</table>