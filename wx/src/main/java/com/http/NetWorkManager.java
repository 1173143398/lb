package com.http;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class NetWorkManager {

	private Map<String,NetWorkClient> clients = new HashMap<String,NetWorkClient>();
	
	public void addClient(String key,NetWorkClient client) {
		clients.put(key, client);
	}
	
	public NetWorkClient getClient(String key){
		return clients.get(key);
	}
}
