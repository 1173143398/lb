package com.parse;

import com.message.IMessage;

public interface ContentParser {
	
	IMessage messageToBean(String message,Class<? extends IMessage> parseType);
	
	String beanToMessage(IMessage message,Class<? extends IMessage> parseType);
}
