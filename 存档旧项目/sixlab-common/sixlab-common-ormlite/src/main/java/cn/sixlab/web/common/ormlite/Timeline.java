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

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;

import java.sql.SQLException;

/**
 * @author 六楼的雨/loki
 * @since 1.0.0
 */
public class Timeline {

    public static void add(Timeline timeline){
        try {
            Dao<Timeline, ?> dao = DaoManager.createDao(DbUtil.conn(), Timeline.class);
            dao.create(timeline);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
