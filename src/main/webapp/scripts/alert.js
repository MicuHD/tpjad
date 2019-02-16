function clearMessages(){
    $("#message-area").hide();
    $("#message-area").html("");
}

function showMessage(messageTitle, messageBody, hasOkButton){
    $('html, body').animate({ scrollTop: 0 }, 'fast');

    var msg = "<h2>" + messageTitle + "</h2><p>" + messageBody + "</p>";
    if(hasOkButton){
        msg += "<button class='btn btn-basic btn-fill' onClick=\"location.href='index.html'\">Pagina Principal&abreve;</button>";
    }
    $("#message-area").html(msg);
    $("#message-area").fadeIn(200);
}

$(function(){
    $("#message-area").hide();
});