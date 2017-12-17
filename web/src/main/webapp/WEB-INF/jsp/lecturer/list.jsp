<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Lecturers">
<jsp:attribute name="body">

    <my:a href="/persons/new" class="btn btn-primary">
        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
        New Lecturer
    </my:a>
    <my:a href="http://localhost:8080/pa165/" class="btn btn-primary">
        <span class="glyphicon glyphicon" aria-hidden="true"></span>
        Return to home page
    </my:a>
    <table class="table">
        <caption>Lecturers</caption>
        <thead>
            <tr>
                <th>id</th>
                <th>Username</th>
                <th>First name</th>
                <th>Middle name</th>
                <th>Last name</th>
                <th>Native speaker</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${lecturers}" var="lecturer">
                <tr>
                    <td>${lecturer.id}</td>
                    <td>${lecturer.userName}</td>
                    <td>${lecturer.firstName}</td>
                    <td>${lecturer.middleName}</td>
                    <td>${lecturer.lastName}</td>
                    <td>${lecturer.nativeSpeaker}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    
</jsp:attribute>
</my:pagetemplate>
