/*
 * Copyright (c) 1995 Sixlab. All rights reserved.
 *
 * Under the GPLv3(AKA GNU GENERAL PUBLIC LICENSE Version 3).
 * see http://www.gnu.org/licenses/gpl-3.0-standalone.html
 *
 * For more information, please see
 * http://sixlab.cn/
 */
package cn.sixlab.web.site.beans;

import org.nutz.dao.entity.annotation.Table;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Column;

/**
 * @author 六楼的雨/loki
 * @since 1.0.0
 */
@Table("six_site_usermeta")
public class SixSiteUsermeta {

    @Id
    private int umetaId;
    @Column("user_id")
    private int userId;
    @Column("meta_key")
    private String metaKey;
    @Column("meta_value")
    private String metaValue;

    public int getUmetaId () {
        return umetaId;
    }

    public void setUmetaId(int umetaId) {
        this.umetaId = umetaId;
    }

    public int getUserId () {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getMetaKey () {
        return metaKey;
    }

    public void setMetaKey(String metaKey) {
        this.metaKey = metaKey;
    }

    public String getMetaValue () {
        return metaValue;
    }

    public void setMetaValue(String metaValue) {
        this.metaValue = metaValue;
    }

}