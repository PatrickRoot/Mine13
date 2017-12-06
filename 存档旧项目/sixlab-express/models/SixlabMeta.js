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

var tableName = 'sixlab_meta';
var obj = {
    id: {type: 'serial', key: true},
    keyId: {type: 'integer', mapsTo:'key_id'},
    metaKey: {type: 'text', mapsTo:'meta_key'},
    metaVal: {type: 'text', mapsTo:'meta_val'},
    metaFlag: {type: 'text', mapsTo:'meta_flag'},
    metaRemark: {type: 'text', mapsTo:'meta_remark'},
};
var method = {
    
};

var Model = function (db) {
    var model = db.define(tableName, obj, method);
    
    return model;
};

module.exports = Model;