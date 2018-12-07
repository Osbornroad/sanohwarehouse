<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="en">
<head>
    <jsp:include page="fragments/headerTags.jsp"/>
    <script>
        <%--var partId = ${partId};--%>
        var ajaxUrl = "ajax/" + partId + "/";
        reference = "childParts";

    </script>

</head>

<body>

    <jsp:include page="fragments/navbar.jsp"/>

    <div class="container float-left m-3">
        <div class="row mt-4 mb-4">
            <div class="col">
                <h3>${partName}</h3>
            </div>
            <div class="col">
                <a class="btn btn-outline-info float-right" onclick="openModalEdit('create')"><span class="glyphicon glyphicon-plus"></span></a>
            </div>
        </div>
    </div>
</body>