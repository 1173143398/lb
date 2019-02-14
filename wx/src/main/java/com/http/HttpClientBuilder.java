package com.http;

import java.util.concurrent.TimeUnit;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;


public class HttpClientBuilder {

	private int maxTotal = 0;
	
    private int defaultMaxPerRoute = 0;
    
    private int connectionRequestTimeout;
    
    private int connectTimeout;
    
    private int socketTimeout;
    
    private long maxIdleTime;
    

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
	
	
}
