package com.cc.dao;

import com.cc.entity.MeetRoom;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface MeetRoomDao {

    List<MeetRoom> findAll(@Param("roomName") String roomName);

    void add(MeetRoom meetRoom);

    MeetRoom findOne(String id);

    void delete(String id);

    void update(MeetRoom meetRoom);

    List<MeetRoom> findRoomName(@Param("areaid") String areaid, @Param("roombuilding") String roombuilding, @Param("roomfloor") String roomfloor);

    MeetRoom findRoomByRoomName(String roomName);

    List<MeetRoom> findRoom(@Param("roomareaname") String roomareaname, @Param("roombuilding") String roombuilding, @Param("roomfloor") String roomfloor, @Param("manageruid") String manageruid, @Param("suboffice") String suboffice);
}
