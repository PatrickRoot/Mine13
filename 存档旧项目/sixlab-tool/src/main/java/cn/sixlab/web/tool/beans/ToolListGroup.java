package cn.sixlab.web.tool.beans;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.List;

@DatabaseTable(tableName = "tool_list_group")
public class ToolListGroup {

    @DatabaseField(generatedId = true)
    private Integer id;

    @DatabaseField(columnName = "group_name")
    private String groupName;

    @DatabaseField(columnName = "order")
    private Integer order;

    @DatabaseField(columnName = "list_name")
    private String listName;

    private List<ToolListItem> toolListItemList;

    public List<ToolListItem> getToolListItemList() {
        return toolListItemList;
    }

    public void setToolListItemList(
            List<ToolListItem> toolListItemList) {
        this.toolListItemList = toolListItemList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }
}