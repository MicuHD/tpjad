$(document).ready(function() {

    var placeholder = document.createElement("img");
    placeholder.src="../images/placeholder.png";
    $('.upload-image-preview').html(placeholder);

    $("input[name=file]").change(function () {
        if (this.files && this.files[0]) {
            var reader = new FileReader();

            reader.onload = function (e) {
                var img = $('<img>').attr('src', e.target.result);


                if(img.width() <=500 && img.height() <=500 )
                    $('.upload-image-preview').html(img);
                else $('.upload-image-preview').html("image to big for preview");
            };

            reader.readAsDataURL(this.files[0]);
        }
    });


});



$(document).ready(function() {
    $("#save-image").click(function(event){

        var form = $('#localStorageTest')[0];

        var data = new FormData(form);
        data.append("description", $("#descriere").val());

        // disabled the submit button
        $("#save-image").prop("disabled", true);

        $.ajax({
            type: "POST",
            enctype: 'multipart/form-data',
            url: "/api/user/image/save",
            data: data,
            processData: false,
            contentType: false,
            cache: false,
            timeout: 600000,
            success: function (data) {

                $("#result").text(data);
                console.log("SUCCESS : ", data);
                $("#save-image").prop("disabled", false);

            },
            error: function (e) {

                $("#result").text(e.responseText);
                console.log("ERROR : ", e);
                $("#save-image").prop("disabled", false);

            }
        });

    });
});



$(function () {
    $('.pics').glisse({
        changeSpeed: 550,
        speed: 500,
        effect:'bounce',
        fullscreen: true
    });
});

$(document).ready(function() {
    $("#view-photos").click(function(event){





        // disabled the submit button
        $("#view-photos").prop("disabled", true);

        $.ajax({
            type: "GET",
            dataType: 'json',
            url: "/api/user/images/getAll",
            contentType: 'application/json; charset=utf-8',
            cache: false,
            timeout: 600000,
            success: function (data) {
                var items = [];

                for (var i = 0; i < data.length; i++) {
                    // outputfromserver[i] can be used to get each value

                    items.push('<img src="' + data[i].path + '" title="' + data[i].description+'"/>');
                    items.push('<div class="desc-img">' +  data[i].description+'</>');
                    items.push('<button  class="dynamicdelete" id="' + data[i].id +'"  > delete </button>');
                    items.push('<button class="dynamicupdate"> update </button>');






                }


               $('#album-view').html(items);

                $("#album-log").text(items);
                console.log("SUCCESS : ", data);
                $("#view-photos").prop("disabled", false);

            },
            error: function (e) {

                $(".album-log").text(e.responseText);
                console.log("ERROR : ", e);
                $("#view-photos").prop("disabled", false);

            }
        });

    });


});


$(document).on('click','.dynamicdelete',function(e){


    $("#album-log").text(e.target.id);
    data  = e.target.id;

    $.ajax({
        type: "DELETE",
        url: "/api/user/images/delete/"+data,
        success: function (data) {

            $("#result").text(data);
            console.log("SUCCESS : ", data);
            $('.dynamicdelete').prop("disabled", false);
            $('#view-photos').trigger('click');

        },
        error: function (e) {

            $("#result").text(e.responseText);
            console.log("ERROR : ", e);
            $(".dynamicdelete").prop("disabled", false);

        }


});

})



