/**
 * @Copyright © Sixlab 2014
 * @author 六楼的雨/loki
 * @email <nianqinianyi@163.com>
 */
package cn.sixlab.sixrecords.base;

import cn.sixlab.sixrecords.base.util.W;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

/**
 * //TODO
 *
 * @author 六楼的雨/loki
 * @date 2014/12/28 17:12
 */
@Controller
public class BaseController {
    private static Logger logger = LoggerFactory.getLogger(BaseController.class);

    protected void logger() {
        logger.info("\nclass : " + this.getClass().getName() + "\nRequest : " + W.getRequest().getRequestURI());
    }
}
