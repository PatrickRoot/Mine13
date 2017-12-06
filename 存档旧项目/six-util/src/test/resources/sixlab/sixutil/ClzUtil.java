/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @email <nianqinianyi@163.com>
 */
package sixlab.sixutil;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * TODO
 *
 * @author 六楼的雨/loki
 * @date 2015/4/8 16:56
 */
public class ClzUtil {

    private static Map<Class, Object> beanMap = new HashMap<>();

    public static<T> T getBean(Class<T> clz){
        Object obj = beanMap.get(clz);
        return (T) beanMap.get(clz);
    }

    public static void addBeans(Class... clz) {
        for (Class aClass : clz) {
            try {
                beanMap.putIfAbsent(aClass, aClass.newInstance());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public static void diObjects() {
        for (Class aClass : beanMap.keySet()) {
            Field[] fields = aClass.getDeclaredFields();
            for (Field field : fields) {
                Class clz = field.getType();
                Object objValue = beanMap.get(clz);
                if (null != objValue) {
                    field.setAccessible(true);
                    Object targetObj = beanMap.get(aClass);
                    try {
                        field.set(targetObj, objValue);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
