package com.cc.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cc.dao.AddUserDao;
import com.cc.entity.UserInternal;
import com.cc.service.AddUserService;

/**
 * @author TANGXIAN
 */
@Service("addUserService")
public class AddUserServiceImpl implements AddUserService {
    @Autowired
    private AddUserDao addUserDao;


    public void addUser(String userId, String meetId) {
        addUserDao.addUser(userId,meetId);
    }

    @Override
    public List<UserInternal> findUserByMeetId(String userId) {
        return addUserDao.findUserByMeetId(userId);
    }

    @Override
    public void deleteUser(String userId, String meetId) {
        addUserDao.deleteUser(userId,meetId);
    }

    @Override
    public UserInternal findByUserId(int id) {
        return addUserDao.findByUserId(id);
    }



}
