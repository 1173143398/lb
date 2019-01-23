package com.message.client;

import com.alibaba.fastjson.annotation.JSONField;
import com.message.IMessage;

public class TokenMessage implements IMessage{

	@JSONField(name = "access_token")
	private String accessToken;
	@JSONField(name = "expires_in")
	private int expiresIn;
	@JSONField(name = "errcode")
	private String errcode;
	@JSONField(name = "errmsg")
	private String errmsg;
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public int getExpiresIn() {
		return expiresIn;
	}
	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}
	public String getErrcode() {
		return errcode;
	}
	public void setErrcode(String errcode) {
		this.errcode = errcode;
	}
	public String getErrmsg() {
		return errmsg;
	}
	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
	@Override
	public String toString() {
		return "TokenMessage [accessToken=" + accessToken + ", expiresIn="
				+ expiresIn + ", errcode=" + errcode + ", errmsg=" + errmsg
				+ "]";
	}
	
	
}
