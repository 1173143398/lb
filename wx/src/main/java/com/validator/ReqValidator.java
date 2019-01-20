package com.validator;

import com.message.IMessage;

public interface ReqValidator {

	boolean validate(IMessage message,Class<? extends IMessage> requiedType);
}
