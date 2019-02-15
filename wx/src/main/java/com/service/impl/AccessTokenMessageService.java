package com.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.config.ClientConfig;
import com.config.SystemConfig;
import com.context.SystemContext;
import com.context.TransactionContext;
import com.http.NetWorkClient;
import com.http.NetWorkManager;
import com.message.IMessage;
import com.message.client.TokenMessage;
import com.service.SystemConfigService;
import com.util.ClassUtil;
import com.util.StringUtil;
import com.util.TimeUtil;

@Service("accessTokenMessageService")
public class AccessTokenMessageService extends AbstractClientMessageService {

	private static Log log = LogFactory.getLog(AccessTokenMessageService.class);
	@Autowired
	private SystemConfigService systemConfigService;
	
	@Autowired
	private NetWorkManager netWorkManager;
	
	public IMessage beforeSend(ClientConfig clientConfig, IMessage message,
			Class<? extends IMessage> requiredType) {
		return message;
	}

	public IMessage afterSend(IMessage message) {
		TokenMessage token = (TokenMessage)message;
		log.info("获取token:" + token);
		if(StringUtil.isNull(token.getErrcode())) {
			SystemContext systemContext = TransactionContext.getSystemContext();
			systemContext.setAccessToken(token.getAccessToken());
			SystemConfig systemConfig = new SystemConfig();
			systemConfig.setAccessToken(token.getAccessToken());
			systemConfig.setExpiresIn(token.getExpiresIn());
			systemConfig.setTms(TimeUtil.getTms());
			systemConfig.setJsapiTicket(systemContext.getJsapiTicket());
			systemConfigService.update(systemConfig);
		}
		return message;
	}

	@Override
	protected IMessage service(ClientConfig clientConfig, IMessage message) {
		NetWorkClient client = netWorkManager.getClient(clientConfig.getMethod());
		String newUrl = this.formatUrl(clientConfig.getUrl(), message);
		Class<? extends IMessage> reqClass = ClassUtil.getClass(clientConfig.getReqClass(), IMessage.class);
		String msg = parserManager.getParser(clientConfig.getReqMsgType()).beanToMessage(message, reqClass);
		String send = client.send(newUrl, msg);
		Class<? extends IMessage> respClass = ClassUtil.getClass(clientConfig.getRespClass(), IMessage.class);
		IMessage messageToBean = parserManager.getParser(clientConfig.getRespMsgType()).messageToBean(send, respClass);
		afterSend(messageToBean);
		return messageToBean;
	}

}
