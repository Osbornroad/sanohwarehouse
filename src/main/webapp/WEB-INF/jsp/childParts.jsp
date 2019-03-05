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
        var ajaxUrl = partId + "/ajax/";
        reference = "childParts";

        $(document).ready(function() {
           table = $('#childPartsTable').DataTable({
               ajax : {
                   url : ajaxUrl,
                   dataSrc : ""
               },
               ordering : false,
               columns : [
                   {
                       "data" : "childPartName"
                   },
                   {
                       "data" : "quantity"
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
           })
        });

        function renderDeleteBtn(data, type, row) {
            var id = row.id;
            var referenceName = row.name;
            if (type == 'display') {
                return '<a href="#" onclick="deleteRow(' + id + ', \'' + referenceName + '\');">' +
                    '<span class="glyphicon glyphicon-remove" style="color: darkred" aria-hidden="true"></span></a>';
            }
        }

        function deleteRow(id, referenceName) {
            bootbox.confirm({
                title: "Delete " + reference,
                message: "Are you sure to delete " + reference + " " + referenceName + "?\nAction is irreversible.",
                callback: function (result) {
                    if (result === true) {
                        $.ajax({
                            url: "/ajax/" + id,
                            type: 'DELETE',
                            success: function () {
                                table.ajax.reload();
                            },
                            error: function(){
                                bootbox.alert("You could not delete this child part")
                            }
                        });
                    }
                }
            });
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
                <table class="table table-hover table-striped display table-sm small" id="childPartsTable">
                    <thead>
                    <tr>
                        <th>Child part name</th>
                        <th>Quantity</th>
                        <th width="40px"></th>
                        <th width="10px"></th>
                    </tr>
                    </thead>
                </table>
            </div>
        </div>
        <div class="row mt-5 pl-3">
            <a role="button" class="btn btn-outline-primary" href="/parts">
                Back to Part list
            </a>
        </div>
    </div>
</body>