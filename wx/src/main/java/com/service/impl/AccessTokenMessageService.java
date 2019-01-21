package com.service.impl;

import org.springframework.stereotype.Service;

import com.config.ClientConfig;
import com.context.SystemContext;
import com.context.TransactionContext;
import com.message.IMessage;
import com.message.client.TokenMessage;
import com.service.ClientMessageService;

@Service("accessTokenMessageService")
public class AccessTokenMessageService implements ClientMessageService {

	@Override
	public IMessage beforeSend(ClientConfig clientConfig, IMessage message,
			Class<? extends IMessage> requiredType) {
		return null;
	}

	@Override
	public IMessage afterSend(ClientConfig clientConfig, IMessage message,
			Class<? extends IMessage> requiredType) {
		TokenMessage token = (TokenMessage)message;
		SystemContext systemContext = TransactionContext.getSystemContext();
		systemContext.setAccessToken(token.getAccessToken());
		return null;
	}

}
