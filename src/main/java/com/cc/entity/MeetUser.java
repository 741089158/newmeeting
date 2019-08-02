package com.cc.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 用户实体类
 * @author HOEP
 * @data 2019/4/24
 */

@Data
@ToString
public class MeetUser implements Serializable {
//    用户ID
    private Integer id;
//    所属分部ID
    private Integer subofficeid;
//    用户名
    private String username;
//    所属分部
    private String suboffice;
//    密码
    private String password;
//    性别
    private String sex;
//    邮箱
    private String email;
//    创建时间
    private String createdate;
//    备注
    private String tel;
//    排序
    private Integer orderBy;
//    状态
    private Integer status;
//    是否禁用
    private Integer isdisabled;
//    操作人
    private String operuser;
//    操作时间
    private String operdate;
//    用户职位
    private String deptid;

    private String uname;

    private Integer isExternal;

    private List<Role> roles;


    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
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

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
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

    public String getOperdate() {
        return operdate;
    }

    public void setOperdate(String operdate) {
        this.operdate = operdate;
    }

    public String getDeptid() {
        return deptid;
    }

    public void setDeptid(String deptid) {
        this.deptid = deptid;
    }

    public Integer getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(Integer orderBy) {
        this.orderBy = orderBy;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public Integer getIsExternal() {
        return isExternal;
    }

    public void setIsExternal(Integer isExternal) {
        this.isExternal = isExternal;
    }
}
