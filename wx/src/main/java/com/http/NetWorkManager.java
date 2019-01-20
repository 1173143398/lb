package com.http;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class NetWorkManager {

	private Map<String ,Map<String,NetWorkClient>> clients = new HashMap<String,Map<String,NetWorkClient>>();
	
	public void addClient(String key,Map<String,NetWorkClient> client) {
		clients.put(key, client);
	}
	
	public NetWorkClient getClient(String key,String method) {
		return clients.get(key).get(method);
	}
}
