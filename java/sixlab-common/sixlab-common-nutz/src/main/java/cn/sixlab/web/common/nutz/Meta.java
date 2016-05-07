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

import cn.sixlab.web.common.nutz.bean.SixlabMeta;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;

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

        Dao dao = DbUtil.dao();
        Cnd cnd = Cnd.where("key", "=", key);
        if (null != keyId) {
            cnd = cnd.and("key_id", "=", keyId);
        }
        List<SixlabMeta> metaList = dao.query(SixlabMeta.class, cnd);
        if (null != metaList && metaList.size() > 0) {
            meta = metaList.get(0);
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
        Dao dao = DbUtil.dao();
        dao.insert(meta);
        return meta;
    }
}
