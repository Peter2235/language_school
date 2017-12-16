<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="New course">
    <jsp:attribute name="body">

        <form:form method="post" action="${pageContext.request.contextPath}/course/create"
                   modelAttribute="courseCreate" cssClass="form-horizontal">

            <div class="form-group ${language_error?'has-error':''}">
                <form:label path="name" cssClass="col-sm-2 control-label">Name</form:label>
                    <div class="col-sm-10">
                    <form:input path="name" cssClass="form-control"/>
                    <form:errors path="name" cssClass="help-block"/>
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
                    <p class="help-block"><form:errors path="language" cssClass="error"/></p>
                </div>
            </div>

            <div class="form-group">
                <form:label path="proficiencyLevel" cssClass="col-sm-2 control-label">Proficiency Level</form:label>
                    <div class="col-sm-10">
                    <form:select path="proficiencyLevel" cssClass="form-control">
                        <c:forEach items="${proficiencyLevels}" var="c">
                            <form:option value="${c}">${c}</form:option>
                        </c:forEach>
                    </form:select>
                    <p class="help-block"><form:errors path="proficiencyLevel" cssClass="error"/></p>
                </div>
            </div>

            <button class="btn btn-primary" type="submit">Create Course</button>
        </form:form>

    </jsp:attribute>
</my:pagetemplate>