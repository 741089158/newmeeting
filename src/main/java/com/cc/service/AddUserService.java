package com.cc.service;

import java.util.List;

import com.cc.entity.UserInternal;


public interface AddUserService {
   void addUser(String userId, String meetId);
   List<UserInternal> findUserByMeetId(String userId);

   void deleteUser(String userId, String meetId);

   UserInternal findByUserId(int id);
}
