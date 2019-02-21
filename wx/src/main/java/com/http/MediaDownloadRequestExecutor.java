package com.http;

import java.io.File;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

public class MediaDownloadRequestExecutor implements RequestExecutor<String, String> {

	private String filePath;
	
	public MediaDownloadRequestExecutor(String filePath){
		this.filePath = filePath;
	}
	
	@Override
	public String execute(CloseableHttpClient httpClient, String url,
			String data) throws Exception {
		HttpGet get = new HttpGet(url);
		CloseableHttpResponse execute = httpClient.execute(get);
		InputStream content = execute.getEntity().getContent();
		FileUtils.copyInputStreamToFile(content,new File(filePath));
		EntityUtils.consume(execute.getEntity());
		return "";
	}

}
