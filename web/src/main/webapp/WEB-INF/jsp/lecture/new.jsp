<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="New lecture">
    <jsp:attribute name="body">

        <form:form method="post" action="${pageContext.request.contextPath}/lecture/create"
                   modelAttribute="lectureCreate" cssClass="form-horizontal">

            <div class="form-group">
                <form:label path="course.id" cssClass="col-sm-2 control-label">Course</form:label>
                    <div class="col-sm-10">
                    <form:select path="course.id" cssClass="form-control">
                        <c:forEach items="${courses}" var="c">
                            <form:option value="${c.id}">${c.name}</form:option>
                        </c:forEach>
                    </form:select>
                    <p class="help-block"><form:errors path="course.id" cssClass="error"/></p>
                </div>
            </div>   

            <div class="form-group">
                <form:label path="lecturer.id" cssClass="col-sm-2 control-label">Lecturer</form:label>
                    <div class="col-sm-10">
                    <form:select path="lecturer.id" cssClass="form-control">
                        <c:forEach items="${lecturers}" var="c">
                            <form:option value="${c.id}">${c.lastName}</form:option>
                        </c:forEach>
                    </form:select>
                    <p class="help-block"><form:errors path="lecturer.id" cssClass="error"/></p>
                </div>
            </div>

            <div class="form-group ${topic_error?'has-error':''}">
                <form:label path="topic" cssClass="col-sm-2 control-label">Topic</form:label>
                    <div class="col-sm-10">
                    <form:input path="topic" cssClass="form-control"/>
                    <form:errors path="topic" cssClass="help-block"/>
                </div>
            </div>

            <button class="btn btn-primary" type="submit">Create Lecture</button>
        </form:form>

    </jsp:attribute>
</my:pagetemplate>