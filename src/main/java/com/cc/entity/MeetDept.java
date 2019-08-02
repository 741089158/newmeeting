package com.cc.entity;


import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author HOEP
 * @data 2019/4/23
 */

@Data
@ToString
public class MeetDept implements Serializable {
//    部门id

    private String deptid;
//    部门地址

    private String deptaddr;
//    默认

    private String deptdefault;
//    部门名称

    private String deptname;
//    电话

    private String depttel;
//    邮件

    private String email;
//    所属分部id

    private Integer subid;
//    结束时间

    private String endtime;
//    开始时间

    private String starttime;




    public String getDeptid() {
        return deptid;
    }

    public void setDeptid(String deptid) {
        this.deptid = deptid;
    }

    public String getDeptaddr() {
        return deptaddr;
    }

    public void setDeptaddr(String deptaddr) {
        this.deptaddr = deptaddr;
    }

    public String getDeptdefault() {
        return deptdefault;
    }

    public void setDeptdefault(String deptdefault) {
        this.deptdefault = deptdefault;
    }

    public String getDeptname() {
        return deptname;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname;
    }

    public String getDepttel() {
        return depttel;
    }

    public void setDepttel(String depttel) {
        this.depttel = depttel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getSubid() {
        return subid;
    }

    public void setSubid(Integer subid) {
        this.subid = subid;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

}
