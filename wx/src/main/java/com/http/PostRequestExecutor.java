package com.http;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.util.Constants;

@Component
public class PostRequestExecutor implements RequestExecutor<String, String> {

	@Override
	public String execute(CloseableHttpClient httpClient, String url,
			String data) throws Exception {
		HttpPost post = new HttpPost(url);
		post.setHeader("Content-Type", "application/json; charset=utf-8");
		post.setEntity(new StringEntity(data));
		CloseableHttpResponse execute = httpClient.execute(post);
		return EntityUtils.toString(execute.getEntity());
	}


}
