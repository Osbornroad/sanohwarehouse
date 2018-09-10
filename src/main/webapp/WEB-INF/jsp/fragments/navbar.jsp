<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<nav class="navbar navbar-expand-sm bg-primary navbar-dark">
    <a class="navbar-brand" href="<c:url value="/main"/>">SANOH</a>
    <ul class="navbar-nav">
        <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
                Reference
            </a>
            <div class="dropdown-menu">
                <a class="dropdown-item" href="<c:url value="/operations"/>">Operations</a>
                <a class="dropdown-item" href="#">Parts</a>
                <a class="dropdown-item" href="#">Staff</a>
                <a class="dropdown-item" href="<c:url value="/users"/>">Users</a>
            </div>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="#">Link 2</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="#">Link 3</a>
        </li>
    </ul>
</nav>