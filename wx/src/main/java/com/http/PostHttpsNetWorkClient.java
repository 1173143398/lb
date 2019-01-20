package com.http;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostHttpsNetWorkClient implements NetWorkClient ,InitializingBean{

	@Autowired
	private NetWorkManager netWorkManager;
	
	@Autowired
	private CloseableHttpClient closeableHttpClient;
	
	@Override
	public String send(String url, String message) {
		try {
			HttpPost post = new HttpPost(url);
			CloseableHttpResponse execute = closeableHttpClient.execute(post);
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
		clients.put("POST", this);
		netWorkManager.addClient("HTTPS", clients);
	}

}
