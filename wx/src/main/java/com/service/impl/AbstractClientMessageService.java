package com.service.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;

import com.context.TransactionContext;
import com.execpt.WxException;
import com.message.IMessage;
import com.parse.IWxExpressionParser;
import com.service.ClientMessageService;
import com.util.StringUtil;

public abstract class AbstractClientMessageService implements
		ClientMessageService {

	protected String formatUrl(IWxExpressionParser wxExpressionParser,String url,IMessage reqParam) {
		Pattern compile = Pattern.compile("(\\$\\{\\w+\\})");
		Matcher matcher = compile.matcher(url);
		while(matcher.find()){
			String param = matcher.group();
			String pName = param.replaceAll("[\\$|\\{|\\}]", "");
			String value = null;
			try{
				value = wxExpressionParser.getValue(TransactionContext.getSystemContext(), pName);
				if(StringUtil.isNull(value)){
					value = wxExpressionParser.getValue(reqParam, pName);
				}
			}catch(Exception e){
				value = wxExpressionParser.getValue(reqParam, pName);
			}
			if(StringUtil.isNull(value)){
				throw new WxException("获取参数值失败" + pName);
			}
			url = url.replace(param, value);
		}
		return url;
	}

}
