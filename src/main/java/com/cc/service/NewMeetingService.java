package com.cc.service;

import com.cc.entity.Remeet;

import java.util.List;

public interface NewMeetingService {

	List<Remeet> getHistoryMeeting(Integer page, Integer size, String username);

}
