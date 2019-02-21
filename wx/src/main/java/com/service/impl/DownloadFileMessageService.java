package com.service.impl;

import org.springframework.stereotype.Service;

import com.config.ClientConfig;
import com.http.MediaDownloadRequestExecutor;
import com.message.IMessage;
import com.message.client.DownloadFileInMessage;
import com.util.ClassUtil;
import com.util.StringUtil;

@Service("downloadFileMessageService")
public class DownloadFileMessageService extends AbstractClientMessageService {

	@Override
	public IMessage doService(ClientConfig clientConfig, IMessage message) throws Exception{
		String formatUrl = this.formatUrl(clientConfig.getUrl(), message);
		DownloadFileInMessage downloadFileMessage = (DownloadFileInMessage)message;
		
		MediaDownloadRequestExecutor mediaDownloadRequestExecutor = new MediaDownloadRequestExecutor(downloadFileMessage.getFilePath());
		String upload = mediaDownloadRequestExecutor.execute(httpClient, formatUrl, null);
		
		if(StringUtil.isNull(upload) == false){
			Class<? extends IMessage> respClass = ClassUtil.getClass(clientConfig.getRespClass(), IMessage.class);
			IMessage messageToBean = parserManager.getParser(clientConfig.getRespMsgType()).messageToBean(upload, respClass);
			return messageToBean;
		}
		return null;
	}

}
