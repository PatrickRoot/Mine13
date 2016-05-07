/*
 * Copyright (c) 1995 Sixlab. All rights reserved.
 *
 * Under the GPLv3(AKA GNU GENERAL PUBLIC LICENSE Version 3).
 * see http://www.gnu.org/licenses/gpl-3.0-standalone.html
 *
 * For more information, please see
 * http://sixlab.cn/
 */
package cn.sixlab.web.common.core;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author 六楼的雨/loki
 * @since 1.0.0-SNAPSHOT
 */
public class Prop {

    private static Properties prop = null;
    static {
        init();
    }

    private static void init() {
        InputStream is = Prop.class.getResourceAsStream("/prop.properties");
        try {
            prop = new Properties();
            prop.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String get(String key){
        if(null==prop || null==prop.get(key)){
            init();
        }
        return prop.getProperty(key);
    }

    public static Properties jdbc(Class clz) throws IOException {
        InputStream is = clz.getResourceAsStream("/jdbc.properties");
        Properties properties = new Properties();
        properties.load(is);
        return properties;
    }
}
