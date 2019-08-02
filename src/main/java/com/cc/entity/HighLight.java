package com.cc.entity;


import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class HighLight {

    private int id;
    private String uid;     //highlight人工号
    private int mid;        //关联会议id
    private String description;//描述
    private String createTime;//创建时间
    private String hid;     //被highlight人工号

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getHid() {
        return hid;
    }

    public void setHid(String hid) {
        this.hid = hid;
    }
}
