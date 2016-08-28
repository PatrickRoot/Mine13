package cn.sixlab.web.tool.beans;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "tool_list_item")
public class ToolListItem {

    @DatabaseField(generatedId = true)
    private Integer id;

    @DatabaseField(columnName = "item_name")
    private String itemName;

    @DatabaseField(columnName = "group_id")
    private Integer groupId;

    @DatabaseField(columnName = "order")
    private Integer order;

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }
}