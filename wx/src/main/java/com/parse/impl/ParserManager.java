package com.parse.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.parse.ContentParser;

@Service
public class ParserManager {

	private Map<String,ContentParser> parsers = new HashMap<String,ContentParser>();
	
	public void addParser(String type,ContentParser parser) {
		parsers.put(type, parser);
	}
	
	public ContentParser getParser(String type) {
		return parsers.get(type);
	}
}
