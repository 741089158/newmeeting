package com.cc.service;

import com.cc.entity.Remeet;
import com.cc.entity.RepeatMeeting;
import com.cc.entity.ResponseData;
import com.cc.entity.UserInternal;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.List;

public interface TaskMeetingService {
    List<RepeatMeeting> findMeeting(Integer status, String repeatType);

    void update(int id);

    ResponseData addRepeatReserve(RepeatMeeting repeatMeeting, HttpServletRequest request) throws Exception;

    List<RepeatMeeting> findRepeatMeeting(Integer page, Integer size, String meetName);

    List<RepeatMeeting> findMeetByUsername(Integer page, Integer limit, String username, String queryName);

    void deleteMeetingById(Integer pId);
}
