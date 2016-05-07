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
package cn.sixlab.own.api.db.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 六楼的雨/loki
 * @since 1.0.0(2016/1/28)
 */
public class DbUtil {

    private static String url;

    static {
        url = "jdbc:mysql://127.0.0.1:3306/blog?useUnicode=true&characterEncoding=UTF-8";
        url += "&user=root";
        url += "&password=root";
    }

    public static boolean queryById(JsonMap json, String table, String id){
        try {

            Connection conn = DriverManager.getConnection(url);

            String sql = " select * from "+table+" where id = ? ";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, id);

            ResultSet rs = preparedStatement.executeQuery();
            json.put("data", getMaps(rs));

            rs.close();
            preparedStatement.close();
            conn.close();

            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public static boolean queryByCondition(JsonMap json, String table, String condition) {
        try {
            Connection conn = DriverManager.getConnection(url);

            String sql = " select * from " + table + " where ? ";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, condition);

            ResultSet rs = preparedStatement.executeQuery();
            json.put("data", getMaps(rs));

            rs.close();
            preparedStatement.close();
            conn.close();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean querySize(JsonMap json, String table, String condition) {
        try {
            Connection conn = DriverManager.getConnection(url);

            String sql = " select COUNT(1) from " + table + " where ? ";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, condition);

            ResultSet rs = preparedStatement.executeQuery();

            int data = 0;
            while (rs.next()) {
                data = rs.getInt(1);
            }

            json.put("data", data);

            rs.close();
            preparedStatement.close();
            conn.close();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean queryLast(JsonMap json, String table) {
        try {
            Connection conn = DriverManager.getConnection(url);

            String sql = " select * from " + table + " limit 1 ORDER BY id DESC ";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            ResultSet rs = preparedStatement.executeQuery();
            json.put("data", getMaps(rs));

            rs.close();
            preparedStatement.close();
            conn.close();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean execute(JsonMap json, String sql) {
        try {
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();

            json.put("data", stmt.execute(sql));

            stmt.close();
            conn.close();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static List<Map<String, Object>> getMaps(ResultSet rs) throws SQLException {
        List<Map<String, Object>> list = new ArrayList<>();

        ResultSetMetaData m = rs.getMetaData();
        int columns = m.getColumnCount();

        while (rs.next()) {
            Map<String, Object> obj = new HashMap<>();
            for (int i = 1; i <= columns; i++) {
                String columnName = m.getColumnName(i);
                obj.put(columnName, rs.getObject(i));
            }
            list.add(obj);
        }

        return list;
    }
}
