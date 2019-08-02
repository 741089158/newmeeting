package com.cc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.cc.entity.UserInternal;

/**
 * @author TANGXIAN
 */
@Repository
public interface AddUserDao {

	void addUser(@Param("userId") String userId, @Param("meetId") String meetId);

	void deleteUser(@Param("userId") String userId, @Param("meetId") String meetId);

	List<UserInternal> findUserByMeetId(String id);

	UserInternal findByUserId(int id);

}
