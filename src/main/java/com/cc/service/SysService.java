package com.cc.service;

import java.util.List;

import com.cc.entity.SysAdmin;
import com.cc.entity.User;

public interface SysService {

	boolean savaAdmin(String adminNo, String adminName, User current);

	SysAdmin findOne(String adminNo);

	List<SysAdmin> findAll();

	boolean deleteAdmin(SysAdmin sysAdmin);

}
