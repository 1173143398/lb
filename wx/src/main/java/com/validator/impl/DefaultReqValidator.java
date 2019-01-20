package com.validator.impl;

import org.springframework.stereotype.Service;

import com.message.IMessage;
import com.validator.ReqValidator;

@Service("defaultReqValidator")
public class DefaultReqValidator implements ReqValidator {

	@Override
	public boolean validate(IMessage message, Class<? extends IMessage> requiedType) {
		return true;
	}

}
