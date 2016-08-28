$(document).ready(function () {
    var index = 1;
    pageInit();

    $(window).resize(function(){
        setCellSize();
    });

    $(document).off("click", ".cell");
    $(document).on("click", ".cell", function () {
        getNewIndex();
        console.log($(".cell[index='" + index + "']"));
        console.log($(".cell[index='" + index + "']").offset());
        $("html,body").animate({
            scrollTop: $(".cell[index='" + index + "']").offset().top-10,
            scrollLeft: $(".cell[index='" + index + "']").offset().left-10
        }, 1500);
    });

    function setCellSize() {
        var height = $(window).height();
        var width = $(window).width();
        $("#container").width(width * 3 + 16);
        $("#container").height(height * 3 + 16);
        $(".cell").width(width-30);
        $(".cell").height(height-30);
        console.log($(".cell[index='" + index + "']"));
        console.log($(".cell[index='" + index + "']").offset());
        $("html,body").scrollTop($(".cell[index='" + index + "']").offset().top - 10);
        $("html,body").scrollLeft($(".cell[index='" + index + "']").offset().left - 10);
    }

    function pageInit(){
        setCellSize();
    }

    function getNewIndex(){
        var newIndex = Math.floor(Math.random() * 10 + 1);
        if (newIndex > 9) {
            newIndex = 9;
        } else if (newIndex < 1) {
            newIndex = 1;
        }

        if(newIndex == index){
            getNewIndex()
        }else{
            index = newIndex;
        }
    }
});