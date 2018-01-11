<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Student Administration">
<jsp:attribute name="body">
    <my:a href="http://localhost:8080/pa165/person/list" class="btn btn-primary">
        <span class="glyphicon glyphicon" aria-hidden="true"></span>
        Back
    </my:a>
        
        <table class="table table-bordered">
            <tbody>
            <tr>
                <th class="col-md-4">id</th>
                <td>${person.id}</td>
            </tr>
            <tr>
                <th>Username</th>
                <td>${person.userName}</td>
            </tr>
            <tr>
                <th>First name</th>
                <td>${person.firstName}</td>
            </tr>
            <tr>
                <th>Middle name</th>
                <td>${person.middleName}</td>
            </tr>
            <tr>
                <th>Last name</th>
                <td>${person.lastName}</td>
            </tr>
            <tr>
                <th>Role</th>
                <td>${person.admin? "Admin" : "User"}</td>
            </tr>
            </tbody>
        </table>
            <h2>Students lectures</h2>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>id</th>
                <th>Time</th>
                <th>Topic</th>
                <th>Course</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${lectures}" var="lecture">
                <tr>
                    <td>${lecture.id}</td>
                    <td>${lecture.time}</td>
                    <td>${lecture.topic}</td>
                    <td>${lecture.course.name}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

</jsp:attribute>
</my:pagetemplate>