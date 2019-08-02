package com.cc.service.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cc.dao.MeetDeptDao;
import com.cc.dao.MeetUserDao;
import com.cc.entity.MeetUser;
import com.cc.entity.UserInternal;
import com.cc.service.MeetUserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * @author HOEP
 * @data 2019/4/24
 */
@Service("meetUserServiceImpl")
public class MeetUserServiceImpl implements MeetUserService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MeetUserDao meetUserDao;

    @Autowired
    private MeetDeptDao meetDeptDao;

//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;


//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        System.out.println("经过认证类:"+username);
//        return new User(username,"",getAuthority(username));
//        //return null;
//    }

//    public List<?> getAuthority(String username){
//        return null;
//    }
//
//    //作用就是返回一个List集合，集合中装入的是角色描述
//    public List<?> getAuthority(List<Role> roles) {
//        return list;
//    }


    public List<Map<String, String>> findAll(Integer page, Integer size, String username) {
        PageHelper.startPage(page, size);
        return meetUserDao.findAll(username);
    }

    public void add(MeetUser meetUser) {
        //对密码进行加密处理
        //meetUser.setPassword(bCryptPasswordEncoder.encode(meetUser.getPassword()));
        meetUserDao.add(meetUser);
    }


    public Map<String, String> findById(Integer Id) {
        return meetUserDao.findById(Id);
    }

    public void update(MeetUser meetUser) {
        //对密码进行加密处理
        //meetUser.setPassword(bCryptPasswordEncoder.encode(meetUser.getPassword()));
        meetUserDao.update(meetUser);
    }

    public void delete(Integer id) {
        meetUserDao.delete(id);
    }

    /**
     * 查询内部联系人
     *
     * @param page
     * @param size
     * @param internal
     * @return
     */

    public List<UserInternal> findInternal(Integer page, Integer size, String internal, String name) {
        PageHelper.startPage(page, size);
        List<UserInternal> list = meetUserDao.findInternal(internal, name);
        return list;
    }

    /**
     * 查找内部联系人
     */
    @Override
    public Map<String, Object> findInternalJSON(Integer page, Integer size, String internal, String name) {
        Page<Object> p = PageHelper.startPage(page, size);
        List<UserInternal> list = meetUserDao.findInternal(internal, name);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", p.getTotal());
        map.put("data", list);
        return map;
    }

    @Override
    public List<UserInternal> findInternal(String internal, String name) {
        List<UserInternal> list = meetUserDao.findInternal(internal, name);
        return list;
    }


    /**
     * 添加联系人
     *
     * @param internal
     */
    public void addInternal(UserInternal internal) {
        internal.setStatus(1);
        meetUserDao.addInternal(internal);
    }

    /**
     * 删除
     *
     * @param
     */

    @Transactional  //事务管理
    public void deleteInternal(Integer id) {
        meetUserDao.deleteInternal(id);
    }

    /**
     * 批量删除
     *
     * @param ids
     */

    @Transactional  //事务管理
    public void deleteInternal(Integer[] ids) {
        try {
            if (ids != null && ids.length > 0) {
                for (Integer id : ids) {
                    meetUserDao.deleteInternal(id);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public UserInternal findOne(Integer id) {
        return meetUserDao.findOne(id);
    }

    public void updateLinkman(UserInternal userInternal) {
        meetUserDao.updateLinkman(userInternal);
    }

    @Override
    public List<Map<String, String>> findDept() {
        return meetDeptDao.findDept();
    }

    @Override
    public MeetUser findByUsername(String username) {
        return meetUserDao.findByUsername(username);
    }

    @Override
    public List<Integer> getRoleIdByUserId(Integer id) {
        return meetUserDao.getRoleIdByUserId(id);
        //return null;
    }

}
