/*
 * Copyright (c) 2017 Sixlab. All rights reserved.
 *
 * Under the GPLv3(AKA GNU GENERAL PUBLIC LICENSE Version 3).
 * see http://www.gnu.org/licenses/gpl-3.0-standalone.html
 *
 * For more information, please see
 * http://sixlab.cn/
 */
var express = require('express');
var router = express.Router();

filter = {};

filter.loginFilter = function (req, res, next) {
    var username = req.query.username;
    var password = req.query.password;
    req.models.SixlabUser.find({username: username}, function (err, user) {
        if (err) throw err;
        if (user && user.length === 1) {
            if (user[0].password == password) {
                next();
            } else {
                res.send({
                    state: -10,
                    msg: "密码错误"
                });
            }
        } else {
            res.send({
                state: -20,
                msg: "用户不存在"
            });
        }
    });
}

module.exports = filter;
