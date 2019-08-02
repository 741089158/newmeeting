package com.cc.service;

import java.util.List;
import java.util.Map;

import com.cc.entity.MeetUser;
import com.cc.entity.UserInternal;

/**
 * @author HOEP
 * @data 2019/4/24
 */
public interface MeetUserService  {
	List<Map<String, String>> findAll(Integer page, Integer size, String username);

	void add(MeetUser meetUser);

	Map<String, String> findById(Integer Id);

	void update(MeetUser meetUser);

	void delete(Integer id);

	List<UserInternal> findInternal(Integer page, Integer size, String internal, String name);

	Map<String, Object> findInternalJSON(Integer page, Integer size, String internal, String name);

	List<UserInternal> findInternal(String internal, String name);

    void addInternal(UserInternal internal);

	void deleteInternal(Integer id);

	void deleteInternal(Integer[] ids);

	UserInternal findOne(Integer id);

	void updateLinkman(UserInternal userInternal);

    List<Map<String ,String>> findDept();

    MeetUser findByUsername(String username);

	List<Integer> getRoleIdByUserId(Integer id);
}
