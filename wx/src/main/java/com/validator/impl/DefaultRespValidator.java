package com.validator.impl;

import org.springframework.stereotype.Service;

import com.message.IMessage;
import com.validator.RespValidator;

@Service("defaultRespValidator")
public class DefaultRespValidator implements RespValidator {

	@Override
	public boolean validate(IMessage message, Class<? extends IMessage> requiredType) {
		// TODO Auto-generated method stub
		return true;
	}

}
