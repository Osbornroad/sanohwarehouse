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
    </style>
    <script>
        var ajaxUrl = "incomings/ajax/";
        reference = "incoming";

        $(document).ready(function() {
            table = $('#incomingTable').DataTable({
                ajax : {
                    url : ajaxUrl,
                    dataSrc : ""
                },
                columns : [
                    {
                        "data" : "incomingDateTime",
                        "render": function (date, type, row) {
                            if (type == 'display') {
                                return formatDate(date);
                            }
                            return date;
                        }
                    },
                    {
                        "data" : "finishPart",
                        "render" : function (date, type, row) {
                            if (type == 'display') {
                                return date.name;
                            }
                            return date;
                        }
                    },
                    {"data" : "quantity"},
                    {
                        "data" : "comments",
                        "defaultContent" : ""
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

        function openModalEdit(id) {
            clearForm();
            document.getElementById("modalTitle").innerHTML = id === "create" ? "New " + reference : "Edit " + reference;
            $.get(ajaxUrl + id, function (data) {
                setEnabled(!(data.partType === "TUBE"));
                $.each(data, function (key, value) {
                    form.find("input[name='" + key + "']").val(
                        key === "incomingDateTime" ? formatDate(value) : value
                    );
                    form.find("select[name='" + key + "']").val(
                        function() {
                            var currentValue;
                            switch(key) {
                                case "finishPart":
                                    currentValue = "FinishPart{partType=" + value.partType + ", name=\'" + value.name + "\', id=" + value.id + "}";
                                    break;
                                default:
                                    currentValue = value;
                            }
                            return currentValue;
                        }
                    );
                });
            })
                .done(function () {
                    if (id === "create") {
                        // clearForm();
                    } else {
                        /*$('#createdDateTime').val(
                            formatDate($('#incomingDateTime').val())
                        );*/
                    }
                    $('#editRow').modal('show');
                })
                .fail(function() {
                    bootbox.alert("Something wrong with incoming creation or edition")
                });


        }

    </script>
</head>
<body>

<jsp:include page="fragments/navbar.jsp"/>

<div class="container float-left m-3">
    <div class="row mt-4 mb-4">
        <div class="col">
            <h3>Incoming list</h3>
        </div>
        <div class="col">
            <a class="btn btn-outline-info float-right" onclick="openModalEdit('create')"><span class="glyphicon glyphicon-plus"></span></a>
        </div>
    </div>
    <div class="row">
        <div class="col-12">
            <table class="table table-hover table-striped display table-sm small" id="incomingTable">
                <thead class="thead-light">
                <tr>
                    <th>Date time</th>
                    <th>Finish Part</th>
                    <th>Quantity</th>
                    <th>Comments</th>
                    <%--<th>Variants</th>--%>
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
                    <%--<input type="hidden" id="incomingDateTime" name="incomingDateTime" class="to-empty">--%>
                    <div class="form-group">
                        <label for="incomingDateTime" class="control-label col-xs-3">Date Time</label>
                        <div class="col-xs-9">
                            <input readonly="readonly" class="form-control to-empty not-empty"
                                   id="incomingDateTime" name="incomingDateTime">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="finishPart" class="control-label col-xs-3">Finish Part</label>
                        <div class="col-xs-9">
                            <select class="form-control to-empty not-empty" id="finishPart" name="finishPart">
                                <c:forEach var="item" items="${allFinishParts}">
                                    <option value="${item}">${item.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="quantity" class="control-label col-xs-3">Quantity</label>
                        <div class="col-xs-9">
                            <input class="form-control to-empty not-empty" id="quantity" name="quantity"
                                   placeholder="Input quantity">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="comments" class="control-label col-xs-3">Comments</label>
                        <div class="col-xs-9">
                            <input class="form-control to-empty not-empty" id="comments" name="comments"
                                   placeholder="Input comment">
                        </div>
                    </div>
                    <%--<input type="hidden" id="variantSet" name="variantSet" class="to-empty">--%>


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