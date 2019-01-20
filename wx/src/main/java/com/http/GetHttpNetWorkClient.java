package com.http;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetHttpNetWorkClient implements NetWorkClient,InitializingBean {

	@Autowired
	private CloseableHttpClient closeableHttpClient;
	
	@Autowired
	private NetWorkManager netWorkManager;
	
	@Override
	public String send(String url, String message) {
		try {
			HttpGet get = new HttpGet(url);
			CloseableHttpResponse execute = closeableHttpClient.execute(get);
			return EntityUtils.toString(execute.getEntity());
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Map<String,NetWorkClient> clients = new HashMap<String,NetWorkClient>();
		clients.put("GET", this);
		netWorkManager.addClient("HTTP", clients);
	}

}
