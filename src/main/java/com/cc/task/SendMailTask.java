package com.cc.task;

import com.cc.controller.SelectController;
import com.cc.entity.Mail;
import com.cc.service.LdapService;
import com.cc.service.MailService;
import com.cc.tools.SendMailHelper;
import com.cc.tools.SendMailHelper2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class SendMailTask {

	@Autowired
	private MailService mailService;

	@Autowired
	private SelectController selectController;

	@Scheduled(cron = "* 0/10 * * * ? ") // 间隔20分钟执行
	public void Method() {
		selectController.org(new ArrayList<>());
	}

	// 邮件定时任务
	// @Scheduled(cron = "0/30 * * * * ? ") // 间隔30秒执行
	public void taskCycle() {
		try {
			List<Mail> list = mailService.findAll(1);			//查询会议状态为1的邮件  -->未发送
			for (Mail mail : list) {
				String startTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(mail.getCreatedate());
				String endTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(mail.getSenddate());

				boolean bool = new SendMailHelper2().sendVideoMail(mail.getReceivemailaccount(), mail.getMailtitle(),
						mail.getMailtitle(), mail.getMailcontent(), mail.getRemark(), startTime, endTime,mail.getMailsign());
				if (bool) {
					// 发送成功
					mail.setSenddate(new Date());
					mail.setStatus(2);						//-->发送成功
					mailService.update(mail);
				} else {
					// 发送失败
					mail.setSenddate(new Date());
					mail.setStatus(3);						//-->发送失败
					mailService.update(mail);
				}
				// 每隔多少秒发送
				Thread.sleep(5000l);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
