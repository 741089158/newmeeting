//package com.cc.component;
//
//import javax.servlet.Filter;
//
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import edu.yale.its.tp.cas.client.filter.CASFilter;
//
//@Configuration
//public class FilterConfig {
//
//	//单点登陆
//	//@Bean
//	public FilterRegistrationBean<Filter> testFilterRegistration() {
//		FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<>();
//		registration.setFilter(new CASFilter());
//		registration.addUrlPatterns("/*");
//		registration.addInitParameter("edu.yale.its.tp.cas.client.filter.loginUrl", "http://ssoservice.ymtc.com/sso-server/login");
//		registration.addInitParameter("edu.yale.its.tp.cas.client.filter.validateUrl", "http://ssoservice.ymtc.com/sso-server/serviceValidate");
//		registration.addInitParameter("edu.yale.its.tp.cas.client.filter.serverName", "10.18.88.26:8080");
//		//registration.addInitParameter("edu.yale.its.tp.cas.client.filter.serverName", "localhost:8080");
//		registration.addInitParameter("edu.yale.its.tp.cas.client.filter.initContextClass", "com.cc.component.MyContextInit");
//		registration.setName("SSO Filter");
//		registration.setOrder(1);
//		return registration;
//	}
//
//}
