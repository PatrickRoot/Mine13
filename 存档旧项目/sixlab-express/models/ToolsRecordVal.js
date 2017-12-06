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

var tableName = 'tools_record_val';
var obj = {
    id: {type: 'serial', key: true},
    itemId: {type: 'integer', mapsTo:'item_id'},
    recordDate: {type: 'date', mapsTo:'record_date'},
    recordVal: {type: 'text', mapsTo:'record_val'},
};
var method = {
    
};

var Model = function (db) {
    var model = db.define(tableName, obj, method);
    
    return model;
};

module.exports = Model;