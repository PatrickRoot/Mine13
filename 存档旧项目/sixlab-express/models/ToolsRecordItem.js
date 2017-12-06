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

var tableName = 'tools_record_item';
var obj = {
    id: {type: 'serial', key: true},
    itemName: {type: 'text', mapsTo:'item_name'},
    itemOrder: {type: 'integer', mapsTo:'item_order'},
    isDel: {type: 'text', mapsTo:'is_del'},
    remark: {type: 'text', mapsTo:'remark'},
};
var method = {
    
};

var Model = function (db) {
    var model = db.define(tableName, obj, method);
    
    return model;
};

module.exports = Model;