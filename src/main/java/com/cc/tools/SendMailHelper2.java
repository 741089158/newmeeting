package com.cc.tools;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;

import javax.activation.DataHandler;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

/**
 * 邮件发送帮助类
 * @author TANGXIAN
 *
 */
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SendMailHelper2 {


    private static final Logger logger = LoggerFactory.getLogger(SendMailHelper2.class);
//    private static String sendEmailAccount = "ymtcmeeting@ymtc.com";//发送邮箱
//    private static String sendEmailPassword = "Wh123456";//密码
//    private static String sendEmailSMTPHost = "email.xmc.wh";//邮箱服务器
//    private static String sendEmailSMTPPort = "25";//端口

    private static String sendEmailAccount = "741089158@qq.com";//发送邮箱
    private static String sendEmailPassword = "zudsrcwxrfpmbceg";//密码
    private static String sendEmailSMTPHost = "smtp.qq.com";//邮箱服务器
    private static String sendEmailSMTPPort = "25";//端口

    private class EmailAuthenticator extends Authenticator {
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(sendEmailAccount, sendEmailPassword);
        }
    }


    //邮件配置
    public MimeMessage getMimeMessage(String receiveMailAccount, String title, String subject,
                                      String addr, String startTime, String endTime) {
        Properties props = new Properties();
        MimeMessage message = null;
        try {
            props.setProperty("mail.transport.protocol", "smtp"); // 使用的协议（JavaMail规范要求）
//			props.setProperty("mail.smtp.host", sendEmailSMTPHost); // 发件人的邮箱的
//			props.setProperty("mail.smtp.auth", "true"); // 需要请求认证
//			props.setProperty("mail.smtp.starttls.enable", "true");
//			props.setProperty("mail.smtp.ssl", "true");
            props.setProperty("mail.transport.protocol", "smtp");   // 使用的协议（JavaMail规范要求）
            props.setProperty("mail.smtp.host", sendEmailSMTPHost);
            props.setProperty("mail.smtp.port", sendEmailSMTPPort);
            props.setProperty("mail.smtp.auth", "false");
            //props.setProperty("mail.smtp.auth", "true");
//			final String smtpPort = "465";
//			props.setProperty("mail.smtp.port", smtpPort);
//			props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//	        props.setProperty("mail.smtp.socketFactory.fallback", "false");
//	        props.setProperty("mail.smtp.socketFactory.port", smtpPort);

            Authenticator authenticator = new EmailAuthenticator();
            Session session = Session.getInstance(props, authenticator);
            session.setDebug(true);
            message = new MimeMessage(session);
            message.setFrom(new InternetAddress(sendEmailAccount));
//			InternetAddress[] sendTo = InternetAddress.parse(receiveMailAccount);
//	        message.setRecipients(MimeMessage.RecipientType.TO, sendTo);
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiveMailAccount, title, "UTF-8"));
            message.setSubject(subject);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return message;
    }


    /**
     * 发送带日历会议邮件
     *
     * @param receiveMailAccount 收件人邮箱
     * @param title              标题
     * @param subject
     * @param content            内容
     * @param addr               会议地址
     * @param startTime          会议开始时间
     * @param endTime            会议结束时间
     */
    public boolean sendVideoMail(String receiveMailAccount, String title, String subject, String content, String addr,
                String startTime, String endTime,String uuid) {
            //System.out.println(sendEmailAccount+"--------------------");
            boolean bool;
            startTime = getUtc(startTime);
            endTime = getUtc(endTime);
            //System.out.println("startTime=" + startTime + ",  endTime=" + endTime);

        MimeMessage message = getMimeMessage(receiveMailAccount, title, subject, addr, startTime, endTime);
        StringBuffer buffer = new StringBuffer();
        buffer.append("BEGIN:VCALENDAR\n")
                .append("PRODID:-//Microsoft Corporation//Outlook 9.0 MIMEDIR//EN\n")
                .append("VERSION:2.0\n")
                .append("METHOD:REQUEST\n")
                .append("BEGIN:VEVENT\n")
                .append("ATTENDEE;ROLE=REQ-PARTICIPANT;")
                .append("RSVP=TRUE:MAILTO:")
                .append(receiveMailAccount).append("\n")
                .append("ORGANIZER:MAILTO:")
                .append(receiveMailAccount).append("\n")
                .append("DTSTART:").append(startTime).append("\n")
                .append("DTEND:").append(endTime).append("\n")
                .append("LOCATION:").append(addr).append("\n")
                //.append("UID:").append(UUID.randomUUID().toString()).append("\n")// 如果id相同的话，outlook会认为是同一个会议请求，所以使用uuid。
                .append("UID:").append(uuid.toString()).append("\n")
                .append("CATEGORIES:SuccessCentral Reminder\n")
                .append("DESCRIPTION:").append(content).append("\n")
                .append("SUMMARY:").append(subject).append("\n")
                .append("PRIORITY:5\n").append("CLASS:PUBLIC\n")
                .append("BEGIN:VALARM\n")
                .append("TRIGGER:-PT15M\n").append("ACTION:DISPLAY\n")
                .append("DESCRIPTION:Reminder\n").append("END:VALARM\n")
                .append("END:VEVENT\n").append("END:VCALENDAR");
        BodyPart messageBodyPart = new MimeBodyPart();
        // 测试下来如果不这么转换的话，会以纯文本的形式发送过去，
        // 如果没有method=REQUEST;charset=\"UTF-8\"，outlook会议附件的形式存在，而不是直接打开就是一个会议请求
        try {
            messageBodyPart.setDataHandler(new DataHandler(
                    new ByteArrayDataSource(buffer.toString(), "text/calendar;method=REQUEST;charset=\"UTF-8\"")));
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            message.setContent(multipart);

            Transport.send(message);
            bool = true;
        } catch (Exception e) {
            e.printStackTrace();
            bool = false;
        }
        return bool;
    }

    /**
     * 发送普通邮件
     *
     * @param receiveMailAccount 收件人邮箱
     * @param title              邮件标题
     * @param subject            邮件主题
     * @param content            邮件正文(支持HTML语法)
     * @return
     */
    public boolean sendMail(String receiveMailAccount, String title, String subject, String content) {
        boolean bool;
        MimeMessage message = getMimeMessage(receiveMailAccount, title, subject, "", "", "");
        try {
            message.setContent(content, "text/html;charset=UTF-8");
            Transport.send(message);
            bool = true;
        } catch (MessagingException e) {
            e.printStackTrace();
            bool = false;
        }
        return bool;
    }

    /**
     * 转 UTC 时间
     *
     * @param str 字符串格式时间
     * @return UTC 时间
     */
    public static String getUtc(String str) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long millionSeconds = 0;
        try {
            millionSeconds = sdf.parse(str).getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Date date = new Date(millionSeconds);
        String nowTime = sdf.format(date);
        String utcTime = nowTime.replace("-", "").replace(" ", "T").replace(":", "");
        return utcTime;
    }

    public static void main(String[] args) {
        //String receiveMailAccount = "18694062393@163.com";
        String receiveMailAccount = "lin13163293367@outlook.com";
        String title = "this is a title";
        String subject = "this is a subject";
        String content = "this is a content";
        String addr = "5樓203";
        String startTIme = "2019-07-31 16:00:00";
        String endTIme = "2019-07-31 17:00:00";

        //boolean bool1 = new SendMailHelper2().sendVideoMail(receiveMailAccount, title, subject, content, addr, startTIme, endTIme);
        boolean bool1 = new SendMailHelper2().sendCancel(receiveMailAccount, title, subject, content, addr, startTIme, endTIme,"123");
        System.out.println("send1 -->> " + (bool1 ? "success" : "failed"));

        //boolean bool2 = new SendMailHelper2().sendMail(receiveMailAccount, title, subject, content);
        //System.out.println("send1 -->> " + (bool2 ? "success" : "failed"));

//		String ymdhms = "2019-07-03 10:30:00";
//		formatTime(ymdhms);
//		System.out.println(getUtc(ymdhms));
    }


    //取消会议
    public boolean sendCancel(String receiveMailAccount, String title, String subject, String content, String addr,
                                 String startTime, String endTime,String uuid) {
        //System.out.println(sendEmailAccount+"--------------------");
        boolean bool;
        startTime = getUtc(startTime);
        endTime = getUtc(endTime);
        //System.out.println("startTime=" + startTime + ",  endTime=" + endTime);

        MimeMessage message = getMimeMessage(receiveMailAccount, title, subject, addr, startTime, endTime);
        StringBuffer buffer = new StringBuffer();
        buffer.append("BEGIN:VCALENDAR\n").append("PRODID:-//Microsoft Corporation//Outlook 9.0 MIMEDIR//EN\n")
                .append("VERSION:2.0\n").append("METHOD:CANCEL\n").append("BEGIN:VEVENT\n")
                .append("ATTENDEE;ROLE=REQ-PARTICIPANT;")
                .append("RSVP=TRUE:MAILTO:").append(receiveMailAccount).append("\n")
                .append("ORGANIZER:MAILTO:").append(receiveMailAccount).append("\n")
                .append("DTSTART:").append(startTime).append("\n")
                .append("DTEND:").append(endTime).append("\n")
                .append("LOCATION:").append(addr).append("\n")
                //.append("UID:").append(UUID.randomUUID().toString()).append("\n")// 如果id相同的话，outlook会认为是同一个会议请求，所以使用uuid。
                .append("UID:").append(uuid.toString()).append("\n")
                .append("CATEGORIES:SuccessCentral Reminder\n")
                .append("DESCRIPTION:").append(content).append("\n")
                .append("SUMMARY:").append(subject).append("\n")
                .append("PRIORITY:5\n").append("CLASS:PUBLIC\n")
                .append("BEGIN:VALARM\n")
                .append("TRIGGER:-PT15M\n").append("ACTION:DISPLAY\n")
                .append("DESCRIPTION:Reminder\n").append("END:VALARM\n")
                .append("END:VEVENT\n").append("END:VCALENDAR");
        BodyPart messageBodyPart = new MimeBodyPart();
        // 测试下来如果不这么转换的话，会以纯文本的形式发送过去，
        // 如果没有method=REQUEST;charset=\"UTF-8\"，outlook会议附件的形式存在，而不是直接打开就是一个会议请求
        try {
            messageBodyPart.setDataHandler(new DataHandler(
                    new ByteArrayDataSource(buffer.toString(), "text/calendar;method=REQUEST;charset=\"UTF-8\"")));
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            message.setContent(multipart);

            Transport.send(message);
            bool = true;
        } catch (Exception e) {
            e.printStackTrace();
            bool = false;
        }
        return bool;
    }

}
