package com.websocket;

public interface WsPushMessageService {

	void pushMessage(String sessionId,String message);
}
