$(document).ready(function() {
    $("#login").click(function(event){
        $('#stage').load('api/photos/getAll');
    });
});

