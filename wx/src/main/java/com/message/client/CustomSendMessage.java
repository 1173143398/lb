package com.message.client;

import java.util.Map;

import com.alibaba.fastjson.annotation.JSONField;
import com.message.IMessage;

public class CustomSendMessage implements IMessage {

	@JSONField(name = "touser")
	private String toUser;
	
	private String msgType;
	private Map<String,String> text;
	public String getToUser() {
		return toUser;
	}
	public void setToUser(String toUser) {
		this.toUser = toUser;
	}
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	public Map<String, String> getText() {
		return text;
	}
	public void setText(Map<String, String> text) {
		this.text = text;
	}
	
	
}
