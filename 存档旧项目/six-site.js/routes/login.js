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
 * @since 1.0.0
 */
var user = require('../model/user');
var express = require('express');
var router = express.Router();

router.use(function timeLog(req, res, next) {
    console.log('Time: ', Date.now());
    var username = req.cookies.six_username;
    if (username) {
        res.redirect('/');
    }else{
        next();
    }
});

router.get('/login', getLogin);
router.post('/login', postLogin);
router.get('/reg', getReg);
router.post('/reg', postReg);

module.exports = router;

function getLogin(req, res) {
    res.render('login');
};

function getReg(req, res) {
    res.render('reg');
};

function postLogin(req, res) {
    var username = req.body.username;
    var password = req.body.password;
    res.send({username: username, password:password});
};

function postReg(req, res) {
    var username = req.body.username;
    var password = req.body.password;
    var newUser = user.addUser(username, password);
    res.send({username: username, password: password, user: newUser});
};