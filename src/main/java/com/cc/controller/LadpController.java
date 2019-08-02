package com.cc.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cc.entity.ResponseData;
import com.cc.service.NewUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/ldap")
public class LadpController {

	@Autowired
	private NewUserService newUserService;

	@RequestMapping("/getUser")
	@ResponseBody
	public ResponseData getUser(Integer page, Integer size, String name, String groupSign) {
		List<Map<String, String>> allData = new ArrayList<>();
		try {
			PageHelper.startPage(page,size);
			allData = newUserService.queryAllUser(name, groupSign); // 全部数据
		} catch (Exception e) {
			e.printStackTrace();
		}
		PageInfo<Map<String, String>> resPage = new PageInfo<Map<String, String>>(allData);
		return new ResponseData((int)resPage.getTotal(), 0, "", resPage.getList());
	}

	@RequestMapping("/queryUser")
	@ResponseBody
	public ResponseData queryUser(String name) {
		Map<String, String> map = null;
		try {
			map = newUserService.queryUserByName(name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ResponseData data = new ResponseData(map.size(), 0, "", map);
		return data;
	}






}
