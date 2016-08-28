package cn.sixlab.web.tool.beans;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

@DatabaseTable(tableName="tools_record_val")
public class ToolsRecordVal {

    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(columnName = "item_id")
    private int itemId;
    @DatabaseField(columnName = "record_date")
    private Date recordDate;
    @DatabaseField(columnName = "record_val")
    private String recordVal;

    public int getId () {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getItemId () {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public Date getRecordDate () {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    public String getRecordVal () {
        return recordVal;
    }

    public void setRecordVal(String recordVal) {
        this.recordVal = recordVal;
    }

}