/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @email <nianqinianyi@163.com>
 */
package cn.sixlab.sixrecords.base.directive;

import cn.sixlab.sixrecords.base.util.U;
import cn.sixlab.sixrecords.base.util.W;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import org.apache.commons.lang3.ObjectUtils;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

/**
 * TODO
 *
 * @author 六楼的雨/loki
 * @date 2015/6/30 15:43
 */
public class BodyDirective implements TemplateDirectiveModel {

    @Override
    public void execute(Environment env, Map params, TemplateModel[] loopVars,
            TemplateDirectiveBody body) throws TemplateException, IOException {
        StringBuilder stringBuilder = new StringBuilder();

        String type = null;
        if (null != params && params.size() != 0) {
            type = ObjectUtils.toString(params.get("type"));
        }

        String path = W.getRequest().getContextPath();

        if ("top".equals(type)) {

            stringBuilder.append("<div style=\"height: 40px;width:100%;background-color: #666666;\">\n" +
                    "    <div style=\"margin: 0 auto;height:30px;width: 1000px;\">\n" +
                    "        <div style=\"color: #eeeeee;float:left\">\n" +
                    "            <a href=\"" + path + "/\"><img src=\"" + path + "/res/img/logo.png\" style=\"height: 40px;\"></a>\n" +
                    "        </div>\n");

            if (U.isLogin()) {
                String username = U.getUsername();
                stringBuilder.append("<div style=\"color: #eeeeee;float:right;padding-top: 10px;\">"+username+" | <a href='" + path + "/logout.lab'>退出</a></div>\n" +
                        "    </div>\n");
            }else{
                stringBuilder.append("<div style=\"color: #eeeeee;float:right;padding-top: 10px;\"><a href='" + path + "/login.lab'>登录</a> / <a href='" + path + "/user/reg.lab'>注册</a></div>\n" +
                        "    </div>\n");
            }
            stringBuilder.append("</div>\n" +
                    "<div style=\"clear:both;\"></div><div style='width: 100%;'><div style='margin: 0 auto;width: 1000px;'>");
        } else {
            stringBuilder.append("</div></div><div style=\"height: 40px;width: 100%;background-color: #666666;\">\n" +
                    "    <div style=\"margin: 0 auto;padding-top: 10px;height:30px;width: 1000px;text-align:center;color: #eeeeee\">\n" +
                    "        Copyright © 2015, Sixlab - All Rights Reserved\n" +
                    "    </div>\n" +
                    "</div>");
        }

        Writer out = env.getOut();
        out.write(stringBuilder.toString());
    }
}
