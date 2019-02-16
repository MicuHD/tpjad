function clearErrors() {
    $("#errors-username").empty();
    $("#errors-password").empty();
}
function validate() {
    clearErrors();
    var valid = true;
    var username = $("#username").val();
    if (!username) {
        $("#errors-username").html("Username-ul introdus nu este corect");
        valid = false;
    }
    if (!$("#password").val()) {
        $("#errors-password").html("Parola nu poate să fie nulă");
        valid = false;
    }
    return valid;
}


function doLogin(){
    if (validate()) {
        var username = $("#username").val();
        var passwd = $("#password").val();
        var user = {
            username: username,
            password: passwd
        };
        $.ajax({
            type:"POST",
            url:"/api/user/login",
            data: JSON.stringify(user),
            contentType:"application/json; charset=utf-8",
            dataType: "json",
            success: function(){
                window.location.href = "/album.html";
            },
            error: function(){
                showMessage("Eroare","Cont blocat sau credențiale greșite", false);
            }
        });
    }
    else {
        showMessage("Eroare","Nu ați completat corect toate câmpurile necesare!", false);
        //alert("Nu ați completat corect toate câmpurile necesare!");
    }
}
$(document).ready(function () {
    window.onkeydown = function (e) {
        var code = e.keyCode ? e.keyCode : e.which;
        if (code === 13) { //enter
            doLogin();
        }
    };

    $("#btn-submit").click(function () {
        doLogin();
    });
});