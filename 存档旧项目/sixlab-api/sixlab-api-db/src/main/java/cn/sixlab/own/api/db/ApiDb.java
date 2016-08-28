package cn.sixlab.own.api.db;

import cn.sixlab.own.api.db.handler.DatabaseHandler;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class ApiDb {
    public static void main(String[] args) {
        port(9610);

        get("/", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            return new ModelAndView(attributes, "404.ftl");
        }, new FreeMarkerEngine());

        DatabaseHandler.handle();

        exception(Exception.class, (e, request, response) -> {
            response.redirect("/");
        });
    }
}
