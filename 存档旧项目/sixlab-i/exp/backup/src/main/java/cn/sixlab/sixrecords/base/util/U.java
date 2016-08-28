/**
 * @Copyright © Sixlab 2014
 * @author 六楼的雨/loki
 * @email <nianqinianyi@163.com>
 */
package cn.sixlab.sixrecords.base.util;

import cn.sixlab.sixrecords.base.BaseController;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * 仅限于此项目的使用这个类Util类，太常用就使用SixUtil。
 *
 * @author 六楼的雨/loki
 * @date 2014/12/28 19:25
 */
public class U extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(U.class);

    public static boolean isLogin() {
        Integer userId = getUserId();
        String username = getUsername();
        if(null==userId || StringUtils.isEmpty(username)){
            return false;
        }else {
            return true;
        }
    }

    public static Integer getUserId() {
        Integer userId = null;
        try {
            String userIdStr = (String) W.getCookie(C.login_user_id);
            userId = Integer.valueOf(userIdStr);
        } catch (Exception e) {
            userId = null;
        }
        return userId;
    }

    public static String getUsername() {
        String username = null;
        try {
            username = (String) W.getCookie(C.login_user_name);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return username;
    }

    public static void loginValidate(){
        if (!isLogin()){
            try {
                W.getResponse().sendRedirect(W.getRequest().getContextPath()+"/index.lab");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String encode(Integer id) {
        String username = null;
        StringBuffer sb = new StringBuffer(String.valueOf(id*10 + C.login_auth_num));
        sb.reverse();
        Integer temp = C.login_auth_num - Integer.valueOf(sb.toString());
        return "u-"+temp.toString();
    }

    public static Integer decode(String str) {
        try {
            str = str.substring(2);
            Integer temp = C.login_auth_num - Integer.valueOf(str);
            StringBuffer sb = new StringBuffer(temp.toString());
            sb.reverse();
            Integer id = Integer.valueOf(sb.toString()) - C.login_auth_num;
            return id / 10;
        }catch (Exception e){
            return null;
        }
    }
}
