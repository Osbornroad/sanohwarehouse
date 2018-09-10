<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>Osbornroad</head>
<body>
<h2>Sanoh warehouse.</h2>
<br/>
<%--<a href="<c:url value="/delivery"/>" target="_blank">Test delivery</a>--%>
<% response.sendRedirect("/main");%>
<a href="<c:url value="/operations"/>" target="_blank">Operation list</a>
</body>
</html>


