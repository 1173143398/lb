package com.http;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.util.Constants;
import com.util.StringUtil;

@Component
public class GetRequestExecutor implements RequestExecutor<String, String> {

	
	@Override
	public String execute(CloseableHttpClient httpClient, String url,
			String data) throws Exception {
		if(StringUtil.isNull(data) == false){
			url = url + "?" + data;
		}
		HttpGet get = new HttpGet(url);
		CloseableHttpResponse execute = httpClient.execute(get);
		return EntityUtils.toString(execute.getEntity());
	}

}
