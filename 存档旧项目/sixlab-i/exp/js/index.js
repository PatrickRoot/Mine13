$(document).ready(function () {
    pageInit();

    $(window).resize(function () {
        setCellSize();
    });

    $(window).scroll(function () {

    });

    function setCellSize() {
        var height = $(window).height();
        var width = $(window).width()/3;

        $(".block").width(width>150?width:150);
        $(".block").height(height>350?height:350);
    }

    function pageInit() {
        setCellSize();
    }
});