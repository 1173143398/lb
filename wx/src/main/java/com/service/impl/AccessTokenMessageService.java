package com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.config.ClientConfig;
import com.context.SystemContext;
import com.context.TransactionContext;
import com.message.IMessage;
import com.message.client.TokenMessage;
import com.service.ClientMessageService;
import com.service.SystemConfigService;
import com.util.StringUtil;

@Service("accessTokenMessageService")
public class AccessTokenMessageService implements ClientMessageService {

	@Autowired
	private SystemConfigService systemConfigService;
	
	@Override
	public IMessage beforeSend(ClientConfig clientConfig, IMessage message,
			Class<? extends IMessage> requiredType) {
		return message;
	}

	@Override
	public IMessage afterSend(ClientConfig clientConfig, IMessage message,
			Class<? extends IMessage> requiredType) {
		TokenMessage token = (TokenMessage)message;
		if(StringUtil.isNull(token.getErrcode())) {
			SystemContext systemContext = TransactionContext.getSystemContext();
			systemContext.setAccessToken(token.getAccessToken());
		}
		return message;
	}

}
