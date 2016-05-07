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
 * @date 2015/4/7 18:03
 */
public class JsDirective implements TemplateDirectiveModel {

    private static Map<String, String> jss = new HashMap<>();

    static {
        jss.put("jq", "jquery/jquery.min.js");
        jss.put("jq.blockUI", "jquery/jQuery.blockUI.js");

        jss.put("jq.alerts", "jquery-alerts/jquery.alerts.js");
        jss.put("jq.form", "jquery-form/jquery.form.js");
        jss.put("jq.validate", "jquery-validate/jquery.validate.min.js");
        jss.put("juicer", "juicer/juicer-min.js");

        jss.put("echarts.bar", "echarts/chart/bar.js");
        jss.put("echarts.chord", "echarts/chart/chord.js");
        jss.put("echarts.eventRiver", "echarts/chart/eventRiver.js");
        jss.put("echarts.force", "echarts/chart/force.js");
        jss.put("echarts.funnel", "echarts/chart/funnel.js");
        jss.put("echarts.gauge", "echarts/chart/gauge.js");
        jss.put("echarts.k", "echarts/chart/k.js");
        jss.put("echarts.line", "echarts/chart/line.js");
        jss.put("echarts.map", "echarts/chart/map.js");
        jss.put("echarts.pie", "echarts/chart/pie.js");
        jss.put("echarts.radar", "echarts/chart/radar.js");
        jss.put("echarts.scatter", "echarts/chart/scatter.js");
        jss.put("echarts", "echarts/echarts.js");
        jss.put("echarts.all", "echarts/echarts-all.js");
    }
    
    @Override
    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
            throws TemplateException, IOException {
        if(null!=body){
            StringWriter stringWriter = new StringWriter();
            body.render(stringWriter);

            String jsKeyStr = "";
            jsKeyStr = StringUtils.replace(stringWriter.toString(), "\n", ",");
            jsKeyStr = StringUtils.replace(jsKeyStr, "\r", ",");

            String[] jsKeys = null;
            jsKeys = StringUtils.split(jsKeyStr, ",");

            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String resUrl = request.getContextPath() + "/res/";

            StringBuilder stringBuilder = new StringBuilder();
            for (String jsKey : jsKeys) {
                if (StringUtils.isNotEmpty(jsKey)){
                    jsKey = jsKey.trim();
                    String jsValue = jss.get(jsKey);

                    if (StringUtils.isEmpty(jsValue)) {
                        stringBuilder.append("<script type='text/javascript' charset='utf-8' src='"
                                + resUrl + "js/" + jsKey + ".js?r=" + Math.random() + "'></script>");
                    } else {
                        stringBuilder.append("<script type='text/javascript' charset='utf-8' src='"
                                + resUrl + "common/" + jsValue + "'></script>");
                    }
                }
            }
            Writer out = env.getOut();
            out.write(stringBuilder.toString());
        }
    }
}
