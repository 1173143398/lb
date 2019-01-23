package com.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.config.ClientConfig;
import com.config.SystemConfig;
import com.context.SystemContext;
import com.context.TransactionContext;
import com.message.IMessage;
import com.message.client.TokenMessage;
import com.service.ClientMessageService;
import com.service.SystemConfigService;
import com.util.StringUtil;
import com.util.TimeUtil;

@Service("accessTokenMessageService")
public class AccessTokenMessageService implements ClientMessageService {

	private static Log log = LogFactory.getLog(AccessTokenMessageService.class);
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
		log.info("获取token:" + token);
		if(StringUtil.isNull(token.getErrcode())) {
			SystemContext systemContext = TransactionContext.getSystemContext();
			systemContext.setAccessToken(token.getAccessToken());
			SystemConfig systemConfig = new SystemConfig();
			systemConfig.setAccessToken(token.getAccessToken());
			systemConfig.setExpiresIn(token.getExpiresIn());
			systemConfig.setTms(TimeUtil.getTms());
			systemConfigService.update(systemConfig);
		}
		return message;
	}

}
