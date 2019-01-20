package com.parse;

import com.message.IMessage;

public interface ReqParser {

	IMessage parse(String message,Class<? extends IMessage> parseType,String reqType);
}
