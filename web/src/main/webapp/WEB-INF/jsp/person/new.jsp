<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%--
    @author Viktor Slany
 --%>

<my:pagetemplate title="New person">
    <jsp:attribute name="body">

    <my:a href="/persons/list" class="btn btn-primary">
        <span class="glyphicon glyphicon" aria-hidden="true"></span>
        Save
    </my:a>
    <my:a href="/persons/list" class="btn btn-primary">
        <span class="glyphicon glyphicon" aria-hidden="true"></span>
        Cancel
    </my:a>
        <form:form method="post" action="${pageContext.request.contextPath}/person/create"
                   modelAttribute="personCreate" cssClass="form-horizontal">
            <div class="form-group ${name_error?'has-error':''}">
                <div class="col-sm-10">
                    User Name:
                    <form:input path="userName" cssClass="form-control"/>
                    <form:errors path="userName" cssClass="help-block"/>
                    First Name:
                    <form:input path="firstName" cssClass="form-control"/>
                    <form:errors path="firstName" cssClass="help-block"/>
                    Last Name:
                    <form:input path="lastName" cssClass="form-control"/>
                    <form:errors path="lastName" cssClass="help-block"/>
                </div>
            </div>

        </form:form>

    </jsp:attribute>
</my:pagetemplate>