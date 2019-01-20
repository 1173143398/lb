package com.validator;

import com.message.IMessage;

public interface RespValidator {

	boolean validate(IMessage message,Class<? extends IMessage> requiredType);
}
