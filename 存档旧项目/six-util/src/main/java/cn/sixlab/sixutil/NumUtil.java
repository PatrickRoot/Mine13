/*
 * Copyright (c) 1995 Sixlab. All rights reserved.
 *
 * Under the GPLv3(AKA GNU GENERAL PUBLIC LICENSE Version 3).
 * see http://www.gnu.org/licenses/gpl-3.0-standalone.html
 *
 * For more information, please see
 * http://sixlab.cn/
 */
package cn.sixlab.sixutil;

/**
 * @author 六楼的雨/loki
 * @since 1.0.0
 */
public class NumUtil {

    /**
     * 比较两个Integer类型的值是否相等。
     *
     * @since 1.0.0
     * @param x 第一个Integer
     * @param y 第二个Integer
     * @return 两个Integer的值是否相等。
     */
    public static boolean isEqual(Integer x, Integer y) {
        if(x == null){
            if(y == null){
                return true;
            }else {
                return false;
            }
        } else if (x.equals(y)) {
            return true;
        } else {
            return false;
        }
    }

}
