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
    <script src="/resources/js/datatablesUtil.js"></script>
    <script>
        ajaxUrl = "users/ajax/";
        reference = "user";

        $(document).ready(function() {
            table = $('#userTable').DataTable({
                ajax : {
                    url : ajaxUrl,
                    dataSrc : ""
                },
                columns : [
                    {"data" : "name"},
                    {"data" : "email"},
                    {"data" : "password"},
                    {
                        "data" : "registered",
                        "render": function (date, type, row) {
                            if (type == 'display') {
                                return formatDate(date);
                            }
                            return date;
                        }
                    },
                    {"data" : "roles"},
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


<div class="container float-left m-3">
    <div class="row mt-4 mb-4">
        <div class="col">
            <h3>User list</h3>
        </div>
        <div class="col">
            <a class="btn btn-outline-info float-right" onclick="openModalEdit('create')"><span class="glyphicon glyphicon-plus"></span></a>
        </div>
    </div>
    <div class="row">
        <div class="col-12">
            <table class="table table-hover" id="userTable">
                <thead>
                <tr>
                    <th>Name</th>
                    <th>E-mail</th>
                    <th>Password</th>
<%--
                    <th>Enabled</th>
--%>
                    <th>Registered</th>
                    <th>Roles</th>
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
                        <label for="name" class="control-label col-xs-3">Name</label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="name" name="name"
                                   placeholder="Input name of user" autofocus>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="email" class="control-label col-xs-3">E-mail</label>

                        <div class="col-xs-9">
                            <input type="email" class="form-control" id="email" name="email"
                                   placeholder="Input e-mail">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="password" class="control-label col-xs-3">Password</label>

                        <div class="col-xs-9">
                            <input type="password" class="form-control" id="password" name="password"
                                   placeholder="Input password">
                        </div>
                    </div>
                    <%--<div class="form-group">
                        <label for="enabled" class="control-label col-xs-3">Enabled</label>

                        <div class="col-xs-9">
                            <select class="form-control" id="enabled" name="enabled">
                                <option>true</option>
                                <option>false</option>
                            </select>
                        </div>
                    </div>--%>

                    <input type="hidden" id="registered" name="registered">
                    <%--<div class="form-group">
                        <label for="enabled" class="control-label col-xs-3">Enabled</label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="enabled" name="enabled"
                                   placeholder="Input enabled or not">
                        </div>
                    </div>--%>
                    <div class="form-group">
                        <label for="roles" class="control-label col-xs-3">Roles</label>

                        <div class="col-xs-9">
                            <select multiple class="form-control" id="roles" name="roles">
                                <option>ROLE_ADMIN</option>
                                <option>ROLE_USER</option>
                            </select>
                        </div>
                    </div>
                   <%-- <div class="form-group">
                        <label for="roles" class="control-label col-xs-3">Roles</label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="roles" name="roles"
                                   placeholder="Input roles">
                        </div>
                    </div>--%>

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