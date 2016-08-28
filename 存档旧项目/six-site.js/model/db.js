/*
 * Copyright (c) 1995 Sixlab. All rights reserved.
 *
 * Under the GPLv3(AKA GNU GENERAL PUBLIC LICENSE Version 3).
 * see http://www.gnu.org/licenses/gpl-3.0-standalone.html
 *
 * For more information, please see
 * http://sixlab.cn/
 */
/**
 *
 * @author 六楼的雨/loki
 * @since 1.0.0(2016/1/10)
 */
var mysql = require('mysql');
var connection = mysql.createConnection({
    host: 'localhost',
    port: 3306,
    database:'six_site',
    user: 'root',
    password: 'root'
});

module.exports.conn = connection;