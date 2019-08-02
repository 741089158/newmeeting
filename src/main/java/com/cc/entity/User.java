package com.cc.entity;
import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    private Integer id;
    private Integer subofficeid;
    private String username;
    private String suboffice;
    private String password;
    private String sex;
    private String email;
    private Date createdate;
    private String remark;
    private Integer order;
    private Integer status;
    private Integer isdisabled;
    private String operuser;
    private  Date operdate;
    private String name;
    private String tel;

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private Integer isexternal;  //外部联系人

    public Integer getIsexternal() {
        return isexternal;
    }

    public void setIsexternal(Integer isexternal) {
        this.isexternal = isexternal;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSubofficeid() {
        return subofficeid;
    }

    public void setSubofficeid(Integer subofficeid) {
        this.subofficeid = subofficeid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSuboffice() {
        return suboffice;
    }

    public void setSuboffice(String suboffice) {
        this.suboffice = suboffice;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsdisabled() {
        return isdisabled;
    }

    public void setIsdisabled(Integer isdisabled) {
        this.isdisabled = isdisabled;
    }

    public String getOperuser() {
        return operuser;
    }

    public void setOperuser(String operuser) {
        this.operuser = operuser;
    }

    public Date getOperdate() {
        return operdate;
    }

    public void setOperdate(Date operdate) {
        this.operdate = operdate;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", subofficeid=" + subofficeid +
                ", username='" + username + '\'' +
                ", suboffice='" + suboffice + '\'' +
                ", password='" + password + '\'' +
                ", sex='" + sex + '\'' +
                ", email='" + email + '\'' +
                ", createdate=" + createdate +
                ", remark='" + remark + '\'' +
                ", order=" + order +
                ", status=" + status +
                ", isdisabled=" + isdisabled +
                ", operuser='" + operuser + '\'' +
                ", operdate=" + operdate +
                ", name='" + name + '\'' +
                ", tel='" + tel + '\'' +
                ", isexternal=" + isexternal +
                '}';
    }
}

