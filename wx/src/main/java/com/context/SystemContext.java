package com.context;

public class SystemContext {

	private String accessToken;
	private String appId;
	private String appSecret;
	private String serverMsgType;
	private String timerUpdateTokenUrl;
	private String token;
	private String jsapiTicket;
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public synchronized String getAccessToken() {
		return accessToken;
	}

	public synchronized void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
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

	public String getServerMsgType() {
		return serverMsgType;
	}

	public void setServerMsgType(String serverMsgType) {
		this.serverMsgType = serverMsgType;
	}

	public String getTimerUpdateTokenUrl() {
		return timerUpdateTokenUrl;
	}

	public void setTimerUpdateTokenUrl(String timerUpdateTokenUrl) {
		this.timerUpdateTokenUrl = timerUpdateTokenUrl;
	}

	public String getJsapiTicket() {
		return jsapiTicket;
	}

	public void setJsapiTicket(String jsapiTicket) {
		this.jsapiTicket = jsapiTicket;
	}
	
}
