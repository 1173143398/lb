package com.http;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SSLContextBuilder {

	private static Log log = LogFactory.getLog(SSLContextBuilder.class);
	
	public static SSLContext getInstance() {
		try {
			SSLContext sc = SSLContext.getInstance("TLS");
			// 实现一个X509TrustManager接口，用于绕过验证，不用修改里面的方法
			X509TrustManager trustManager = new X509TrustManager() {

				@Override
				public void checkClientTrusted(X509Certificate[] chain, String authType)
						throws java.security.cert.CertificateException {
					log.info("=========================================");
					log.info("===========checkClientTrusted======="+authType);
					log.info("=========================================");
				}

				@Override
				public void checkServerTrusted(X509Certificate[] chain, String authType)
						throws java.security.cert.CertificateException {
					log.info("=========================================");
					log.info("===========checkServerTrusted======="+authType);
					log.info("=========================================");
				}

				@Override
				public X509Certificate[] getAcceptedIssuers() {
					log.info("=========================================");
					log.info("===========getAcceptedIssuers=======");
					log.info("=========================================");
					return null;
				}

				
			};
			sc.init(null, new TrustManager[] { trustManager }, null);
			return sc;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
