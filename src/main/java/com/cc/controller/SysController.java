package com.cc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.cc.entity.SysAdmin;
import com.cc.service.SysService;
import com.cc.util.GetUser;

@RestController
@RequestMapping("/sys")
public class SysController {

	@Autowired
	private SysService sysService;

	@RequestMapping("/savaAdmin")
	public String savaAdmin(HttpServletRequest request,
			@RequestParam(name = "adminName", required = false, defaultValue = "") String adminName,
			@RequestParam(name = "adminNo", required = true) String adminNo) {
		Map<String, Object> result = new HashMap<>();
		if (sysService.findOne(adminNo) != null) {
			result.put("state", -1);
			result.put("msg", "该人员已是管理员");
		} else if (sysService.savaAdmin(adminNo, adminName, GetUser.current(request))) {
			result.put("state", 1);
			result.put("msg", "添加成功");
		} else {
			result.put("state", -1);
			result.put("msg", "添加失败");
		}
		return JSON.toJSONString(result);
	}

	@RequestMapping("/listData")
	public String listData() {
		List<SysAdmin> dataList = sysService.findAll();
		Map<String, Object> result = new HashMap<>();
		result.put("code", 0);
		result.put("msg", "");
		result.put("data", dataList);
		result.put("count", result.size());
		return JSON.toJSONString(result);
	}

	@RequestMapping("/deleteAdmin")
	public String deleteAdmin(@RequestParam(name = "pId", required = true) String adminNo) {
		Map<String, Object> result = new HashMap<>();
		SysAdmin sysAdmin = sysService.findOne(adminNo);
		if (sysAdmin == null) {
			result.put("state", -1);
			result.put("msg", "不存在人员");
		} else if (sysService.deleteAdmin(sysAdmin)) {
			result.put("state", 1);
			result.put("msg", "删除成功");
		} else {
			result.put("state", -1);
			result.put("msg", "删除失败");
		}
		return JSON.toJSONString(result);
	}

}
