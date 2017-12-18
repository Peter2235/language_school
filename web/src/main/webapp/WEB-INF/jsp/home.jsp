<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate>
<jsp:attribute name="body">

    <div class="jumbotron">
        <h1>Welcome to Language School !</h1>
        <p class="lead">This is our language school where you can join to our courses. </p>
        <p><a class="btn btn-lg btn-success" href="${pageContext.request.contextPath}/course/list"
              role="button">Show Courses</a></p>
        <p><a class="btn btn-lg btn-success" href="${pageContext.request.contextPath}/lecture/list"
              role="button">Show Lectures</a></p>
        <p><a class="btn btn-lg btn-success" href="${pageContext.request.contextPath}/lecturer/list"
              role="button">Show Lecturers</a></p>
        <p><a class="btn btn-lg btn-success" href="${pageContext.request.contextPath}/person/list"
              role="button">Show Students</a></p>
    </div>


<%--    <div class="row">
        <c:forEach begin="1" end="12" var="i">
        <div class="col-xs-12 col-sm-6 col-md-2 col-lg-1">
            <p><button class="btn btn-default">Button ${i}</button></p>
        </div>
        </c:forEach>
    </div>--%>

</jsp:attribute>
</my:pagetemplate>