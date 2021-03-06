package com.parse.impl;

import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.stereotype.Service;

import com.parse.IWxExpressionParser;

@Service
public class WxExpressionParser implements IWxExpressionParser {
	
	private SpelExpressionParser parser = new SpelExpressionParser();
	
	@Override
	public String getValue(Object context, String expression) {
		Expression parseExpression = parser.parseExpression(expression);
		String value = parseExpression.getValue(context, String.class);
		return value;
	}

}
