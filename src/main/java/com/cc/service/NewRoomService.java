package com.cc.service;

import java.util.Map;

import com.cc.entity.User;

public interface NewRoomService {

	Boolean saveRoom(Map<String, Object> roomMap, User current);

	Map<String, Object> showOne(String roomId);

	boolean updateRoom(Map<String, Object> roomMap, User current);

}
