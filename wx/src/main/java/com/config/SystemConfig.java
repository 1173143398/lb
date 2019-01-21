package com.config;

public class SystemConfig {

	private String serverMsgType;
	private String appId;
	private String appSecret;
	private String accessToken;
	private String timerUpdateTokenUrl;
	public String getServerMsgType() {
		return serverMsgType;
	}
	public void setServerMsgType(String serverMsgType) {
		this.serverMsgType = serverMsgType;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getAppSecret() {
		return appSecret;
	}
	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getTimerUpdateTokenUrl() {
		return timerUpdateTokenUrl;
	}
	public void setTimerUpdateTokenUrl(String timerUpdateTokenUrl) {
		this.timerUpdateTokenUrl = timerUpdateTokenUrl;
	}
	
	
}
