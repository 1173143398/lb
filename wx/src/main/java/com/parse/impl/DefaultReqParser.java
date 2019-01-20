package com.parse.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.execpt.WxException;
import com.message.IMessage;
import com.parse.ContentParser;
import com.parse.ReqParser;

@Service("defaultReqParser")
public class DefaultReqParser implements ReqParser {

	@Autowired
	private ParserManager parserManager;
	
	@Override
	public IMessage parse(String message,Class<? extends IMessage> parseType,String reqType) {
		ContentParser parser = parserManager.getParser(reqType);
		if(parser == null) {
			throw new WxException("请求报文转Bean解析器不存在");
		}
		return parser.reqParse(message, parseType);
	}

}
