package com.cc.service.Impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.cc.controller.ReserveController;
import com.cc.dao.TaskMeetingDao;
import com.cc.entity.Remeet;
import com.cc.entity.RepeatMeeting;
import com.cc.entity.ResponseData;
import com.cc.entity.User;
import com.cc.service.AppointmentMeetService;
import com.cc.service.NewUserService;
import com.cc.service.TaskMeetingService;
import com.cc.util.DateChange;
import com.cc.util.GetUser;
import com.github.pagehelper.PageHelper;

@Service
public class TaskMeetingServiceImpl implements TaskMeetingService {

    @Autowired
    private TaskMeetingDao taskMeetingDao;

    @Autowired
    private AppointmentMeetService appointmentMeetService;

    @Autowired
    private NewUserService newUserService;

    @Override
    public List<RepeatMeeting> findMeeting(Integer status, String repeatType) {
        return taskMeetingDao.findMeeting(status, repeatType);
    }

    @Override
    public void update(int id) {
        taskMeetingDao.update(id);
    }


    //添加循环会议
    @Override
    @Transactional
    public ResponseData addRepeatReserve(RepeatMeeting repeatMeeting, HttpServletRequest request) throws Exception {
        ResponseData data = new ResponseData();
        try {
            taskMeetingDao.addRepeatReserve(repeatMeeting);//添加循环会议信息
        } catch (Exception e) {
            e.printStackTrace();
        }
        //循环类型
        String repeatType = repeatMeeting.getRepeatType();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        //开始时间
        Date createTime = sf.parse(repeatMeeting.getCreateTime());
        //结束时间
        Date endTime = sf.parse(repeatMeeting.getEndTime());

        String[] s = repeatMeeting.getCreateTime().split(" ");
        //每日循环会议
        if (repeatType.equals("everydays")) {
            //循环天数
            int days = 0;
            try {
                days = caculateTotalTime(repeatMeeting.getCreateTime(), repeatMeeting.getEndTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Date date = null;
            for (int i = 0; i < days; i++) {
                //开会时间
                if (i == 0) {
                    date = createTime;
                } else {
                    date = getNextDay(date);
                }
                //添加会议数据
                Remeet remeet = new Remeet();
                remeet.setMeetName(repeatMeeting.getMeetName());//设置会议名
                remeet.setMeetDate(sf.format(date) + " " + s[1]);//设置当前会议时间
                remeet.setMeetDescription(repeatMeeting.getDescription());//描述
                remeet.setMeetLaber("重复会议");
                remeet.setMeetType("循环会议");
                remeet.setMeetRoomId(repeatMeeting.getRoomId());//会议室id
                remeet.setMeetRoomName(repeatMeeting.getMeetRoomName());//会议室名
                remeet.setState(1);
                remeet.setRepeatType(repeatMeeting.getRepeatType());
                remeet.setMeetTime(repeatMeeting.getMeetTime());//时长
                remeet.setUserId(repeatMeeting.getUserId());
                remeet.setRid(repeatMeeting.getId());//添加循环会议id
                remeet.setDefaultLayout(repeatMeeting.getDefaultlayout()); //设置会议预订人工号
                //登陆用户
                User users = GetUser.current(request);
                if (users.getUsername() == null) {
                    users.setUsername("A905663");
                }
                //查询联系人
                List<Map<String, String>> user = new ArrayList<Map<String, String>>();
                Map<String, String> stringStringMap = null;//登陆用户信息
                try {
                    //根据工号查询name
                    stringStringMap = newUserService.queryUserByAccount(users.getUsername());
                    user.add(stringStringMap);
                } catch (Exception e) {
                    e.printStackTrace();
                }


                String userId = remeet.getUserId();
                //加条件判断?
                String[] split = userId.split(",");
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
                //判断单次会议
                List list = checkRepeat(remeet);
                if (list == null||list.size()==0) {
                    List<Map<String, String>> userList = new ReserveController().getUser(user);
                    System.out.println(userList);
                    appointmentMeetService.repeatMeet(remeet, userList, request);//添加会议
                }else {
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//回滚,取消循环会议
                    data.setCode(404);
                    data.setMessage("会议时间冲突!");
                    return data;
                }
            }
            data.setCode(200);
            data.setMessage("添加循环会议成功!");
            return data;
        }
        //工作日循环会议
        if (repeatType.equals("everyworks")) {

            //循环天数
            int days = 0;
            try {
                days = caculateTotalTime(repeatMeeting.getCreateTime(), repeatMeeting.getEndTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Date date = null;
            for (int i = 0; i < days; i++) {
                //开会时间
                if (i == 0) {
                    date = createTime;
                } else {
                    date = getNextDay(date);
                }
                String weekOfDate = getWeekOfDate(date);
                String day1 = "周六";
                String day2 = "周日";
                //如果日期为周六或周日  跳过
                if (weekOfDate.equals(day1) || weekOfDate.equals(day2)) {
                    continue;
                } else {
                    //添加会议数据
                    Remeet remeet = new Remeet();
                    // remeet.setId(repeatMeeting.getId());
                    remeet.setMeetName(repeatMeeting.getMeetName());//设置会议名
                    remeet.setMeetDate(sf.format(date) + " " + s[1]);//设置当前会议时间
                    remeet.setMeetDescription(repeatMeeting.getDescription());//描述
                    remeet.setMeetLaber("重复会议");
                    remeet.setMeetType("循环会议");
                    remeet.setMeetRoomId(repeatMeeting.getRoomId());//会议室id
                    remeet.setMeetRoomName(repeatMeeting.getMeetRoomName());//会议室名
                    remeet.setState(1);
                    remeet.setRepeatType(repeatMeeting.getRepeatType());//设置会议类型
                    remeet.setMeetTime(repeatMeeting.getMeetTime());//时长
                    remeet.setUserId(repeatMeeting.getUserId());
                    remeet.setRid(repeatMeeting.getId());//添加循环会议id
                    remeet.setDefaultLayout(repeatMeeting.getDefaultlayout()); //设置会议预订人工号


                    //登陆用户
                    User users = GetUser.current(request);
                    if (users.getUsername() == null) {
                        users.setUsername("A905663");
                    }
                    //查询联系人
                    List<Map<String, String>> user = new ArrayList<Map<String, String>>();
                    Map<String, String> stringStringMap = null;//登陆用户信息
                    try {
                        //根据工号查询name
                        stringStringMap = newUserService.queryUserByAccount(users.getUsername());
                        user.add(stringStringMap);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    String userId = remeet.getUserId();
                    String[] split = userId.split(",");
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
                    //判断单次会议

                    List list = checkRepeat(remeet);
                    if (list == null||list.size()==0) {
                        List<Map<String, String>> userList = new ReserveController().getUser(user);
                        System.out.println(userList);
                        appointmentMeetService.repeatMeet(remeet, userList, request);//添加会议
                    }else {
                        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//回滚,取消循环会议
                        data.setCode(404);
                        data.setMessage("会议时间冲突!");
                        return data;
                    }
                }
            }
            data.setCode(200);
            data.setMessage("添加循环会议成功!");
            return data;
        }

        //每周循环会议
        if (repeatType.equals("everyweeks")) {

            //循环天数
            int days = 0;
            try {
                days = caculateTotalTime(repeatMeeting.getCreateTime(), repeatMeeting.getEndTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Date date = null;

            String week = repeatMeeting.getWeeks();
            String[] splits = week.split(",");
            //获取重复会议  星期数组
            List<String> strings = Arrays.asList(splits);
            for (int i = 0; i < days; i++) {
                //开会时间
                if (i == 0) {
                    date = createTime;
                } else {
                    date = getNextDay(date);
                }

                String weekOfDate = getWeekOfDate(date);

                for (String string : strings) {
                    if (weekOfDate.equals(string)) {
                        //添加会议数据
                        Remeet remeet = new Remeet();
                        // remeet.setId(repeatMeeting.getId());
                        remeet.setMeetName(repeatMeeting.getMeetName());//设置会议名
                        remeet.setMeetDate(sf.format(date) + " " + s[1]);//设置当前会议时间
                        remeet.setMeetDescription(repeatMeeting.getDescription());//描述
                        remeet.setMeetLaber("周重复会议");
                        remeet.setMeetType("循环会议");
                        remeet.setMeetRoomId(repeatMeeting.getRoomId());//会议室id
                        remeet.setMeetRoomName(repeatMeeting.getMeetRoomName());//会议室名
                        remeet.setState(1);
                        remeet.setRepeatType(repeatMeeting.getRepeatType());//设置会议类型
                        remeet.setMeetTime(repeatMeeting.getMeetTime());//时长
                        remeet.setUserId(repeatMeeting.getUserId());
                        remeet.setRid(repeatMeeting.getId());//添加循环会议id
                        remeet.setDefaultLayout(repeatMeeting.getDefaultlayout()); //设置会议预订人工号


                        //登陆用户
                        User users = GetUser.current(request);
                        if (users.getUsername() == null) {
                            users.setUsername("A905663");
                        }
                        //查询联系人
                        List<Map<String, String>> user = new ArrayList<Map<String, String>>();
                        Map<String, String> stringStringMap = null;//登陆用户信息
                        try {
                            //根据工号查询name
                            stringStringMap = newUserService.queryUserByAccount(users.getUsername());
                            user.add(stringStringMap);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        String userId = remeet.getUserId();
                        String[] split = userId.split(",");
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
                        //判断单次会议

                        List list = checkRepeat(remeet);
                        if (list == null||list.size()==0) {
                            List<Map<String, String>> userList = new ReserveController().getUser(user);
                            System.out.println(userList);
                            appointmentMeetService.repeatMeet(remeet, userList, request);//添加会议
                        }else {
                            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//回滚,取消循环会议
                            data.setCode(404);
                            data.setMessage("会议时间冲突!");
                            return data;
                        }
                    }
                }
            }
            data.setCode(200);
            data.setMessage("添加循环会议成功!");
            return data;
        }

        //每月循环会议
        if (repeatType.equals("everymonths")) {

            //循环天数
            int days = 0;
            try {
                days = caculateTotalTime(repeatMeeting.getCreateTime(), repeatMeeting.getEndTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Date date = null;


            String[] splits = repeatMeeting.getWeeks().split(",");
            //获取重复会议  循环日期数组
            List<String> strings = Arrays.asList(splits);
            for (int i = 0; i < days; i++) {
                //开会时间
                if (i == 0) {
                    date = createTime;
                } else {
                    date = getNextDay(date);
                }
                int num = Integer.parseInt(sf.format(date).split("-")[2]);//多少号  01,11...

                String weekOfDate = getWeekOfDate(date);

                for (String string : strings) {
                    if (Integer.parseInt(string) == num) {
                        //添加会议数据
                        Remeet remeet = new Remeet();
                        //remeet.setId(repeatMeeting.getId());
                        remeet.setMeetName(repeatMeeting.getMeetName());//设置会议名
                        remeet.setMeetDate(sf.format(date) + " " + s[1]);//设置当前会议时间
                        remeet.setMeetDescription(repeatMeeting.getDescription());//描述
                        remeet.setMeetLaber("月重复会议");
                        remeet.setMeetType("循环会议");
                        remeet.setMeetRoomId(repeatMeeting.getRoomId());//会议室id
                        remeet.setMeetRoomName(repeatMeeting.getMeetRoomName());//会议室名
                        remeet.setState(1);
                        remeet.setRepeatType(repeatMeeting.getRepeatType());//设置会议类型
                        remeet.setMeetTime(repeatMeeting.getMeetTime());//时长
                        remeet.setUserId(repeatMeeting.getUserId());
                        remeet.setRid(repeatMeeting.getId());//添加循环会议id
                        remeet.setDefaultLayout(repeatMeeting.getDefaultlayout()); //设置会议预订人工号


                        //登陆用户
                        User users = GetUser.current(request);
                        if (users.getUsername() == null) {
                            users.setUsername("A905663");
                        }
                        //查询联系人
                        List<Map<String, String>> user = new ArrayList<Map<String, String>>();
                        Map<String, String> stringStringMap = null;//登陆用户信息
                        try {
                            //根据工号查询name
                            stringStringMap = newUserService.queryUserByAccount(users.getUsername());
                            user.add(stringStringMap);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        String userId = remeet.getUserId();
                        String[] split = userId.split(",");
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
                        //判断单次会议
                        List list = checkRepeat(remeet);
                        if (list == null||list.size()==0) {
                            List<Map<String, String>> userList = new ReserveController().getUser(user);
                            System.out.println(userList);
                            appointmentMeetService.repeatMeet(remeet, userList, request);//添加会议
                        }else {
                            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//回滚,取消循环会议
                            data.setCode(404);
                            data.setMessage("会议时间冲突!");
                            return data;
                        }
                    }
                }
            }
            data.setCode(200);
            data.setMessage("添加循环会议成功!");
            return data;
        }
        return new ResponseData();
    }

    @Override
    public List<RepeatMeeting> findRepeatMeeting(Integer page, Integer size, String meetName) {
        PageHelper.startPage(page, size);
        return taskMeetingDao.findRepeatMeeting(meetName);
    }

    @Override
    public List<RepeatMeeting> findMeetByUsername(Integer page, Integer limit, String username, String queryName) {
        PageHelper.startPage(page, limit);
        return taskMeetingDao.findMeetByUsername(username,queryName);
    }

    @Override
    public void deleteMeetingById(Integer pId) {
        taskMeetingDao.deleteMeetingById(pId);
    }

    //循环会议冲突检查
    public List checkRepeat(Remeet remeet) throws ParseException {
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


    public static int caculateTotalTime(String startTime, String endTime) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = null;
        Date date = formatter.parse(startTime);
        long ts = date.getTime();
        date1 = formatter.parse(endTime);
        long ts1 = date1.getTime();
        long ts2 = ts1 - ts;
        int totalTime = 0;
        totalTime = (int) (ts2 / (24 * 3600 * 1000) + 1);
        return totalTime;
    }


    //根据会议id查询参会人员
    public List getInternal(String name) {
        //查询联系人
        List<Map<String, String>> user = new ArrayList<Map<String, String>>();
        String strip = StringUtils.strip(name, "[]");
        String[] split = strip.split(",");
        for (String s : split) {
            Map<String, String> map = newUserService.queryUserByName(s);
            user.add(map);
        }
        return user;
    }

    //获取当前系统下一天日期
    public static Date getNextDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        date = calendar.getTime();
        return date;
    }

    /**
     * 获取当前日期是星期几<br>
     *
     * @param dt
     * @return 当前日期是星期几
     */
    public static String getWeekOfDate(Date dt) {
        String[] weekDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);

        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }
}
