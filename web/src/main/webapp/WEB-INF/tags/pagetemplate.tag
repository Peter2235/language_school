<%@ tag pageEncoding="utf-8" dynamic-attributes="dynattrs" trimDirectiveWhitespaces="true" %>
<%@ attribute name="title" required="false" %>
<%@ attribute name="head" fragment="true" %>
<%@ attribute name="body" fragment="true" required="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="${pageContext.request.locale}">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title><c:out value="${title}"/></title>
        <!-- bootstrap loaded from content delivery network -->
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico" type="image/x-icon">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" crossorigin="anonymous">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css"  crossorigin="anonymous">
        <jsp:invoke fragment="head"/>
    </head>
    <body>
        <!-- navigation bar -->
        <nav class="navbar navbar-inverse navbar-static-top">
            <div class="container">
                <a class="navbar-brand" href="${pageContext.request.contextPath}">Home</a>

                <c:if test="${sessionScope.homepage != null}">
                    <form class="navbar-text pull-right" method="get" action="${pageContext.request.contextPath}/auth">
                        <c:if test="${sessionScope.admin == null && sessionScope.person == null}">
                            <button type="submit" class="btn">Login</button>
                        </c:if>
                    </form>
                    <form class="navbar-text pull-right" method="get" action="${pageContext.request.contextPath}/auth/logout">
                        <c:if test="${sessionScope.admin != null || sessionScope.person != null}">
                            Logged in as <c:out value="${person.userName}${admin.userName}"/>
                            &nbsp;&nbsp;

                            <button type="submit" class="btn">Logout</button>
                        </c:if>
                    </form>
                </c:if>
            </div>
        </nav>

        <div class="container">

            <!-- page title -->
            <c:if test="${not empty title}">
                <div class="page-header">
                    <h1><c:out value="${title}"/></h1>
                </div>
            </c:if>

            <!-- page body -->
            <jsp:invoke fragment="body"/>

            <!-- footer -->
            <footer class="footer">
                <p>&copy;&nbsp;<%=java.time.Year.now().toString()%>&nbsp;Masaryk University</p>
            </footer>
        </div>
    </body>

</html>

