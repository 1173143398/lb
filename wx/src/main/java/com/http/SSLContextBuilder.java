package com.http;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class SSLContextBuilder {

	public static SSLContext getInstance() {
		try {
			SSLContext sc = SSLContext.getInstance("TLS");
			// 实现一个X509TrustManager接口，用于绕过验证，不用修改里面的方法
			X509TrustManager trustManager = new X509TrustManager() {

				@Override
				public void checkClientTrusted(X509Certificate[] chain, String authType)
						throws java.security.cert.CertificateException {
					
				}

				@Override
				public void checkServerTrusted(X509Certificate[] chain, String authType)
						throws java.security.cert.CertificateException {
					
				}

				@Override
				public X509Certificate[] getAcceptedIssuers() {
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
