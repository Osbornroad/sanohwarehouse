<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="en">
<head>
    <jsp:include page="fragments/headerTags.jsp"/>
    <script>
        var ajaxUrl = "jobs/ajax/";
        reference = "job";

        $(document).ready(function() {
            table = $('#jobsTable').DataTable({
                ajax : {
                    url : ajaxUrl,
                    dataSrc : ""
                },
                columns : [
                    {"data" : "operation"},
                    {"data" : "machine"},
                    {"data" : "cycleTime"},
                    {
                        "render": renderEditBtn,
                        "defaultContent": "",
                        "orderable": false
                    },
                    {
                        "render": renderDeleteBtn,
                        "defaultContent": "",
                        "orderable": false
                    }
                ],
                "initComplete": makeEditable
            });
        } );
    </script>

    <style>
        .container {
            max-width: 900px;
        }

    </style>
</head>
<body>

<jsp:include page="fragments/navbar.jsp"/>

<h3>You have't access to this page!</h3>

<%--<div class="container float-left m-3">
    <div class="row mt-4 mb-4">
        <div class="col">
            <h3>Job list</h3>
        </div>
        <div class="col">
            &lt;%&ndash;<a class="btn btn-outline-info float-right" onclick="openModalEdit('create')"><span class="glyphicon glyphicon-plus"></span></a>&ndash;%&gt;
        </div>
    </div>
    <div class="row">
        <div class="col-12">
            <table class="table table-hover" id="jobsTable">
                <thead>
                <tr>
                    <th>Operation</th>
                    <th>Machine</th>
                    <th>Cycle Time</th>
                    <th width="60"></th>
                    <th width="60"></th>
                </tr>
                </thead>
            </table>
        </div>
    </div>
</div>--%>

</body>