package com.message.client;

import com.message.IMessage;

public class CreateMenuOutMessage implements IMessage{

	//{"errcode":0,"errmsg":"ok"}
	private String errcode;
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
	
	
}
