package com.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("xml")
public class TextInMessage implements IMessage {

	@XStreamAlias("ToUserName")
	private String toUserName;
	
	@XStreamAlias("FromUserName")
	private String fromUserName;
	
	@XStreamAlias("CreateTime")
	private String createTime;
	
	@XStreamAlias("MsgType")
	private String msgType;
	
	@XStreamAlias("Content")
	private String content;
	
	@XStreamAlias("MsgId")
	private String msgId;
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getMsgId() {
		return msgId;
	}
	public void setMsgId(String msgId) {
		this.msgId = msgId;
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
