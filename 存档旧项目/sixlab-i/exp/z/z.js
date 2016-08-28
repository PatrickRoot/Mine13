$(document).ready(function () {
    var isQuiet = true;
    var isShow = false;
    $(document).off("click", "#showOrHide");
    $(document).on("click", "#showOrHide", function () {
        if(isQuiet){
            if (isShow) {
                hiding();
            } else {
                showing();
            }
        }
    });

    function showing() {
        $("#noHeight").animate({"height": "200px"}, 'fast');
        $("#noHeight").animate({"height": "-=40px"}, 'slow');
        $("#noHeight").animate({"height": "200px"}, 'slow');

        $("#innerDiv").animate({"margin-top": "0px"}, 'fast');
        $("#innerDiv").animate({"margin-top": "-=40px"}, 'slow');
        $("#innerDiv").animate({"margin-top": "0px"}, 'slow', function(){
            isShow = true;
            isQuiet = true;
        });
    }

    function hiding() {
        $("#noHeight").animate({"height": "0px"}, 'fast');
        $("#noHeight").animate({"height": "+=40px"}, 'slow');
        $("#noHeight").animate({"height": "0px"}, 'slow');

        $("#innerDiv").animate({"margin-top": "-200px"}, 'fast');
        $("#innerDiv").animate({"margin-top": "+=40px"}, 'slow');
        $("#innerDiv").animate({"margin-top": "-200px"}, 'slow', function () {
            isShow = false;
            isQuiet = true;
        });
    }

});