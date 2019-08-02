package com.cc.service.Impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cc.dao.SysAdminDao;
import com.cc.entity.SysAdmin;
import com.cc.entity.User;
import com.cc.service.SysService;

@Service
public class SysServiceImpl implements SysService {

	@Autowired
	private SysAdminDao sysAdminDao;

	public boolean savaAdmin(String adminNo, String adminName, User current) {
		SysAdmin newSysAdin = new SysAdmin();
		newSysAdin.setAdminNo(adminNo);
		newSysAdin.setAdminName(adminName);
		newSysAdin.setCreateUser(current.getUsername());
		newSysAdin.setCreateTime(new Date());
		try {
			sysAdminDao.save(newSysAdin);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public SysAdmin findOne(String adminNo) {
		return sysAdminDao.findOne(adminNo);
	}

	public List<SysAdmin> findAll() {
		return sysAdminDao.findAll();
	}

	public boolean deleteAdmin(SysAdmin sysAdmin) {
		try {
			sysAdminDao.delete(sysAdmin);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
