package cn.sixlab.web.tool.beans;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="tools_record_item")
public class ToolsRecordItem {

    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(columnName = "item_name")
    private String itemName;
    @DatabaseField(columnName = "order")
    private int order;
    @DatabaseField(columnName = "remark")
    private String remark;

    public int getId () {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemName () {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getOrder () {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getRemark () {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}