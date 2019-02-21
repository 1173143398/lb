package com.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.config.ClientConfig;
import com.config.SystemConfig;
import com.context.TransactionContext;
import com.http.GetRequestExecutor;
import com.message.IMessage;
import com.message.client.TokenMessage;
import com.service.SystemConfigService;
import com.util.StringUtil;
import com.util.TimeUtil;

@Service("accessTokenMessageService")
public class AccessTokenMessageService extends AbstractClientMessageService {

	private static Log log = LogFactory.getLog(AccessTokenMessageService.class);
	@Autowired
	private SystemConfigService systemConfigService;
	
	
	public IMessage beforeSend(ClientConfig clientConfig, IMessage message,
			Class<? extends IMessage> requiredType) {
		return message;
	}

	public IMessage afterSend(TokenMessage token) {
		log.info("获取token:" + token);
		if(StringUtil.isNull(token.getErrcode())) {
			SystemConfig systemConfig = TransactionContext.getSystemConfig();
			systemConfig.setAccessToken(token.getAccessToken());
			systemConfig.setExpiresIn(token.getExpiresIn());
			systemConfig.setTms(TimeUtil.getTms());
			systemConfigService.update(systemConfig);
		}
		return token;
	}

	@Override
	public IMessage doService(ClientConfig clientConfig, IMessage message) throws Exception {
		String newUrl = this.formatUrl(clientConfig.getUrl(), message);
		
		GetRequestExecutor getRequestExecutor = new GetRequestExecutor();
		String send = getRequestExecutor.execute(httpClient, newUrl, null);
		
		TokenMessage messageToBean = (TokenMessage)parserManager.getParser(clientConfig.getRespMsgType()).messageToBean(send, TokenMessage.class);
		afterSend(messageToBean);
		return messageToBean;
	}

}
