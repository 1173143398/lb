package com.service.impl;

import org.springframework.stereotype.Service;

import com.config.ServerConfig;
import com.message.IMessage;
import com.message.server.TextInMessage;
import com.message.server.TextOutMessage;
import com.service.ServerMessageService;

@Service("textServerMessageService")
public class TextServerMessageService implements ServerMessageService{

	@Override
	public IMessage doService(ServerConfig serverConfig,IMessage message) {
		TextInMessage inMsg = (TextInMessage)message;
		TextOutMessage outMsg = new TextOutMessage();
		outMsg.setToUserName(inMsg.getFromUserName());
		outMsg.setFromUserName(inMsg.getToUserName());
		outMsg.setCreateTime(inMsg.getCreateTime());
		outMsg.setMsgType(inMsg.getMsgType());
		outMsg.setContent(inMsg.getContent());
		return outMsg;
	}

}
