$(document).ready(function () {

    var validate = $("#frm").validate({
        rules: {
            username: {
                required: true,
                remote:{
                    url: "checkUsername.lab",
                    data: {'username':function(){return $("#username").val();}},
                    type: "post"
                }
            },
            password: {required: true},
            rePwd: {required: true}
        },
        messages:{
            username:{
                remote:"存在"
            }
        }
    });

    var submitHandle = {
        dataType: 'json',
        success: function (data) {
            if (data.success == 1) {
                location.href = contextPath + "/";
            }
        }
    };

    $(document).off("click", "#submitBtn");
    $(document).on("click", "#submitBtn", function () {
        var check = validate.form();
        if (!check) {
            validate.focusInvalid();
            return false;
        }
        //var username = $("#username").val();
        //var password = $("#username").val();
        //var rePwd = $("#rePwd").val();
        //$.ajax({
        //    url:contextPath+"/user/submitReg.lab",
        //    dataType:"json",
        //    data:{
        //        username: username,
        //        password: password,
        //        rePwd:rePwd
        //    },
        //    success: function (data) {
        //        if("1"==data.success){
        //            location.href = contextPath + "/";
        //        }
        //    },
        //    complete: function (data) {
        //        console.log(data);
        //    }
        //});
        $("#frm").ajaxSubmit(submitHandle);
    });

});