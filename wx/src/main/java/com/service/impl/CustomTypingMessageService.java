package com.service.impl;

import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.config.ClientConfig;
import com.http.PostRequestExecutor;
import com.parse.IWxExpressionParser;
import com.service.ClientMessageService;
import com.util.StringUtil;

@Service("customTypingService")
public class CustomTypingMessageService implements ClientMessageService {

	@Autowired
	private CloseableHttpClient httpClient;
	
	@Autowired
	private IWxExpressionParser wxExpressionParser;
	
	@Override
	public String doService(ClientConfig clientConfig, String message) throws Exception{
		String url = StringUtil.formatUrl(wxExpressionParser, clientConfig.getUrl(), null);
		PostRequestExecutor postRequestExecutor = new PostRequestExecutor();
		String rs = postRequestExecutor.execute(httpClient, url, message);
		return "SUCCESS" + rs;
	}

}
