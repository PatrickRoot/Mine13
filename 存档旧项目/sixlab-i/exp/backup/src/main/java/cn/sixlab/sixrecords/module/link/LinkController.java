/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @email <nianqinianyi@163.com>
 */
package cn.sixlab.sixrecords.module.link;

import cn.sixlab.sixrecords.base.BaseController;
import cn.sixlab.sixrecords.base.util.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * TODO
 *
 * @author 六楼的雨/loki
 * @date 2015/7/3 20:36
 */
@Controller
@RequestMapping("/link")
public class LinkController extends BaseController{
    @Autowired
    private LinkService linkService;

    @RequestMapping("run")
    @ResponseBody
    public Json run(String sql) {
        logger();
        Json json = new Json();
        linkService.execute(json, sql);
        return json;
    }

    @RequestMapping("get")
    @ResponseBody
    public Json get(String data) {
        logger();
        Json json = new Json();
        linkService.get(json, data);
        return json;
    }

}
