var orm = require('orm');
var SixlabUser = require('./SixlabUser');
var SixlabMeta = require('./SixlabMeta');
var ToolsCodeLine = require('./ToolsCodeLine');
var ToolsHisEvent = require('./ToolsHisEvent');
var ToolsMovie = require('./ToolsMovie');
var ToolsNote = require('./ToolsNote');
var ToolsRecordItem = require('./ToolsRecordItem');
var ToolsRecordVal = require('./ToolsRecordVal');
var ToolsShow = require('./ToolsShow');
var ToolsMoneyAccount = require('./ToolsMoneyAccount');
var ToolsMoneyBill = require('./ToolsMoneyBill');

var models = orm.express("mysql://root:root@127.0.0.1/sixlab", {
    define: function (db, models, next) {
        models.SixlabUser = SixlabUser(db);
        models.SixlabMeta = SixlabMeta(db);
        models.ToolsCodeLine = ToolsCodeLine(db);
        models.ToolsHisEvent = ToolsHisEvent(db);
        models.ToolsMovie = ToolsMovie(db);
        models.ToolsNote = ToolsNote(db);
        models.ToolsRecordItem = ToolsRecordItem(db);
        models.ToolsRecordVal = ToolsRecordVal(db);
        models.ToolsShow = ToolsShow(db);
        models.ToolsMoneyAccount = ToolsMoneyAccount(db);
        models.ToolsMoneyBill = ToolsMoneyBill(db);
        next();
    }
});

module.exports = models;