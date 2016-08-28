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
var express = require('express');
var router = express.Router();

// 该路由使用的中间件
router.use(function timeLog(req, res, next) {
    console.log('Time: ', Date.now());
    var username = req.cookies.six_username;
    if(username){
        res.cookie('six_username', username, {maxAge: 900000, httpOnly: true, path: '/'});
    }
    next();
});

var admin = require('./routes/admin');
var category = require('./routes/category');
var post = require('./routes/post');
var tag = require('./routes/tag');
var user = require('./routes/user');
var login = require('./routes/login');
var index = require('./routes/index');

router.get('/admin/', admin);
router.get('/category/', category);
router.get('/post/:postId', post);
router.get('/tag/', tag);
router.get('/user/', user);
router.get('/login', login);
router.post('/login', login);
router.get('/reg', login);
router.post('/reg', login);
router.get('/', index);

module.exports = router;