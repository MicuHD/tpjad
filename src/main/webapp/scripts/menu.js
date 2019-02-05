$(document).ready(function() {
    $("#login").click(function(event){
        $('#menu').load('login.html');
    });
});

$(document).ready(function() {
    $("#register").click(function(event){
        $('#menu').load('register.html');
    });
});

$(document).ready(function() {
    $("#loginServer").click(function(event){
        $('#display').load('login.html');
    });
});