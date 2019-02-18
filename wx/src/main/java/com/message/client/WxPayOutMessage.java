package com.message.client;

import com.alibaba.fastjson.annotation.JSONField;
import com.message.IMessage;

public class WxPayOutMessage implements IMessage {

	@JSONField(name = "return_code")
	private String returnCode;
	
	@JSONField(name = "return_msg")
	private String returnMsg; 
	
	@JSONField(name = "prepay_id")
	private String prepayId;
	
	@JSONField(name = "code_url")
	private String codeUrl;
	
	@JSONField(name = "err_code")
	private String errCode;
	
	@JSONField(name = "err_code_des")
	private String errCodeDes;

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

	public String getPrepayId() {
		return prepayId;
	}

	public void setPrepayId(String prepayId) {
		this.prepayId = prepayId;
	}

	public String getCodeUrl() {
		return codeUrl;
	}

	public void setCodeUrl(String codeUrl) {
		this.codeUrl = codeUrl;
	}

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public String getErrCodeDes() {
		return errCodeDes;
	}

	public void setErrCodeDes(String errCodeDes) {
		this.errCodeDes = errCodeDes;
	}
	
	
}
