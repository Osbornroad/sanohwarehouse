var table;
var form;
var reference;
var ajaxUrl;

function formatDate(date) {
    return date.replace('T', ' ').substr(0, 16);
}

function makeEditable() {
    form = $('#detailsForm');
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
    clearForm();
    document.getElementById("modalTitle").innerHTML = id === "create" ? "New " + reference : "Edit " + reference;
    $.get(ajaxUrl + id, function (data) {
        setEnabled(!(data.partType === "TUBE"));
        $.each(data, function (key, value) {
            form.find("input[name='" + key + "']").val(
                key === "registered" ? formatDate(value) : value
            );
            if (key === "partType" && value === "TUBE")
                isTube = true;
            if (key === "enabled")
                document.getElementById("checkboxEnabled").checked = value;
            form.find("select[name='" + key + "']").val(
                function() {
                    var currentValue;
                    switch(key) {
                        case "operation":
                            currentValue = value.fullName;
                            break;
                        case "partCode":
                            currentValue = value.name;
                            break;
                        case "finishPartSet":
                            var finishPartArray = [];
                            var finishPart;
                            $.each(value, function (i, v) {
                                finishPart = "FinishPart{partType=" + v.partType + ", name=\'" + v.name + "\', id=" + v.id + "}";
                                finishPartArray.push(finishPart);
                            })
                            // currentValue = ["FinishPart{partType=CLUSTER, name='17501-4CM1A', id=3}", "FinishPart{partType=CLUSTER, name='17501-4CM0A', id=1}"];
                            currentValue = finishPartArray;
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
                clearForm();
                $('#enabled').val('true');
                $('#buttonLinkToOperation').hide();
            } else {
                $('#buttonLinkToOperation').show();
            }

            $('#editRow').modal('show');
        })
        .fail(function() {
            bootbox.alert("You could not edit yourself")
        });
}

function setEnabled(isNotTube) {
    $('#length').prop('disabled', isNotTube);
    $('#partCode').prop('disabled', isNotTube);
}

function clearForm() {
    var elements = document.getElementsByClassName("to-empty");
    for (var ii=0; ii < elements.length; ii++) {
        elements[ii].value = "";
    }
}

/*function validationFunction() {
    var allFilled = true;
    var allInputs = $( ".not-empty" );

    $(allInputs).each(function() {
        if($(this).val().length === 0) {
            allFilled = false;
            return false;
        }
    });
    return allFilled;
}

$(document).ready(function() {
    $(window).keydown(function(event){
        if( (event.keyCode == 13) && (validationFunction() == false) ) {
            event.preventDefault();
            return false;
        }
    });
});*/

$(document).ready(function() {
    $(window).keydown(function(event){
        if(event.keyCode == 13) {
            // $('#submit').focus();
            var focused = document.activeElement;
            if ($(focused).hasClass("enter-pressed")) {
            } else {
                event.preventDefault();
            }
        }
    });
});

function save() {
    $.ajax({
        type: "POST",
        url: ajaxUrl,
        data: form.serialize(),
        success: function () {
            $('#editRow').modal('hide');
            table.ajax.reload();
            // successNoty('common.saved');
        },
        statusCode:{
            422: response422()
        }
    });
}

function response422() {
    //Nothing to do
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
                    },
                    error: function(){
                        bootbox.alert("You could not delete yourself")
                    }
                });
            }
        }
    });
}

$(document).on('shown.bs.modal', '.modal', function () {
    $(this).find('[autofocus]').focus();
});


