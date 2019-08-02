package com.cc.dao;

import com.cc.entity.HistoryMeet;
import com.cc.entity.Remeet;

import java.util.List;


public interface HistoryMeetDao {

   //根据用户id查询历史会议
    List<HistoryMeet> findByUserId(int UserId);

    //查询所有会议
    List<HistoryMeet> findAll();
}
