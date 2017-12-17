<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%--
    @author Viktor Slany
 --%>

<my:pagetemplate title="Students">
<jsp:attribute name="body">

    <my:a href="/person/new" class="btn btn-primary">
        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
        New Student
    </my:a>
    <my:a href="http://localhost:8080/pa165/" class="btn btn-primary">
        <span class="glyphicon glyphicon" aria-hidden="true"></span>
        Return to home page
    </my:a>
    <table class="table">
        <caption>Persons</caption>
        <thead>
        <tr>
            <th>id</th>
            <th>Username</th>
            <th>First name</th>
            <th>Middle name</th>
            <th>Last name</th>
            <th>Role</th>
        </tr>
        </thead>

        <tbody>
        <c:forEach items="${persons}" var="person">
                <tr>
                    <td>${person.id}</td>
                    <td>${person.userName}</td>
                    <td></td>
                    <td></td>
                    <td>${person.lastName}</td>
                    <td></td>
                    <td>
                        <form method="post" action="${pageContext.request.contextPath}/person/delete/${person.id}">
                            <button type="submit" class="btn btn-primary">Delete</button>
                        </form>
                    </td>
                </tr>

            </c:forEach>
        </tbody>
    </table>



</jsp:attribute>
</my:pagetemplate>