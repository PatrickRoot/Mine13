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

import cn.sixlab.web.site.beans.SixSiteAttr;
import cn.sixlab.web.site.beans.SixSiteUsers;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSession;

/**
 * @author 六楼的雨/loki
 * @since 1.0.0
 */
@IocBean
@At("/prop")
public class PropModule {
    private static Logger logger = LoggerFactory.getLogger(PropModule.class);

    @Inject
    protected Dao dao;

    @At
    public int count(){

        return dao.count(SixSiteAttr.class);
    }

    @At
    @Ok("jsp:index")
    public String query(HttpSession session) {
        return session.getAttribute("me").toString();
    }

    @At
    @Ok("->:/test.ftl")
    public String test(HttpSession session) {
        return session.getAttribute("me").toString();
    }

    @At
    public Object login(@Param("username") String name, @Param("password") String password,
            HttpSession session) {
        SixSiteUsers user = dao.fetch(SixSiteUsers.class, Cnd.where("user_login", "=", name).and("user_pass", "=", password));
        if (user == null) {
            return false;
        } else {
            session.setAttribute("me", user.getId());
            return true;
        }
    }

    @At
    @Ok(">>:/")
    public void logout(HttpSession session) {
        session.invalidate();
    }
}
