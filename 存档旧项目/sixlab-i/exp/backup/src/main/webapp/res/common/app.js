var contextPath = "/six";
var login_user_id = "login_user_id";
var login_user_name = "login_user_name";

$(document).ready(function () {
    $("a").each(function () {
        var href = $(this).attr("href");
        if (href) {
            href = contextPath + href;
            $(this).attr("href", href);
        }
    });
});

function isUrl(href) {
    if ("string" === typeof href && "" !== href) {
        var length = href.length;
        var len = href.split("#").length - 1;
        return len !== length
    }
}

function isLogin() {
    var userId = getCookie("login_user_id");
    var username = getCookie("login_user_name");
    if (typeof userId === "undefined" || null == userId || "" == userId) {
        return false;
    }
    if (typeof username === "undefined" || null == username || "" == username) {
        return false;
    }
    return true;
}

function getCookie(c_name) {
    if (document.cookie.length > 0) {
        var c_start = document.cookie.indexOf(c_name + "=");
        if (c_start != -1) {
            c_start = c_start + c_name.length + 1;
            var c_end = document.cookie.indexOf(";", c_start);
            if (c_end == -1) {
                c_end = document.cookie.length
            }
            return unescape(document.cookie.substring(c_start, c_end));
        }
    }
    return "";
}

function setCookie(c_name, value, expiredays) {
    var exData = new Date();
    exData.setDate(exData.getDate() + expiredays);
    document.cookie = c_name + "=" + escape(value) + ((expiredays == null) ? "" : "; expires=" + exData.toGMTString())
}

function checkCookie() {
    username = getCookie('username');
    if (username != null && username != "") {
        alert('Welcome again ' + username + '!')
    }
    else {
        username = prompt('Please enter your name:', "");
        if (username != null && username != "") {
            setCookie('username', username, 365)
        }
    }
}