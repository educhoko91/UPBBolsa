<%@ page import="edu.upb.upbBolsa.Company" %>

    <label for="name">
        <g:message code="company.name.label" default="Name"/>
        <span class="required-indicator">*</span>
    </label>
    <g:textField name="name" maxlength="50" required="" value="${companyInstance?.name}" class="text"/>

    <label for="code">
        <g:message code="company.code.label" default="Code"/>
        <span class="required-indicator">*</span>
    </label>
    <g:textField name="code" maxlength="50" required="" value="${companyInstance?.name}" class="text"/>

    <label for="file"><g:message code="company.excelFile.label" default="Excel File" /></label>
    <input type="file" name="file" id="file" />
