package com.cc.service;

import java.util.List;
import java.util.Map;

public interface NewUserService {

	Map<String, String> queryUserByAccount(String uId);

	Map<String, String> queryUserByName(String name);
	
	List<Map<String, String>> queryAllUser(String likeName, String group);

	List<Map<String, String>> queryAllGroup();

	List<Map<String, String>> queryUserByGroup(String str);


}
