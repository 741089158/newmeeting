package com.cc.dao;

import com.cc.entity.MeetUser;
import com.cc.entity.UserInternal;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author HOEP
 * @data 2019/4/24
 */
public interface MeetUserDao {
    List<Map<String,String>> findAll(@Param("username") String username);

    void add(MeetUser meetUser);


    Map<String,String> findById(Integer Id);

    void update(MeetUser meetUser);

    void delete(Integer id);


    /*查询联系人*/
    List<UserInternal> findInternal(@Param("internal") String internal, @Param("name") String name);

    void addInternal(UserInternal internal);

    void deleteInternal(Integer id);


    UserInternal findOne(Integer id);

    void updateLinkman(UserInternal userInternal);

    MeetUser findByUsername(String username);

    List<Map<String,String>> findMenuListByUsername(String username);

    List<Integer> getRoleIdByUserId(Integer id);
}
