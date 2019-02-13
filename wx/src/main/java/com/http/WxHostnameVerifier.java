package com.http;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class WxHostnameVerifier implements HostnameVerifier {

	private static Log log = LogFactory.getLog(WxHostnameVerifier.class);
	
	@Override
	public boolean verify(String hostname, SSLSession session) {
		log.info("============================================");
		log.info("===================verify====================="+hostname);
		log.info("============================================");
		return true;
	}

}
