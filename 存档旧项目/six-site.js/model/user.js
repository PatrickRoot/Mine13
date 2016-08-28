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
var db = require('./db');
var crypto = require('crypto');
var conn = db.conn;

module.exports = {
    getByUsername:getByUsername,
    addUser:addUser
};

function addUser(username, password) {
    var md5sum = crypto.createHash('md5');
    md5sum.update(password);
    password = md5sum.digest('hex');
    var sql = 'INSERT INTO six_site_users(username, password) VALUES(?,?)';

    var user;
    db.conn.connect();
    conn.query(sql, [username, password], function (err, rows, fields) {
        if (err) throw err;
        user = rows[0];
        console.log(rows);
    });
    db.conn.end();

    return user;
};

function getByUsername(username) {
    conn.connect();
    var sql = 'SELECT * FROM six_site_users where username=?';

    var user;
    conn.query(sql,[username], function (err, rows, fields) {
        if (err) throw err;
        user = rows[0];
        console.log(user);
    });

    conn.end();
    return user;
};