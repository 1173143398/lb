package com.parse.impl;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.message.IMessage;
import com.parse.ContentParser;
import com.util.JsonUtil;

@Service
public class JsonContentParser implements ContentParser,InitializingBean {

	@Autowired
	private ParserManager parserManager;
	
	
	@Override
	public IMessage messageToBean(String message, Class<? extends IMessage> parseType) {
		return JsonUtil.jsonToBean(message, parseType);
	}

	@Override
	public String beanToMessage(IMessage message, Class<? extends IMessage> parseType) {
		return JsonUtil.beanToJson(message, parseType);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		parserManager.addParser("JSON", this);
	}

}
