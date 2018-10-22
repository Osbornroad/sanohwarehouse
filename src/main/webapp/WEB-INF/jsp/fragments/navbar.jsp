<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<style>
    /* unvisited link */
    a:link {
        color: black;
    }

    /* mouse over link */
    a:hover {
        color: white;
    }

    /* selected link */
    a:active {
        color: white;
    }
</style>

<nav class="navbar navbar-expand-sm bg-primary navbar-dark">
    <a class="navbar-brand" href="<c:url value="/main"/>">SANOH</a>

            <button type="button" class="navbar-toggler" data-toggle="collapse" data-target="#myNavbar">
                <span class="navbar-toggler-icon"></span>
            </button>
        <div class="collapse navbar-collapse" id="myNavbar">
            <ul class="nav navbar-nav">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
                        Reference
                    </a>
                    <div class="dropdown-menu">
                        <a class="dropdown-item" href="<c:url value="/operations"/>">Operations</a>
                        <a class="dropdown-item" href="<c:url value="/parts"/>">Parts</a>
                        <a class="dropdown-item" href="#">Staff</a>
                        <a class="dropdown-item" href="<c:url value="/admin/users"/>">Users</a>
                    </div>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Link 2</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Link 3</a>
                </li>
            </ul>
            <ul class="nav navbar-nav ml-auto">
                <li>
                    <form:form action="logout" method="post">
                        <sec:authorize access="isAuthenticated()">
                            <c:if test="${pageContext.request.userPrincipal.name != null}">
                                <span class="glyphicon glyphicon-user"></span>
                                    ${pageContext.request.userPrincipal.name}  |  <button class="btn btn-primary" type="submit">
                                        <span class="glyphicon glyphicon-log-out" aria-hidden="true"></span>
                                    </button>
                            </c:if>
                        </sec:authorize>
                    </form:form>


                <%--                    <sec:authorize access="isAuthenticated()">
                        &lt;%&ndash;authenticated as <sec:authentication property="principal.username" />&ndash;%&gt;
                        <!-- For login user -->
                        <c:url value="/j_spring_security_logout" var="logoutUrl" />
                        <form action="${logoutUrl}" method="post" id="logoutForm">
                            <input type="hidden" name="${_csrf.parameterName}"
                                   value="${_csrf.token}" />
                        </form>
                        <script>
                            function formSubmit() {
                                document.getElementById("logoutForm").submit();
                            }
                        </script>

                        <c:if test="${pageContext.request.userPrincipal.name != null}">
                            <span class="glyphicon glyphicon-user"></span>
                            ${pageContext.request.userPrincipal.name}  |  <a href="javascript:formSubmit()">
                            <span class="glyphicon glyphicon-log-out"></span> Logout</a>
                        </c:if>
                    </sec:authorize>
                </li>--%>
            </ul>
        </div>
</nav>