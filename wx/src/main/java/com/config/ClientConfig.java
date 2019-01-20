package com.config;

public class ClientConfig {

	private String funcNo;
	private String funcDesc;
	private String shcema;
	private String url;
	private String method;
	private String reqClass;
	private String reqMsgType;
	private String respClass;
	private String respMsgType;
	private String serviceBean;
	
	public String getFuncNo() {
		return funcNo;
	}
	public void setFuncNo(String funcNo) {
		this.funcNo = funcNo;
	}
	public String getFuncDesc() {
		return funcDesc;
	}
	public void setFuncDesc(String funcDesc) {
		this.funcDesc = funcDesc;
	}
	
	public String getShcema() {
		return shcema;
	}
	public void setShcema(String shcema) {
		this.shcema = shcema;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getReqClass() {
		return reqClass;
	}
	public void setReqClass(String reqClass) {
		this.reqClass = reqClass;
	}
	public String getReqMsgType() {
		return reqMsgType;
	}
	public void setReqMsgType(String reqMsgType) {
		this.reqMsgType = reqMsgType;
	}
	public String getRespClass() {
		return respClass;
	}
	public void setRespClass(String respClass) {
		this.respClass = respClass;
	}
	public String getRespMsgType() {
		return respMsgType;
	}
	public void setRespMsgType(String respMsgType) {
		this.respMsgType = respMsgType;
	}
	public String getServiceBean() {
		return serviceBean;
	}
	public void setServiceBean(String serviceBean) {
		this.serviceBean = serviceBean;
	}
	
	
}
