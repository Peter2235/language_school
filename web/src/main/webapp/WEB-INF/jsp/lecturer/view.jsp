<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Lecturer Detail">
    <jsp:attribute name="body">

        <table class="table">
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
                <tr>
                    <td>${lecturer.id}</td>
                    <td>${lecturer.userName}</td>
                    <td>${lecturer.firstName}</td>
                    <td>${lecturer.middleName}</td>
                    <td>${lecturer.lastName}</td>
                    <td>${lecturer.language}</td>
                    <td>${lecturer.nativeSpeaker}</td>
                </tr>
            </tbody>
        </table>
        <table class="table">
            <thead>
                <tr>
                    <th>id</th>
                    <th>Time</th>
                    <th>Topic</th>
                    <th>Course</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <c:forEach items="${lectures}" var="lecture">
                        <td>${lecture.id}</td>
                        <td>${lecture.time}</td>
                        <td>${lecture.topic}</td>
                        <td>${lecture.course.name}</td>
                    </c:forEach>
                </tr>
            </tbody>
        </table>
        <my:a href="/lecturer/list" class="btn btn-primary">List of Lecturers</my:a>

    </jsp:attribute>
</my:pagetemplate>