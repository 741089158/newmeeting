package com.cc.service.Impl;

import static com.cc.util.DateChange.changeTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cc.dao.AppointmentMeetDao;
import com.cc.dao.MailDao;
import com.cc.entity.HistoryMeet;
import com.cc.entity.Mail;
import com.cc.entity.MeetUser;
import com.cc.entity.Remeet;
import com.cc.entity.RepeatMeeting;
import com.cc.entity.User;
import com.cc.service.AppointmentMeetService;
import com.cc.util.GetUser;
import com.cc.util.PKGenerate;
import com.github.pagehelper.PageHelper;

@Service("appointmentMeetService")
public class AppointmentServiceimpl implements AppointmentMeetService {

	@Autowired
	private AppointmentMeetDao appointmentMeetDao;

	@Autowired
	private MailDao mailDao;

	// 预定本地会议
	public void appointmentMeet(Remeet remeet, List<Map<String, String>> user, HttpServletRequest request)
			throws ParseException {
		User users = GetUser.current(request); // 登陆用户
		try {
			appointmentMeetDao.appointmentMeet(remeet); // 添加会议室据
		} catch (Exception e) {
			e.printStackTrace(); // TODO 异常不处理了吗？
		}

		if (user != null && user.size() > 0) {
			for (Map<String, String> oneUser : user) {
				if (oneUser==null){
					continue;
				}
				appointmentMeetDao.insertUserIdAndMeetId(oneUser.get("sAMAccountName"), remeet.getId());

				// 邮件内容
				String content = "您好！" + "\\n" + users.getName() //
						+ " 邀请您参加以下会议：" //
						+ "\\n    会议名称:" + remeet.getMeetName() //
						+ "\\n    会议开始时间:" + remeet.getMeetDate() //
						+ "\\n    开会地点:" + remeet.getMeetRoomName(); //

				String password = remeet.getRequireCallId(); // 会议密码
				if (password != null && password.length() != 0) {
					content += "\\n    会议密码:" + password;
				}
				String vedioMIdStr = "";
				Integer vedioMId = remeet.getUri(); // 会议号
				if (vedioMId != null) {
					vedioMIdStr = vedioMId.toString();
					content += "\\n    会议号:" + vedioMIdStr;
				}

				String meetDate = remeet.getMeetDate() + ":00"; // 开始时间
				String meetTime = remeet.getMeetTime(); // 时长 HH:mm
				Date startTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(meetDate); // 开始时间
				Date endTime = new Date(startTime.getTime() + changeTime(meetTime)); // 结束时间

				Mail mail = new Mail();
				mail.setReceivemailaccount(oneUser.get("mail"));
				mail.setMailtitle(remeet.getMeetName());
				mail.setMailsubject(remeet.getMeetName());
				mail.setMailcontent(content);
				mail.setCreatedate(startTime);
				mail.setRemark(remeet.getMeetRoomName()); // remark --->remeet.getMeetRoomName() 会议室
				mail.setStatus(1);
				mail.setIsdisabled(0);
				mail.setSenddate(endTime);
				mail.setMailsign(PKGenerate.generateUUID()); // 会议邀请标识uuid
				mail.setMid(remeet.getId());				//会议id
				System.out.println(mail);
				try {
					if (mail.getReceivemailaccount() != null && mail.getReceivemailaccount() != "") {
						mailDao.add(mail);
					}
				} catch (Exception e) {
					e.printStackTrace(); // TODO 异常不处理吗？
				}
			}
		}
	}

