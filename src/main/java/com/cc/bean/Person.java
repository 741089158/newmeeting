package com.cc.bean;

import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.Entry;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Entry(objectClasses = { "User" }, base = "ou=Users Org Chart,DC=xmc,DC=wh")
public class Person {
	/**
	 * 主键
	 */
	@Attribute
	private String personId;

	/**
	 * 人员姓名
	 */
	@Attribute(name = "cn")
	private String personName;

	/**
	 * 组织ID
	 */
	@Attribute(name = "orgId")
	private String orgId;

	/**
	 * 性别
	 */
	@Attribute(name = "sex")
	private Integer sex;

	/**
	 * 电话
	 */
	@Attribute(name = "mobile")
	private String mobile;

	/**
	 * 邮箱
	 */
	@Attribute(name = "email")
	private String email;

	/**
	 * 工号
	 */
	@Attribute(name = "jobNo")
	private String jobNo;

	/**
	 * 状态
	 */
	@Attribute
	protected Integer status;

	@Attribute
	protected Integer disOrder;

	/**
	 * 工作单位
	 */
	@Attribute
	private String company;

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getJobNo() {
		return jobNo;
	}

	public void setJobNo(String jobNo) {
		this.jobNo = jobNo;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getDisOrder() {
		return disOrder;
	}

	public void setDisOrder(Integer disOrder) {
		this.disOrder = disOrder;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

}
