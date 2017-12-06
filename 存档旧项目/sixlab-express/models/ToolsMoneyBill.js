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

var tableName = 'tools_money_bill';
var obj = {
    id: {type: 'serial', key: true},
    accountId: {type: 'integer', mapsTo:'account_id'},
    billTitle: {type: 'text', mapsTo:'bill_title'},
    fromAccountId: {type: 'integer', mapsTo:'from_account_id'},
    moneyType: {type: 'integer', mapsTo:'money_type'},
    billMoney: {type: 'integer', mapsTo:'bill_money'},
    billDate: {type: 'date', mapsTo:'bill_date'},
    billOrder: {type: 'integer', mapsTo:'bill_order'},
    billDesc: {type: 'text', mapsTo:'bill_desc'},
    isInit: {type: 'integer', mapsTo:'is_init'},
    attr1: {type: 'text', mapsTo:'attr1'},
    attr2: {type: 'text', mapsTo:'attr2'},
    attr3: {type: 'text', mapsTo:'attr3'},
    attr4: {type: 'text', mapsTo:'attr4'},
};
var method = {
    
};

var Model = function (db) {
    var model = db.define(tableName, obj, method);
    
    return model;
};

module.exports = Model;