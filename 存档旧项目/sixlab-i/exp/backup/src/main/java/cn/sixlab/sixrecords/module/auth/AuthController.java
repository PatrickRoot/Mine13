/**
 * @Copyright © Sixlab 2014
 * @author 六楼的雨/loki
 * @email <nianqinianyi@163.com>
 */
package cn.sixlab.sixrecords.module.auth;

import cn.sixlab.sixrecords.base.BaseController;
import cn.sixlab.sixrecords.base.util.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * todo
 *
 * @author 六楼的雨/loki
 * @date 2014/12/29 19:08
 */
@Controller
public class AuthController extends BaseController{
    @Autowired
    private AuthService loginService;

    /**
     * 提交登录
     *
     * @param username 用户名
     * @param password 密码
     * @return ajax数据
     */
    @ResponseBody
    @RequestMapping(value = "/submitLogin")
    public Json submitLogin(String username, String password) {
        logger();
        Json json = new Json();

        loginService.submitLogin(json, username, password);

        return json;
    }

    /**
     * test
     *
     * @return ajax数据
     */
    @ResponseBody
    @RequestMapping(value = "/test")
    public Json test() {
        logger();
        Json json = new Json();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://127.13.126.130:3306/blog?useUnicode=true&amp;characterEncoding=UTF-8";
            String username = "adminefXpph3";
            String password = "J4d5S2BhmyAT";
            Connection con = DriverManager.getConnection(url, username, password);
            con.close();
        }catch (Exception e){
            json.setSuccess("0");
            json.setMessage(e.getMessage());
        }
        return json;
    }
}
