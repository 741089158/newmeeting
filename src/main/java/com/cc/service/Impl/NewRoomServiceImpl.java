package com.cc.service.Impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.time.FastDateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cc.dao.MeetRoomDao;
import com.cc.entity.MeetRoom;
import com.cc.entity.User;
import com.cc.service.NewRoomService;
import com.cc.util.BeanKit;
import com.cc.util.PKGenerate;

@Service
public class NewRoomServiceImpl implements NewRoomService {

	@Autowired
	private MeetRoomDao meetRoomDao;

	private FastDateFormat formarTime = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");

	public Boolean saveRoom(Map<String, Object> roomMap, User current) {
		MeetRoom meetRoom = BeanKit.map2bean(roomMap, MeetRoom.class);
		meetRoom.setRoomId(PKGenerate.generateUUID());
		meetRoom.setCreateDate(formarTime.format(new Date()));
		meetRoom.setCreateUser(current.getUsername());
		meetRoom.setRoomStatus("1"); // 启用状态?
		meetRoom.setIsStart("1"); // 启用状态?
		try {
			meetRoomDao.add(meetRoom);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("会议室添加异常 ！！！");
			return false;
		}
		return true;
	}

	public Map<String, Object> showOne(String roomId) {
		MeetRoom meetRoom = meetRoomDao.findOne(roomId);
		return new HashMap<>(BeanKit.bean2map(meetRoom));
	}

	@Override
	public boolean updateRoom(Map<String, Object> roomMap, User current) {
		MeetRoom meetRoom = BeanKit.map2bean(roomMap, MeetRoom.class);
		meetRoom.setLastUpdateDate(formarTime.format(new Date()));
		meetRoom.setLastUpdateUser(current.getUsername());
		try {
			meetRoomDao.update(meetRoom);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("会议室修改异常 ！！！");
			return false;
		}
		return true;
	}

}
