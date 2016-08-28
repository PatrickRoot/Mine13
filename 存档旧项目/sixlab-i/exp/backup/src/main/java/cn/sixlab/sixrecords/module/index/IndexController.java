/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @email <nianqinianyi@163.com>
 */
package cn.sixlab.sixrecords.module.index;

import cn.sixlab.sixrecords.base.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * TODO
 *
 * @author 六楼的雨/loki
 * @date 2015/4/7 20:09
 */
@Controller
public class IndexController extends BaseController{
    private static Logger logger = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping(value = "/index")
    public String login(ModelMap modelMap) {
        logger();
        return "back/index";
    }
}
