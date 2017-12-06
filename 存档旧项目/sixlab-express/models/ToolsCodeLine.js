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

var tableName = 'tools_code_line';
var obj = {
    id: {type: 'serial', key: true},
    checkDate: {type: 'date', mapsTo:'check_date'},
    allNum: {type: 'integer', mapsTo:'all_num'},
    codeNum: {type: 'integer', mapsTo:'code_num'},
    codeType: {type: 'text', mapsTo:'code_type'},
};
var method = {
    
};

var Model = function (db) {
    var model = db.define(tableName, obj, method);
    
    return model;
};

module.exports = Model;