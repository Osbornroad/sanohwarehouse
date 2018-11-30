<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="en">
<head>

    <jsp:include page="fragments/headerTags.jsp"/>

<%--<!-- Required meta tags -->
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
    <script src="/resources/js/datatablesUtil.js"></script>--%>
    <script>
        ajaxUrl = "users/ajax/";
        reference = "user";
        var enabledAdmins = 0;

        $(document).ready(function() {
            table = $('#userTable').DataTable({
                ajax : {
                    url : ajaxUrl,
                    dataSrc : ""
                },
                columns : [
                    {"data" : "name"},
                    {"data" : "email"},
                    // {"data" : "password"},
                    {
                        "data" : "enabled",
                        // "render" : renderCheckBox
                        "render": renderCheckBox
                    },
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

        function renderCheckBox(data, type, row) {
            if (data.toString() === "true")
                return '<span class="glyphicon glyphicon-check" aria-hidden="true"></span>';
            else
                return '<span class="glyphicon glyphicon-unchecked" aria-hidden="true"></span>';
        }

        function clickCheckbox() {
            var checkbox = document.getElementById("checkboxEnabled");
            var enabled = document.getElementById("enabled");
            if (checkbox.checked) {
                enabled.value = "true";
            } else {
                // $('#checkboxEnabled').prop('checked', true);
                // $('#alertDisable').show();


                enabled.value = "false";
            }
        }

        function response422() {
            $('#name').val("").attr("placeholder", "This name already used");
        }

        $(document).on('show.bs.modal', function () {
            $('#alertDisable').hide();
        });

        function placeholderNameReset() {
            $('#name').attr("placeholder", "Input name of user");
        }

/*        $(document).on('hidden.bs.modal', function () {
            $('#name').attr("placeholder", "Input name of user");
        });*/
/*
        function clickCheckbox() {
            var label = $(document).getElementById("labelForEnabled");
            var checkbox = $(document).getElementById("enabled");
            if (checkbox.checked) {
                label.style.color("Red");
            } else {
                label.style.color("Green");
            }
        }*/



    </script>

    <style>
        .container {
            max-width: 900px;
        }

        #alertDisable {
            color: red;
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
            <table class="table table-hover table-striped display table-sm small" id="userTable">
                <thead class="thead-light">
                <tr>
                    <th>Name</th>
                    <th>E-mail</th>
                    <%--<th>Password</th>--%>
                    <th>Enabled</th>
                    <th>Registered</th>
                    <th>Roles</th>
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
                            <input type="text" class="form-control to-empty" id="name" name="name"
                                   placeholder="Input name of user" autofocus onclick="placeholderNameReset()">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="email" class="control-label col-xs-3">E-mail</label>

                        <div class="col-xs-9">
                            <input type="email" class="form-control to-empty" id="email" name="email"
                                   placeholder="Input e-mail">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="password" class="control-label col-xs-3">Password</label>

                        <div class="col-xs-9">
                            <input type="password" class="form-control to-empty" id="password" name="password"
                                   placeholder="Input password">
                        </div>
                    </div>
                    <div class="form-row">
                        <%--<label for="enabled" class="control-label col-xs-3">Enabled</label>--%>
                        <%--<div class="row">--%>
                            <div class="col-xs-4">
                                <div class="checkbox" onclick="clickCheckbox()">
                                    <label id="labelForEnabled"><input type="checkbox" id="checkboxEnabled"<%-- name="checkbox"--%>> Enabled</label>
                                </div>
                            </div>
                            <div class="col-xs-6" id="alertDisable">
                                *** You could not disable yourself! ***
                            </div>
                        <%--</div>--%>
                        <%--<div class="col-xs-9">
                            <select class="form-control" id="enabled" name="enabled">
                                <option>true</option>
                                <option>false</option>
                            </select>
                        </div>--%>
                    </div>
          <%--          <div class="form-group">
                        <div class="col-xs-9" id="alertDisable">
                            *** You could not disable yourself! ***
                        </div>
                    </div>--%>
                    <input type="hidden" id="enabled" name="enabled" class="to-empty">

                    <input type="hidden" id="registered" name="registered" class="to-empty">
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
                            <select multiple class="form-control to-empty" id="roles" name="roles">
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