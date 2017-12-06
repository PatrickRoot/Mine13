/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @Email <nianqinianyi@163.com>
 */
package sixlab.sixutil;

/**
 * 传入数字类型，并进行相关判断或者操作。或者对传入的数字进行处理。
 *
 * @author 六楼的雨/loki
 * @date 2015/2/17 19:46
 */
public class NumUtil {

    /**
     *
     *
     * @param num1
     * @param num2
     * @return
     */
    public static Boolean isNotEquals(Integer num1, Integer num2) {
        if( num1 == null ){
            if( num2 == null ){
                return true;
            }
            return false;
        }else if( num1.equals(num2) ){
            return true;
        }else{
            return false;
        }
    }
}
