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

var tableName = 'tools_movie';
var obj = {
    id: {type: 'serial', key: true},
    movieName: {type: 'text', mapsTo:'movie_name'},
    produceYear: {type: 'text', mapsTo:'produce_year'},
    director: {type: 'text', mapsTo:'director'},
    remark: {type: 'text', mapsTo:'remark'},
    viewDate: {type: 'date', mapsTo:'view_date'},
    doubanKey: {type: 'text', mapsTo:'douban_key'},
    doubanScore: {type: 'number', mapsTo:'douban_score'},
    doubanInfo: {type: 'text', mapsTo:'douban_info'},
    infoStatus: {type: 'text', mapsTo:'info_status'},
};
var method = {
    
};

var Model = function (db) {
    var model = db.define(tableName, obj, method);
    
    return model;
};

module.exports = Model;