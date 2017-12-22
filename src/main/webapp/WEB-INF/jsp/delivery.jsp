<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delivery</title>
</head>
<body>
<h2>Delivery test page</h2>
<br/>
<a href="<c:url value="/delivery/getinfo"/>" target="_blank">Get delivery info</a>
<br/>
<table>
    <tr>
        <th width="80">field_key</th>
        <th width="80">aPoint</th>
        <th width="80">number</th>
    </tr>
    <tr>
        <td>${testDelivery.field_key}</td>
        <td>${testDelivery.aPoint}</td>
        <td>${testDelivery.number}</td>
    </tr>
</table>

<br/>
<br/>
<a href="<c:url value="/delivery/getallinfo"/>" target="_blank">Get all delivery info</a>
<br/>
<table>
    <tr>
        <th width="80">field_key</th>
        <th width="80">aPoint</th>
        <th width="80">number</th>
    </tr>
    <c:forEach items="${testAllDelivery}" var="delivery">
        <tr>
            <td>${delivery.field_key}</td>
            <td>${delivery.aPoint}</td>
            <td>${delivery.number}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
