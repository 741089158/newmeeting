package com.cc.dao;

import com.cc.entity.Menu;
import com.cc.entity.Role;
import com.cc.entity.TreeNode;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface RoleDao {
    List<Menu> findMenu();

    List<Role> findAll(@Param("roleName") String roleName);

    Role findOne(Integer id);

    void update(Role role);

    void delete(int id);

    void add(Role role);

    List<TreeNode> roleTreeList();

    List<TreeNode> roleTreeListByUserId(List<Integer> roleId);

    List<Map<Integer,Integer>> findRoleByUserId(Integer userId);

    void deleteRole(Integer id);

    void addRole(@Param("userId") Integer userId, @Param("roleId") Integer roleId);
}
