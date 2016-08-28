/*
 * Copyright (c) 1995 Sixlab. All rights reserved.
 *
 * Under the GPLv3(AKA GNU GENERAL PUBLIC LICENSE Version 3).
 * see http://www.gnu.org/licenses/gpl-3.0-standalone.html
 *
 * For more information, please see
 * http://sixlab.cn/
 */
package cn.sixlab.web.site.module;

import cn.sixlab.web.site.beans.SixSiteUsers;
import cn.sixlab.web.site.service.IndexService;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 六楼的雨/loki
 * @since 1.0.0
 */
@IocBean
@At("/index")
public class IndexModule {
    private static Logger logger = LoggerFactory.getLogger(IndexModule.class);

    @Inject
    protected IndexService indexService;

    @Inject
    protected Dao dao;

    @At
    public int count() {
        return dao.count(SixSiteUsers.class);
    }
}
