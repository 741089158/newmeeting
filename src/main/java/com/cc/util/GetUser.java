package com.cc.util;

import javax.servlet.http.HttpServletRequest;

import com.cc.entity.User;

public class GetUser {

	private static final String DEFAULT_USERNAME = "E000051";
	private static final String DEFAULT_NAME = "测试用户";
	private static final String DEFAULT_DEPARTMENT = "WHYMTC";

	public static User current(HttpServletRequest request) {
		User newUser = new User();
		String unAttr = (String) request.getSession().getAttribute("username");
		if (unAttr == null) {
			newUser.setUsername(DEFAULT_USERNAME);
		} else {
			newUser.setUsername(unAttr);
		}
		String nAttr = (String) request.getSession().getAttribute("name");
		if (nAttr == null) {
			newUser.setName(DEFAULT_NAME);
		} else {
			newUser.setName(nAttr);
		}
		String depAttr = (String) request.getSession().getAttribute("department");
		if (depAttr == null) {
			newUser.setSuboffice(DEFAULT_DEPARTMENT);
		} else {
			newUser.setSuboffice(depAttr);
		}
		return newUser;
	}

}
