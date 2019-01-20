package com.http;

import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

public class WxPoolingHttpClientConnectionManager extends PoolingHttpClientConnectionManager {

	private static Registry<ConnectionSocketFactory> getWxRegistry() {
        return RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.getSocketFactory())
                .register("https", new SSLConnectionSocketFactory(SSLContextBuilder.getInstance(),new WxHostnameVerifier()))
                .build();
    }
	public WxPoolingHttpClientConnectionManager() {
		super(getWxRegistry());
	}
}
