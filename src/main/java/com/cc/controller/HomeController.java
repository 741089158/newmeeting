package com.cc.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	// 首页
	@RequestMapping("/home")
	public String home(HttpServletRequest request) {
		return "/home";
	}

	// 会议预定
	@RequestMapping("/reserve")
	public String reserve(HttpServletRequest request) {
		return "/home";
	}

	// 我的会议   /home/own
	@RequestMapping("/own")
	public String own(HttpServletRequest request) {
		return "/home/own";
	}

	//我的日程
	@RequestMapping("/schedule")
	public String schedule(HttpServletRequest request) {
		return "/schedule/userSchedule";
	}

	// 历史会议
	@RequestMapping("/history")
	public String history(HttpServletRequest request) {
		return "/home/history";
	}

	// 会议管理
	@RequestMapping("/roomManage")
	public String roomManage(HttpServletRequest request) {
		return "/home/roomManage";
	}

	// 系统管理
	@RequestMapping("/sysManage")
	public String sysManage(HttpServletRequest request) {
		return "/home/sysManage";
	}

	// 后台管理
	@RequestMapping("/userManage")
	public String userManage(HttpServletRequest request) {
		return "/home/roomManage";
	}

	// 后台管理
	@RequestMapping("/repeat")
	public String repeat(HttpServletRequest request) {
		return "/home/repeat";
	}

}
