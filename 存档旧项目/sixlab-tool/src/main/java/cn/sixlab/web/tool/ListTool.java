/*
 * Copyright (c) 2016 Sixlab. All rights reserved.
 *
 * Under the GPLv3(AKA GNU GENERAL PUBLIC LICENSE Version 3).
 * see http://www.gnu.org/licenses/gpl-3.0-standalone.html
 *
 * For more information, please see
 * http://sixlab.cn/
 * 
 * @author 六楼的雨/loki
 * @since 1.0.0(2016/3/8)
 */
package cn.sixlab.web.tool;

import cn.sixlab.web.tool.beans.ToolListGroup;
import cn.sixlab.web.tool.beans.ToolListItem;
import cn.sixlab.web.tool.util.DbUtil;
import cn.sixlab.web.tool.util.JsonMap;
import com.alibaba.fastjson.JSON;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.support.ConnectionSource;
import org.apache.commons.collections4.CollectionUtils;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.freemarker.FreeMarkerEngine;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;

/**
 * @author 六楼的雨/loki
 * @since 1.0.0(2016/3/8)
 */
public class ListTool {

    public static void handler() {

        //列出所有的任务
        get("/list/all", (req, resp) -> queryList("list/lists.ftl", req, resp), new FreeMarkerEngine());

        //添加一个list或者修改一个list
        get("/list/edit/list/*", (req, resp) -> editList("list/list.ftl", req, resp), new FreeMarkerEngine());

        //添加一个Group或者修改一个Group
        get("/list/edit/group/*", ListTool::editGroup);

        //添加一个item或者修改一个item
        get("/list/edit/item/*", ListTool::editItem);

        //删除一个list
        get("/list/del/list/*", ListTool::delList);

        //删除一个Group
        get("/list/del/group/*", ListTool::delGroup);

        //删除一个Item
        get("/list/del/item/*", ListTool::delItem);

        //对一个list中所有内容重新排序
        get("/list/sort/*", ListTool::sort);
    }

    private static String editItem(Request req, Response res)
            throws SQLException, InterruptedException {
        JsonMap json = new JsonMap();

        String itemName = req.splat()[0];
        String groupId = req.queryParams("groupId");

        ConnectionSource conn = DbUtil.conn();
        Dao<ToolListItem, Integer> dao = DaoManager.createDao(conn, ToolListItem.class);
        if (req.splat().length == 0) {
            ToolListItem item = new ToolListItem();
            item.setGroupId(Integer.valueOf(groupId));
            item.setOrder(0);
            item.setItemName(itemName);
            dao.create(item);
        } else {
            String itemId = req.splat()[0];
            ToolListItem item = dao.queryForId(Integer.valueOf(itemId));
            item.setGroupId(Integer.valueOf(groupId));
            item.setOrder(0);
            item.setItemName(itemName);
            dao.update(item);
        }

        conn.close();
        return JSON.toJSONString(json);
    }

    private static String editGroup(Request req, Response res)
            throws SQLException, InterruptedException {
        JsonMap json = new JsonMap();

        String groupName = req.splat()[0];
        String listName = req.queryParams("listName");

        ConnectionSource conn = DbUtil.conn();
        Dao<ToolListGroup, Integer> dao = DaoManager.createDao(conn, ToolListGroup.class);
        if (req.splat().length == 0) {
            ToolListGroup group = new ToolListGroup();
            group.setGroupName(groupName);
            group.setOrder(0);
            group.setListName(listName);
            dao.create(group);
        } else {
            String groupId = req.splat()[0];
            ToolListGroup group = dao.queryForId(Integer.valueOf(groupId));
            group.setGroupName(groupName);
            group.setOrder(0);
            group.setListName(listName);
            dao.update(group);
        }

        conn.close();
        return JSON.toJSONString(json);
    }

