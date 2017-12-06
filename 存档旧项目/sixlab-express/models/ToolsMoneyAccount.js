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

var tableName = 'tools_money_account';
var obj = {
    id: {type: 'serial', key: true},
    accountName: {type: 'text', mapsTo:'account_name'},
    accountType: {type: 'text', mapsTo:'account_type'},
    accountDesc: {type: 'text', mapsTo:'account_desc'},
    creditLimit: {type: 'integer', mapsTo:'credit_limit'},
    isDeleted: {type: 'text', mapsTo:'is_deleted'},
    accountGroup: {type: 'text', mapsTo:'account_group'},
    accountOrder: {type: 'integer', mapsTo:'account_order'},
};
var method = {
    
};

var Model = function (db) {
    var model = db.define(tableName, obj, method);
    
    return model;
};

module.exports = Model;