<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%--
    @author Viktor Slany
 --%>

<my:pagetemplate title="Persons">
<jsp:attribute name="body">

    <my:a href="/persons/new" class="btn btn-primary">
        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
        Create new person
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
                </tr>
            </c:forEach>
        </tbody>
    </table>



</jsp:attribute>
</my:pagetemplate>