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

var tableName = 'tools_show';
var obj = {
    id: {type: 'serial', key: true},
    showName: {type: 'text', mapsTo:'show_name'},
    showSeason: {type: 'integer', mapsTo:'show_season'},
    showEpisode: {type: 'integer', mapsTo:'show_episode'},
    showStatus: {type: 'text', mapsTo:'show_status'},
    viewStatus: {type: 'text', mapsTo:'view_status'},
    tv: {type: 'text', mapsTo:'tv'},
    remark: {type: 'text', mapsTo:'remark'},
    doubanKey: {type: 'text', mapsTo:'douban_key'},
    beginDate: {type: 'date', mapsTo:'begin_date'},
    finishDate: {type: 'date', mapsTo:'finish_date'},
    updateDate: {type: 'date', mapsTo:'update_date'},
};
var method = {
    
};

var Model = function (db) {
    var model = db.define(tableName, obj, method);
    
    return model;
};

module.exports = Model;