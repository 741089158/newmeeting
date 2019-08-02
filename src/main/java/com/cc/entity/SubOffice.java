package com.cc.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class SubOffice implements Serializable {

    private Integer subOfficeId;
    private String subOfficeName;//分部名称
    private String remark;//备注
    private Integer orderBy;//排序
    private String subId;//分部id
    private String isDisabled;//是否禁用
    private String operUser;//操作人
    private String operDate;//操作时间

    public Integer getSubOfficeId() {
        return subOfficeId;
    }

    public void setSubOfficeId(Integer subOfficeId) {
        this.subOfficeId = subOfficeId;
    }

    public String getSubOfficeName() {
        return subOfficeName;
    }

    public void setSubOfficeName(String subOfficeName) {
        this.subOfficeName = subOfficeName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(Integer orderBy) {
        this.orderBy = orderBy;
    }

    public String getSubId() {
        return subId;
    }

    public void setSubId(String subId) {
        this.subId = subId;
    }

    public String getIsDisabled() {
        return isDisabled;
    }

    public void setIsDisabled(String isDisabled) {
        this.isDisabled = isDisabled;
    }

    public String getOperUser() {
        return operUser;
    }

    public void setOperUser(String operUser) {
        this.operUser = operUser;
    }

    public String getOperDate() {
        return operDate;
    }

    public void setOperDate(String operDate) {
        this.operDate = operDate;
    }
}
