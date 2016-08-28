/*
 * Copyright (c) 1995 Sixlab. All rights reserved.
 *
 * Under the GPLv3(AKA GNU GENERAL PUBLIC LICENSE Version 3).
 * see http://www.gnu.org/licenses/gpl-3.0-standalone.html
 *
 * For more information, please see
 * http://sixlab.cn/
 */
package cn.sixlab.web.common.nutz;

import cn.sixlab.web.common.core.Prop;
import org.nutz.dao.Dao;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.impl.SimpleDataSource;

import java.util.Properties;

/**
 * @author 六楼的雨/loki
 * @since 1.0.0
 */
public class DbUtil {

    private static String URL = null;
    private static String USERNAME = null;
    private static String PASSWORD = null;
    private static String DRIVER = null;

    public static Dao dao() {
        try {
            if (null == URL) {
                Properties properties = Prop.jdbc(DbUtil.class);
                URL = properties.getProperty("jdbc.url");
                DRIVER = properties.getProperty("jdbc.driver");
                USERNAME = properties.getProperty("jdbc.username");
                PASSWORD = properties.getProperty("jdbc.password");
            }
            SimpleDataSource ds = new SimpleDataSource();
            ds.setDriverClassName(DRIVER);
            ds.setJdbcUrl(URL);
            ds.setUsername(USERNAME);
            ds.setPassword(PASSWORD);
            Dao dao = new NutDao(ds);
            return dao;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
