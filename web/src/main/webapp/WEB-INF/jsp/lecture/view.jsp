<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>

<my:pagetemplate title="Lecture Administration">
    <jsp:attribute name="body">
     <my:a href="http://localhost:8080/pa165/lecture/list" class="btn btn-primary">
            <span class="glyphicon glyphicon" aria-hidden="true"></span>
            Back
        </my:a>

        <table class="table table-bordered">
            <tbody>
                <tr>
                    <th class="col-md-4">id</th>
                    <td>${lecture.id}</td>
                </tr>
                <tr>
                    <th>topic</th>
                    <td>${lecture.topic}</td>
                </tr>
                <tr>
                    <th>time</th>
                    <td>${localDateTimeFormat.format(lecture.time)}</td>
                </tr>
                <tr>
                    <th>lecturer</th>
                    <td><c:out value="${lecture.lecturer.firstName} ${lecture.lecturer.lastName}"/></td>
                </tr>
            </tbody>
        </table>
        <form method="post" action="${pageContext.request.contextPath}/lecture/assign/${lecture.id}">
            <button type="submit" class="btn btn-primary">Assign to this lecture</button>
        </form>
        <form method="post" action="${pageContext.request.contextPath}/lecture/unassign/${lecture.id}">
            <button type="submit" class="btn">Leave lecture</button>
        </form>
        <h2>Lectures Students</h2>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>id</th>
                    <th>Username</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${lecture.persons}" var="person">
                    <tr>

                        <td>${person.id}</td>
                        <td>${person.userName}</td>
                        <td>${person.firstName}</td>
                        <td>${person.lastName}</td>

                    </tr>
                </c:forEach>
            </tbody>
        </table>

    </jsp:attribute>
</my:pagetemplate>