package com.service;

public interface SignService {

	boolean checkSign(String signature,String timestamp,String nonce);
}
