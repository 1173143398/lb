package com.message.server;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.message.IMessage;

@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class TextInMessage implements IMessage {

	@XmlElement(name = "ToUserName")
	private String toUserName;
	
	@XmlElement(name = "FromUserName")
	private String fromUserName;
	
	@XmlElement(name = "CreateTime")
	private String createTime;
	
	@XmlElement(name = "MsgType")
	private String msgType;
	
	@XmlElement(name = "Content")
	private String content;
	
	@XmlElement(name = "MsgId")
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
