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
@Table("six_site_options")
public class SixSiteOptions {

    @Id
    private int optionId;
    @Column("option_name")
    private String optionName;
    @Column("option_value")
    private String optionValue;
    @Column("autoload")
    private String autoload;

    public int getOptionId () {
        return optionId;
    }

    public void setOptionId(int optionId) {
        this.optionId = optionId;
    }

    public String getOptionName () {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }

    public String getOptionValue () {
        return optionValue;
    }

    public void setOptionValue(String optionValue) {
        this.optionValue = optionValue;
    }

    public String getAutoload () {
        return autoload;
    }

    public void setAutoload(String autoload) {
        this.autoload = autoload;
    }

}