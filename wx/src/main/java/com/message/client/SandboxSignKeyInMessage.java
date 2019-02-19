package com.message.client;

import com.message.IMessage;

public class SandboxSignKeyInMessage implements IMessage {

	private String mchId;
	private String nonceStr;
	public String getMchId() {
		return mchId;
	}
	public void setMchId(String mchId) {
		this.mchId = mchId;
	}
	public String getNonceStr() {
		return nonceStr;
	}
	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}
	
	
}
