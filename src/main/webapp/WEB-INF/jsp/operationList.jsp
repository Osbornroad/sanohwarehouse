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
    <%--<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">--%>
    <link rel="stylesheet" href="/webjars/bootstrap/4.1.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="/webjars/datatables/1.10.19/css/dataTables.bootstrap4.min.css">
    <link rel="stylesheet" href="/resources/bootstrap4-glyphicons/css/bootstrap-glyphicons.css">
    <script src="/webjars/jquery/3.1.1-1/jquery.js"></script>
    <script src="/webjars/jquery/3.1.1-1/jquery.min.js"></script>
    <script src="/webjars/datatables/1.10.19/js/jquery.dataTables.min.js"></script>
    <script src="/webjars/datatables/1.10.19/js/dataTables.bootstrap4.min.js"></script>
    <script src="/webjars/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="/resources/js/bootbox.min.js"></script>
    <%--<script src="/webjars/bootbox/4.4.0/bootbox.js"></script>--%>
    <script>
        var table;
        var ajaxUrl = "operations/ajax/";

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
                        "render": renderDeleteBtn,
                        "defaultContent": "",
                        "orderable": false
                    }
                ]
            });
        } );

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

/*        $('#confirm-delete').on('click', '.btn-ok', function(e) {
            var $modalDiv = $(e.delegateTarget);
            var id = $(this).data('recordId');
            $.ajax({url: '/operations/' + id, type: 'DELETE'});
            // $.post('/api/record/' + id).then()
            $modalDiv.addClass('loading');
            setTimeout(function() {
                $modalDiv.modal('hide').removeClass('loading');
            }, 1)
            table.ajax.reload();
        });

        $('#confirm-delete').on('show.bs.modal', function(e) {
            var data = $(e.relatedTarget).data();
            $('.title', this).text(data.recordTitle);
            $('.btn-ok', this).data('recordId', data.recordId);
        });*/
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
            <a class="btn btn-outline-info float-right" href="/operations/new"><span class="glyphicon glyphicon-plus"></span></a>
            <%--<a class="btn btn-outline-info font-weight-bold h3" href="/operations/new"><h3>+</h3></a>--%>
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
                </tr>
                </thead>
<%--                <tbody>
                <c:forEach items="${allOperationList}" var="operation" varStatus="oStatus" >
                    <jsp:useBean id="operation" scope="page" type="com.gmail.osbornroad.model.jpa.Operation"/>
                    <tr>
                        <td class="align-middle"><a href="/operations/${operation.id}">${operation.operationName}</a></td>
                        <td class="align-middle">${operation.operationSequence}</td>
                        &lt;%&ndash;<td class="align-middle"><button class="btn btn-default" data-href="/delete.php?id=54" data-toggle="modal" data-target="#confirm-delete">&ndash;%&gt;
                            &lt;%&ndash;Delete record #54&ndash;%&gt;
                        &lt;%&ndash;</button></td>&ndash;%&gt;
                        <td class="align-middle">
                            &lt;%&ndash;<sf:form action="operations/${operation.id}" method="delete">&ndash;%&gt;
                            <button class="btn btn-outline-danger float-right btn-sm"
                                    data-record-id="${operation.id}"
                                    data-record-title="${operation.operationName}"
                                    &lt;%&ndash;data-href="/operations/${operation.id}"&ndash;%&gt;
                                    data-toggle="modal"
                                    data-target="#confirm-delete"><span class="glyphicon glyphicon-remove"></span></button>
                            &lt;%&ndash;</sf:form>&ndash;%&gt;
                        </td>
                    </tr>
                </c:forEach>
                </tbody>--%>
            </table>
        </div>
    </div>
</div>

<div class="modal fade" id="confirm-delete" tabindex="-1"
     role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <div class="col">
                    <h4 class="modal-title">Delete operation</h4>
                </div>
                <div class="col float-right">
                    <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">×</button>
                </div>
            </div>
            <div class="modal-body">
                <p>You are about to delete <b><i class="title"></i></b> operation, this procedure is irreversible.</p>
                <p>Do you want to proceed?</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                <button type="button" class="btn btn-danger btn-ok">Delete</button>
                <%--<a class="btn btn-danger btn-ok">Delete</a>--%>
            </div>
        </div>
    </div>
</div>

<script>
    $('#confirm-delete').on('click', '.btn-ok', function(e) {
        var $modalDiv = $(e.delegateTarget);
        var id = $(this).data('recordId');
        $.ajax({url: '/operations/' + id, type: 'DELETE'});
        // $.post('/api/record/' + id).then()
        $modalDiv.addClass('loading');
        setTimeout(function() {
            $modalDiv.modal('hide').removeClass('loading');
        }, 0);
        // location.reload(true);
        // table.clear().rows.draw();
        // table.ajax.reload();
    });
    $('#confirm-delete').on('show.bs.modal', function(e) {
        var data = $(e.relatedTarget).data();
        $('.title', this).text(data.recordTitle);
        $('.btn-ok', this).data('recordId', data.recordId);
    })
/*    $('#confirm-delete').on('show.bs.modal', function(e) {
        $(this).find('.btn-ok').attr('href', $(e.relatedTarget).data('href'));
    });*/
</script>
</body>

</html>