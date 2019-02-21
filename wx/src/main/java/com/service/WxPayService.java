package com.service;

import com.message.client.WxJsapiPayMessage;
import com.message.client.WxPayInMessage;
import com.message.client.WxPayOutMessage;

public interface WxPayService {

	String sign(WxJsapiPayMessage wxJsapiPayMessage);
	
	WxPayOutMessage unifiedOrder(WxPayInMessage wxPayInMessage);
	
	String getJsapiAuthorizeOpenIdUrl(String redirectUrl);
	
	String getJsapiAuthorizeOpenId(String code,String state)  throws Exception;
	
	String getJsapiUserInfo(String redirectUrl);
}
