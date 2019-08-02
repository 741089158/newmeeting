package com.cc.component;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("spring")
public class ConnConfig {

	private String rootPath;//项目基本路径
	private String sendEmailAccount;//登陆邮箱
	private String sendEmailPassword;//邮箱密码
	private String sendEmailSMTPHost;//邮箱服务器
	private String sendEmailSMTPPort;//端口

	public String getSendEmailAccount() {
		return sendEmailAccount;
	}

	public void setSendEmailAccount(String sendEmailAccount) {
		this.sendEmailAccount = sendEmailAccount;
	}

	public String getSendEmailPassword() {
		return sendEmailPassword;
	}

	public void setSendEmailPassword(String sendEmailPassword) {
		this.sendEmailPassword = sendEmailPassword;
	}

	public String getSendEmailSMTPHost() {
		return sendEmailSMTPHost;
	}

	public void setSendEmailSMTPHost(String sendEmailSMTPHost) {
		this.sendEmailSMTPHost = sendEmailSMTPHost;
	}

	public String getSendEmailSMTPPort() {
		return sendEmailSMTPPort;
	}

	public void setSendEmailSMTPPort(String sendEmailSMTPPort) {
		this.sendEmailSMTPPort = sendEmailSMTPPort;
	}

	public String getRootPath() {
		return rootPath;
	}

	public void setRootPath(String rootPath) {
		this.rootPath = rootPath;
	}
}
