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

                var token = $("meta[name='_csrf']").attr("content");
                var header = $("meta[name='_csrf_header']").attr("content");
                $(document).ajaxSend(function(e, xhr, options) {
                    xhr.setRequestHeader(header, token);
                });*/
}

function renderEditBtn(data, type, row) {
    if (type == 'display') {
        return '<a href="#" onclick="openModalEdit(' + row.id + '/*, \'' + "edit" + '\'*/);">' +
            '<span class="glyphicon glyphicon-pencil" style="color: blue" aria-hidden="true"></span></a>';
    }
}

function openModalEdit(id) {
    document.getElementById("modalTitle").innerHTML = id === "create" ? "New " + reference : "Edit " + reference;
    $.get(ajaxUrl + id, function (data) {
        $.each(data, function (key, value) {
            form.find("input[name='" + key + "']").val(
                key === "registered" ? formatDate(value) : value
            );
            form.find("select[name='" + key + "']").val(
                /*key === "dateTime" ? formatDate(value) : */value
            );
        });
    });

    if (id === "create") {
        var elements = document.getElementsByTagName("input");
        for (var ii=0; ii < elements.length; ii++) {
            if (elements[ii].type == "text" || "hidden" || "email" || "password") {
                elements[ii].value = "";
            }
        }
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


