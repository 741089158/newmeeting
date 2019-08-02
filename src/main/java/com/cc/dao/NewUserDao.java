package com.cc.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface NewUserDao {

	public List<Map<String, String>> queryAllGroup();

	public List<Map<String, String>> queryAllUser(@Param("likeName") String likeName, @Param("group") String group);

	public Map<String, String> queryUserByName(String name);

	public Map<String, String> queryUserByAccount(String account);

    List<Map<String,String>> queryUserByGroup(String fullName);
}
