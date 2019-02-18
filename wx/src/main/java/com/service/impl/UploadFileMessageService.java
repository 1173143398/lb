package com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.config.ClientConfig;
import com.http.FilePo;
import com.http.FileTransferManager;
import com.message.IMessage;
import com.message.client.UploadFileInMessage;
import com.util.ClassUtil;
import com.util.StringUtil;

@Service("uploadFileMessageService")
public class UploadFileMessageService extends AbstractClientMessageService {

	@Autowired
	private FileTransferManager fileTransferManager;
	
	@Override
	public IMessage doService(ClientConfig clientConfig, IMessage message) {
		String formatUrl = this.formatUrl(clientConfig.getUrl(), message);
		UploadFileInMessage uploadFileMessage = (UploadFileInMessage)message;
		FilePo file = new FilePo();
		file.setFilePath(uploadFileMessage.getFilePath());
		file.setName("media");
		file.setUrl(formatUrl);
		String upload = fileTransferManager.getFileTransfer(clientConfig.getMethod()).upload(file, null);
		if(StringUtil.isNull(upload) == false){
			Class<? extends IMessage> respClass = ClassUtil.getClass(clientConfig.getRespClass(), IMessage.class);
			IMessage messageToBean = parserManager.getParser(clientConfig.getRespMsgType()).messageToBean(upload, respClass);
			return messageToBean;
		}
		return null;
	}

}
