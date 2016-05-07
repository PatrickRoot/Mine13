/**
 * @Copyright © Sixlab 2014
 * @author 六楼的雨/loki
 * @email <nianqinianyi@163.com>
 */
package cn.sixlab.sixrecords.module.auth;

import cn.sixlab.sixrecords.base.BaseBusiness;
import cn.sixlab.sixrecords.base.util.C;
import cn.sixlab.sixrecords.base.util.Json;
import cn.sixlab.sixrecords.base.util.W;
import cn.sixlab.sixrecords.dao.bean.SixUser;
import org.apache.commons.codec.digest.DigestUtils;
import org.nutz.dao.Cnd;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;
import java.util.Date;

/**
 * todo
 *
 * @author 六楼的雨/loki
 * @date 2014/12/29 19:21
 */
@Service
@Transactional
public class AuthService extends BaseBusiness {
    private static Logger logger = LoggerFactory.getLogger(AuthService.class);

    /**
     * 处理提交登录
     * @param json 用来返回的ajax数据
     * @param username 用户名
     * @param password 密码
     */
    public void submitLogin(Json json, String username, String password) {
        logger.info("尝试登录："+username);
        SixUser sixUser = dao.fetch(SixUser.class, Cnd.where("username","=",username));
        if(null!=sixUser){
            String md5 = DigestUtils.md5(password).toString();
            if ( !sixUser.getPassword().equals(md5)) {
                json.setSuccess("0");
                json.setMessage("密码不符");
                logger.info("登录失败，密码不符：" + username);
            }else{
                Cookie idCookie = new Cookie(C.login_user_id, sixUser.getId().toString());
                Cookie nameCookie = new Cookie(C.login_user_name, sixUser.getUsername());
                idCookie.setMaxAge(C.cookie_max_age);
                idCookie.setPath("/");
                nameCookie.setMaxAge(C.cookie_max_age);
                nameCookie.setPath("/");
                W.getResponse().addCookie(idCookie);
                W.getResponse().addCookie(nameCookie);
                logger.info("登录成功：" + username);
                logger.info("ip:" + W.getIp());

                Integer number = sixUser.getLoginNum();
                if(null!=number){
                    sixUser.setLoginNum(number++);
                }
                sixUser.setLastLoginIp(W.getIp());
                sixUser.setLastLoginTime(new Date());
                dao.update(sixUser);
            }
        }else{
            json.setSuccess("0");
            json.setMessage("找不到用户");
            logger.info("登录失败，找不到用户：" + username);
        }

    }
}
