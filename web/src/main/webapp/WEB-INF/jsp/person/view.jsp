<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Perosn Administration">
<jsp:attribute name="body">

<table class="table">
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
    <tr>
        <td>${person.id}</td>
        <td>${person.userName}</td>
        <td></td>
        <td></td>
        <td>${person.lastName}</td>
        <td></td>
    </tr>
    </tbody>
</table>

</jsp:attribute>
</my:pagetemplate>