package com.service.impl;

import java.io.File;

import org.springframework.stereotype.Service;

import com.config.ClientConfig;
import com.http.MediaUploadRequestExecutor;
import com.message.IMessage;
import com.message.client.UploadFileInMessage;
import com.util.ClassUtil;
import com.util.StringUtil;

@Service("uploadFileMessageService")
public class UploadFileMessageService extends AbstractClientMessageService {

	@Override
	public IMessage doService(ClientConfig clientConfig, IMessage message) throws Exception{
		String formatUrl = this.formatUrl(clientConfig.getUrl(), message);
		UploadFileInMessage uploadFileMessage = (UploadFileInMessage)message;
		MediaUploadRequestExecutor mediaUploadRequestExecutor = new MediaUploadRequestExecutor();
		String upload = mediaUploadRequestExecutor.execute(httpClient, formatUrl, new File(uploadFileMessage.getFilePath()));
		if(StringUtil.isNull(upload) == false){
			Class<? extends IMessage> respClass = ClassUtil.getClass(clientConfig.getRespClass(), IMessage.class);
			IMessage messageToBean = parserManager.getParser(clientConfig.getRespMsgType()).messageToBean(upload, respClass);
			return messageToBean;
		}
		return null;
	}

}
