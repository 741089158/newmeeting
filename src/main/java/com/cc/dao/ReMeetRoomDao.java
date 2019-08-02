package com.cc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.cc.entity.Appointment_Meeting;
import com.cc.entity.MeetRoom;

@Repository
public interface ReMeetRoomDao {

	// 查询楼层
	List<MeetRoom> findFloor(@Param("roomareaname") String roomAreaName, @Param("roombuilding") String roombuilding);

	// 查询建筑
	List<MeetRoom> findBuilding(@Param("roomareaname") String roomAreaName);

	// 查询地区
	List<MeetRoom> findArea(@Param("manageruid") String manageruid, @Param("suboffice") String suboffice);

	// 查询会议室
	List<MeetRoom> findRoom(@Param("areaid") String areaid, @Param("roombuilding") String roombuilding,
                            @Param("roomfloor") String roomfloor, @Param("roomId") String roomId);

	// 根据id查会议室
	MeetRoom findById(String id);

	// 根据时间查找未被使用的会议室id
	List<Appointment_Meeting> findByDate(@Param("startTime") String startTime, @Param("endTime") String endTime);

	// 取楼层并取出所有房间
	List<MeetRoom> findFloorWithRooms(@Param("roomareaname") String roomareaname, @Param("roombuilding") String roombuilding,
                                      @Param("manageruid") String manageruid, @Param("department") String department);

	// 无条件查询
	List<MeetRoom> roomAreaManager();

	List<MeetRoom> findBuilding2(@Param("area") String area);
}
