<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Course Administration">
<jsp:attribute name="body">

<table class="table">
        <thead>
            <tr>
                 <th>Id</th>
                 <th>Name</th>
                 <th>Language</th>
                 <th>Proficiency level</th>
                 <th>Lectures</th>
            </tr>
        </thead>
        <tbody>
             <tr>
                <td>${course.id}</td>
                <td>${course.name}</td>
                <td><c:out value="${course.language}"/></td>
                <td><c:out value="${course.proficiencyLevel}"/></td>
                 <td>
                    <c:forEach items="${lectures}" var="lecture">
                        ${lecture.topic}
                    </c:forEach>
                    ,
                </td>
             </tr>
        </tbody>
    </table>
            
</jsp:attribute>
</my:pagetemplate>