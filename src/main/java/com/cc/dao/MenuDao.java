package com.cc.dao;

import com.cc.entity.Menu;
import com.cc.entity.MenuNode;
import com.cc.entity.TreeNode;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenuDao {

    List<Menu> findAll(@Param("menuName") String menuName);

    Menu findOne(Integer id);

    void update(Menu menu);

    void add(Menu menu);

    void delete(int id);

    /**
     * 获取菜单列表树
     * @return
     */
    List<TreeNode> menuTreeList();

    /**
     * 获取菜单列表树
     * @param MenuIds
     * @return
     */
    List<TreeNode> menuTreeListByMenuIds(List<Integer> MenuIds);

    /**
     * 根据角色id获取 菜单
     * @param roleId
     * @return
     */
    List<Integer> getMenuIdsByRoleId(Integer roleId);


    /**
     * 根据角色获取菜单
     * @param roleIds
     * @return
     */
    List<MenuNode> getMenusByRoleIds(List<Integer> roleIds);

    void deleteMenu(Integer roleId);

    void addMenu(@Param("roleId") Integer roleId, @Param("menuId") Integer menuId);
}
