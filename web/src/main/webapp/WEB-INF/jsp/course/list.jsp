<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Courses">
<jsp:attribute name="body">

    <my:a href="/course/new" class="btn btn-primary">
            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
            New Course
    </my:a>
    <table class="table">
        <caption>Courses</caption>
        <thead>
            <tr>
                <th>Id</th>
                <th>Name</th>
                <th>Language</th>
                <th>Proficiency level</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${courses}" var="course">
                <tr>
                    <td>${course.id}</td>
                    <td>${course.name}</td>
                    <td><c:out value="${course.language}"/></td>
                    <td><c:out value="${course.proficiencyLevel}"/></td>
                    <td>
                        <my:a href="/course/view/${course.id}" class="btn btn-primary">View</my:a>
                    </td>
                    <td>
                        <form method="post" action="${pageContext.request.contextPath}/course/delete/${course.id}">
                            <button type="submit" class="btn btn-primary">Delete</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</jsp:attribute>
</my:pagetemplate>