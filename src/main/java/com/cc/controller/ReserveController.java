package com.cc.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cc.entity.Remeet;
import com.cc.entity.RepeatMeeting;
import com.cc.entity.ResponseData;
import com.cc.entity.User;
import com.cc.service.AppointmentMeetService;
import com.cc.service.NewUserService;
import com.cc.service.TaskMeetingService;
import com.cc.tools.MeetingApi;
import com.cc.util.DateChange;
import com.cc.util.GetUser;

//会议室预订
@RestController
@RequestMapping("/reserve")
public class ReserveController {

    @Autowired
    private NewUserService newUserService;

    @Autowired
    private AppointmentMeetService appointmentMeetService;

    @Autowired
    private TaskMeetingService taskMeetingService;

    /**
     * 预订本地会议
     *
     * @param date
     * @param time
     * @param remeet
     * @param request
     * @return
     */
    @RequestMapping("/localMeeting")
    @ResponseBody
    @Transactional
    public ResponseData appointmmet(@RequestParam(value = "date") String date,
                                    @RequestParam(value = "time") String time, Remeet remeet, HttpServletRequest request) throws ParseException {

       // System.out.println(remeet);
        String datetime = date.trim() + " " + time.trim();
        remeet.setMeetDate(datetime);
        // 联系人集合
        List<Map<String, String>> user = new ArrayList<Map<String, String>>();

        // 登陆用户
        User users = GetUser.current(request);

        // 设置会议预订人工号
        remeet.setDefaultLayout(users.getUsername());
        // 登陆用户信息
        Map<String, String> stringStringMap = null;
        try {
            // 根据工号查询name
            stringStringMap = newUserService.queryUserByAccount(users.getUsername());
            user.add(stringStringMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        // 加条件判断?
        String[] split = remeet.getUserId().split(",");
        for (String u : split) {
            Map<String, String> maps = null;
            try {
                if (u.contains("[Group]")){//如果是组
                    String substring = u.substring(7);
                    List<Map<String, String>> list = newUserService.queryUserByGroup(substring);//用户集合
                    //System.out.println(list);
                    for (Map<String, String> stringMap : list) {//遍历组织的用户集合
                        String name = stringMap.get("name");
                        if (name!=""&&name!=null){
                            maps = newUserService.queryUserByName(stringMap.get("name")); // 查询联系人
                            user.add(maps);
                        }
                    }
                }else {//是用户
                    maps = newUserService.queryUserByName(u); // 查询联系人
                    user.add(maps);
                }

            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        ResponseData data = new ResponseData();
        List list = checkMeeting(remeet);
        if (list == null||list.size()==0) {
            List<Map<String, String>> userList = new ReserveController().getUser(user);
            appointmentMeetService.appointmentMeet(remeet, userList, request);
            data.setCode(200);
            data.setMessage("预订成功!");
            return data;
        }else {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//回滚,取消循环会议
            data.setCode(404);
            data.setMessage("会议时间冲突!");
            return data;
        }
    }


    //去重
    public List<Map<String, String>>  getUser(List<Map<String, String>> list){
        Set set = new HashSet();
        List<Map<String, String>> listNew = new ArrayList<Map<String, String>>();
        set.addAll(list);
        listNew.addAll(set);
        return listNew;
    }

    /**
     * 预订视频会议
     *
     * @param date
     * @param time
     * @param remeet
     * @param password
     * @param request
     * @return
     * @throws ParseException
     */
    @RequestMapping("/videoMeet")
    @ResponseBody
    public Object videoMeet(@RequestParam(value = "date") String date, @RequestParam(value = "time") String time,
                            Remeet remeet, @RequestParam(value = "password", required = false) String password,
                            HttpServletRequest request) throws ParseException {
        String datetime = date.trim() + " " + time.trim();
        remeet.setMeetDate(datetime);
        //会议室冲突检查
        ResponseData data = new ResponseData();
        List list = checkMeeting(remeet);
        if (list != null&&list.size()!=0) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//回滚,取消循环会议
            data.setCode(404);
            data.setMessage("会议时间冲突!");
            return data;
        }

        if (password != "" && password != null) {
            remeet.setRequireCallId(password); // 设置会议密码
        }
        Map<String, Object> map = new HashMap<>();
        map.put("name", remeet.getMeetName()); // 会议名
        String tDate = date + " " + time;
        String s = DateChange.changeTime(tDate, "yyyy-MM-dd HH:mm");
        tDate = s.replace(" ", "T") + ":00.000Z";
        map.put("startTime", tDate); // 开始时间
        int durationSeconds = 0;
        try {
            durationSeconds = DateChange.changeTime(remeet.getMeetTime()) / 1000 / 60;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        map.put("durationSeconds", durationSeconds); // 时长 分
        map.put("type", 0); // 会议类型 0 视频会议
        if (password != null && password.trim().length() > 0) {
            map.put("password", password); // 密码
        }
        map.put("description", remeet.getMeetName()); // 描述
        map.put("repetition", 0); // 重复会议
        String result = "";
        // 预订会议
        result = MeetingApi.addMeeting(map); // 调用视频会议api
        JSONObject jsonObject = JSON.parseObject(result);
        if (jsonObject.getInteger("status") == 200) {
            remeet.setUri(Integer.parseInt(jsonObject.getJSONArray("data").get(1).toString())); // 视频会议设置会议号

            // 查询联系人
            List<Map<String, String>> user = new ArrayList<Map<String, String>>();
            // 登陆用户
            User users = GetUser.current(request);

            // 设置会议预订人工号
            remeet.setDefaultLayout(users.getUsername());
            Map<String, String> stringStringMap = null; // 登陆用户信息
            try {
                // 根据工号查询name
                stringStringMap = newUserService.queryUserByAccount(users.getUsername());
                user.add(stringStringMap);
            } catch (Exception e) {
                e.printStackTrace();
            }


            String userId = remeet.getUserId();
            String strip = StringUtils.strip(userId, "[]");
            String[] split = strip.split(",");
            for (String u : split) {
                Map<String, String> maps = null;
                try {
                    if (u.contains("[Group]")){//如果是组
                        String substring = u.substring(7);
                        List<Map<String, String>> listUser = newUserService.queryUserByGroup(substring);//用户集合
                        for (Map<String, String> stringMap : listUser) {//遍历组织的用户集合
                            String name = stringMap.get("name");
                            if (name!=""&&name!=null){
                                maps = newUserService.queryUserByName(stringMap.get("name")); // 查询联系人
                                user.add(maps);
                            }
                        }
                    }else {//是用户
                        maps = newUserService.queryUserByName(u); // 查询联系人
                        user.add(maps);
                    }

                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
            try {
                List<Map<String, String>> userList = new ReserveController().getUser(user);
                // 添加会议数据
                appointmentMeetService.appointmentMeet(remeet, userList, request);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return JSON.parseObject(result);
    }

    /**
     * 循环会议
     *
     * @param time
     * @param day
     * @param createTime
     * @param endTime
     * @param week
     * @param months
     * @param remeet
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/repeat")
    @ResponseBody
    public ResponseData reserve(@RequestParam(value = "time") String time, @RequestParam(value = "day") String day,
                                @RequestParam(value = "createTime") String createTime, @RequestParam(value = "endTime") String endTime,
                                String week, String months, Remeet remeet, HttpServletRequest request) throws Exception {
        // 设置时间
        String datetime = createTime.trim() + " " + time.trim();
        remeet.setMeetDate(datetime);

        User users = GetUser.current(request);

        // 添加会议数据
        RepeatMeeting repeatMeeting = new RepeatMeeting();
        repeatMeeting.setCreateTime(remeet.getMeetDate());
        repeatMeeting.setEndTime(endTime);
        repeatMeeting.setMeetName(remeet.getMeetName());
        repeatMeeting.setMeetRoomName(remeet.getMeetRoomName());
        repeatMeeting.setType(remeet.getMeetType());
        repeatMeeting.setMeetTime(remeet.getMeetTime());
        repeatMeeting.setDescription(remeet.getMeetDescription());
        repeatMeeting.setRepeatType(day);
        repeatMeeting.setStatus(1);
        repeatMeeting.setIsDefault(1);
        repeatMeeting.setUserId(remeet.getUserId());
        repeatMeeting.setTitle(remeet.getMeetLaber());
        repeatMeeting.setRoomId(remeet.getMeetRoomId());
        repeatMeeting.setDefaultlayout(users.getUsername());
        if (week != null) {
            repeatMeeting.setWeeks(week);
        }
        if (months != null && (week == null || week == "")) {
            repeatMeeting.setWeeks(months);
        }
        ResponseData data = new ResponseData();

        ResponseData responseData = taskMeetingService.addRepeatReserve(repeatMeeting, request);
        data.setCode(responseData.getCode());
        data.setMessage(responseData.getMessage());
        return data;

    }


    //会议冲突检查
    public List checkMeeting(Remeet remeet) throws ParseException {
        //1:根据会议室id查询所有预订会议
        List<Remeet> list = null;
        try {
            list = appointmentMeetService.findMeetingByRoomId(remeet.getMeetRoomId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Remeet> meets = new ArrayList<Remeet>();
        String dateTime = remeet.getMeetDate();//预订会议开始时间 字符串
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date startTime = sf.parse(dateTime);//预订会议开始时间   date类型
        Date endTime = new Date(startTime.getTime() + DateChange.changeTime(remeet.getMeetTime()));//预订会议结束时间   date类型

        for (Remeet meet : list) {
            String meetDate = meet.getMeetDate(); //已预订会议开始时间
            String meetTime = meet.getMeetTime();//已预订会议时长
            Date startTime1 = sf.parse(meetDate);//已预订会议开始时间   date类型
            Date endTime1 = new Date(startTime1.getTime() + DateChange.changeTime(meetTime));//已预订会议结束时间   date类型
            if ((startTime.compareTo(startTime1)>=0&&startTime.compareTo(endTime1)<0)||
                    (endTime.compareTo(endTime1)<=0&&endTime.compareTo(startTime1)>0)||
                    (startTime.compareTo(startTime1)<=0&&endTime.compareTo(endTime1)>=0)){
                meets.add(remeet);
            }
        }
        return meets;
    }
}
