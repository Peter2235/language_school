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
                <div id="navbar" class="collapse navbar-collapse">
                    <ul class="nav navbar-nav">
                        <c:if test="${not empty authenticatedUser}">
                            <li><my:a href="/user/show">Users</my:a></li>
                            <c:if test="${not empty authenticatedUser && authenticatedUser.userType == 'ADMIN'}">
                                <li><my:a href="/sport/list">Sports</my:a></li>
                            </c:if>
                        </c:if>
                    </ul>
                    <c:choose>
                        <c:when test="${empty authenticatedUser}">
                            <form class="navbar-form pull-right" action="${pageContext.request.contextPath}/login/trylogin" method ="post">
                                <input class="span2" type="text" placeholder="Login name" name="logname">
                                <input class="span2" type="password" placeholder="Password" name="password">
                                <button type="submit" class="btn">Sign in</button>
                            </form>
                        </c:when>
                        <c:otherwise>
                            <form class="navbar-text pull-right" method="post" action="${pageContext.request.contextPath}/login/logout">
                                Logged in as <c:out value="${authenticatedUser.firstName} ${authenticatedUser.lastName}"/>
                                &nbsp;&nbsp;
                                <button type="submit" class="btn">Logout</button>
                            </form>
                        </c:otherwise>
                    </c:choose>

                </div><!--/.nav-collapse -->

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

