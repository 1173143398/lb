package com.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.context.TransactionContext;
import com.execpt.WxException;
import com.message.IMessage;
import com.parse.IWxExpressionParser;

public class StringUtil {

	public static boolean isNull(Object o) {
		if(o == null || "".equals(o)) {
			return true;
		}
		return false;
	}
	
	public static String formatUrl(IWxExpressionParser wxExpressionParser,String url,IMessage reqParam) {
		Pattern compile = Pattern.compile("(\\$\\{\\w+\\})");
		Matcher matcher = compile.matcher(url);
		StringBuffer sb = new StringBuffer();
		while(matcher.find()){
			String param = matcher.group();
			String pName = param.replaceAll("[\\$|\\{|\\}]", "");
			String value = null;
			try{
				value = wxExpressionParser.getValue(TransactionContext.getSystemConfig(), pName);
				if(StringUtil.isNull(value)){
					value = wxExpressionParser.getValue(reqParam, pName);
				}
			}catch(Exception e){
				value = wxExpressionParser.getValue(reqParam, pName);
			}
			if(StringUtil.isNull(value)){
				throw new WxException("获取参数值失败" + pName);
			}
			matcher.appendReplacement(sb, value);
		}
		matcher.appendTail(sb);
		return sb.toString();
	}
}
