package cn.sixlab.web.common.ormlite.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="sixlab_meta")
public class SixlabMeta {

    @DatabaseField(columnName = "id")
    private int id;
    @DatabaseField(columnName = "key_id")
    private int keyId;
    @DatabaseField(columnName = "key")
    private String key;
    @DatabaseField(columnName = "value")
    private String value;
    @DatabaseField(columnName = "flag")
    private String flag;
    @DatabaseField(columnName = "remark")
    private String remark;

    public int getId () {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getKeyId() {
        return keyId;
    }

    public void setKeyId(int keyId) {
        this.keyId = keyId;
    }

    public String getKey () {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue () {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getFlag () {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getRemark () {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}