package com.cc.entity;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class Role {

    private Integer id;
    private String roleName;        //角色名称
    private String createDate;      //创建时间
    private String roleDesc;        //描述
    private Integer priority;       //排序
    private Integer status;         //状态
    private String isDisabled;      //是否禁用
    private String operuser;        //操作人
    private String operdate;        //操作时间
    private Integer pid;            //上级id
    private List<Menu> menu;        //菜单集合
    private List<MeetUser> user;    //用户集合


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getIsDisabled() {
        return isDisabled;
    }

    public void setIsDisabled(String isDisabled) {
        this.isDisabled = isDisabled;
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

    public List<Menu> getMenu() {
        return menu;
    }

    public void setMenu(List<Menu> menu) {
        this.menu = menu;
    }

    public List<MeetUser> getUser() {
        return user;
    }

    public void setUser(List<MeetUser> user) {
        this.user = user;
    }
}
