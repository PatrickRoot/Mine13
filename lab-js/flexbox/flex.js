$(document).ready(function(){

    $(document).off("click", ".clear");
    $(document).on("click", ".clear", function () {
        $(".flex-con-1").attr("class", "flex-con flex-con-1");
        $(".flex-con-2").attr("class", "flex-con flex-con-2");
        $(".flex-item").attr("class", "flex-item");
    });

    $(document).off("click", ".row");
    $(document).on("click", ".row", function () {
        $(".flex-con-1").addClass("flex-row");
        $(".flex-con-2").addClass("flex-row-reverse");
    });

    $(document).off("click", ".column");
    $(document).on("click", ".column", function () {
        $(".flex-con-1").addClass("flex-column");
        $(".flex-con-2").addClass("flex-column-reverse");
    });

    $(document).off("click", ".wrap");
    $(document).on("click", ".wrap", function () {
        $(".flex-con-1").addClass("flex-wrap");
        $(".flex-con-2").addClass("flex-wrap");
    });

    $(document).off("click", ".wrap-reverse");
    $(document).on("click", ".wrap-reverse", function () {
        $(".flex-con-1").addClass("flex-wrap-reverse");
        $(".flex-con-2").addClass("flex-wrap-reverse");
    });

    $(document).off("click", ".just-start");
    $(document).on("click", ".just-start", function () {
        $(".flex-con-1").addClass("flex-just-start");
        $(".flex-con-2").addClass("flex-just-start");
    });

    $(document).off("click", ".just-end");
    $(document).on("click", ".just-end", function () {
        $(".flex-con-1").addClass("flex-just-end");
        $(".flex-con-2").addClass("flex-just-end");
    });

    $(document).off("click", ".just-center");
    $(document).on("click", ".just-center", function () {
        $(".flex-con-1").addClass("flex-just-center");
        $(".flex-con-2").addClass("flex-just-center");
    });

    $(document).off("click", ".just-around");
    $(document).on("click", ".just-around", function () {
        $(".flex-con-1").addClass("flex-just-around");
        $(".flex-con-2").addClass("flex-just-around");
    });

    $(document).off("click", ".just-between");
    $(document).on("click", ".just-between", function () {
        $(".flex-con-1").addClass("flex-just-between");
        $(".flex-con-2").addClass("flex-just-between");
    });

    $(document).off("click", ".items-start");
    $(document).on("click", ".items-start", function () {
        $(".flex-con-1").addClass("flex-items-start");
        $(".flex-con-2").addClass("flex-items-start");
    });

    $(document).off("click", ".items-end");
    $(document).on("click", ".items-end", function () {
        $(".flex-con-1").addClass("flex-items-end");
        $(".flex-con-2").addClass("flex-items-end");
    });

    $(document).off("click", ".items-center");
    $(document).on("click", ".items-center", function () {
        $(".flex-con-1").addClass("flex-items-center");
        $(".flex-con-2").addClass("flex-items-center");
    });

    $(document).off("click", ".items-baseline");
    $(document).on("click", ".items-baseline", function () {
        $(".flex-con-1").addClass("flex-items-baseline");
        $(".flex-con-2").addClass("flex-items-baseline");
    });

    $(document).off("click", ".items-stretch");
    $(document).on("click", ".items-stretch", function () {
        $(".flex-con-1").addClass("flex-items-stretch");
        $(".flex-con-2").addClass("flex-items-stretch");
    });
    ////

    $(document).off("click", ".content-start");
    $(document).on("click", ".content-start", function () {
        $(".flex-con-1").addClass("flex-content-start");
        $(".flex-con-2").addClass("flex-content-start");
    });

    $(document).off("click", ".content-end");
    $(document).on("click", ".content-end", function () {
        $(".flex-con-1").addClass("flex-content-end");
        $(".flex-con-2").addClass("flex-content-end");
    });

    $(document).off("click", ".content-center");
    $(document).on("click", ".content-center", function () {
        $(".flex-con-1").addClass("flex-content-center");
        $(".flex-con-2").addClass("flex-content-center");
    });

    $(document).off("click", ".content-around");
    $(document).on("click", ".content-around", function () {
        $(".flex-con-1").addClass("flex-content-around");
        $(".flex-con-2").addClass("flex-content-around");
    });

    $(document).off("click", ".content-between");
    $(document).on("click", ".content-between", function () {
        $(".flex-con-1").addClass("flex-content-between");
        $(".flex-con-2").addClass("flex-content-between");
    });

    $(document).off("click", ".content-stretch");
    $(document).on("click", ".content-stretch", function () {
        $(".flex-con-1").addClass("flex-content-stretch");
        $(".flex-con-2").addClass("flex-content-stretch");
    });

    $(document).off("click", ".px");
    $(document).on("click", ".px", function () {
        $(".flex-item").addClass("width-200");
    });

    $(document).off("click", ".py");
    $(document).on("click", ".py", function () {
        $(".flex-item:odd").addClass("height-30");
        $(".flex-item:even").addClass("height-40");
    });

    $(document).off("click", ".noxy");
    $(document).on("click", ".noxy", function () {
        $(".flex-item").removeClass("width-200");
        $(".flex-item").removeClass("height-200");
    });














});