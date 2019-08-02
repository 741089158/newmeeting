package com.cc.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cc.bean.OptionBean;
import com.cc.entity.MeetRoom;
import com.cc.service.LdapService;
import com.cc.service.NewUserService;
import com.cc.service.ReMeetRoomService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/select")
public class SelectController {

    @Autowired
    private ReMeetRoomService reMeetRoomService;

    @Autowired
    private NewUserService newUserService;

    // 会议室的地区
    @RequestMapping("/roomArea")
    public String roomArea(HttpServletRequest request) {
        return JSONObject.toJSONString(reMeetRoomService.findArea(request));
    }

    // 无条件查询 会议室的地区
    @RequestMapping("/roomAreaManager")
    public String roomAreaManager() {
        return JSONObject.toJSONString(reMeetRoomService.roomAreaManager());
    }

	/**
	 * 获取会议室区域名，去重
	 */
	@RequestMapping("/distinctRoomArea")
	public String distinctRoomArea() {
		List<OptionBean> result = new ArrayList<>();
		Set<String> disHelper = new HashSet<>(); // 辅助去重
		List<MeetRoom> ccc = reMeetRoomService.roomAreaManager();
		if (ccc == null || ccc.size() == 0) {
			result.add(new OptionBean("", "查无数据"));
			return JSONObject.toJSONString(result);
		}
		for (MeetRoom mr : ccc) {
			String name = mr.getRoomAreaName();
			if (disHelper.contains(name)) {
				continue;
			}
			disHelper.add(name);
			result.add(new OptionBean(name, name));
		}
		return JSONObject.toJSONString(result);
	}

	// 会议室的楼号
	@RequestMapping("/roomBuilding")
	public String roomBuilding(@RequestParam(name = "roomareaname", required = false, defaultValue = "") String roomAreaName) {
		return JSON.toJSONString(reMeetRoomService.findBuilding(roomAreaName));
	}

	// 会议室的楼号
	@RequestMapping("/roomBuilding2")
	public String roomBuilding2(@RequestParam(name = "area", required = false, defaultValue = "") String area) {
		return JSON.toJSONString(reMeetRoomService.findBuilding2(area));
	}

	// 会议室的楼层
	@RequestMapping("/floor")
	public String floor(@RequestParam(name = "roomareaname", required = false, defaultValue = "") String area,
			@RequestParam(value = "building") String building) {
		return JSONObject.toJSONString(reMeetRoomService.floor(area, building));
	}

    // 查询联系人
    @RequestMapping(value = "/internal")
    public String internal(@RequestBody String username) {
        String s = JSONObject.parseObject(username).getString("username");
        System.out.println(s);
        if (s == "" || s == null) {
            s = "";
        }
        List<Map<String, String>> list = newUserService.queryAllUser(s, "");
        if (s != "" && s != null) {
            List<OptionBean> organization = getOrganization();//组织集合
            for (OptionBean optionBean : organization) {
                if (optionBean.getId().contains(s)) {
                    Map<String, String> map = new HashMap<>();
                    map.put("name", optionBean.getId());
                    //map.put("sAMAccountName", optionBean.getId());
                    list.add(map);
                }
            }
        }
        return JSONObject.toJSONString(list);
    }

    //组织
    private static List<OptionBean> organization = null;


    public List<OptionBean> getOrganization() {
        List result = new ArrayList<>();
        if (organization == null) {
            org(result);
        } else {
            result = organization;
        }
        return result;
    }


    //查询组织
    public void org(List result) {
        List<Map<String, String>> orgs = newUserService.queryAllGroup();
        if (orgs != null && orgs.size() > 0) {
            for (Map<String, String> orgMap : orgs) {
                String formatOU = "[Group]" + orgMap.get("ou");
                result.add(new OptionBean(formatOU, formatOU));
            }
        }
        organization = result;
    }

    // 人员下拉框
    @RequestMapping("/person")
    public String person() {
        List<OptionBean> res = new ArrayList<>();
        List<Map<String, String>> users = newUserService.queryAllUser("", "");
        if (users != null && users.size() > 0) {
            for (Map<String, String> user : users) {
                res.add(new OptionBean(user.get("sAMAccountName"), user.get("name")));
            }
        }
        return JSON.toJSONString(res);
    }

    // 所属组织
    @RequestMapping("/organization")
    public String organization() {
        List<OptionBean> res = new ArrayList<>();
        List<Map<String, String>> orgs = newUserService.queryAllGroup();
        if (orgs != null && orgs.size() > 0) {
            for (Map<String, String> org : orgs) {
                String ou = org.get("ou");
                res.add(new OptionBean(ou, ou));
            }
        }
        return JSON.toJSONString(res);
    }

}
