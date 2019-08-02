package com.cc.service.Impl;

import com.cc.dao.RoleDao;
import com.cc.entity.Menu;
import com.cc.entity.Role;
import com.cc.entity.TreeNode;
import com.cc.service.RoleService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public List<Menu> findMenu() {
        return roleDao.findMenu();
    }

    @Override
    public List<Role> findAll(Integer page, Integer limit, String roleName) {
        PageHelper.startPage(page, limit);
        return roleDao.findAll(roleName);
    }

    @Override
    public Role findOne(Integer id) {
        return roleDao.findOne(id);
    }

    @Override
    public void update(Role role) {
        roleDao.update(role);
    }

    @Override
    public void add(Role role) {
        roleDao.add(role);
    }

    @Override
    public void delete(int id) {
        roleDao.delete(id);
    }

    @Override
    public List<TreeNode> roleTreeList() {
        return roleDao.roleTreeList();
    }

    @Override
    public List<TreeNode> roleTreeListByUserId(List<Integer> roleId) {
        return roleDao.roleTreeListByUserId(roleId);
    }

    @Override
    public List<Integer> findRoleByUserId(Integer userId) {
        List<Map<Integer, Integer>> roleId = roleDao.findRoleByUserId(userId);
        List<Integer> list = new ArrayList<>();
        if (roleId != null) {
            for (Map<Integer, Integer> map : roleId) {
                list.add(map.get("roleId"));
            }
        }
        return list;
    }

    @Override
    public void deleteRole(Integer userId) {
        roleDao.deleteRole(userId);
    }

    @Override
    public void addRole(Integer userId,List<String> id) {
        for (String roleId : id) {
            roleDao.addRole(userId,Integer.parseInt(roleId));
        }
    }
}
