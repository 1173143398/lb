package com.websocket;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

public class WsWebSocketHandler implements WebSocketHandler,WsPushMessageService {

	private static Log log = LogFactory.getLog(WsWebSocketHandler.class);
	 //保存用户链接
    private static ConcurrentHashMap<String, WebSocketSession> users = new ConcurrentHashMap<String, WebSocketSession>();
	@Override
	public void afterConnectionEstablished(WebSocketSession session)
			throws Exception {
		log.info("====afterConnectionEstablished=====");
		session.sendMessage(new TextMessage("Server:connected OK"));
		users.put(session.getId(),session);
	}

	@Override
	public void handleMessage(WebSocketSession session,
			WebSocketMessage<?> message) throws Exception {
		// TODO Auto-generated method stub
		TextMessage returnMessage = new TextMessage(message.getPayload()   + " received at server");  
		log.info(session.getHandshakeHeaders().getFirst("Cookie"));  
		log.info("id:"+session.getId());
		session.sendMessage(returnMessage);  
	}

	@Override
	public void handleTransportError(WebSocketSession session,
			Throwable exception) throws Exception {
		log.error("===>",exception);
		 if(session.isOpen()){  
			 session.close();  
	     }  
		 users.remove(session.getId());
		 log.info("websocket connection closed......");  
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session,
			CloseStatus closeStatus) throws Exception {
		log.info("websocket connection closed......");  
		users.remove(session.getId());
	}

	@Override
	public boolean supportsPartialMessages() {
		return false;
	}

	@Override
	public void pushMessage(String sessionId,String message) {
		try {
			users.get(sessionId).sendMessage(new TextMessage(message));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
