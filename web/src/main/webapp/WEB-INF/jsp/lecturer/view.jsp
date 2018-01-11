<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="${lecturer.firstName} ${lecturer.lastName} Detail">
    <jsp:attribute name="body">

        <table class="table table-bordered">
            <tbody>
            <tr>
                <th class="col-md-4">id</th>
                <td>${lecturer.id}</td>
            </tr>
            <tr>
                <th>Username</th>
                <td>${lecturer.userName}</td>
            </tr>
            <tr>
                <th>First name</th>
                <td>${lecturer.firstName}</td>
            </tr>
            <tr>
                <th>Middle name</th>
                <td>${lecturer.middleName}</td>
            </tr>
            <tr>
                <th>Last name</th>
                <td>${lecturer.lastName}</td>
            </tr>
            <tr>
                <th>Language</th>
                <td>${lecturer.language}</td>
            </tr>
            <tr>
                <th>Native speaker</th>
                <td>${lecturer.nativeSpeaker}</td>
            </tr>
            </tbody>
        </table>
        <div style="margin:10px 0">
            <my:a href="/lecturer/edit/${lecturer.id}"
                  class="btn btn-primary">Edit ${lecturer.firstName} ${lecturer.lastName} Information</my:a>
        </div>
            </br>
            <h2>Lecturers Lectures</h2>
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
        <my:a href="/lecturer/list" class="btn btn-primary">List of Lecturers</my:a>


    </jsp:attribute>
</my:pagetemplate>