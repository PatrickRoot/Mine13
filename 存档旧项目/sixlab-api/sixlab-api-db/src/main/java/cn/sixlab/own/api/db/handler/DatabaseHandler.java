package cn.sixlab.own.api.db.handler;

import static spark.Spark.get;

public class DatabaseHandler {

    public static void handle() {

        //增

        //删

        //改

        //查
        get("/db/id/:table/:id", Query::queryById);

        get("/db/query/:table/:condition", Query::queryByCondition);

        get("/db/size/:table/:condition", Query::querySize);
        get("/db/size/:table", Query::querySize);

        get("/db/last/:table", Query::queryLast);
    }
}
