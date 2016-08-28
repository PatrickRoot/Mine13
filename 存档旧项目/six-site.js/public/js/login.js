/*
 * Copyright (c) 1995 Sixlab. All rights reserved.
 *
 * Under the GPLv3(AKA GNU GENERAL PUBLIC LICENSE Version 3).
 * see http://www.gnu.org/licenses/gpl-3.0-standalone.html
 *
 * For more information, please see
 * http://sixlab.cn/
 */
/**
 *
 * @author 六楼的雨/loki
 * @since 1.0.0(2016/1/10)
 */
define(function (require, exports, module) {
    $(document).ready(function () {
        $('.ui.form').form({
            fields: {
                username: {
                    identifier: 'username',
                    rules: [
                        {
                            type: 'empty',
                            prompt: '请输入用户名'
                        }
                    ]
                },
                password: {
                    identifier: 'password',
                    rules: [
                        {
                            type: 'empty',
                            prompt: '请输入密码'
                        },
                        {
                            type: 'length[6]',
                            prompt: '密码至少6位字符'
                        }
                    ]
                }
            },
            onSuccess: function () {
                $.ajax({
                    dataType: "json",
                    type: "POST",
                    url: "login",
                    data: $('.ui.form').serialize()
                }).done(function (data) {
                    console.log(data);
                }).fail(function (data) {
                    console.log(data);
                });
                return false;
            }
        });
    });
});