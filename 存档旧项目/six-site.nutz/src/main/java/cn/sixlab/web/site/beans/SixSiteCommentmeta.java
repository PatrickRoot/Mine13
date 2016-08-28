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
@Table("six_site_commentmeta")
public class SixSiteCommentmeta {

    @Id
    private int metaId;
    @Column("comment_id")
    private int commentId;
    @Column("meta_key")
    private String metaKey;
    @Column("meta_value")
    private String metaValue;

    public int getMetaId () {
        return metaId;
    }

    public void setMetaId(int metaId) {
        this.metaId = metaId;
    }

    public int getCommentId () {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
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