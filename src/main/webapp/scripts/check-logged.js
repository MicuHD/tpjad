var promiseLog = $.when($.get("/api/user/logged").done(function (res) {
    if (res === -1) {
        window.location.href = "index.html";
    } else {
        window.userID = res;
    }
}));

$(function() {
    var profile = $("#profile");
    var space = "&nbsp;&nbsp;&nbsp;&nbsp;";
    promiseLog.then(function() {
        $.get("/api/user/get/" + userID).done(function(res) {
            profile.html($("<span>").html(space + "Sunte&tcedil;i conectat ca " + res.name + "!" + space));

            var btnLogout = $("<a href='#'><span class='glyphicon glyphicon-log-out'></span></a>");
            profile.append(btnLogout);
            profile.append(space);
            btnLogout.click(function() {
                $.get("/api/user/logout").done(function () {
                    // showMessage("Informa&tcedil;ie","A&tcedil;i ie&scedil;it cu succes din sistem.", true);
                    window.location.replace("index.html");
                });
            });

        }).fail(function() {
            window.location.replace("index.html");
            profile.html($("<span>").html("Nu sunte&tcedil;i conectat. :/"));
        })
    });
});