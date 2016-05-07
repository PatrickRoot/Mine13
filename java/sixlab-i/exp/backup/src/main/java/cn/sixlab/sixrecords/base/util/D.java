/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @email <nianqinianyi@163.com>
 */
package cn.sixlab.sixrecords.base.util;

import org.nutz.dao.Dao;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.impl.SimpleDataSource;

/**
 * TODO
 *
 * @author 六楼的雨/loki
 * @date 2015/6/30 13:50
 */
public class D {
    public static Dao nutzDao(){
        Dao dao = null;
        try {
            String host = System.getenv("OPENSHIFT_MYSQL_DB_HOST");
            String port = System.getenv("OPENSHIFT_MYSQL_DB_PORT");
            String username = System.getenv("OPENSHIFT_MYSQL_DB_USERNAME");
            String password = System.getenv("OPENSHIFT_MYSQL_DB_PASSWORD");
            SimpleDataSource ds = new SimpleDataSource();
            ds.setDriverClassName("com.mysql.jdbc.Driver");
            ds.setJdbcUrl("jdbc:mysql://" + host + ":" + port + "/j?useUnicode=true&amp;characterEncoding=UTF-8");
            ds.setUsername(username);
            ds.setPassword(password);
            dao = new NutDao(ds);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return dao;
    }
}
