package com.config;

public class WxPayConfig {

	private String appId;
	private String mchId;
	private String key;
	private String certPath;
	private String wxDomain;
	private String primaryDomain;
	private String notifyUrl;
	private String autoReport;
	private String useSandbox;
	
	private String sandboxSignKey;
	
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getMchId() {
		return mchId;
	}
	public void setMchId(String mchId) {
		this.mchId = mchId;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getCertPath() {
		return certPath;
	}
	public void setCertPath(String certPath) {
		this.certPath = certPath;
	}
	public String getWxDomain() {
		return wxDomain;
	}
	public void setWxDomain(String wxDomain) {
		this.wxDomain = wxDomain;
	}
	public String getPrimaryDomain() {
		return primaryDomain;
	}
	public void setPrimaryDomain(String primaryDomain) {
		this.primaryDomain = primaryDomain;
	}
	public String getNotifyUrl() {
		return notifyUrl;
	}
	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}
	public String getAutoReport() {
		return autoReport;
	}
	public void setAutoReport(String autoReport) {
		this.autoReport = autoReport;
	}
	public String getUseSandbox() {
		return useSandbox;
	}
	public void setUseSandbox(String useSandbox) {
		this.useSandbox = useSandbox;
	}
	public String getSandboxSignKey() {
		return sandboxSignKey;
	}
	public void setSandboxSignKey(String sandboxSignKey) {
		this.sandboxSignKey = sandboxSignKey;
	}
	
}
