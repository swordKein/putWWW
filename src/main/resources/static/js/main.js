$(document).ready(function () {

    $("#btnSubmit").click(function (event) {
        event.preventDefault();
        fire_ajax_submit();
    });

    $("#files").on("change", function(){
        event.preventDefault();
        fire_ajax_submit();
    });

});

function fire_ajax_submit() {

    // Get form
    var form = $('#fileUploadForm')[0];

    var data = new FormData(form);

    data.append("CustomField", "This is some extra data, testing");

    $("#btnSubmit").prop("disabled", true);

    $.ajax({
        type: "POST",
        enctype: 'multipart/form-data',
        url: "/api/read",
        //url: "/api/upload/multi",
        data: data,
        //http://api.jquery.com/jQuery.ajax/
        //https://developer.mozilla.org/en-US/docs/Web/API/FormData/Using_FormData_Objects
        processData: false, //prevent jQuery from automatically transforming the data into a query string
        contentType: false,
        cache: false,
        timeout: 600000,
        success: function (data) {

            $("#result").text(data);
            console.log("SUCCESS : ", data);
            $("#txt_view_byte").html(data.readbyte);
            $("#txt_view_utf8").html(data.strutf8);
            $("#txt_view_cp949").html(data.strcp949);
            $("#txt_view_hdfs").html(data.strutf8);

            $("#btnSubmit").prop("disabled", false);

        },
        error: function (e) {

            $("#result").text(e.responseText);
            console.log("ERROR : ", e);
            $("#btnSubmit").prop("disabled", false);

        }
    });

}