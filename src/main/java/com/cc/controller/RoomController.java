package com.cc.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.cc.bean.OptionBean;
import com.cc.entity.MeetRoom;
import com.cc.entity.Remeet;
import com.cc.entity.ResponseData;
import com.cc.entity.User;
import com.cc.service.AppointmentMeetService;
import com.cc.service.MeetRoomService;
import com.cc.service.NewRoomService;
import com.cc.service.NewUserService;
import com.cc.service.ReMeetRoomService;
import com.cc.util.DateChange;
import com.cc.util.GetUser;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("room")
@CrossOrigin
public class RoomController {

	@Autowired
	private ReMeetRoomService reMeetRoomService;

	@Autowired
	private MeetRoomService meetRoomService;

	@Autowired
	private NewUserService newUserService;

	@Autowired
	private AppointmentMeetService appointmentMeetService;

	@RequestMapping("schedule")
	public String schedule() {
		// 该方法已经过时， 请转至ReserveController
		return "/schedule/schedule";
	}

	@RequestMapping("userSchedule")
	public String userSchedule() {
		// 该方法已经过时， 请转至ReserveController
		return "/schedule/userSchedule";
	}

	@Autowired
	private NewRoomService newRoomService;

	/**
	 * ^查询楼层和楼层下的会议室<br>
	 * ^只展示公开状态和登录人为管理员的会议室
	 */
	@RequestMapping("roomData")
	@ResponseBody
	public String roomData(HttpServletRequest request,
			@RequestParam(name = "roomareaname", required = false, defaultValue = "") String area,
			@RequestParam(name = "building", required = false, defaultValue = "") String building) {
		User current = GetUser.current(request);
		//System.out.println(current);
		return reMeetRoomService.findFloor(area, building, current);
	}

