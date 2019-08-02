package com.cc.service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.cc.entity.Appointment_Meeting;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cc.dao.AppointmentMeetDao;
import com.cc.entity.Remeet;
import com.cc.service.NewMeetingService;
import com.cc.util.BeanKit;

@Service
public class NewMeetingServiceImpl implements NewMeetingService {

	@Autowired
	private AppointmentMeetDao appointmentMeetDao;

	@Override
	public List<Remeet> getHistoryMeeting(Integer page,Integer size,String username) {
		PageHelper.startPage(page, size);
		List<Remeet> list = appointmentMeetDao.findHistoryMeeting(username);
		return list;
	}

}
