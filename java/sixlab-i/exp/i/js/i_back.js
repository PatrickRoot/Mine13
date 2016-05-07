
$(document).ready(function () {

    var current = 1;
    var sectionHeight = 0;
    function setSectionSize() {
        sectionHeight = $(window).height();
        sectionHeight < 400 ? sectionHeight = 400 : '';
        sectionHeight > 1000 ? sectionHeight = 1000 : '';
        $(".s-section").height(sectionHeight);
    }

    function addAnimation(selector, time) {
        $(selector).css("animation-name", "bounceIn 5s");
        $(selector).css("-webkit-animation", "bounceIn 5s");
        setTimeout(function () {
            $(selector).css("animation", "none");
            $(selector).css("-webkit-animation", "none");
        }, time);
    }

    void function(){
        setSectionSize();
        addAnimation("#s-section1 .bounceIn", 5000);
    }();

    $(window).resize(function () {
        setSectionSize();
    });

    $(window).scroll(function () {
        var scrollTop = $(window).scrollTop();

        if (scrollTop <= 0) {
            $("#s-top-navbar").addClass("s-nav-default-height");
            $("#s-top-navbar").removeClass("s-nav-background-color");
        } else {
            $("#s-top-navbar").addClass("s-nav-background-color");
            $("#s-top-navbar").removeClass("s-nav-default-height");
        }

        if (scrollTop > 0 && scrollTop <= sectionHeight) {

        } else if (scrollTop > sectionHeight && scrollTop <= sectionHeight * 2) {

        }

    });

    $(document).off("click", ".tabTitle");
    $(document).on("click", ".tabTitle", function () {
        $(this).blur();
        var index = $(this).parent().index();
        var element = $($(".s-section")[index]);
        $("html,body").animate({
            scrollTop: element.offset().top
        }, 1200);
        addAnimation(element.find(".bounceIn"), 5000);
        return false;
    });
});