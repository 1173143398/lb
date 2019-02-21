package com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.config.ClientConfig;
import com.config.SystemConfig;
import com.context.TransactionContext;
import com.http.GetRequestExecutor;
import com.message.IMessage;
import com.message.client.JsapiTicketMessage;
import com.service.SystemConfigService;
import com.util.ClassUtil;
import com.util.TimeUtil;

@Service("jsapiTicketMessageService")
public class JsapiTicketMessageService extends AbstractClientMessageService {

	@Autowired
	private SystemConfigService systemConfigService;
	
	@Override
	public IMessage doService(ClientConfig clientConfig, IMessage message) throws Exception{
		String formatUrl = this.formatUrl(clientConfig.getUrl(), message);
		Class<? extends IMessage> reqClass = ClassUtil.getClass(clientConfig.getReqClass(), IMessage.class);
		String msg = parserManager.getParser(clientConfig.getReqMsgType()).beanToMessage(message, reqClass);
		
		GetRequestExecutor getRequestExecutor = new GetRequestExecutor();
		String send = getRequestExecutor.execute(httpClient, formatUrl, msg);
		
		Class<? extends IMessage> respClass = ClassUtil.getClass(clientConfig.getRespClass(), IMessage.class);
		JsapiTicketMessage jsapiTicketMessage = (JsapiTicketMessage)parserManager.getParser(clientConfig.getRespMsgType()).messageToBean(send, respClass);
		if("0".equals(jsapiTicketMessage.getErrcode())){
			SystemConfig systemConfig = TransactionContext.getSystemConfig();
			systemConfig.setExpiresIn(jsapiTicketMessage.getExpiresIn());
			systemConfig.setTms(TimeUtil.getTms());
			systemConfig.setJsapiTicket(jsapiTicketMessage.getTicket());
			systemConfigService.update(systemConfig);
		}
		return jsapiTicketMessage;
	}

}
