<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Lectures">
<jsp:attribute name="body">
    
    <table class="table">
        <caption>Lectures</caption>
        <thead>
            <tr>
                <th>id</th>
                <th>time</th>
                <th>course</th>
                <th>topic</th>
                <th>lecturer</th>
                <th>students</th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${lectures}" var="lecture">
                <tr>
                    <td>${lecture.id}</td>
                    <td></td>
                    <td><c:out value="${lecture.course.name}"/></td>
                    <td>${lecture.topic}</td>
                    <td><c:out value="${lecture.lecturer.firstName} ${lecture.lecturer.lastName}"/></td>
                    <td></td>
                    <td>
                        <my:a href="/lecture/view/${lecture.id}" class="btn btn-primary">View</my:a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <button class="btn btn-primary" type="submit">Create Lecture</button>
    
</jsp:attribute>
</my:pagetemplate>