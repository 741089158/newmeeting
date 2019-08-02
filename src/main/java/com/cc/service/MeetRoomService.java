package com.cc.service;

import com.cc.entity.MeetRoom;
import com.cc.entity.Result;
import com.cc.entity.User;
import org.springframework.stereotype.Service;


import java.text.ParseException;
import java.util.List;


public interface MeetRoomService {
    List<MeetRoom> findAll(Integer page, Integer size, String roomName);

    void add(MeetRoom meetRoom) throws ParseException;

    MeetRoom findOne(String id);


    void delete(String id);

    void update(MeetRoom meetRoom);

    List<MeetRoom> findRoomName(String areaid, String roombuilding, String roomfloor);

    List<MeetRoom> findRoom(String areaId, String roomBuilding, String floor, User user);

    List<MeetRoom> findList();

    MeetRoom findRoomByRoomName(String roomName);
}
