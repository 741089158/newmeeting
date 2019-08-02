package com.cc.entity;

import lombok.Data;
import lombok.ToString;

/**
 * jquery ztree 插件的节点
 *
 */
@Data
@ToString
public class TreeNode {

    /**
     * 节点id
     */
    private Long id;

    /**
     * 父节点id
     */
    private Long pId;

    /**
     * 节点名称
     */
    private String name;

    /**
     * 是否打开节点
     */
    private Boolean open;

    /**
     * 是否被选中
     */
    private Boolean checked;

    /**
     * 节点图标  single or group
     */
    private String iconSkin;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPId() {
        return pId;
    }

    public void setPId(Long pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public String getIconSkin() {
        return iconSkin;
    }

    public void setIconSkin(String iconSkin) {
        this.iconSkin = iconSkin;
    }

    /**
     * 创建tree的父级节点
     *
     */
    public static TreeNode createParent() {
        TreeNode treeNode = new TreeNode();
        treeNode.setChecked(true);
        treeNode.setId(0L);
        treeNode.setName("顶级");
        treeNode.setOpen(true);
        treeNode.setPId(0L);
        return treeNode;
    }
}
