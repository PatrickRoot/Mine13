/*
 * Copyright (c) 1995 Sixlab. All rights reserved.
 *
 * Under the GPLv3(AKA GNU GENERAL PUBLIC LICENSE Version 3).
 * see http://www.gnu.org/licenses/gpl-3.0-standalone.html
 *
 * For more information, please see
 * http://sixlab.cn/
 */
package cn.sixlab.web.common.ormlite;

import cn.sixlab.web.common.core.Prop;
import com.j256.ormlite.db.MysqlDatabaseType;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author 六楼的雨/loki
 * @since 1.0.0
 */
public class DbUtil {

    private static String URL = null;
    private static String USERNAME = null;
    private static String PASSWORD = null;

    public static ConnectionSource conn(){
        try {
            if (null == URL) {
                Properties properties = Prop.jdbc(DbUtil.class);
                URL = properties.getProperty("jdbc.url");
                USERNAME = properties.getProperty("jdbc.username");
                PASSWORD = properties.getProperty("jdbc.password");
            }
            return new JdbcConnectionSource(URL, USERNAME, PASSWORD, new MysqlDatabaseType());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
