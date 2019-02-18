package com.http;

import java.io.InputStream;
import java.security.KeyStore;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;

import com.execpt.WxException;
import com.util.StringUtil;


public class HttpClientBuilder {

	private int maxTotal = 0;
	
    private int defaultMaxPerRoute = 0;
    
    private int connectionRequestTimeout;
    
    private int connectTimeout;
    
    private int socketTimeout;
    
    private long maxIdleTime;
    
    private String certPassword;
    
    private String certPath;

    public static HttpClientBuilder create(){
    	return new HttpClientBuilder();
    }
    
	public CloseableHttpClient build() {
		RequestConfig requestConfig = RequestConfig
				.custom()
				.setConnectTimeout(getConnectTimeout())
				.setConnectionRequestTimeout(getConnectionRequestTimeout())
				.setSocketTimeout(getSocketTimeout())
				.build();
		org.apache.http.impl.client.HttpClientBuilder httpClientBuilder = 
				HttpClients
				.custom()
				.setMaxConnTotal(getMaxTotal())
				.setMaxConnPerRoute(getDefaultMaxPerRoute())
				.setDefaultRequestConfig(requestConfig)
				.evictExpiredConnections()
				.evictIdleConnections(this.getMaxIdleTime(),TimeUnit.SECONDS);
		return httpClientBuilder.build();
	}
	
	public CloseableHttpClient buildSSL() throws Exception{
		if(StringUtil.isNull(certPassword)){
			throw new WxException("证书密码缺失");
		}
		if(StringUtil.isNull(certPath)){
			throw new WxException("证书路径缺失");
		}
		 // 证书
        char[] password = certPassword.toCharArray();
        InputStream certStream = getCertStream();
        KeyStore ks = KeyStore.getInstance("PKCS12");
        ks.load(certStream, password);

        // 实例化密钥库 & 初始化密钥工厂
        KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        kmf.init(ks, password);

        // 创建 SSLContext
        SSLContext sslContext = SSLContextBuilder.create().loadKeyMaterial(ks, password).build();
        
		RequestConfig requestConfig = RequestConfig
				.custom()
				.setConnectTimeout(getConnectTimeout())
				.setConnectionRequestTimeout(getConnectionRequestTimeout())
				.setSocketTimeout(getSocketTimeout())
				.build();
		org.apache.http.impl.client.HttpClientBuilder httpClientBuilder = 
				HttpClients
				.custom()
				.setSSLContext(sslContext)
				.setMaxConnTotal(getMaxTotal())
				.setMaxConnPerRoute(getDefaultMaxPerRoute())
				.setDefaultRequestConfig(requestConfig)
				.evictExpiredConnections()
				.evictIdleConnections(this.getMaxIdleTime(),TimeUnit.SECONDS);
		return httpClientBuilder.build();
	}

	private InputStream getCertStream() {
		InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(this.getCertPath());
		return resourceAsStream;
	}

	public int getMaxTotal() {
		return maxTotal;
	}

	public void setMaxTotal(int maxTotal) {
		this.maxTotal = maxTotal;
	}

	public int getDefaultMaxPerRoute() {
		return defaultMaxPerRoute;
	}

	public void setDefaultMaxPerRoute(int defaultMaxPerRoute) {
		this.defaultMaxPerRoute = defaultMaxPerRoute;
	}

	public int getConnectionRequestTimeout() {
		return connectionRequestTimeout;
	}

	public void setConnectionRequestTimeout(int connectionRequestTimeout) {
		this.connectionRequestTimeout = connectionRequestTimeout;
	}

	public int getConnectTimeout() {
		return connectTimeout;
	}

	public void setConnectTimeout(int connectTimeout) {
		this.connectTimeout = connectTimeout;
	}

	public int getSocketTimeout() {
		return socketTimeout;
	}

	public void setSocketTimeout(int socketTimeout) {
		this.socketTimeout = socketTimeout;
	}

	public long getMaxIdleTime() {
		return maxIdleTime;
	}

	public void setMaxIdleTime(long maxIdleTime) {
		this.maxIdleTime = maxIdleTime;
	}

	public String getCertPassword() {
		return certPassword;
	}

	public void setCertPassword(String certPassword) {
		this.certPassword = certPassword;
	}

	public String getCertPath() {
		return certPath;
	}

	public void setCertPath(String certPath) {
		this.certPath = certPath;
	}
	
}
