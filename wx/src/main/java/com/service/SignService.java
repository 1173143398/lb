package com.service;

public interface SignService {

	boolean checkSign(String signature,String timestamp,String nonce);
	
	String sign(String jsapiTicket,String noncestr,String timestamp,String url);
}
