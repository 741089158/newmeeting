package com.cc.tools;
/**
 * 邮件发送帮助类
 * @author TANGXIAN
 *
 */

import com.cc.entity.Remeet;
import com.cc.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public class SendMailHelper {
	private static final String MAIL_FILE = "mail.properties";
    private static final Logger logger = LoggerFactory.getLogger(SendMailHelper.class);
    private static Properties prop = new Properties();
    /**
     * 发送邮件
     * @param receiveMailAccount 收件人邮箱
     * @param title 邮件标题
     * @param subject 邮件主题
     * @param content 邮件正文(支持HTML语法)
     * @return
     */
    public static boolean sendMail(String receiveMailAccount, String title, String subject,String content){
    	boolean bool = false;
    	try {
             String sendEmailAccount = "ymtcmeeting@ymtc.com";//发送邮箱
             String sendEmailPassword = "Wh123456";//密码
             String sendEmailSMTPHost = "email.xmc.wh";//邮箱服务器
             String sendEmailSMTPPort = "25";//端口
    		 // 1. 创建参数配置, 用于连接邮件服务器的参数配置
            Properties props = new Properties();                    // 参数配置
            props.setProperty("mail.transport.protocol", "smtp");   // 使用的协议（JavaMail规范要求）
            props.setProperty("mail.smtp.host", sendEmailSMTPHost);   // 发件人的邮箱的 SMTP 服务器地址
            props.setProperty("mail.smtp.port",sendEmailSMTPPort);
            props.setProperty("mail.smtp.auth", "false");            // 需要请求认证
            // PS: 某些邮箱服务器要求 SMTP 连接需要使用 SSL 安全认证 (为了提高安全性, 邮箱支持SSL连接, 也可以自己开启),
            //     如果无法连接邮件服务器, 仔细查看控制台打印的 log, 如果有有类似 “连接失败, 要求 SSL 安全连接” 等错误,
            //     打开下面 /* ... */ 之间的注释代码, 开启 SSL 安全连接。
            /*
            // SMTP 服务器的端口 (非 SSL 连接的端口一般默认为 25, 可以不添加, 如果开启了 SSL 连接,
            //                  需要改为对应邮箱的 SMTP 服务器的端口, 具体可查看对应邮箱服务的帮助,
            //                  QQ邮箱的SMTP(SLL)端口为465或587, 其他邮箱自行去查看)
            final String smtpPort = "465";
            props.setProperty("mail.smtp.port", smtpPort);
            props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.setProperty("mail.smtp.socketFactory.fallback", "false");
            props.setProperty("mail.smtp.socketFactory.port", smtpPort);
            */
            // 2. 根据配置创建会话对象, 用于和邮件服务器交互
            Session session = Session.getInstance(props);
            session.setDebug(true);                                 // 设置为debug模式, 可以查看详细的发送 log

            // 3. 创建一封邮件    sendEmailAccount
            MimeMessage message = createMimeMessage(session, sendEmailAccount, receiveMailAccount, title, subject, content);

            // 4. 根据 Session 获取邮件传输对象
            Transport transport = session.getTransport();
            // 5. 使用 邮箱账号 和 密码 连接邮件服务器, 这里认证的邮箱必须与 message 中的发件人邮箱一致, 否则报错
            //
            //    PS_01: 成败的判断关键在此一句, 如果连接服务器失败, 都会在控制台输出相应失败原因的 log,
            //           仔细查看失败原因, 有些邮箱服务器会返回错误码或查看错误类型的链接, 根据给出的错误
            //           类型到对应邮件服务器的帮助网站上查看具体失败原因。
            //
            //    PS_02: 连接失败的原因通常为以下几点, 仔细检查代码:
            //           (1) 邮箱没有开启 SMTP 服务;
            //           (2) 邮箱密码错误, 例如某些邮箱开启了独立密码;
            //           (3) 邮箱服务器要求必须要使用 SSL 安全连接;
            //           (4) 请求过于频繁或其他原因, 被邮件服务器拒绝服务;
            //           (5) 如果以上几点都确定无误, 到邮件服务器网站查找帮助。
            //
            //    PS_03: 仔细看log, 认真看log, 看懂log, 错误原因都在log已说明。
            //transport.connect(sendEmailAccount, sendEmailPassword);
            transport.connect(sendEmailAccount, sendEmailPassword);
            // 6. 发送邮件, 发到所有的收件地址, message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
            transport.sendMessage(message, message.getAllRecipients());
            // 7. 关闭连接
            transport.close();
    		bool = true;
		} catch (Exception e) {
			bool = false;
			e.printStackTrace();
		}
    	return bool;
    }
    /**
     * 创建一封只包含文本的简单邮件
     * @param session 和服务器交互的会话
     * @param sendMailAccount 发件人邮箱
     * @param receiveMailAccount 收件人邮箱
     * @param title 邮件标题
     * @param subject 邮件主题
     * @param content 邮件正文(支持HTML语法)
     * @return
     * @throws Exception
     */
    public static MimeMessage createMimeMessage(Session session, String sendMailAccount, String receiveMailAccount, String title, String subject, String content) throws Exception {
        // 1. 创建一封邮件
        MimeMessage message = new MimeMessage(session);
        // 2. From: 发件人（昵称有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改昵称）
        message.setFrom(new InternetAddress(sendMailAccount, title, "UTF-8"));
        // 3. To: 收件人（可以增加多个收件人、抄送、密送）
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMailAccount, "", "UTF-8"));
        // 4. Subject: 邮件主题（标题有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改标题）
        message.setSubject(subject, "UTF-8");
        // 5. Content: 邮件正文（可以使用html标签）（内容有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改发送内容）
        message.setContent(content, "text/html;charset=UTF-8");
        // 6. 设置发件时间
        message.setSentDate(new Date());
        // 7. 保存设置
        message.saveChanges();
        return message;
    }



    /**
     * 获取mail.properties文件数据
     * @return
     */
   /* public static String getString(String key){
        try {
            if (prop.isEmpty()) {
                prop.load(SendMailHelper.class.getClassLoader().getResourceAsStream(MAIL_FILE));
            }
            return prop.getProperty(key);
        }
        catch (Exception ex)
        {
            logger.error("Load localization file error.", ex);
            return "";
        }
    }*/


    public static void main(String[] args) {
        User users=new User();
        Remeet remeet=new Remeet();
        String content = "您好！"+
                "\\n"+ users.getUsername() + ": 邀请您参加以下会议：" +
                "\\n    会议名称:" + remeet.getMeetName() +
                "\\n    会议主持人:" + users.getUsername() +
                "\\n    会议开始时间:" + remeet.getMeetDate() +
                "\\n    会议号:" + remeet.getUri() +
                "\\n    开会地点" + remeet.getMeetRoomName() + "";
    	//boolean bool = sendMail("ymtcmeeting@ymtc.com", "这是一封测试邮件title", "这是一封测试邮件suject", content);
        boolean bool = new SendMailHelper2().sendVideoMail("ymtcmeeting@ymtc.com", "这是一封测试邮件title", "这是一封测试邮件suject", content, "5-A05", "2019-07-05 17:00:00", "2019-07-05 18:00:00","1234");
        if(bool){
    		System.out.println("发送成功");
    	}else{
    		System.out.println("发送失败");
    	}
	}
}
