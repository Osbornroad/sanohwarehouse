<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="en">
<head>
    <jsp:include page="fragments/headerTags.jsp"/>
    <style>
        .container {
            max-width: 900px;
        }

        childrow {
            font-size: 10px;
        }

        td.details-control {
            background: url("/resources/images/details_open.png") no-repeat center center;
            cursor: pointer;
        }
        tr.shown td.details-control {
            background: url("/resources/images/details_close.png") no-repeat center center;
        }
    </style>
    <script>
        var ajaxUrl = "parts/ajax/";
        reference = "part";

        $(document).ready(function() {
            table = $('#partTable').DataTable({
                "scrollY":        "600px",
                "scrollCollapse": true,
                "paging":         false,
                ajax : {
                    url : ajaxUrl,
                    dataSrc : ""
                },
                columns : [
                    {
                        "className": 'details-control',
                        "orderable": false,
                        "data": null,
                        "defaultContent": ''
                    },
                    {"data" : "name"},
                    {"data" : "partType"},
                    {
                        "data" : "length",
                        "defaultContent" : ""
                    },
                    {
                        "data" : "partCode",
                        "defaultContent" : "",
                        "render" : function (partCode, type, row) {
                            return formatPartCode(partCode);
                        }
                    },
                    {
                        "data" : "jobSet",
                        "render" : function (opsFlow, type, row) {
                            // if (type == 'display') {
                                return '<a href="/jobs/' + row.id + '">' + formatOpsFlow(opsFlow) + '</a>';
                            // }
                        }
                    },
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

            function formatPartCode(partCode) {
                if (typeof partCode !== "undefined" && typeof partCode !== "null")
                    return partCode.code;
                return "";
            }

            function renderEditBtn(data, type, row) {
                if (type == 'display') {
                    return '<a href="#" onclick="openModalEdit(' + row.id + ', \'' + "edit" + '\');">' +
                        '<span class="glyphicon glyphicon-pencil" style="color: blue" aria-hidden="true"></span></a>';
                }
            }

            $('#partType').on('change', function (e) {
                // var optionSelected = $("option:selected", this);
                var valueSelected = this.value;
                setEnabled(valueSelected !== "TUBE");
            });

            // Add event listener for opening and closing details
            $('#partTable tbody').on('click', 'td.details-control', function () {
                var tr = $(this).closest('tr');
                var row = table.row( tr );

                if ( row.child.isShown() ) {
                    // This row is already open - close it
                    row.child.hide();
                    tr.removeClass('shown');
                }
                else {
                    // Open this row
                    row.child( format(row.data()) ).show();
                    tr.addClass('shown');
                }
            } );
        } );

        function format ( d ) {
            var jobs= d.jobSet;
            var childTable = '<table cellpadding="5" cellspacing="0" border="0" style="padding-left:50px; margin-left: 100px">'+
                '<tr>' +
                '<th width="200">Operation</th>' +
                '<th width="200">Machine</th>' +
                '<th>Cycle time</th>' +
                '</tr>';
            jobs.forEach(function (item, i, arr)
            {
                childTable = childTable +
                    '<tr>' +
                    '<td>' + item.operation.fullName + '</td>' +
                    '<td>' + item.machine + '</td>' +
                    '<td>' + item.cycleTime + '</td>' +
                    '</tr>';
            });
            return childTable;
        }

        function formatOpsFlow(opsFlow) {
            var flowForDisplay = " ";
            var previousOperation = "FakeOperation";
            opsFlow.forEach(function (item, index) {
                var currentOperation = item.operation.shortName;
                if (previousOperation !== currentOperation) {
                    flowForDisplay = flowForDisplay + currentOperation + ' > ';
                    previousOperation = currentOperation;
                }
            });
            return flowForDisplay;
        }

        function generateLinkToOperation() {
            save();
            var modalId = document.getElementById("id").value;
            window.open("/jobs/" + modalId, "_self");
        }


        
    </script>


</head>
<body>

<jsp:include page="fragments/navbar.jsp"/>

<div class="container float-left m-3">
    <div class="row mt-4 mb-4">
        <div class="col">
            <h3>Part list</h3>
        </div>
        <div class="col">
            <a class="btn btn-outline-info float-right" onclick="openModalEdit('create')"><span class="glyphicon glyphicon-plus"></span></a>
        </div>
    </div>
    <div class="row">
        <div class="col-12">
            <table class="table table-hover table-striped display table-sm small" id="partTable">
                <thead class="thead-light">
                <tr>
                    <th width="10px"></th>
                    <th width="100px">Name</th>
                    <th width="80px">Type</th>
                    <th width="80px">Length</th>
                    <th width="80px">Code</th>
                    <th>Operations</th>
                    <th width="40px"></th>
                    <th width="10px"></th>
                </tr>
                </thead>
            </table>
        </div>
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
                    <div class="form-group">
                        <label for="name" class="control-label col-xs-3">Name</label>
                        <div class="col-xs-9">
                            <input class="form-control to-empty not-empty" id="name" name="name"
                                   placeholder="Input name of part" autofocus>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="partType" class="control-label col-xs-3">Type</label>
                        <div class="col-xs-9">
                            <select class="form-control to-empty not-empty" id="partType" name="partType">
                                <c:forEach var="item" items="${partTypeList}">
                                    <option value="${item}">${item}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="length" class="control-label col-xs-3">Length</label>
                        <div class="col-xs-9">
                            <input class="form-control to-empty" id="length" name="length"
                                   placeholder="Input length of tube">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="partCode" class="control-label col-xs-3">Code</label>
                        <div class="col-xs-9">
                            <select class="form-control to-empty not-empty" id="partCode" name="partCode">
                                <c:forEach var="item" items="${partCodeList}">
                                    <option value="${item}">${item.code}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-9" >
                            <a role="button"  id="buttonLinkToOperation" class="btn btn-outline-primary mt-3 mb-3" href="#" onclick=generateLinkToOperation()>Operations</a>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-offset-3 col-xs-9">
                            <button class="btn btn-primary toBeEmpty float-right enter-pressed" type="button" onclick="save()">
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

</html>