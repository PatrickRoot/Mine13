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

/* GET users listing. */
router.get('/', function (req, res, next) {
    res.send('respond with a resource');
});

router.get('/login', function (req, res, next) {
    var username = req.query.username;
    var password = req.query.password;
    req.models.SixlabUser.find({username: username}, function (err, user) {
        if (err) throw err;
        if (user && user.length === 1) {
            if (user[0].password == password) {
                res.send('login');
            } else {
                res.send('password error');
            }
        } else {
            res.send('user not exist');
        }
    });
});

module.exports = router;
