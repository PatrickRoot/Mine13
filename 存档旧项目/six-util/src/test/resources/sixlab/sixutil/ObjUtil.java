/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @email <nianqinianyi@163.com>
 */
package sixlab.sixutil;

import java.lang.reflect.Field;

/**
 * //TODO
 *
 * @author 六楼的雨/loki
 * @date 2015/4/4 17:30
 */
public class ObjUtil {

    public static void copyBean(Object target, Object source){
        Field[] fields = target.getClass().getDeclaredFields();
        for( Field field : fields ){
            String fieldName = field.getName();
            Class fieldType = field.getType();
            try{
                Field sourceField = source.getClass().getDeclaredField(fieldName);
                field.setAccessible(true);
                sourceField.setAccessible(true);
                Object value = sourceField.get(source);
                field.set(target,value);
            }catch(NoSuchFieldException e){
                continue;
            }catch(IllegalAccessException e){
                continue;
            }
        }
    }
}