	@Override
	public void repeatMeet(Remeet remeet, List<Map<String, String>> user, HttpServletRequest request)
			throws ParseException {
		// 登陆用户
		User users = GetUser.current(request);
		try {
			appointmentMeetDao.repeatMeet(remeet);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			// 添加会议室据
			appointmentMeetDao.appointmentMeet(remeet);

		} catch (Exception e) {
			// e.printStackTrace();
		}
		// 将会议创建人添加到集合
		// user.add(stringStringMap);
		if (user != null && user.size() > 0) {
			for (Map<String, String> u : user) {
				try {
					if (u==null){
						continue;
					}
					appointmentMeetDao.insertUserIdAndMeetId(u.get("sAMAccountName"), remeet.getId());
				} catch (Exception e) {
					e.printStackTrace();
				}
				String content = "您好！" + "\\n" + users.getName() + " 邀请您参加以下会议：" + "\\n    会议名称:" + remeet.getMeetName()
						+
						// "\\n 会议主持人:" + users.getUsername() +
						"\\n    会议开始时间:" + remeet.getMeetDate() + "\\n    开会地点:" + remeet.getMeetRoomName();

				String password = remeet.getRequireCallId(); // 会议密码
				if (password != null && password.length() != 0) {
					content += "\\n    会议密码:" + password;
				}

				String num = "";
				Integer uri = remeet.getUri(); // 会议号
				if (uri != null) {
					num = uri.toString();
					content += "\\n    会议号:" + num;
				}

				String meetDate = remeet.getMeetDate() + ":00"; // 开始时间
				String meetTime = remeet.getMeetTime(); // 时长 HH:mm
				Date startTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(meetDate); // 开始时间
				Date endTime = new Date(startTime.getTime() + changeTime(meetTime)); // 结束时间
				
				Mail mail = new Mail();
				mail.setReceivemailaccount(u.get("mail"));
				mail.setMailtitle(remeet.getMeetName());
				mail.setMailsubject(remeet.getMeetName());
				mail.setMailcontent(content);
				mail.setCreatedate(startTime);
				mail.setRemark(remeet.getMeetRoomName()); // remark --->remeet.getMeetRoomName() 会议室
				mail.setStatus(1);
				mail.setIsdisabled(0);
				mail.setSenddate(endTime);
				mail.setMailsign(PKGenerate.generateUUID()); // 会议邀请标识uuid
				mail.setMid(remeet.getId());
				
				try {
					if (mail.getReceivemailaccount() != null && mail.getReceivemailaccount() != "") {
						mailDao.add(mail);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

//	public static void main(String[] args) {
//		String s = PKGenerate.generateUUID();
//		System.out.println(s);
//	}

	public List<Remeet> findAll() {
		return appointmentMeetDao.findAll();
	}

	public List<Remeet> findPage(int page, int size, String meetName) {
		PageHelper.startPage(page, size);
		return appointmentMeetDao.findAll(meetName);
	}

	// 查询所有会议
	public List<Remeet> findPage(int page, int size) {
		PageHelper.startPage(page, size);
		return appointmentMeetDao.findAll();
	}

	public List<HistoryMeet> findPageHistory(Integer page, Integer size, String username, String meetName) {
		PageHelper.startPage(page, size);
		return appointmentMeetDao.findPageHistory(username, meetName);
	}

	public List<MeetUser> findHistoryUser(Integer page, Integer size, Integer id) {
		PageHelper.startPage(page, size);
		return appointmentMeetDao.findHistoryUser(id);
	}

	@Override
	public Remeet findOne(Integer id) {
		return appointmentMeetDao.findOne(id);
	}

	@Override
	public void update(Remeet remeet) {
		appointmentMeetDao.update(remeet);
	}

	@Override
	public List<Remeet> findMeeting(Integer state, String repeatType) {
		return appointmentMeetDao.findMeeting(state, repeatType);
	}

	@Override
	public Remeet findByRid(int rid) {
		return appointmentMeetDao.findByRid(rid);
	}

	@Override
	public RepeatMeeting findRepeatMeeting(Integer id) {
		return appointmentMeetDao.findRepeatMeeting(id);
	}

	@Override
	public void updateState(Integer id) {
		appointmentMeetDao.updateState(id);
	}

	@Override
	public List<Remeet> findMeetByUserId(Integer id) {
		return appointmentMeetDao.findMeetByUserId(id);
	}

	@Override
	public List<Remeet> findMeetByUsername(Integer page, Integer size, String username, String meetName,
			String meetDate) {
		PageHelper.startPage(page, size);
		return appointmentMeetDao.findMeetByUsername(username, meetName, meetDate);
	}

	@Override
	public void insertUserIdAndMeetId(Integer userId, Integer meetId) {
		appointmentMeetDao.insertUserIdAndMeetId(userId, meetId);
	}

	@Override
	public List<Remeet> findMeetByUserName(String username) {
		return appointmentMeetDao.findMeetByUsername(username, "", "");
	}

	@Override
	public List<Remeet> findMeetingByRoomId(String roomId) {
		return appointmentMeetDao.findMeetingByRoomId(roomId);
	}

	@Override
	public void deleteMeetingById(String pId) {
		appointmentMeetDao.deleteMeetingById(pId);
	}

}
