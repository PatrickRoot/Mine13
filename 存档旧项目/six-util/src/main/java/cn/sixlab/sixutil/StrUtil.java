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

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * @author 六楼的雨/loki
 * @since 1.0.0
 */
public class StrUtil {

    //genStrFromTemp() 方法默认的参数分隔符。
    private static final String STR_TEMP_SEP = "$";

    //字符串查找时未找到标识
    private static final Integer INDEX_NOT_FOUND = -1;

    /**
     * 判断字符是否不是数字
     *
     * @param str 要判断的字符串
     * @return 如果{@code str}不是数字，则返回true； 如果{@code str}是数字，则返回false。
     */
    public static Boolean isNotNumber(String str) {
        return !NumberUtils.isNumber(str);
    }

    /**
     * 判断字符是否是正整数
     *
     * @param str 要判断的字符串
     * @return 如果{@code str}是正整数，则返回true； 如果{@code str}不是正整数，则返回false。
     */
    public static Boolean isPositiveIntegralNumber(String str) {
        try {
            Long value = Long.valueOf(str);
            if (value > 0) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 判断字符是否不是正整数
     *
     * @param str 要判断的字符串
     * @return 如果{@code str}是正整数，则返回false； 如果{@code str}不是正整数，则返回true。
     */
    public static Boolean isNotPositiveIntegralNumber(String str) {
        try {
            Long value = Long.valueOf(str);
            if (value > 0) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return true;
        }
    }

    /**
     * 判断字符是否是自然数
     *
     * @param str 要判断的字符串
     * @return 如果{@code str}是自然数，则返回true； 如果{@code str}不是自然数，则返回false。
     */
    public static Boolean isNaturalNumber(String str) {
        try {
            long value = Long.valueOf(str);
            if (value >= 0) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 判断字符是否不是自然数
     *
     * @param str 要判断的字符串
     * @return 如果{@code str}是自然数，则返回true； 如果{@code str}不是自然数，则返回false。
     */
    public static Boolean isNotNaturalNumber(String str) {
        try {
            long value = Long.valueOf(str);
            if (value >= 0) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return true;
        }
    }

    /**
     * 判断字符是否是邮箱格式
     *
     * @param str 要判断的字符串
     * @return 如果{@code str}是邮箱格式字符串，则返回true； 如果{@code str}不是邮箱格式字符串，则返回false。
     */
    public static Boolean isEmail(String str) {
        String regex = "w+([-+.]w+)*@w+([-.]w+)*.w+([-.]w+)*";
        return str.matches(regex);
    }

    /**
     * 传入可能为null的字符串，返回不会null的字符串。 字符串不为null则返回原值，如果为null，返回空字符。
     * 当字符串为null时，可自定义返回的字符，详见
     *
     * @param str 要传入的字符串
     * @return 如果{@code str}不为null，则返回字符串本身； 如果{@code str}为null，则返回空字符串。
     * @see #notNullStr(java.lang.String, java.lang.String)
     */
    public static String notNullStr(String str) {
        return null == str ? "" : str;
    }

    /**
     * 传入可能为null的字符串，返回不会null的字符串。 字符串不为null则返回原值，如果为null，返回{@code defaultStr}。
     * {@code defaultStr}参数可省略，此时默认返回空字符串。详见
     *
     * @param str 要传入的字符串
     * @param defaultStr 字符串为null的时候返回的值。
     * @return 如果{@code str}不为null，则返回字符串本身； 如果{@code str}为null，则返回{@code
     * defaultStr}。
     * @see #notNullStr(java.lang.String)
     */
    public static String notNullStr(String str, String defaultStr) {
        return null == str ? (null == defaultStr ? "" : defaultStr) : str;
    }

    /**
     * 返回驼峰命名法的字符串，首字母是小写。
     *
     * @param ulStr 要转换的字符串。
     * @return 返回驼峰命名法的字符串，首字母是小写。
     * @since 1.0.0
     */
    public static String getCamel(String ulStr) {
        return getCamel(ulStr, true);
    }

    /**
     * 返回驼峰命名法的字符串，首字母是大写还是小写根据参数来判断。
     *
     * @param ulStr 要转换的字符串。
     * @param isFirstLower 首字母是否小写。
     * @return 返回驼峰命名法的字符串，@{code isFirstLower}为true，则首字母也是小写，否则首字母大写。
     * @since 1.0.0
     */
    public static String getCamel(String ulStr, boolean isFirstLower) {
        StringBuffer result = new StringBuffer();

        String[] ulArray = ulStr.split("_");

        boolean isFirst = true;
        for (String s : ulArray) {
            if (StringUtils.isNotEmpty(s)) {
                s = StringUtils.lowerCase(s);
                if (isFirst && isFirstLower) {
                    isFirst = false;
                    result.append(s);
                } else {
                    result.append(StringUtils.swapCase(s.substring(0, 1))).append(s.substring(1, s.length()));
                }
            }
        }

        return result.toString();
    }


}
