/*
 * Copyright (c) 1995 Sixlab. All rights reserved.
 *
 * Under the GPLv3(AKA GNU GENERAL PUBLIC LICENSE Version 3).
 * see http://www.gnu.org/licenses/gpl-3.0-standalone.html
 *
 * For more information, please see
 * http://sixlab.cn/
 */
package cn.sixlab.web.i.i.module;

import cn.sixlab.web.i.i.service.IndexService;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.Ok;

/**
 * @author 六楼的雨/loki
 * @since 1.0.0
 */
@IocBean
@Ok("json")
@Fail("http:500")
public class IndexModule {
    @Inject
    protected IndexService service;

}
