package com.parse;

import com.message.IMessage;

public interface ContentParser {
	
	IMessage reqParse(String message,Class<? extends IMessage> parseType);
	
	String respParse(IMessage message,Class<? extends IMessage> parseType);
}
