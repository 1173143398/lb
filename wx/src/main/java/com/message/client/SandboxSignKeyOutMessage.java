package com.message.client;

import com.alibaba.fastjson.annotation.JSONField;
import com.message.IMessage;

public class SandboxSignKeyOutMessage implements IMessage {

	@JSONField(name = "return_code")
	private String returnCode;
	
	@JSONField(name = "return_msg")
	private String returnMsg;
	
	@JSONField(name = "mch_id")
	private String mchId;
	
	@JSONField(name = "sandbox_signkey")
	private String sandboxSignkey;
	
	public String getReturnCode() {
		return returnCode;
	}
	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}
	public String getReturnMsg() {
		return returnMsg;
	}
	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}
	public String getMchId() {
		return mchId;
	}
	public void setMchId(String mchId) {
		this.mchId = mchId;
	}
	public String getSandboxSignkey() {
		return sandboxSignkey;
	}
	public void setSandboxSignkey(String sandboxSignkey) {
		this.sandboxSignkey = sandboxSignkey;
	}
	
	
}
