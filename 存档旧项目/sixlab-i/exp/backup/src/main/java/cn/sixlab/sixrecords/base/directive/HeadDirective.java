/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @email <nianqinianyi@163.com>
 */
package cn.sixlab.sixrecords.base.directive;

import cn.sixlab.sixrecords.base.util.C;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Writer;
import java.util.Map;
import java.util.Random;

/**
 * //TODO
 *
 * @author 六楼的雨/loki
 * @date 2015/4/7 17:37
 */
public class HeadDirective implements TemplateDirectiveModel {
    
    @Override
    public void execute(Environment env, Map params, TemplateModel[] loopVars,
            TemplateDirectiveBody body) throws TemplateException, IOException {
        String title = C.DEFAULT_TITLE;
        if (null != params && params.size() != 0) {
            title = ObjectUtils.toString(params.get("title"));
        }

        StringBuilder stringBuilder = new StringBuilder();

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String resUrl = request.getContextPath() + "/res/";

        double temp = new Random(1).nextDouble();
        stringBuilder.append("<meta http-equiv='content-type' content='text/html;charset=utf-8'>\n");
        stringBuilder.append("<meta name='renderer' content='webkit'>\n");
        stringBuilder.append("<meta content='width=device-width; initial-scale=1.0;' name='viewport' />\n");
        stringBuilder.append("<meta http-equiv='X-UA-Compatible' content='IE=edge' />\n");
        stringBuilder.append("<title>" + title + "</title>\n");
        stringBuilder.append("<link href='" + resUrl + "css/common/base.css?r=" + temp + "' rel='stylesheet' type='text/css'/>\n");
        stringBuilder.append("<link href='" + resUrl + "css/bootstrap/bootstrap.min.css?r=" + temp + "' rel='stylesheet' type='text/css'/>\n");
        stringBuilder.append("<script src='" + resUrl + "common/jquery/jquery.min.js?r=" + temp + "' type='text/javascript' ></script>\n");
        stringBuilder.append("<script src='" + resUrl + "common/app.js?r=" + temp + "' type='text/javascript' ></script>\n");
        stringBuilder.append("<script src='" + resUrl + "common/bootstrap/bootstrap.min.js?r=" + temp + "' type='text/javascript' ></script>\n");

        Writer out = env.getOut();
        out.write(stringBuilder.toString());
    }
}
