/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @email <nianqinianyi@163.com>
 */
package cn.sixlab.sixrecords.module.user;

import cn.sixlab.sixrecords.base.BaseController;
import cn.sixlab.sixrecords.base.util.Json;
import cn.sixlab.sixrecords.dao.bean.SixUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * TODO
 *
 * @author 六楼的雨/loki
 * @date 2015/6/30 14:10
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController{
    @Autowired
    private UserService userService;

    @RequestMapping("submitReg")
    @ResponseBody
    public Json submitReg(SixUser sixUser, String rePwd) {
        logger();
        Json json = new Json();

        userService.submitReg(json, sixUser, rePwd);

        return json;
    }

    @RequestMapping("checkUsername")
    @ResponseBody
    public Json checkUsername(String username) {
        logger();
        Json json = new Json();

        userService.checkUsername(json, username);

        return json;
    }
}
