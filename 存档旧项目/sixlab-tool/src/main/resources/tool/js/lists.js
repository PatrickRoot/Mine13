$(document).ready(function () {

    $(document).off("click", "#addListBtn");
    $(document).on("click", "#addListBtn", function () {
        var listName = $("#listName").val();
        location.href = "/list/edit/list/" + listName;
    });

    $(document).off("click", ".delListBtn");
    $(document).on("click", ".delListBtn", function () {
        var listName = $(this).attr("data-list-name");
        $.ajax({
            url: "/list/del/list/" + listName,
            type: "post",
            success: function (data) {
                if (data.success) {
                    location.reload();
                }
            }
        });
    });

});