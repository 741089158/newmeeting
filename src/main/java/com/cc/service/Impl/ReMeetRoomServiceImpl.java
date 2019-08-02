package com.cc.service.Impl;

import com.alibaba.fastjson.JSON;
import com.cc.dao.ReMeetRoomDao;
import com.cc.entity.Appointment_Meeting;
import com.cc.entity.MeetRoom;
import com.cc.entity.User;
import com.cc.service.ReMeetRoomService;
import com.cc.util.GetUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service("reMeetRoomService")
public class ReMeetRoomServiceImpl implements ReMeetRoomService {

	@Autowired
	private ReMeetRoomDao reMeetRoomDao;

	public List<MeetRoom> floor(String areaid, String building) {
		return reMeetRoomDao.findFloor(areaid, building);
	}

	public String findFloor(String areaid, String building, User current) {
		
		List<MeetRoom> list = reMeetRoomDao.findFloorWithRooms(areaid, building, current.getUsername(), current.getSuboffice());
		Map<String, List<MeetRoom>> fMap = new TreeMap<>();
		for (MeetRoom mr : list) {
			if (fMap.get(mr.getRoomFloor()) == null) {
				fMap.put(mr.getRoomFloor(), new ArrayList<>());
			}
			fMap.get(mr.getRoomFloor()).add(mr);
		}
		return JSON.toJSONString(fMap);
	}

	public List<MeetRoom> findArea(HttpServletRequest request) {
		User current = GetUser.current(request);
		return reMeetRoomDao.findArea(current.getUsername(),current.getSuboffice());
	}

	public List<MeetRoom> findBuilding(String area) {
		return reMeetRoomDao.findBuilding(area);
	}

	public List<MeetRoom> findBuilding2(String area) {
		return reMeetRoomDao.findBuilding2(area);
	}

	public List<MeetRoom> findRoom(String areaid, String building, String floor, String roomId) {
		return reMeetRoomDao.findRoom(areaid, building, floor, roomId);
	}

	public MeetRoom findById(String id) {
		return reMeetRoomDao.findById(id);
	}

	public List<Appointment_Meeting> findByDate(String startTime, String endTime) {
		return reMeetRoomDao.findByDate(startTime, endTime);
	}

	@Override
	public List<MeetRoom> updateMeetRoom(String areaId, String building, String floor, String roomId) {
		return reMeetRoomDao.findRoom(areaId, building, floor, roomId);
	}

	@Override
	public List<MeetRoom> roomAreaManager() {
		return reMeetRoomDao.roomAreaManager();
	}
}