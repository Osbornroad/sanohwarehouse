<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="/webjars/bootstrap/4.1.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="/webjars/datatables/1.10.19/css/dataTables.bootstrap4.min.css">
    <link rel="stylesheet" href="/resources/bootstrap4-glyphicons/css/bootstrap-glyphicons.css">
    <script src="/webjars/jquery/3.1.1-1/jquery.js"></script>
    <script src="/webjars/jquery/3.1.1-1/jquery.min.js"></script>
    <script src="/webjars/datatables/1.10.19/js/jquery.dataTables.min.js"></script>
    <script src="/webjars/datatables/1.10.19/js/dataTables.bootstrap4.min.js"></script>
    <script src="/webjars/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="/resources/js/bootbox.min.js"></script>
    <script>
        var table;
        var ajaxUrl = "operations/ajax/";
        var form;

        $(document).ready(function() {
            table = $('#operationTable').DataTable({
                ajax : {
                    url : ajaxUrl,
                    dataSrc : ""
                },
                columns : [
                    {"data" : "operationName"},
                    {"data" : "operationSequence"},
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

        function makeEditable() {
            form = $('#detailsForm');

/*            $(document).ajaxError(function (event, jqXHR, options, jsExc) {
                failNoty(event, jqXHR, options, jsExc);
            });

            var token = $("meta[name='_csrf']").attr("content");
            var header = $("meta[name='_csrf_header']").attr("content");
            $(document).ajaxSend(function(e, xhr, options) {
                xhr.setRequestHeader(header, token);
            });*/
        }

        function renderEditBtn(data, type, row) {
            if (type == 'display') {
                return '<a class="btn btn-xs btn-primary" onclick="openModalEdit(' + row.id + '/*, \'' + "edit" + '\'*/);">' +
                    '<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></a>';
            }
        }

        function openModalEdit(id) {
            document.getElementById("modalTitle").innerHTML = id === "create" ? "New operation" : "Edit operation";
            $.get(ajaxUrl + id, function (data) {
                $.each(data, function (key, value) {
                    form.find("input[name='" + key + "']").val(
                        /*key === "dateTime" ? formatDate(value) : */value
                    );
                });
            });
            $('#editRow').modal('show');
/*            $('#modalTitle').html(i18n[editTitleKey]);
            $.get(ajaxUrl + id, function (data) {
                $.each(data, function (key, value) {
                    form.find("input[name='" + key + "']").val(
                        key === "dateTime" ? formatDate(value) : value
                    );
                });
                $('#editRow').modal();
            });*/
        }

        function save() {
            $.ajax({
                type: "POST",
                url: ajaxUrl,
                data: form.serialize(),
                success: function () {
                    $('#editRow').modal('hide');
                    table.ajax.reload();
                    // successNoty('common.saved');
                }
            });
        }

        function renderDeleteBtn(data, type, row) {
            var id = row.id;
            var opName = row.operationName;
            if (type == 'display') {
                return '<a class="btn btn-xs btn-danger" onclick="deleteRow(' + id + ', \'' + opName + '\');">' +
                    '<span class="glyphicon glyphicon-remove" aria-hidden="true"></span></a>';
            }
        }

        function deleteRow(id, opName) {
            bootbox.confirm({
                title: "Delete operation",
                message: "Are you sure to delete operation " + opName + "?\nAction is irreversible.",
                callback: function (result) {
                    if (result === true) {
                        $.ajax({
                            url: ajaxUrl + id,
                            type: 'DELETE',
                            success: function () {
                                table.ajax.reload();
                            }
                        });
                    }
                }
            });
        }
    </script>

    <style>
        .container {
            max-width: 900px;
        }

    </style>

</head>
<body>
<div class="container float-left m-3">
    <div class="row mt-4 mb-4">
        <div class="col">
            <h3>Operation list</h3>
        </div>
        <div class="col">
            <a class="btn btn-outline-info float-right" onclick="openModalEdit('create')"><span class="glyphicon glyphicon-plus"></span></a>
        </div>
    </div>
    <div class="row">
        <div class="col-12">
            <table class="table table-hover" id="operationTable">
                <thead>
                <tr>
                    <th>Name</th>
                    <th>Sequence</th>
                    <th width="60"></th>
                    <th width="60"></th>
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
                    <input type="hidden" id="id" name="id">

                    <div class="form-group">
                        <label for="operationName" class="control-label col-xs-3">Name</label>

                        <div class="col-xs-9">
                            <input class="form-control" id="operationName" name="operationName"
                                   placeholder="Input name of operation">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="operationSequence" class="control-label col-xs-3">Sequence</label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="operationSequence" name="operationSequence"
                                   placeholder="Input sequence of operation">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-offset-3 col-xs-9">
                            <button class="btn btn-primary" type="button" onclick="save()">
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