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
router.get('/', function (req, res, next) {
    res.send('respond with a resource');
});

router.get('/accountList', function (req, res, next) {
    req.models.ToolsMoneyAccount.find().order('accountGroup').order('accountOrder').order('-id').all(function (err, accountList) {
        var result = {};
        if(err){
            result.success = 0;
            result.msg = 'MySQL error';
        }else{
            result.success = 1;
            result.data = accountList;
        }
        res.send(result);
    });
});

router.get('/groupList', function (req, res, next) {
    req.models.ToolsMoneyAccount.aggregate(["account_group"]).count().groupBy("account_group").get(function (err, accountList) {
        console.log(err);
        var result = {};
        if (err) {
            result.success = 0;
            result.msg = 'MySQL error';
        } else {
            result.success = 1;
            result.data = accountList;
        }
        res.send(result);
    });
});

/*
 //The same as "select avg(weight), age from person where country='someCountry' group by age;"
 
 Person.aggregate(["age"], { country: "someCountry" }).avg("weight").groupBy("age").get(function (err, stats) {
 
 req.models.SixlabUser.find().where("password LIKE ?", ['sixlab%']).order('token').order("-id").all(function (err, accountList) {
 console.log(err);
 res.send(accountList);
 });
 
 */
module.exports = router;
