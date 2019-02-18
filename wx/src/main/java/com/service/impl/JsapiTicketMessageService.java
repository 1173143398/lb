package com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.config.ClientConfig;
import com.config.SystemConfig;
import com.context.SystemContext;
import com.context.TransactionContext;
import com.http.NetWorkManager;
import com.message.IMessage;
import com.message.client.JsapiTicketMessage;
import com.service.SystemConfigService;
import com.util.ClassUtil;
import com.util.TimeUtil;

@Service("jsapiTicketMessageService")
public class JsapiTicketMessageService extends AbstractClientMessageService {

	@Autowired
	private NetWorkManager netWorkManager;
	
	@Autowired
	private SystemConfigService systemConfigService;
	
	@Override
	public IMessage doService(ClientConfig clientConfig, IMessage message) {
		String formatUrl = this.formatUrl(clientConfig.getUrl(), message);
		Class<? extends IMessage> reqClass = ClassUtil.getClass(clientConfig.getReqClass(), IMessage.class);
		String msg = parserManager.getParser(clientConfig.getReqMsgType()).beanToMessage(message, reqClass);
		String send = netWorkManager.getClient(clientConfig.getMethod()).send(formatUrl, msg);
		Class<? extends IMessage> respClass = ClassUtil.getClass(clientConfig.getRespClass(), IMessage.class);
		JsapiTicketMessage jsapiTicketMessage = (JsapiTicketMessage)parserManager.getParser(clientConfig.getRespMsgType()).messageToBean(send, respClass);
		if("0".equals(jsapiTicketMessage.getErrcode())){
			SystemContext systemContext = TransactionContext.getSystemContext();
			systemContext.setJsapiTicket(jsapiTicketMessage.getTicket());
			SystemConfig systemConfig = new SystemConfig();
			systemConfig.setAccessToken(systemContext.getAccessToken());
			systemConfig.setExpiresIn(jsapiTicketMessage.getExpiresIn());
			systemConfig.setTms(TimeUtil.getTms());
			systemConfig.setJsapiTicket(systemContext.getJsapiTicket());
			systemConfigService.update(systemConfig);
		}
		return jsapiTicketMessage;
	}

}
