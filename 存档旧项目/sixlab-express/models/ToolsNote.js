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

var tableName = 'tools_note';
var obj = {
    id: {type: 'serial', key: true},
    title: {type: 'text', mapsTo:'title'},
    content: {type: 'text', mapsTo:'content'},
    time: {type: 'date', mapsTo:'time'},
    begin: {type: 'date', mapsTo:'begin'},
    end: {type: 'date', mapsTo:'end'},
    oneLevel: {type: 'text', mapsTo:'one_level'},
    twoLevel: {type: 'text', mapsTo:'two_level'},
};
var method = {
    
};

var Model = function (db) {
    var model = db.define(tableName, obj, method);
    
    return model;
};

module.exports = Model;