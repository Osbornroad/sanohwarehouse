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
        var ajaxUrl = "parts/ajax/";
        reference = "part";

        $(document).ready(function() {
            table = $('#partTable').DataTable({
                ajax : {
                    url : ajaxUrl,
                    dataSrc : ""
                },
                columns : [
                    {"data" : "name"},
                    {"data" : "partType"},
                    {
                        "data" : "operationList",
                        "render" : function (opsFlow, type, row) {
                            // if (type == 'display') {
                                return formatOpsFlow(opsFlow);
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
        } );

        $(document).ready(function() {
            $("#editRow").on('show.bs.modal', function () {
                var operationList = document.getElementById("operationList");
                var allOperations = ${allOperationList};
                if (allOperations) {
                    var i;
                    for (i = 0; i < allOperations.length; i++) {
                        operationList.options.add(new Option(allOperations[i].toString()));
                    }
                }
            })
        });

        function formatOpsFlow(opsFlow) {
            var flowForDisplay = " ";
            opsFlow.forEach(function (item, index) {
                var cutItem = item.substr(0,5);
                flowForDisplay = flowForDisplay + cutItem + ' > ';
            });
            return flowForDisplay;
        }
    </script>

    <style>
        .container {
            max-width: 900px;
        }

    </style>
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
            <table class="table table-hover" id="partTable">
                <thead>
                <tr>
                    <th>Name</th>
                    <th width="80">Type</th>
                    <th>Operations</th>
                    <th width="60"></th>
                    <th width="60"></th>
                </tr>
                </thead>
            </table>
        </div>
    </div>
</div>

<div class="modal fade" id="editRow" on>
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
                            <input class="form-control to-empty" id="name" name="name"
                                   placeholder="Input name of part" autofocus>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="partType" class="control-label col-xs-3">Type</label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control to-empty" id="partType" name="partType"
                                   placeholder="Choose type of part">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="operationList" class="control-label col-xs-3">Operations</label>

                        <div class="col-xs-9">
                            <select multiple class="form-control to-empty" id="operationList" name="operationList">
                                <%--<options items="${allOperationList}"/>--%>
                                <%--<option>HPC</option>
                                <option>LASER</option>
                                <option>CHAMPHERING</option>
                                <option>BRUSHING</option>
                                <option>REDUCTION</option>
                                <option>ENDFORMING</option>
                                <option>SUMI_CUTTING</option>
                                <option>SUMI_OVEN</option>
                                <option>BENDING</option>
                                <option>ASSEMBLY</option>
                                <option>DELIVERY</option>--%>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-offset-3 col-xs-9">
                            <button class="btn btn-primary toBeEmpty" type="button" onclick="save()">
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