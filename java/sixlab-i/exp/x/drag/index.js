$(document).ready(function () {
    var dragging = false;
    var iX, iY;
    var $e;
    $("#container div").mousedown(function (e) {
        $e = $(this);
        dragging = true;
        iX = e.clientX - $(this).offsetLeft;
        iY = e.clientY - $(this).offsetTop;
        $(this).setCapture && $(this).setCapture();
        return false;
    });

    $("#container div").onmousemove(function (e) {
        if (dragging) {
            var e = e || window.event;
            var oX = e.clientX - iX;
            var oY = e.clientY - iY;
            $e.css({"left": oX + "px", "top": oY + "px", "position":"absolute", "cursor": "move"});
            return false;
        }
    });

    $("#container div").mouseup(function (e) {
        dragging = false;
        $e.css({ "position": "none"});
        $(this)[0].releaseCapture();
        e.cancelBubble = true;
    });

});