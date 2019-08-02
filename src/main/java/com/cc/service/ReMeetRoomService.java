package com.cc.service;

import com.cc.entity.Appointment_Meeting;
import com.cc.entity.MeetRoom;
import com.cc.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ReMeetRoomService {

	// 查询楼层
	List<MeetRoom> floor(String areaid, String building);

	/**
	 * ^查询楼层和楼层下的(登录人有权权限的)会议室
	 */
	String findFloor(String areaid, String building, User current);

	// 查询地区
	List<MeetRoom> findArea(HttpServletRequest request);

	// 查询大楼
	List<MeetRoom> findBuilding(String area);

	// 查询会议室
	List<MeetRoom> findRoom(String areaid, String building, String floor, String roomId);

	// 根据Roomid查询会议室
	MeetRoom findById(String id);

	List<Appointment_Meeting> findByDate(String startTime, String endTime);

	// 修改会议室
	List<MeetRoom> updateMeetRoom(String areaId, String building, String floor, String roomId);

	List<MeetRoom> roomAreaManager();

	List<MeetRoom> findBuilding2(String area);
}
