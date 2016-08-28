var fs = require("fs");
var fileName = "count-data.txt";

+function readCount() {

    fs.exists(fileName, function (exists) {
        if (!exists) {
            writeCount(0);
            $("#count-input").val(0);
            $(".count-counter").text(0);
        }else{
            var data = fs.readFileSync(fileName, "utf-8");

            var value = 0;
            if (data) {
                value = parseInt(data);
            }
            $("#count-input").val(value);
            $(".count-counter").text(value);
        }
    });
}();

function writeCount(count) {
    var str = count+"";
    fs.writeFile(fileName, str,function(err){
        if (err)
            console.log("fail " + err);
        else
            console.log("写入文件ok");
    });
}

$(document).off("click", ".count-counter");
$(document).on("click", ".count-counter", function () {
    var count = parseInt($("#count-input").val()) + 1;
    $("#count-input").val(count);
    $(".count-counter").text(count);
    writeCount(count);
});

$(document).off("blur", "#count-input");
$(document).on("blur", "#count-input", function () {
    var count = parseInt($("#count-input").val());
    $("#count-input").val(count);
    $(".count-counter").text(count);
    writeCount(count);
});

$(document).off("click", ".count-btn");
$(document).on("click", ".count-btn", function () {
    var index = parseInt($(this).attr("index"));
    var count = parseInt($("#count-input").val()) + index;
    $("#count-input").val(count);
    $(".count-counter").text(count);
    writeCount(count);
});

$(document).off("keyup", "#count-input");
$(document).on("keyup", "#count-input", function (event) {
    if (event.keyCode == 13){
        var count = parseInt($("#count-input").val());
        $("#count-input").val(count);
        $(".count-counter").text(count);
        writeCount(count);
    }else if(event.keyCode == 38){
        // history.go(0);
        $.ajax({
            url: "index.html",
            dataType: "html"
        }).done(function (html) {
            $("#s-container").html(html);
        });
    }
});