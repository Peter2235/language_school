<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Lectures">
    <jsp:attribute name="body">

        <my:a href="/lecture/new" class="btn btn-primary">
            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
            New Lecture
        </my:a>
        
        <table class="table">
            <caption>Lectures</caption>
            <thead>
                <tr>
                    <th>id</th>
                    <th>time</th>
                    <th>course</th>
                    <th>topic</th>
                    <th>lecturer</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${lectures}" var="lecture">
                    <tr>
                        <td>${lecture.id}</td>
                        <td>${localDateTimeFormat.format(lecture.time)}</td>
            <td><c:out value="${lecture.course.name}"/></td>
            <td>${lecture.topic}</td>
            <td><c:out value="${lecture.lecturer.firstName} ${lecture.lecturer.lastName}"/></td>
            <td></td>
            <td>
                <my:a href="/lecture/view/${lecture.id}" class="btn btn-primary">
                    View
                </my:a>
            </td>
            <td>
                <form method="post" action="${pageContext.request.contextPath}/lecture/delete/${lecture.id}">
                    <button type="submit" class="btn btn-primary">Delete</button>
                </form>
            </td>

        </tr>
    </c:forEach>
</tbody>
</table>
            <h2>Filter lectures by time range</h2>
            <form:form method="post" action="${pageContext.request.contextPath}/lecture/timerange"
                   modelAttribute="lecturesTime" cssClass="form-horizontal">
            <div class="form-group">
                <form:label path="startTimeString" cssClass="col-sm-2 control-label">From</form:label>
                    <div class="col-sm-10">
                    <form:input type="datetime-local" path="startTimeString" cssClass="form-control" />
                    <form:errors path="startTimeString" cssClass="help-block"/>
                </div>
            </div>
            <div class="form-group">
                <form:label path="endTimeString" cssClass="col-sm-2 control-label">To</form:label>
                    <div class="col-sm-10">
                    <form:input type="datetime-local" path="endTimeString" cssClass="form-control" />
                    <form:errors path="endTimeString" cssClass="help-block"/>
                </div>
            </div>
            <button class="btn btn-primary" type="submit">Filter Lectures</button>
        </form:form>
            <my:a href="/lecture/list" class="btn btn-primary">List all Lectures</my:a>

</jsp:attribute>
</my:pagetemplate>