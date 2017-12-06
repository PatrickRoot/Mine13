/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @email <nianqinianyi@163.com>
 */
package sixlab.sixutil;

import java.util.Map;

/**
 * 传入Map集合，并进行相关判断或者操作
 * @author 六楼的雨/loki
 * @date 2015/4/2 22:02
 */
public class MapUtil {

    /**
     * 判断Map是否是null或者size为0
     * @param map
     *         传入的map对象
     * @return  如果map为null或者size是0，则返回true；
     *          如果map不为null，且size不是0，返回false。
     */
    public static Boolean isEmpty(Map map) {
        if( null == map || map.isEmpty() ){
            return true;
        }
        return false;
    }
}
