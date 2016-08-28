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
@Table("six_site_attr")
public class SixSiteAttr {

    @Id
    private int attrId;
    @Column("attr_name")
    private String attrName;
    @Column("attr_link")
    private String attrLink;
    @Column("attr_type")
    private String attrType;
    @Column("description")
    private String description;
    @Column("parent")
    private int parent;
    @Column("count")
    private int count;

    public int getAttrId () {
        return attrId;
    }

    public void setAttrId(int attrId) {
        this.attrId = attrId;
    }

    public String getAttrName () {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    public String getAttrLink () {
        return attrLink;
    }

    public void setAttrLink(String attrLink) {
        this.attrLink = attrLink;
    }

    public String getAttrType () {
        return attrType;
    }

    public void setAttrType(String attrType) {
        this.attrType = attrType;
    }

    public String getDescription () {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getParent () {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public int getCount () {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

}