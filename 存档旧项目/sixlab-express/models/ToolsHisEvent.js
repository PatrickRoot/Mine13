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

var tableName = 'tools_his_event';
var obj = {
    id: {type: 'serial', key: true},
    event: {type: 'text', mapsTo:'event'},
    eventType: {type: 'text', mapsTo:'event_type'},
    keyId: {type: 'integer', mapsTo:'key_id'},
    eventRemark: {type: 'text', mapsTo:'event_remark'},
    eventDate: {type: 'date', mapsTo:'event_date'},
};
var method = {
    
};

var Model = function (db) {
    var model = db.define(tableName, obj, method);
    
    return model;
};

module.exports = Model;