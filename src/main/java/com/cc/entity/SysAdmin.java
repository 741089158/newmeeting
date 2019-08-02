package com.cc.entity;

import java.io.Serializable;
import java.util.Date;

public class SysAdmin implements Serializable {

	private static final long serialVersionUID = -3572826467740620052L;
	
	private String adminNo;
	private String adminName;
	private Date createTime;
	private String createUser;

	public String getAdminNo() {
		return adminNo;
	}

	public void setAdminNo(String adminNo) {
		this.adminNo = adminNo;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

}
