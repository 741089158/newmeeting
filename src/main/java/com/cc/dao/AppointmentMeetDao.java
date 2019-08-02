package com.cc.dao;

import com.cc.entity.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface AppointmentMeetDao {

    //根据用户Id查询所有预约的会议
    List<Remeet>  findAll();
    //条件查询
    List<Remeet>  findAll(@Param("meetName") String meetName);


    //增加预约本地会议
    void appointmentMeet(Remeet remeet);

    //增加预约视屏会议
    void appointmentVideoMeet(Remeet remeet);


    List<HistoryMeet> findPageHistory(@Param("username") String username, @Param("meetName") String meetName);

    List<MeetUser> findHistoryUser(Integer id);

    Remeet findOne(Integer id);

    void update(Remeet remeet);

    List<Remeet> findMeeting(@Param("state") Integer state, @Param("repeatType") String repeatType);

    Remeet findByRid(int rid);

    RepeatMeeting findRepeatMeeting(Integer id);

    void updateState(Integer id);

    List<Remeet> findMeetByUserId(@Param("id") Integer id);

    List<Remeet> findMeetByUsername(@Param("username") String username, @Param("meetName") String meetName, @Param("meetDate") String meetDate);

    void insertUserIdAndMeetId(@Param("userId") Integer userId, @Param("meetId") Integer meetId);
    void insertUserIdAndMeetId(@Param("userId") String userId, @Param("meetId") Integer meetId);

    void repeatMeet(Remeet remeet);

    List<Remeet> findMeetingByRoomId(String roomId);
    
    List<Remeet> findHistoryMeeting(@Param("username") String username);

    void deleteMeetingById(String pId);
}
