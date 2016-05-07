
$(document).ready(function () {

    function addAnimation(selector, time) {
        $(selector).css("animation-name", "bounceIn 5s");
        $(selector).css("-webkit-animation", "bounceIn 5s");
        setTimeout(function () {
            $(selector).css("animation", "none");
            $(selector).css("-webkit-animation", "none");
        }, time);
    }

    void function(){
        addAnimation("#s-container", 5000);
    }();

    $(document).off("click", "#s-name");
    $(document).on("click", "#s-name", function () {
        addAnimation("#s-container", 5000);
    });

    $(document).off("click", ".s-link");
    $(document).on("click", ".s-link", function () {
        var href_link = $(this).attr("href");
        $("#s-container").animate({opacity: 0},1500,"linear",function(){
            location.href = href_link;
        });
        return false;
    });

});