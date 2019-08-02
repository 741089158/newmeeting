package com.cc.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.cc.entity.*;
import com.cc.service.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.alibaba.fastjson.JSON;
import com.cc.util.DateChange;
import com.cc.util.GetUser;
import com.github.pagehelper.PageInfo;

@RestController
@RequestMapping("meeting")
public class MeetingController {

    @Autowired
    private AppointmentMeetService appointmentMeetService;

    @Autowired
    private NewMeetingService newMeetingService;

    @Autowired
    private MeetRoomService meetRoomService;

    @Autowired
    private TaskMeetingService taskMeetingService;

    @Autowired
    private MailService mailService;

    // 获取当天会议列表数据
    @RequestMapping("meetingData")
    public String meetingData(HttpServletRequest request, @RequestParam(name = "roomId", required = true) String roomId
            , @RequestParam(name = "date", required = false) String date) {
        String today = "";
        if (date == "" || date == null) {
            today = DateChange.dateFormat(new Date(), "yyyy-MM-dd");
        } else {
            today = date;
        }
        // 获取当天时间

        List<Remeet> list = appointmentMeetService.findMeetingByRoomId(roomId);
        List<Remeet> result = new ArrayList<>();
        for (Remeet remeet : list) {
            String dataTime = remeet.getMeetDate().split(" ")[0];
            if (today.equals(dataTime)) {
                result.add(remeet);
            }
        }
        ResponseData responseData = new ResponseData(result.size(), 0, "", result);
        return JSON.toJSONString(responseData);
    }

    @RequestMapping("submitMeeting")
    public String submitMeeting() {
        // 该方法已经过时， 请转至ReserveController
        return "";
    }


    // 我的会议数据
    @RequestMapping("myPublicMeeting")
    public String myPublicMeeting(HttpServletRequest request,
                                  @RequestParam(name = "queryName", required = false, defaultValue = "") String queryName,
                                  @RequestParam(name = "date", required = false) String meetDate,
                                  @RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
                                  @RequestParam(name = "limit", required = false, defaultValue = "10") Integer limit) {
        User user = GetUser.current(request);
        if (user.getUsername() == null) {
            user.setUsername("A905663");
        }
        List<Remeet> meet = new ArrayList<>();

        try {
            meet = appointmentMeetService.findMeetByUsername(page, limit, user.getUsername(), queryName, meetDate);
        } catch (Exception e) {
        }
        PageInfo<Remeet> pageInfo = new PageInfo<Remeet>(meet);
        ResponseData data = new ResponseData((int) pageInfo.getTotal(), 0, "成功", meet);
        return JSON.toJSONString(data);
    }


    // 我的循环会议数据
    @RequestMapping("myRepeatMeeting")
    public String myRepeatMeeting(HttpServletRequest request,
                                  @RequestParam(name = "queryName", required = false, defaultValue = "") String queryName,
                                  @RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
                                  @RequestParam(name = "limit", required = false, defaultValue = "10") Integer limit) {
        User user = GetUser.current(request);
        if (user.getUsername() == null) {
            user.setUsername("A905663");
        }
        List<RepeatMeeting> meet = null;
        try {
            meet = taskMeetingService.findMeetByUsername(page, limit, user.getUsername(), queryName);
        } catch (Exception e) {

        }
        PageInfo<RepeatMeeting> pageInfo = new PageInfo<RepeatMeeting>(meet);
        ResponseData data = new ResponseData((int) pageInfo.getTotal(), 0, "成功", meet);
        return JSON.toJSONString(data);
    }


    // 历史会议数据
    @RequestMapping("historyMeeting")
    public String historyMeeting(@RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
                                 @RequestParam(name = "limit", required = false, defaultValue = "10") Integer limit, HttpServletRequest request) {

        User users = GetUser.current(request);
        if (users.getUsername() == null) {
            users.setUsername("A905663");
        }
        List<Remeet> list = null;
        try {
            list = newMeetingService.getHistoryMeeting(page, limit, users.getUsername());
        } catch (Exception e) {
            e.printStackTrace();
        }
        PageInfo<Remeet> pageInfo = new PageInfo<>(list);
        ResponseData data = new ResponseData((int) pageInfo.getTotal(), 0, "成功", list);
        return JSON.toJSONString(data);
    }

