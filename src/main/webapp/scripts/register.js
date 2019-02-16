var errorMsgFullname = "Username-ul nu poate să fie nul";
var errorMsgPasswd = "Parola trebuie s&abreve; conțină cel puțin 8 caractere, din care minim unul special (@$!%*#?&)";
var errorMsgPasswdConf = "Parolele introduse trebuie sa fie identice";
var allErrors = "";
function clearErrors() {
    allErrors = "";
    $("#errors-fullname").empty();
    $("#errors-password").empty();
    $("#errors-password-confirm").empty();
}
function validate() {
    clearErrors();
    var valid = true;
    if (!$("#fullname").val()) {
        $("#errors-fullname").html(errorMsgFullname);
        allErrors += errorMsgFullname + "<br>";
        valid = false;
    }
    var password = $("#password").val();
    if (!(/[A-Za-z]{6,}$/.test(password))) {
        $("#errors-password").html(errorMsgPasswd);
        allErrors += errorMsgPasswd + "<br>";
        valid = false;
    }
    var passwordConfirm = $("#password-confirm").val();
    if (password !== passwordConfirm){
        $("#errors-password-confirm").html(errorMsgPasswdConf);
        allErrors += errorMsgPasswdConf + "<br>";
        valid = false;
    }
    return valid;
}
$(function () {
    $("#btn-submit").click(function () {
        if (validate()) {
            var username = $("#fullname").val();
            var password = $("#password").val();
            var user = {
                username: username,
                password: password
            };
            $.ajax({
                type: "POST",
                url: "/api/user/register",
                data: JSON.stringify(user),
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                success: function (result) {
                    window.location.href = "/index.html";
                },
                error: function (xhr, status, text) {
                    showMessage("Eroare", xhr.responseText + "<br>Nu s-a putut &icirc;nregistra contul.<br>", false);
                }
            });
        }
        else {
            showMessage("Eroare","Nu a&tcedil;i completat corect toate c&acirc;mpurile necesare:<br>" + allErrors, false);
        }
    });
});