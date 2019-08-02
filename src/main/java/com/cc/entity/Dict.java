package com.cc.entity;


import java.io.Serializable;


public class Dict implements Serializable {


    private Integer dictId;      //id
    private String name;    //名称
    private String code;    //字典编码
    private String description;    //描述
    private Integer pid;     //父级id
    private Integer orderby;  //排序

    public Integer getDictId() {
        return dictId;
    }

    public void setDictId(Integer dictId) {
        this.dictId = dictId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getOrderby() {
        return orderby;
    }

    public void setOrderby(Integer orderby) {
        this.orderby = orderby;
    }

    @Override
    public String toString() {
        return "Dict{" +
                "dictId=" + dictId +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", pid=" + pid +
                ", orderby=" + orderby +
                '}';
    }
}
