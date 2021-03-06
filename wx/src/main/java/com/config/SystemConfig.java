package com.config;

import java.sql.Timestamp;

public class SystemConfig {

	private String serverMsgType;
	private String appId;
	private String appSecret;
	private String accessToken;
	private String token;
	private String jsapiTicket;
	private String domain;
	private Timestamp tms;
	private int expiresIn;
	private String securityType;
	private String encodingAeskey;
	
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
	
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public Timestamp getTms() {
		return tms;
	}
	public void setTms(Timestamp tms) {
		this.tms = tms;
	}
	public int getExpiresIn() {
		return expiresIn;
	}
	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getJsapiTicket() {
		return jsapiTicket;
	}
	public void setJsapiTicket(String jsapiTicket) {
		this.jsapiTicket = jsapiTicket;
	}
	public String getSecurityType() {
		return securityType;
	}
	public void setSecurityType(String securityType) {
		this.securityType = securityType;
	}
	public String getEncodingAeskey() {
		return encodingAeskey;
	}
	public void setEncodingAeskey(String encodingAeskey) {
		this.encodingAeskey = encodingAeskey;
	}
	
}
