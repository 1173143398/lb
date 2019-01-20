package com.parse;

import com.message.IMessage;

public interface RespParser {

	String parse(IMessage message,Class<? extends IMessage> requiredType,String respType);
}
