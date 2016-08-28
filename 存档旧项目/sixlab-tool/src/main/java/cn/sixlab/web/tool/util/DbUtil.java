package cn.sixlab.web.tool.util;

import com.j256.ormlite.db.MysqlDatabaseType;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

public class DbUtil {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/blog?useUnicode=true&characterEncoding=UTF-8";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    public static ConnectionSource conn() throws SQLException {
        return new JdbcConnectionSource(URL, USERNAME, PASSWORD, new MysqlDatabaseType());
    }
}
