package com.cc.service.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cc.dao.NewUserDao;
import com.cc.service.NewUserService;

@Service
public class NewUserServiceImpl implements NewUserService {

	@Autowired
	private NewUserDao newUserDao;

	@Override
	public Map<String, String> queryUserByAccount(String uId) {
		return newUserDao.queryUserByAccount(uId);
	}

	@Override
	public Map<String, String> queryUserByName(String name) {
		return newUserDao.queryUserByName(name);
	}

	@Override
	public List<Map<String, String>> queryAllUser(String likeName, String group) {
		likeName = likeName.replace("'", ""); // 替换单引号
		return newUserDao.queryAllUser(likeName, group);
	}

	@Override
	public List<Map<String, String>> queryAllGroup() {
		return newUserDao.queryAllGroup();
	}


	// 根据组名查询用户
	@Override
	public List<Map<String, String>> queryUserByGroup(String str) {

		return newUserDao.queryUserByGroup(str);
	}

}
