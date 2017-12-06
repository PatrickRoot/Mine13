/*
 * Copyright (c) 2017 Sixlab. All rights reserved.
 *
 * Under the GPLv3(AKA GNU GENERAL PUBLIC LICENSE Version 3).
 * see http://www.gnu.org/licenses/gpl-3.0-standalone.html
 *
 * For more information, please see
 * http://sixlab.cn/
 */
var express = require('express');
var router = express.Router();

/* GET users listing. */
router.get('/push', function (req, res, next) {
    
    res.send(req.query.echostr);
});

module.exports = router;
