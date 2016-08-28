/*
 * Copyright (c) 1995 Sixlab. All rights reserved.
 *
 * Under the GPLv3(AKA GNU GENERAL PUBLIC LICENSE Version 3).
 * see http://www.gnu.org/licenses/gpl-3.0-standalone.html
 *
 * For more information, please see
 * http://sixlab.cn/
 */
package cn.sixlab.web.i.i;

import cn.sixlab.web.i.i.beans.SixPropTest;
import org.nutz.dao.Dao;
import org.nutz.dao.util.Daos;
import org.nutz.ioc.Ioc;
import org.nutz.mvc.NutConfig;
import org.nutz.mvc.Setup;

/**
 * @author 六楼的雨/loki
 * @since 1.0.0
 */
public class MainSetup implements Setup{

    @Override
    public void init(NutConfig nutConfig) {
        Ioc ioc = nutConfig.getIoc();
        Dao dao = ioc.get(Dao.class);
        Daos.createTablesInPackage(dao, "cn.sixlab.web.i.i", false);

        SixPropTest sixProp = new SixPropTest();
        sixProp.setK("abc");
        sixProp.setVal("cdetrtrert");
        dao.insert(sixProp);
    }

    @Override
    public void destroy(NutConfig nutConfig) {

    }

}