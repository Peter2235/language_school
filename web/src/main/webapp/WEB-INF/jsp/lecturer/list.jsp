<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Lecturers">
    <jsp:attribute name="body">

        <my:a href="/lecturer/new" class="btn btn-primary">
            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
            New Lecturer
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
                    <th>Language</th>
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
                        <td>${lecturer.language}</td>
                        <td>${lecturer.nativeSpeaker}</td>
                        <td>
                            <my:a href="/lecturer/view/${lecturer.id}" class="btn btn-primary">View</my:a>
                            </td>
                            <td>
                                <form method="post" action="${pageContext.request.contextPath}/lecturer/delete/${lecturer.id}">
                                <button type="submit" class="btn btn-primary">Delete</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

    </jsp:attribute>
</my:pagetemplate>
