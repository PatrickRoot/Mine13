/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @Email <nianqinianyi@163.com>
 */
package sixlab.sixutil;

import java.util.Map;

/**
 * 对传入的String字符串进行判断。
 * 或对传入的参数进行判断，返回String字符串。
 *
 * @author 六楼的雨/loki
 * @date 2015/2/17 19:46
 */
public class StrUtil {

    //genStrFromTemp() 方法默认的参数分隔符。
    private static final String STR_TEMP_SEP = "$";

    //字符串查找时未找到标识
    private static final Integer INDEX_NOT_FOUND = -1;

    /**
     * 判断字符是否是数字
     *
     * @param str
     *         要判断的字符串
     * @return 如果{@code str}是数字，则返回true； 如果{@code str}不是数字，则返回false。
     */
    public static Boolean isNumber(String str) {
        try {
            Double.valueOf(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 判断字符是否不是数字
     *
     * @param str
     *         要判断的字符串
     * @return 如果{@code str}不是数字，则返回true； 如果{@code str}是数字，则返回false。
     */
    public static Boolean isNotNumber(String str) {
        try{
            Double.valueOf(str);
            return false;
        }catch(Exception e){
            return true;
        }
    }

    /**
     * 判断字符串是null或者空字符串
     *
     * @param str
     *         要传入的字符串
     * @return 返回true则{@code str}是null或者空字符串； 返回false则{@code str}既不是null也不是空字符串。
     */
    public static Boolean isEmpty(String str) {
        if (null == str || "".equals(str)) {
            return true;
        }
        return false;
    }

    /**
     * 判断字符串既不是null也不是空字符串
     *
     * @param str
     *         要传入的字符串
     * @return 返回true则{@code str}既不是null也不是空字符串； 返回false则{@code str}是null或者空字符串。
     */
    public static Boolean isNotEmpty(String str) {
        if (null == str || "".equals(str)) {
            return false;
        }
        return true;
    }

    /**
     * 判断传入的字符串是否符合Equals
     *
     * @param str 被判断的第一个字符串
     * @param strs 剩余的所有的字符串
     * @return 如果所有字符串都符合Equals，则返回true，否则返回false。如果所有参数皆为null，返回true。
     */
    public static Boolean isEquals(String str, String... strs) {
        if( null == strs ){
            if(null==str){
                return true;
            }
            return false;
        }else{
            for( String s : strs ){
                if(!s.equals(str)){
                    return false;
                }
            }
            return true;
        }
    }

    /**
     * 判断传入的字符串是否符合Equals。
     *
     * @param nullResult 当所有参数皆为null，返回此参数
     * @param str 被判断的第一个字符串
     * @param strs 剩余的所有的字符串
     * @return 如果所有字符串都符合Equals，则返回true，否则返回false。如果所有参数皆为null，返回{@code nullResult}。
     */
    public static Boolean isEquals(boolean nullResult, String str, String...
            strs) {
        if( null == strs ){
            if(null==str){
                return nullResult;
            }
            return false;
        }else{
            for( String s : strs ){
                if(!s.equals(str)){
                    return false;
                }
            }
            return true;
        }
    }

    /**
     * 判断传入的字符串是否不全符合Equals
     *
     * @param str1 被判断的第一个字符串
     * @param str2 被判断的第二个字符串
     * @return 如果所有字符串都符合Equals，则返回false，否则返回false。如果所有参数皆为null，返回false。
     */
    public static Boolean isNotEquals(String str1, String str2) {
        if(null == str1){
            if(null==str2){
                return false;
            }
            return true;
        }else if( str1.equals(str2)){
            return false;
        }else{
            return true;
        }
    }

    /**
     * 判断传入的字符串是否符合Equals。
     *
     * @param nullResult 当所有参数皆为null，返回此参数
     * @param str 被判断的第一个字符串
     * @param strs 剩余的所有的字符串
     * @return 如果所有字符串都符合Equals，则返回true，否则返回false。如果所有参数皆为null，返回{@code nullResult}。
     */
    public static Boolean isNotEquals(boolean nullResult, String str, String...
            strs) {
        if( null == strs ){
            if(null==str){
                return nullResult;
            }
            return true;
        }else{
            for( String s : strs ){
                if(!s.equals(str)){
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * 将字符串中特定字符串全部替换为另一组字符串
     *
     * @param str
     *         待操作的字符串
     * @param search
     *         将要被替换掉的字符串
     * @param value
     *         用来替换的字符串
     * @return 已被替换好的字符串
     */
    public static String replace(String str, String search, String value) {
        if( isEmpty(str) ){
            return str;
        }
        StringBuilder sb = new StringBuilder(str);
        int index = sb.indexOf(search);
        while( INDEX_NOT_FOUND != index ){
            sb.replace(index, index + search.length(), value);
            index = sb.indexOf(search);
        }
        return sb.toString();
    }

    /**
     * 根据传入的字符串键值对和字符串模板，生成字符串。 默认在模板中以$key$的形式代表key值，map中存放的是key和value。
     * 模板中的分割符可以自定义，详见
     *
     * @param str
     *         字符串模板
     * @param keyValues
     *         字符串键值对
     * @return 已经替换好的字符串。
     * @see #genStrFromTemp(java.lang.String, java.lang.String, java.util.Map)
     */
    public static String genStrFromTemp(String str,
                                        Map<String, String> keyValues) {

        return genStrFromTemp(str, STR_TEMP_SEP, keyValues);
    }

    /**
     * 根据传入的字符串键值对和字符串模板，生成字符串。 默认在模板中以$key$的形式代表key值，map中存放的是key和value。
     * 如果模板中的分割符号使用$,则可不传入{@code separator}参数，详见
     *
     * @param str
     *         字符串模板
     * @param separator
     *         字符串模板中key的分隔符，默认是$。
     * @param keyValues
     *         字符串键值对
     * @return 已经替换好的字符串。
     * @see #genStrFromTemp(java.lang.String, java.util.Map)
     */
    public static String genStrFromTemp(String str, String separator,
                                        Map<String, String> keyValues) {
        if( isEmpty(str) || MapUtil.isEmpty(keyValues) ){
            return str;
        }
        separator = isEmpty(separator) ? STR_TEMP_SEP : separator;

        StringBuilder sb = new StringBuilder(str);

        for( String key : keyValues.keySet() ){
            String sepKey = separator + key + separator;
            String value = keyValues.get(key);
            int index = sb.indexOf(sepKey);
            while( INDEX_NOT_FOUND != index ){
                sb.replace(index, index + sepKey.length(), value);
                index = sb.indexOf(sepKey);
            }
        }

        return sb.toString();
    }


    /**
     * 把一个字符串中的大写转为小写，小写不变
     *
     * @param str 被转化的字符串
     * @return 结果
     */
    public static String lowerCase(String str) {
        if (isEmpty(str)) {
            return str;
        }

        char[] buffer = str.toCharArray();

        for (int i = 0; i < buffer.length; i++) {
            char ch = buffer[i];
            if (Character.isUpperCase(ch)) {
                buffer[i] = Character.toLowerCase(ch);
            } else if (Character.isTitleCase(ch)) {
                buffer[i] = Character.toLowerCase(ch);
            }
        }
        return new String(buffer);
    }

    /**
     * 把一个字符串中的大写转为小写，小写转换为大写
     *
     * @param str 被转化的字符串
     * @return 结果
     */
    public static String swapCase(String str) {
        if (isEmpty(str)) {
            return str;
        }

        char[] buffer = str.toCharArray();

        for (int i = 0; i < buffer.length; i++) {
            char ch = buffer[i];
            if (Character.isUpperCase(ch)) {
                buffer[i] = Character.toLowerCase(ch);
            } else if (Character.isTitleCase(ch)) {
                buffer[i] = Character.toLowerCase(ch);
            } else if (Character.isLowerCase(ch)) {
                buffer[i] = Character.toUpperCase(ch);
            }
        }
        return new String(buffer);
    }
}
