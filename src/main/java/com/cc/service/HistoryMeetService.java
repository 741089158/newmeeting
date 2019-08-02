package com.cc.service;

import com.cc.entity.HistoryMeet;
import com.cc.entity.MeetRoom;

import java.text.ParseException;
import java.util.List;


public interface HistoryMeetService {

    //查询所有会议
    List<HistoryMeet> findAll(int page, int size);

    //按用户id查询会议page
    List<HistoryMeet> findByUserId(int UserId);
}
