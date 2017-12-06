/*
 * Copyright (c) 2017 Sixlab. All rights reserved.
 *
 * Under the GPLv3(AKA GNU GENERAL PUBLIC LICENSE Version 3).
 * see http://www.gnu.org/licenses/gpl-3.0-standalone.html
 *
 * For more information, please see
 * http://sixlab.cn/
 */
var index = require('./index');
var users = require('./users');
var mini = require('./wx/mini');
var wxApp = require('./wx/wxApp');
var show = require('./tools/show');
var movie = require('./tools/movie');
var money = require('./tools/money');
var filter = require('./filter');

var routers = function (app) {

    app.use('/', index);
    app.use('/users', users);

    app.use('/wx/app', wxApp);
    app.use('/wx/mini', mini);
    app.use('/tool/money', money);

    //登录过滤器，此处之后的路由都需要登录
    app.use(filter.loginFilter);

    app.use('/tool/show', show);
    app.use('/tool/movie', movie);
};

module.exports = routers;
