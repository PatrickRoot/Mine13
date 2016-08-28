package cn.sixlab.web.tool;

import static spark.Spark.port;

public class Tool {
    public static void main(String[] args) {
        init();

        ListTool.handler();
        Record.handler();
    }

    private static void init() {
        port(9300);


    }
}
