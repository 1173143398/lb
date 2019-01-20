package com.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("xml")
public class EventMessage implements IMessage {

	@XStreamAlias("Event")
	private String eventType;
	
	@XStreamAlias("ToUserName")
	private String toUserName;
	
	@XStreamAlias("FromUserName")
	private String fromUserName;
	
	@XStreamAlias("CreateTime")
	private String createTime;
	
	@XStreamAlias("MsgType")
	private String msgType;
	
	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	public String getToUserName() {
		return toUserName;
	}
	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}
	
	public String getFromUserName() {
		return fromUserName;
	}
	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}
	
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	
}
