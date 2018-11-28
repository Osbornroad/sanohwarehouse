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
        var partId = ${partId};
        var ajaxUrl = "ajax/" + partId + "/";
        reference = "job";

        $(document).ready(function() {
            table = $('#jobsTable').DataTable({
                ajax : {
                    url : ajaxUrl,
                    dataSrc : ""
                },
                ordering : false,
                columns : [
                    {
                        "data" : "operation",
                        "render" : function (data, type, row) {
                            // if (type == 'display') {
                            return data.fullName;
                        }
                    },
                    {"data" : "machine"},
                    {
                        "data" : "cycleTime",
                        "className" : "text-right pr-4"
                    },
                    {
                        "render": renderEditBtn,
                        "defaultContent": "",
                        "orderable": false,
                        "className" : "text-center"
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

        function renderDeleteBtn(data, type, row) {
            var id = row.id;
            var referenceName = row.operation.fullName;
            if (type == 'display') {
                return '<a href="#" onclick="deleteRow(' + id + ', \'' + referenceName + '\');">' +
                    '<span class="glyphicon glyphicon-remove" style="color: darkred" aria-hidden="true"></span></a>';
            }
        }
    </script>

    <style>
        .container {
            max-width: 700px;
        }
    </style>
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
    <div class="row">
        <div class="col-12">
            <table class="table table-hover table-striped display table-sm small" id="jobsTable">
                <thead>
                <tr>
                    <th>Operation</th>
                    <th>Machine</th>
                    <th width="100px">Cycle Time</th>
                    <th width="40px"></th>
                    <th width="10px"></th>
                </tr>
                </thead>
            </table>
        </div>
    </div>
    <div class="row mt-5">
        <a role="button" class="btn btn-outline-primary" href="/parts">
            Back to Part list
        </a>
    </div>
</div>

<div class="modal fade" id="editRow">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h2 class="modal-title" id="modalTitle"></h2>
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            </div>
            <div class="modal-body">
                <form:form class="form-horizontal" id="detailsForm">
                    <input type="hidden" id="id" name="id" class="to-empty">

                    <%--<input type="hidden" id="part" name="part" class="to-empty">--%>

                    <div class="form-group">
                        <label for="operation" class="control-label col-xs-3">Operation</label>

                        <div class="col-xs-9">

                            <select class="form-control to-empty" id="operation" name="operation"
                                    autofocus>
                                <c:forEach var="item" items="${operationList}">
                                    <option value="${item}">${item}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="machine" class="control-label col-xs-3">Machine</label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control to-empty" id="machine" name="machine"
                                   placeholder="Input name of machine">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="cycleTime" class="control-label col-xs-3">Cycle Time</label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control to-empty" id="cycleTime" name="cycleTime"
                                   placeholder="Input cycle time">
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-xs-offset-3 col-xs-9">
                            <button class="btn btn-primary toBeEmpty float-right" type="button" onclick="save()">
                                <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
                            </button>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>

</body>