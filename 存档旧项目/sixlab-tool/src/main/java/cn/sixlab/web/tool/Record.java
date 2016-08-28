/*
 * Copyright (c) 2016 Sixlab. All rights reserved.
 *
 * Under the GPLv3(AKA GNU GENERAL PUBLIC LICENSE Version 3).
 * see http://www.gnu.org/licenses/gpl-3.0-standalone.html
 *
 * For more information, please see
 * http://sixlab.cn/
 * 
 * @author 六楼的雨/loki
 * @since 1.0.0(2016/3/8)
 */
package cn.sixlab.web.tool;

import cn.sixlab.web.tool.beans.ToolsRecordItem;
import cn.sixlab.web.tool.beans.ToolsRecordVal;
import cn.sixlab.web.tool.util.DbUtil;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.freemarker.FreeMarkerEngine;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

/**
 * @author 六楼的雨/loki
 * @since 1.0.0(2016/3/8)
 */
public class Record {

    public static void handler() {
        //列出所有的时间
        get("/record/times", (req, resp) -> queryTimes("record/times.ftl", req, resp), new FreeMarkerEngine());

        //比较两个
        post("/record/compare", (req, resp) -> compare("record/compare.ftl", req, resp), new FreeMarkerEngine());
        //
        ////添加一个list或者修改一个list
        //get("/list/edit/list/*", (req, resp) -> editList("list/list.ftl", req, resp), new FreeMarkerEngine());
        //
        ////添加一个Group或者修改一个Group
        //get("/list/edit/group/*", Record::editGroup);
        //
        ////添加一个item或者修改一个item
        //get("/list/edit/item/*", Record::editItem);
        //
        ////删除一个list
        //get("/list/del/list/*", Record::delList);
        //
        ////删除一个Group
        //get("/list/del/group/*", Record::delGroup);
        //
        ////删除一个Item
        //get("/list/del/item/*", Record::delItem);
        //
        ////对一个list中所有内容重新排序
        //get("/list/sort/*", Record::sort);
    }

    public static void main(String[] args) throws SQLException {
        ConnectionSource conn = DbUtil.conn();
        Dao<ToolsRecordVal, ?> valDao = DaoManager.createDao(conn, ToolsRecordVal.class);
        Dao<ToolsRecordItem, ?> itemDao = DaoManager.createDao(conn, ToolsRecordItem.class);

        //itemDao.queryBuilder().leftJoin()


        conn.close();
    }

    private static ModelAndView compare(String path, Request req, Response resp)
            throws SQLException {
        Map<String, Object> model = new HashMap<>();

        ConnectionSource conn = DbUtil.conn();
        Dao<ToolsRecordItem, ?> itemDao = DaoManager.createDao(conn, ToolsRecordItem.class);

        List<ToolsRecordItem> items = itemDao.queryBuilder().orderBy("order", true).query();


        Dao<ToolsRecordVal, ?> valDao = DaoManager.createDao(conn, ToolsRecordVal.class);

        List<ToolsRecordVal> results = valDao.queryBuilder().orderBy("record_date", true).groupBy("record_date").query();

        conn.close();

        model.put("times", results);

        return new ModelAndView(model, path);
    }

    private static ModelAndView queryTimes(String path, Request req, Response resp)
            throws SQLException {
        Map<String, Object> model = new HashMap<>();

        ConnectionSource conn = DbUtil.conn();

        Dao<ToolsRecordVal, ?> dao = DaoManager.createDao(conn, ToolsRecordVal.class);

        List<ToolsRecordVal> results = dao.queryBuilder().orderBy("record_date", true).
                orderBy("id", true).groupBy("record_date").query();
    
        conn.close();

        model.put("times", results);

        return new ModelAndView(model, path);
    }

}
