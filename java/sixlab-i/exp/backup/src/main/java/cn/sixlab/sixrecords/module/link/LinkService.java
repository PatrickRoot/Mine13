/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @email <nianqinianyi@163.com>
 */
package cn.sixlab.sixrecords.module.link;

import cn.sixlab.link.beans.LinkBean;
import cn.sixlab.sixrecords.base.BaseBusiness;
import cn.sixlab.sixrecords.base.util.Json;
import com.alibaba.fastjson.JSON;
import org.nutz.dao.Cnd;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TODO
 *
 * @author 六楼的雨/loki
 * @date 2015/7/3 20:37
 */
@Service
public class LinkService extends BaseBusiness {

    public void execute(Json json, String sqlStr) {
        try{
            Sql sql = Sqls.create(sqlStr);
            dao.execute(sql);
        }catch (Exception e){
            json.setMessage("错误");
            json.setSuccess("0");
        }
    }

    public void get(Json json, String data) {
        try {
            LinkBean linkBean = JSON.parseObject(data,LinkBean.class);
            try{
                List list = dao.query(linkBean.getType(),Cnd.wrap(linkBean.getSql()));
                if(null!=list && list.size()>0){
                    try {
                        String value = JSON.toJSONString(list);
                        json.put("value",value);
                    } catch (Exception e) {
                        json.setMessage("object->json错误");
                        json.setSuccess("0");
                    }
                }
            }catch (Exception e){
                json.setMessage("查询Sql错误");
                json.setSuccess("0");
            }
        } catch (Exception e) {
            json.setMessage("json->object错误");
            json.setSuccess("0");
        }
    }
}
