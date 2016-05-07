/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @email <nianqinianyi@163.com>
 */
package cn.sixlab.sixrecords.base.directive;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * TODO
 *
 * @author 六楼的雨/loki
 * @date 2015/4/8 16:02
 */
public class CssDirective implements TemplateDirectiveModel{

    private static Map<String, String> cssS = new HashMap<>();

    static {
        cssS.put("base", "base.css");
    }


    @Override
    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
            throws TemplateException, IOException {
        if(null!=body){
            StringWriter stringWriter = new StringWriter();
            body.render(stringWriter);

            String cssKeyStr = "";
            cssKeyStr = StringUtils.replace(stringWriter.toString(), "\n", ",");
            cssKeyStr = StringUtils.replace(cssKeyStr, "\r", ",");

            String[] cssKeys = null;
            cssKeys = StringUtils.split(cssKeyStr, ",");

            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String resUrl = request.getContextPath() + "/res/";

            StringBuilder stringBuilder = new StringBuilder();
            for (String cssKey : cssKeys) {
                if (StringUtils.isNotEmpty(cssKey)){
                    cssKey = cssKey.trim();
                    String cssValue = cssS.get(cssKey);

                    if (StringUtils.isEmpty(cssValue)) {
                        stringBuilder.append("<link href='" + resUrl + "css/" + cssKey + "?v="
                                + Math.random() + "' rel='stylesheet' type='text/css'/>");
                    } else {
                        stringBuilder.append("<link href='" + resUrl + "css/common/" + cssValue
                                + "' rel='stylesheet' type='text/css'/>");
                    }
                }
            }
            Writer out = env.getOut();
            out.write(stringBuilder.toString());
        }
    }
}
