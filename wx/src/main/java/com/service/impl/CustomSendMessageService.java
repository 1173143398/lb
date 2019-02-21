package com.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.config.ClientConfig;
import com.http.PostRequestExecutor;
import com.parse.IWxExpressionParser;
import com.service.ClientMessageService;
import com.util.StringUtil;

@Service("customSendMessageService")
public class CustomSendMessageService implements ClientMessageService{

	private static Log log = LogFactory.getLog(CustomSendMessageService.class);
	
	@Autowired
	private IWxExpressionParser wxExpressionParser;
	
	@Autowired
	private CloseableHttpClient httpClient;
	
	@Override
	public String doService(ClientConfig clientConfig, String message) throws Exception{
		log.info("发送客户消息");
		String url = StringUtil.formatUrl(wxExpressionParser, clientConfig.getUrl(), null);
		PostRequestExecutor postRequestExecutor = new PostRequestExecutor();
		String rs = postRequestExecutor.execute(httpClient, url, message);
		return "SUCCESS" + rs;
	}

}
