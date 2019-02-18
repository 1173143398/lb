package com.wxpay;

import java.io.InputStream;

public class DefaultWXPayConfig extends WXPayConfig {

	private String appID;
	private String mchID;
	private String key;
	private String certPath;
	private String domain;
	private boolean primaryDomain;
	
	@Override
	public String getAppID() {
		return appID;
	}

	@Override
	public String getMchID() {
		return mchID;
	}

	@Override
	public String getKey() {
		return key;
	}

	@Override
	public InputStream getCertStream() {
		return Thread.currentThread().getContextClassLoader().getResourceAsStream(certPath);
	}

	@Override
	public IWXPayDomain getWXPayDomain() {
		return new IWXPayDomain(){

			@Override
			public void report(String domain, long elapsedTimeMillis,
					Exception ex) {
				
			}

			@Override
			public DomainInfo getDomain(WXPayConfig config) {
				return new DomainInfo(domain,primaryDomain);
			}
			
		};
	}

	public String getCertPath() {
		return certPath;
	}

	public void setCertPath(String certPath) {
		this.certPath = certPath;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public boolean isPrimaryDomain() {
		return primaryDomain;
	}

	public void setPrimaryDomain(boolean primaryDomain) {
		this.primaryDomain = primaryDomain;
	}

	public void setAppID(String appID) {
		this.appID = appID;
	}

	public void setMchID(String mchID) {
		this.mchID = mchID;
	}

	public void setKey(String key) {
		this.key = key;
	}

	
}
