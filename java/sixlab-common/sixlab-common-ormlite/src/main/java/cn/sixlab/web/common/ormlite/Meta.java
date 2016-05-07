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

import cn.sixlab.web.common.ormlite.bean.SixlabMeta;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.stmt.Where;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 六楼的雨/loki
 * @since 1.0.0
 */
public class Meta {

    private static Map<String, SixlabMeta> metaMap = new HashMap<>();

    private static SixlabMeta read(String key, Integer keyId) {
        SixlabMeta meta = null;
        try {
            Dao<SixlabMeta, ?> dao = DaoManager.createDao(DbUtil.conn(), SixlabMeta.class);
            Where<SixlabMeta, ?> queryBuilder = dao.queryBuilder().where().eq("key", key);
            if(null!=keyId){
                queryBuilder = queryBuilder.eq("key_id",keyId);
            }
            List<SixlabMeta> metaList = queryBuilder.query();
            if (null != metaList && metaList.size() > 0) {
                meta = metaList.get(0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return meta;
    }

    public static SixlabMeta get(String key, Integer keyId) {
        SixlabMeta meta = metaMap.get(key + "@" + keyId);
        if (null == meta) {
            meta = read(key, keyId);
            metaMap.put(key + "@" + keyId, meta);
        }
        return meta;
    }

    public static SixlabMeta get(String key) {
        SixlabMeta meta = metaMap.get(key + "@No Id");
        if (null == meta) {
            meta = read(key, null);
            metaMap.put(key + "@No Id", meta);
        }
        return meta;
    }

    public static SixlabMeta add(SixlabMeta meta) {
        try {
            Dao<SixlabMeta, ?> dao = DaoManager.createDao(DbUtil.conn(), SixlabMeta.class);
            dao.create(meta);
            return meta;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}