/* 
 ************************************************************************
 * sixlab.eu.org 2015/10/19 Authors: 六楼的雨/loki <nianqinianyi@163.com>*
 ************************************************************************
 */
package cn.sixlab.sixrecords.module.dao;

import cn.sixlab.sixrecords.base.BaseController;
import cn.sixlab.sixrecords.base.util.Json;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 作者：六楼的雨/loki
 * 创建时间：2015/10/19
 * 功能描述：
 * 版本：1.0-snapshot
 */
@Controller
@RequestMapping(value = "/dao")
public class DaoController extends BaseController {
    
    private static Logger logger = LoggerFactory.getLogger(DaoController.class);
    
    @Autowired
    private DaoService service;
    
    @RequestMapping(value = "/add")
    public Json add(String className, String data) {
        logger.info(">>>>>DaoController.add");
        Json json = new Json();

        service.add(className, data);

        return json;
    }
}
