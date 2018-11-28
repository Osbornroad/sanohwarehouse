var table;
var form;
var reference;
var ajaxUrl;

function formatDate(date) {
    return date.replace('T', ' ').substr(0, 16);
}

function makeEditable() {
    form = $('#detailsForm');

    /*            $(document).ajaxError(function (event, jqXHR, options, jsExc) {
                    failNoty(event, jqXHR, options, jsExc);
                });
*/
                var token = $("meta[name='_csrf']").attr("content");
                var header = $("meta[name='_csrf_header']").attr("content");
                $(document).ajaxSend(function(e, xhr, options) {
                    xhr.setRequestHeader(header, token);
                });
}

function renderEditBtn(data, type, row) {
    if (type == 'display') {
        return '<a href="#" onclick="openModalEdit(' + row.id + ', \'' + "edit" + '\');">' +
            '<span class="glyphicon glyphicon-pencil" style="color: blue" aria-hidden="true"></span></a>';
    }
}


function clickCheckbox() {
    var checkbox = document.getElementById("checkbox");
    var enadled = document.getElementById("enabled");
    if (checkbox.checked) {
        enadled.value = "true";
    } else {
        enadled.value = "false";
    }
}

function setInitCheckbox() {
    var checkbox = document.getElementById("checkbox");
    var enadled = document.getElementById("enabled");
    var currentVal = enadled.value;
    if (currentVal == "true")
        checkbox.checked = "true";
    else
        checkbox.checked = "false";
}

function openModalEdit(id) {
    document.getElementById("modalTitle").innerHTML = id === "create" ? "New " + reference : "Edit " + reference;
    $.get(ajaxUrl + id, function (data) {
        $.each(data, function (key, value) {
            form.find("input[name='" + key + "']").val(
                key === "registered" ? formatDate(value) : value
            );
            if (key === "enabled")
                document.getElementById("checkbox").checked = value;
            form.find("select[name='" + key + "']").val(
                function() {
                    var currentValue;
                    switch(key) {
                        case "operation":
                            currentValue = value.fullName;
                            break;
                        default:
                            currentValue = value;
                    }
                    return currentValue;
                }
            );
        });
    });
    if (id === "create") {
        var elements = document.getElementsByClassName("to-empty");
        for (var ii=0; ii < elements.length; ii++) {
                elements[ii].value = "";
        }
        $('#buttonLinkToOperation').hide();
        /*var buttonLinkToOperation = document.getElementById("buttonLinkToOperation");
        buttonLinkToOperation.display = "none";*/
    } else {
        $('#buttonLinkToOperation').show();
    }

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

$(document).on('shown.bs.modal', '.modal', function () {
    $(this).find('[autofocus]').focus();
});


