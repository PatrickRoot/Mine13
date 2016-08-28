/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @email <nianqinianyi@163.com>
 */
package cn.sixlab.sixrecords.module.user;

import cn.sixlab.sixrecords.base.BaseBusiness;
import cn.sixlab.sixrecords.base.util.Json;
import cn.sixlab.sixrecords.dao.bean.SixUser;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.nutz.dao.Cnd;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * TODO
 *
 * @author 六楼的雨/loki
 * @date 2015/6/30 14:21
 */
@Service
@Transactional
public class UserService extends BaseBusiness {
    private static Logger logger = LoggerFactory.getLogger(UserService.class);
    
    public void submitReg(Json json, SixUser sixUser, String rePwd) {
        logger.info("尝试注册："+sixUser.getUsername());
        if(checkUsername(json,sixUser.getUsername())){
            String password = sixUser.getPassword();
            if (StringUtils.isNotEmpty(password) && password.equals(rePwd)) {
                SixUser newUser = new SixUser();
                newUser.setUsername(sixUser.getUsername());
                newUser.setPassword(DigestUtils.md5(password).toString());
                dao.insert(newUser);
            }
            json.put("username", sixUser.getUsername());
            logger.info("注册成功：" + sixUser.getUsername());
        }
    }

    public boolean checkUsername(Json json, String username) {
        logger.info("验证用户名是否重复：" + username);
        List<SixUser> sixUser = dao.query(SixUser.class, Cnd.where("username","=",username));
        if(null!=sixUser && sixUser.size()>0){
            json.setSuccess("0");
            json.setMessage("昵称已存在，请更换");
            logger.info("用户名重复：" + username);
            return false;
        }
        return true;
    }
}
