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
 * @since 1.0.0(2016/1/28)
 */
package cn.sixlab.own.api.db.handler;

import cn.sixlab.own.api.db.util.DbUtil;
import cn.sixlab.own.api.db.util.JsonMap;
import com.alibaba.fastjson.JSON;
import spark.Request;
import spark.Response;

/**
 * @author 六楼的雨/loki
 * @since 1.0.0(2016/1/28)
 */
public class Query {

    public static String queryById(Request req, Response res) {
        JsonMap json = new JsonMap();

        String table = req.params("table");
        String id = req.params("id");

        DbUtil.queryById(json, table, id);

        return JSON.toJSONString(json);
    }

    public static String queryByCondition(Request req, Response res) {
        JsonMap json = new JsonMap();

        String table = req.params("table");
        String condition = req.params("condition");
        if (null == condition || "".equals(condition)) {
            condition = " 1 = 1 ";
        }

        DbUtil.queryByCondition(json, table, condition);

        return JSON.toJSONString(json);
    }

    public static String querySize(Request req, Response res) {
        JsonMap json = new JsonMap();

        String table = req.params("table");
        String condition = req.params("condition");
        if (null == condition || "".equals(condition)) {
            condition = " 1 = 1 ";
        }

        DbUtil.querySize(json, table, condition);

        return JSON.toJSONString(json);
    }

    public static String queryLast(Request req, Response res) {
        JsonMap json = new JsonMap();

        String table = req.params("table");

        DbUtil.queryLast(json, table);

        return JSON.toJSONString(json);
    }
}
