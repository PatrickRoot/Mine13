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
@Table("six_site_attr_obj")
public class SixSiteAttrObj {

    @Id
    private int objId;
    @Column("attr_id")
    private int attrId;
    @Column("order")
    private int order;

    public int getObjId () {
        return objId;
    }

    public void setObjId(int objId) {
        this.objId = objId;
    }

    public int getAttrId () {
        return attrId;
    }

    public void setAttrId(int attrId) {
        this.attrId = attrId;
    }

    public int getOrder () {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

}