package com.parse.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.execpt.WxException;
import com.message.IMessage;
import com.parse.ContentParser;
import com.parse.RespParser;

@Service("defaultRespParser")
public class DefaultRespParser implements RespParser {

	@Autowired
	private ParserManager parserManager;
	
	@Override
	public String parse(IMessage message,Class<? extends IMessage> requiredType,String reqType) {
		ContentParser parser = parserManager.getParser(reqType);
		if(parser == null) {
			throw new WxException("响应报文转Bean解析器不存在");
		}
		return parser.respParse(message, requiredType);
	}

}
