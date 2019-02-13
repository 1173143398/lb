package com.message.client;

import com.alibaba.fastjson.annotation.JSONField;
import com.message.IMessage;

public class UploadFileOutMessage implements IMessage{

	//{"type":"TYPE","media_id":"MEDIA_ID","created_at":123456789}
	//{"errcode":40004,"errmsg":"invalid media type"}
	@JSONField(name = "media_id")
	private String mediaId;
	
	@JSONField(name = "type")
	private String type;

	@JSONField(name = "created_at")
	private String createdAt;

	@JSONField(name = "errcode")
	private String errcode;
	
	@JSONField(name = "errmsg")
	private String errmsg;
	
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

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	
	
	
}
