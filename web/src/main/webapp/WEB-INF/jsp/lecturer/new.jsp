<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="New lecturer">
    <jsp:attribute name="body">

        <form:form method="post" action="${pageContext.request.contextPath}/lecturer/create"
                   modelAttribute="lecturerCreate" cssClass="form-horizontal">
            <div class="form-group ${userName_error?'has-error':''}">
                <form:label path="userName" cssClass="col-sm-2 control-label">Username</form:label>
                    <div class="col-sm-10">
                    <form:input path="userName" cssClass="form-control"/>
                    <form:errors path="userName" cssClass="help-block"/>
                </div>
            </div>
            <div class="form-group ${firstName_error?'has-error':''}">
                <form:label path="firstName" cssClass="col-sm-2 control-label">First name</form:label>
                    <div class="col-sm-10">
                    <form:input path="firstName" cssClass="form-control"/>
                    <form:errors path="firstName" cssClass="help-block"/>
                </div>
            </div>
            <div class="form-group ${middleName_error?'has-error':''}">
                <form:label path="middleName" cssClass="col-sm-2 control-label">Middle name</form:label>
                    <div class="col-sm-10">
                    <form:input path="middleName" cssClass="form-control"/>
                    <form:errors path="middleName" cssClass="help-block"/>
                </div>
            </div>
            <div class="form-group ${lastName_error?'has-error':''}">
                <form:label path="lastName" cssClass="col-sm-2 control-label">Last name</form:label>
                    <div class="col-sm-10">
                    <form:input path="lastName" cssClass="form-control"/>
                    <form:errors path="lastName" cssClass="help-block"/>
                </div>
            </div>
            <div class="form-group">
                <form:label path="language" cssClass="col-sm-2 control-label">Language</form:label>
                    <div class="col-sm-10">
                    <form:select path="language" cssClass="form-control">
                        <c:forEach items="${languages}" var="c">
                            <form:option value="${c}">${c}</form:option>
                        </c:forEach>
                    </form:select>
                    <form:errors path="language" cssClass="error"/>
                </div>
            </div>
            <div class="form-group ${nativeSpeaker_error?'has-error':''}">
                <form:label path="nativeSpeaker" cssClass="col-sm-2 control-label">Native Speaker</form:label>
                    <div class="col-sm-10">
                    <form:checkbox path="nativeSpeaker" cssClass="form-control"/>
                    <form:errors path="nativeSpeaker" cssClass="help-block"/>
                </div>
            </div>
            <button class="btn btn-primary" type="submit">Create Lecturer</button>
        </form:form>

    </jsp:attribute>
</my:pagetemplate>