    //删除普通会议
    @RequestMapping("delete")
    public String delete(@RequestParam(name = "pId", required = true) String pId) {
        Map<String, Object> result = new HashMap<>();
        try {
            appointmentMeetService.deleteMeetingById(pId);
            List<Mail> list = mailService.findMailByMid(pId);           //根据会议id查询发送的邮件
            for (Mail mail : list) {
                mail.setStatus(0);      //更改状态为0
                mailService.update(mail);
            }
            result.put("state", 200);
            result.put("msg", "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            result.put("state", 404);
            result.put("msg", "删除失败");
        }
        return JSON.toJSONString(result);
    }

    //删除循环会议
    @RequestMapping("deleteRepeat")
    public String deleteRepeat(@RequestParam(name = "pId", required = true) Integer pId) {
        Map<String, Object> result = new HashMap<>();
        try {
            taskMeetingService.deleteMeetingById(pId);
            result.put("state", 200);
            result.put("msg", "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            result.put("state", 404);
            result.put("msg", "删除失败");
        }
        return JSON.toJSONString(result);
    }

    /**
     * 会议室日程列表
     *
     * @return
     */
    @RequestMapping(value = "/fullCalendar")
    @ResponseBody
    public Object fullCalendar(@Param("roomareaname") String roomareaname, @Param("building") String building, @Param("floor") String floor, HttpServletRequest request) {
        //System.out.println(areaId+"--"+building+"--"+floor);
        User current = GetUser.current(request);
        List<MeetRoom> list = meetRoomService.findRoom(roomareaname, building, floor, current);
        List<FullCalendar> fullCalendar = new ArrayList<FullCalendar>();
        for (MeetRoom meetRoom : list) {
            fullCalendar.add(new FullCalendar(meetRoom.getRoomId(), meetRoom.getRoomName(), "#3788d8"));
        }
        return fullCalendar;
    }

    /**
     * 会议室日程事件
     *
     * @return
     * @throws ParseException
     */
    @RequestMapping(value = "/fullEvents", method = RequestMethod.GET)
    @ResponseBody
    public Object fullEvents() throws ParseException {

        List<Remeet> remeets = appointmentMeetService.findAll();
        ArrayList<Events> list = new ArrayList<Events>();
        for (Remeet remeet : remeets) {
            //开始时间
            String meetDate = remeet.getMeetDate();
            String[] s = meetDate.split(" ");
            String start = s[0] + "T" + s[1];
            //结束时间
            String meetTime = remeet.getMeetTime();
            String[] split = meetTime.split(":");
            long second = Integer.parseInt(split[0]) * 60 * 60 * 1000 + Integer.parseInt(split[1]) * 60 * 1000;
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            long time = sf.parse(meetDate).getTime();
            String endTime = sf.format(new Date(time + second));
            String[] s1 = endTime.split(" ");
            String end = s1[0] + "T" + s1[1];
            list.add(new Events(remeet.getMeetRoomId(), start, end, remeet.getMeetName(), remeet.getDefaultLayout()));
        }
        return list;
    }

    /**
     * 根据登陆用户名查询预订会议日程列表
     *
     * @return
     */
    @RequestMapping(value = "/meetFullCalendar", method = RequestMethod.GET)
    @ResponseBody
    public Object userFullCalendar(HttpServletRequest request) {
        User user = GetUser.current(request);
        List<Remeet> list = appointmentMeetService.findMeetByUserName(user.getUsername());
        List<FullCalendar> fullCalendar = new ArrayList<FullCalendar>();
        for (Remeet remeet : list) {
            fullCalendar.add(new FullCalendar(remeet.getId().toString(), remeet.getMeetName(), "blue"));
        }
        return fullCalendar;
    }

    /**
     * 会议日程事件
     *
     * @return
     * @throws ParseException
     */
    @RequestMapping(value = "/meetFullEvents")
    @ResponseBody
    public Object meetFullEvents(HttpServletRequest request) throws ParseException {

        User users = GetUser.current(request);
        if (users.getUsername() == null) {
            users.setUsername("A905663");
        }

        List<Remeet> list = appointmentMeetService.findMeetByUserName(users.getUsername());
        List<Events> events = new ArrayList<Events>();
        for (Remeet remeet : list) {
            //开始时间
            String meetDate = remeet.getMeetDate();
            String[] s = meetDate.split(" ");
            String start = s[0] + "T" + s[1];
            //结束时间
            String meetTime = remeet.getMeetTime();
            String[] split = meetTime.split(":");
            long second = Integer.parseInt(split[0]) * 60 * 60 * 1000 + Integer.parseInt(split[1]) * 60 * 1000;
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            long time = sf.parse(meetDate).getTime();
            String endTime = sf.format(new Date(time + second));
            String[] s1 = endTime.split(" ");
            String end = s1[0] + "T" + s1[1];
            events.add(new Events(remeet.getId().toString(), start, end, remeet.getMeetName(), remeet.getDefaultLayout()));
        }
        return events;
    }


}
