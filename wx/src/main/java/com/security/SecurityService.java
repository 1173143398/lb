package com.security;

public interface SecurityService {
 
	String encrypt(String message,String timeStamp,String nonce,String encryptType,String sign);
	
	String decrypt(String message,String timeStamp,String nonce,String encryptType,String sign);
	
	boolean checkSign(String signature,String timestamp,String nonce);
	
	String sign(String jsapiTicket,String noncestr,String timestamp,String url);
}
