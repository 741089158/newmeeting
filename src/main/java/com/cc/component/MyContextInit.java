package com.cc.component;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import edu.yale.its.tp.cas.client.IContextInit;

public class MyContextInit implements IContextInit {

	@Override
	public String getTranslatorUser(String userName) {
		return userName;
	}

	@Override
	public void initContext(ServletRequest request, ServletResponse servletResponse, FilterChain filterChain, String userName) {
		((HttpServletRequest) request).getSession().setAttribute("username", userName);
		//((HttpServletRequest) request).getSession().setAttribute("username", "A905663");
	}
}
