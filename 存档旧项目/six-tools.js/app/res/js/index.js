
var tpl = $("#s-container-top-block-tpl").html();
for (var toolIndex in tools) {
    var html = juicer(tpl, tools[toolIndex]);
    $("#s-container").append(html);
}

$(document).off("click", ".s-container-block");
$(document).on("click", ".s-container-block", function () {
    var dataPath = this.dataset.path;
    $.ajax({
        url: "tools/" + dataPath + "/index.html",
        dataType: "html"
    }).done(function (html) {
        $("#s-container").html(html);
    });
});