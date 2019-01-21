package com.context;

public class SystemContext {

	private String accessToken;

	public synchronized String getAccessToken() {
		return accessToken;
	}

	public synchronized void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	
	
}
