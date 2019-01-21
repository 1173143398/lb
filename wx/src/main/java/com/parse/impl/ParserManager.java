package com.parse.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.parse.IContentParser;

@Service
public class ParserManager {

	private Map<String,IContentParser> parsers = new HashMap<String,IContentParser>();
	
	public void addParser(String type,IContentParser parser) {
		parsers.put(type, parser);
	}
	
	public IContentParser getParser(String type) {
		return parsers.get(type);
	}
}