	/**
	 * 预定会议冲突检查
	 * 
	 * @param date     日期 格式 yyyy-MM-dd
	 * @param time     时间 格式 HH:mm
	 * @param duration 时长 格式 HH:mm
	 * @return
	 */
	@RequestMapping("/checkTime")
	@ResponseBody
	public Object checkTime(String date, String time, String duration) throws ParseException {
		String dateTime = date + " " + time;
		// 1:查询所有预订会议
		List<Remeet> list = null;
		try {
			list = appointmentMeetService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 2:获取会议开始事件结束事件
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date startTime = sf.parse(dateTime);// 预订会议开始时间
		Date endTime = new Date(startTime.getTime() + DateChange.changeTime(duration));// 预订会议结束时间

		List<Remeet> meets = new ArrayList<Remeet>();
		// 3判断该时间段内是否有被占用的会议室
		for (Remeet remeet : list) {
			String meetDate = remeet.getMeetDate(); // 会议开始时间
			String meetTime = remeet.getMeetTime();// 已预订会议时长
			Date startTime1 = sf.parse(meetDate);// 已预订会议开始时间
			Date endTime1 = new Date(startTime1.getTime() + DateChange.changeTime(meetTime));// 已预订会议结束时间
			if ((startTime.compareTo(startTime1) >= 0 && startTime.compareTo(endTime1) < 0)
					|| (endTime.compareTo(endTime1) <= 0 && endTime.compareTo(startTime1) > 0)
					|| (startTime.compareTo(startTime1) <= 0 && endTime.compareTo(endTime1) >= 0)) {
				meets.add(remeet);
			}
		}
		return meets;
	}

	/**
	 * 预定会议冲突检查
	 * 
	 * @param date     日期 格式 yyyy-MM-dd
	 * @param time     时间 格式 HH:mm
	 * @param duration 时长 格式 HH:mm
	 * @return
	 */
	@RequestMapping("/checkRoom")
	@ResponseBody
	public Object checkRoom(String meetRoomId, String date, String time, String duration) throws ParseException {
		System.out.println(date + "----" + time + "----" + duration);
		String dateTime = date + " " + time;
		// 1:查询所有预订会议
		List<Remeet> list = null;
		try {
			list = appointmentMeetService.findMeetingByRoomId(meetRoomId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 2:获取会议开始事件结束事件
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date startTime = sf.parse(dateTime);// 预订会议开始时间
		Date endTime = new Date(startTime.getTime() + DateChange.changeTime(duration));// 预订会议结束时间

		List<Remeet> meets = new ArrayList<Remeet>();
		// 3判断该时间段内是否有被占用的会议室
		for (Remeet remeet : list) {
			String meetDate = remeet.getMeetDate(); // 会议开始时间
			String meetTime = remeet.getMeetTime();// 已预订会议时长
			Date startTime1 = sf.parse(meetDate);// 已预订会议开始时间
			Date endTime1 = new Date(startTime1.getTime() + DateChange.changeTime(meetTime));// 已预订会议结束时间
			if ((startTime.compareTo(startTime1) >= 0 && startTime.compareTo(endTime1) < 0)
					|| (endTime.compareTo(endTime1) <= 0 && endTime.compareTo(startTime1) > 0)
					|| (startTime.compareTo(startTime1) <= 0 && endTime.compareTo(endTime1) >= 0)) {
				meets.add(remeet);
			}
		}
		return meets;
	}

	// 跳转到预定会议页面
	@RequestMapping("openReserveWin")
	public String openReserveWin(HttpServletRequest request,
			@RequestParam(name = "roomId", required = true) String roomId,
			@RequestParam(name = "date", required = true) String date,
			@RequestParam(name = "time", required = true) String time,
			@RequestParam(name = "meetTimes", required = true) String meetTime) {

		User current = GetUser.current(request);
		String meetName = "%s预定的会议";
		request.setAttribute("meetName", String.format(meetName, current.getName()));// 会议名称
		MeetRoom meetRoom = reMeetRoomService.findById(roomId);
		request.setAttribute("roomId", roomId); // 会议室号
		String datetime = date.trim() + " " + time.trim();
		request.setAttribute("date", date); // 日期
		request.setAttribute("time", time); // 开始时间
		request.setAttribute("meetTimes", meetTime); // 结束时间
		request.setAttribute("datetime", datetime); // 日期 + 时间
		request.setAttribute("meetRoom", meetRoom);

		// 获取人员和分组
		request.setAttribute("usersAndOrgs", usersAndOrgs());

		return "room/reserve";
	}





	/**
	 * 获取人员和组织
	 */
	private List<OptionBean> usersAndOrgs() {
		List<OptionBean> usersAndOrgs = new ArrayList<>();
		List<Map<String, String>> users = newUserService.queryAllUser("", "");
		if (users != null && users.size() > 0) {
			for (Map<String, String> userMap : users) {
				String id = userMap.get("name");
				String title = userMap.get("sAMAccountName") + " " + userMap.get("name");
				usersAndOrgs.add(new OptionBean(id, title));
			}
		}
		List<Map<String, String>> orgs = newUserService.queryAllGroup();
		if (orgs != null && orgs.size() > 0) {
			for (Map<String, String> orgMap : orgs) {
				String formatOU = "[Group]" + orgMap.get("ou");
				usersAndOrgs.add(new OptionBean(formatOU, formatOU));
			}
		}
		return usersAndOrgs;
	}

	@RequestMapping("openCreateWin")
	public String openCreateWin(HttpServletRequest request) {
		return "room/create";
	}

	@RequestMapping("openUpdateWin")
	public String openUpdateWin(HttpServletRequest request,
			@RequestParam(name = "roomId", required = true) String roomId) {
		Map<String, Object> roomMap = newRoomService.showOne(roomId);
		System.out.println(JSON.toJSONString(roomMap));
		request.setAttribute("room", roomMap);
		return "room/update";
	}

	// 添加会议室
	@RequestMapping("createRoom")
	@ResponseBody
	public String createRoom(HttpServletRequest request,
			@RequestParam(name = "isNormal", required = false, defaultValue = "") String isNormal,
			@RequestParam(name = "isPublic", required = false, defaultValue = "") String isPublic,
			@RequestParam(name = "roomAddr1", required = false, defaultValue = "") String roomAddr1,
			@RequestParam(name = "roomAddr1Name", required = false, defaultValue = "") String roomAddr1Name,
			@RequestParam(name = "roomAddr2", required = false, defaultValue = "") String roomAddr2,
			@RequestParam(name = "roomAddr3", required = false, defaultValue = "") String roomAddr3,
			@RequestParam(name = "roomAddr4", required = false, defaultValue = "") String roomAddr4,
			@RequestParam(name = "roomAdmin", required = false, defaultValue = "") String roomAdmin,
			@RequestParam(name = "roomAdminName", required = false, defaultValue = "") String roomAdminName,
			@RequestParam(name = "roomName", required = false, defaultValue = "") String roomName,
			@RequestParam(name = "roomResources[]", required = false, defaultValue = "") String[] roomResources,
			@RequestParam(name = "subOrg", required = false, defaultValue = "") String subOrg,
			@RequestParam(name = "volume", required = false, defaultValue = "0") String volume) {

		Map<String, Object> roomMap = new HashMap<>();
		roomMap.put("isExamine", "on".equals(isNormal) ? "1" : "0");
		roomMap.put("isPublic", "on".equals(isPublic) ? "1" : "0");
		roomMap.put("areaId", roomAddr1);
		roomMap.put("roomAreaName", roomAddr1Name);
		if (roomAddr1Name.equals("WH")){
			roomMap.put("roomArea", "cn-wh");
		}
		if (roomAddr1Name.equals("SH")){
			roomMap.put("roomArea", "cn-sh");
		}

		roomMap.put("roomBuilding", roomAddr2);
		roomMap.put("roomFloor", roomAddr4);
		roomMap.put("managerUid", roomAdmin);
		roomMap.put("manager", roomAdminName);
		roomMap.put("roomName", roomName);
		roomMap.put("subOrg", subOrg);
		roomMap.put("personCount", volume);
		if (newRoomService.saveRoom(roomMap, GetUser.current(request))) {
			Map<String, Object> result = new HashMap<>();
			result.put("state", 1);
			result.put("msg", "添加成功");
			return JSON.toJSONString(result);
		}
		Map<String, Object> result = new HashMap<>();
		result.put("state", -1);
		result.put("msg", "添加失败");
		return JSON.toJSONString(result);
	}

	// 添加会议室
	@RequestMapping("updateRoom")
	@ResponseBody
	public String updateRoom(HttpServletRequest request, @RequestParam(name = "roomId", required = true) String roomId,
			@RequestParam(name = "isNormal", required = false, defaultValue = "") String isNormal,
			@RequestParam(name = "isPublic", required = false, defaultValue = "") String isPublic,
			@RequestParam(name = "roomAddr1", required = false, defaultValue = "") String roomAddr1,
			@RequestParam(name = "roomAddr1Name", required = false, defaultValue = "") String roomAddr1Name,
			@RequestParam(name = "roomAddr2", required = false, defaultValue = "") String roomAddr2,
			@RequestParam(name = "roomAddr3", required = false, defaultValue = "") String roomAddr3,
			@RequestParam(name = "roomAddr4", required = false, defaultValue = "") String roomAddr4,
			@RequestParam(name = "roomAdmin", required = false, defaultValue = "") String roomAdmin,
			@RequestParam(name = "roomAdminName", required = false, defaultValue = "") String roomAdminName,
			@RequestParam(name = "roomName", required = false, defaultValue = "") String roomName,
			@RequestParam(name = "roomResources[]", required = false, defaultValue = "") String[] roomResources,
			@RequestParam(name = "subOrg", required = false, defaultValue = "") String subOrg,
			@RequestParam(name = "volume", required = false, defaultValue = "0") String volume) {

		Map<String, Object> roomMap = newRoomService.showOne(roomId);
		if (roomMap == null || roomMap.size() == 0) {
			Map<String, Object> result = new HashMap<>();
			result.put("state", -2);
			result.put("msg", "未找到指定对象");
			return JSON.toJSONString(result);
		}
		roomMap.put("isExamine", "on".equals(isNormal) ? "1" : "0");
		roomMap.put("isPublic", "on".equals(isPublic) ? "1" : "0");
		roomMap.put("areaId", roomAddr1);
		roomMap.put("roomAreaName", roomAddr1Name);
		if (roomAddr1Name.equals("WH")){
			roomMap.put("roomArea", "cn-wh");
		}
		if (roomAddr1Name.equals("SH")){
			roomMap.put("roomArea", "cn-sh");
		}
		roomMap.put("roomBuilding", roomAddr2);
		roomMap.put("roomFloor", roomAddr4);
		roomMap.put("managerUid", roomAdmin);
		roomMap.put("manager", roomAdminName);
		roomMap.put("roomName", roomName);
		roomMap.put("subOrg", subOrg);
		roomMap.put("personCount", volume);

		if (newRoomService.updateRoom(roomMap, GetUser.current(request))) {
			Map<String, Object> result = new HashMap<>();
			result.put("state", 1);
			result.put("msg", "修改成功");
			return JSON.toJSONString(result);
		}
		Map<String, Object> result = new HashMap<>();
		result.put("state", -1);
		result.put("msg", "修改失败");
		return JSON.toJSONString(result);
	}

	// 会议室管理列表数据
	@RequestMapping("roomTableData")
	@ResponseBody
	public String roomTableData(@RequestParam(name = "queryName", required = false, defaultValue = "") String queryName,
			@RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
			@RequestParam(name = "limit", required = false, defaultValue = "10") Integer limit) {
		List<MeetRoom> dbList = meetRoomService.findAll(page, limit, queryName);
		PageInfo<MeetRoom> pageInfo = new PageInfo<MeetRoom>(dbList);
		ResponseData data = new ResponseData((int) pageInfo.getTotal(), 0, "", dbList);
		return JSON.toJSONString(data);
	}

	@RequestMapping("delete")
	@ResponseBody
	public String delete(@RequestParam(name = "pId", required = true) String pId) {
		try {
			meetRoomService.delete(pId);
		} catch (Exception e) {
			Map<String, Object> result = new HashMap<>();
			result.put("state", -1);
			result.put("msg", "删除失败");
			return JSON.toJSONString(result);
		}
		Map<String, Object> result = new HashMap<>();
		result.put("state", 1);
		result.put("msg", "删除成功");
		return JSON.toJSONString(result);
	}

	/**
	 * 修改状态 删除会议
	 * 
	 * @param id
	 */
	@RequestMapping("/updateState")
	@ResponseBody
	public void updateState(Integer id) {
		appointmentMeetService.updateState(id);
	}



	/**
	 * 跳转到会议纪要页面
	 * @param request
	 * @param tId		会议号
	 * @param tName		会议名称
	 * @param tUser		会议创建人
	 * @return
	 */
	@RequestMapping("meetingSummary")
	public String meetingSummary(HttpServletRequest request,
								 @RequestParam(name = "tId", required = true) String tId,
								 @RequestParam(name = "tName", required = true) String tName,
								 @RequestParam(name = "tUser", required = true) String tUser){
		request.setAttribute("tId",tId);
		request.setAttribute("tName",tName);
		request.setAttribute("tUser",tUser);
		return "home/meetingSummary";
	}

}