    private static ModelAndView editList(String path, Request req, Response resp)
            throws SQLException {
        Map<String, Object> model = new HashMap<>();

        String listName = req.splat()[0];
        ConnectionSource conn = DbUtil.conn();
        Dao<ToolListGroup, ?> dao = DaoManager.createDao(conn, ToolListGroup.class);
        java.util.List<ToolListGroup> groups = dao.queryBuilder().orderBy("order", true).where().eq("list_name", listName).query();

        if (CollectionUtils.isEmpty(groups)) {
            ToolListGroup group = new ToolListGroup();
            group.setOrder(1);
            group.setListName(listName);
            group.setGroupName("默认分组");
            dao.create(group);
            groups.add(group);
        } else {
            Dao<ToolListItem, ?> itemDao = DaoManager.createDao(conn, ToolListItem.class);
            for (ToolListGroup group : groups) {
                java.util.List<ToolListItem> items = itemDao.queryBuilder().orderBy("order", true).where().eq("group_id", group.getId()).query();
                group.setToolListItemList(items);
            }
        }

        conn.close();

        model.put("listName", listName);
        model.put("models", groups);

        return new ModelAndView(model, path);
    }

    private static String delItem(Request req, Response res)
            throws SQLException, InterruptedException {
        JsonMap json = new JsonMap();

        String groupId = req.splat()[0];
        Integer groupIdVal = Integer.valueOf(groupId);

        ConnectionSource conn = DbUtil.conn();
        Dao<ToolListItem, Integer> dao = DaoManager.createDao(conn, ToolListItem.class);

        dao.deleteById(groupIdVal);

        conn.close();

        return JSON.toJSONString(json);
    }

    private static String delGroup(Request req, Response res)
            throws SQLException, InterruptedException {
        JsonMap json = new JsonMap();

        String groupId = req.splat()[0];
        Integer groupIdVal = Integer.valueOf(groupId);
        String delItemSql = " DELETE FROM tool_list_item where group_id = " + groupId;

        ConnectionSource conn = DbUtil.conn();
        Dao<ToolListGroup, Integer> dao = DaoManager.createDao(conn, ToolListGroup.class);

        dao.deleteById(groupIdVal);
        dao.executeRawNoArgs(delItemSql);

        conn.close();

        return JSON.toJSONString(json);
    }

    private static String delList(Request req, Response res)
            throws SQLException, InterruptedException {
        JsonMap json = new JsonMap();

        String listName = req.splat()[0];
        String delItemSql = "DELETE t.*\n" +
                "FROM\n" +
                "    tool_list_item t\n" +
                "WHERE\n" +
                "    EXISTS (\n" +
                "        SELECT\n" +
                "            1\n" +
                "        FROM\n" +
                "            tool_list_group g\n" +
                "        WHERE\n" +
                "            g.id = t.group_id\n" +
                "        AND g.list_name = '" + listName + "'\n" +
                "    )";

        String delGroupSql = "DELETE\n" +
                "FROM\n" +
                "    tool_list_group\n" +
                "WHERE\n" +
                "    list_name = '" + listName + "'";

        ConnectionSource conn = DbUtil.conn();
        Dao<ToolListGroup, ?> dao = DaoManager.createDao(conn, ToolListGroup.class);

        dao.executeRawNoArgs(delItemSql);
        dao.executeRawNoArgs(delGroupSql);

        conn.close();

        return JSON.toJSONString(json);
    }

    private static String sort(Request req, Response res)
            throws SQLException, InterruptedException {
        JsonMap json = new JsonMap();

        ConnectionSource conn = DbUtil.conn();
        Dao<ToolListGroup, ?> dao = DaoManager.createDao(conn, ToolListGroup.class);
        //dao.executeRaw(delItemSql);
        //TODO 1
        conn.close();
        return JSON.toJSONString(json);
    }

    private static ModelAndView queryList(String path, Request req, Response resp)
            throws SQLException {
        Map<String, Object> model = new HashMap<>();

        ConnectionSource conn = DbUtil.conn();
        Dao<ToolListGroup, ?> dao = DaoManager.createDao(conn, ToolListGroup.class);

        GenericRawResults<String[]> rawResults = dao.queryRaw(" select list_name,count(*) from tool_list_group group by list_name ");
        java.util.List<String[]> results = rawResults.getResults();

        conn.close();

        model.put("models", results);

        return new ModelAndView(model, path);
    }

    public static void main(String[] args) throws SQLException {
        ConnectionSource conn = DbUtil.conn();
        Dao<ToolListGroup, ?> dao = DaoManager.createDao(conn, ToolListGroup.class);

        java.util.List<ToolListGroup> groups = dao.queryBuilder().orderBy("order", false).where().eq("list_name", "恩物2").query();
        conn.close();
    }
}
