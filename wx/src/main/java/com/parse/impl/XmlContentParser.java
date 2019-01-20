package com.parse.impl;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.message.IMessage;
import com.parse.ContentParser;
import com.util.XmlUtil;

@Service
public class XmlContentParser implements ContentParser,InitializingBean{

	@Autowired
	private ParserManager parserManager;
	
	@Override
	public IMessage reqParse(String message, Class<? extends IMessage> parseType) {
		 IMessage xmlToBean = XmlUtil.xmlToBean(message, parseType);
		 return xmlToBean;
	}

	@Override
	public String respParse(IMessage message, Class<? extends IMessage> parseType) {
		// TODO Auto-generated method stub
		return XmlUtil.beantoXml(message,parseType);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		parserManager.addParser("XML", this);
	}

}
