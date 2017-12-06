/*
 * Copyright (c) 2016 Sixlab. All rights reserved.
 *
 * Under the GPLv3(AKA GNU GENERAL PUBLIC LICENSE Version 3).
 * see http://www.gnu.org/licenses/gpl-3.0-standalone.html
 *
 * For more information, please see
 * http://sixlab.cn/
 */
var orm = require('orm');

var tableName = 'sixlab_user';
var obj = {
    id: {type: 'serial', key: true},
    username: {type: 'text', mapsTo:'username'},
    password: {type: 'text', mapsTo:'password'},
    token: {type: 'text', mapsTo:'token'},
};
var method = {
    
};

var Model = function (db) {
    var model = db.define(tableName, obj, method);
    
    return model;
};

module.exports = Model;