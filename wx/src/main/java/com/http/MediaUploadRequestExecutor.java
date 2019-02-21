package com.http;

import java.io.File;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

@Component
public class MediaUploadRequestExecutor implements RequestExecutor<String, File> {

	@Override
	public String execute(CloseableHttpClient httpClient, String url, File data)
			throws Exception {
		HttpEntity build = MultipartEntityBuilder
		.create()
		.addBinaryBody("media", data)
		.build();
		HttpPost post = new HttpPost(url);
		post.setEntity(build);
		CloseableHttpResponse execute = httpClient.execute(post);
		return EntityUtils.toString(execute.getEntity());
	}

}
