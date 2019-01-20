package com.service;

import com.message.IMessage;

public interface MessageService {

	IMessage doService(IMessage message,Class<? extends IMessage> requiredType);
}
