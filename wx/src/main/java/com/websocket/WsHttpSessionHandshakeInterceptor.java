package com.websocket;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

public class WsHttpSessionHandshakeInterceptor extends
		HttpSessionHandshakeInterceptor {

	private static Log log = LogFactory.getLog(WsHttpSessionHandshakeInterceptor.class);
	
	@Override
	public boolean beforeHandshake(ServerHttpRequest request,
			ServerHttpResponse response, WebSocketHandler wsHandler,
			Map<String, Object> attributes) throws Exception {
		// 解决The extension [x-webkit-deflate-frame] is not supported问题
        if (request.getHeaders().containsKey("Sec-WebSocket-Extensions")) {
            request.getHeaders().set("Sec-WebSocket-Extensions",
                    "permessage-deflate");
        }

        log.info("Before Handshake");
		return super.beforeHandshake(request, response, wsHandler, attributes);
	}
	
	@Override
	public void afterHandshake(ServerHttpRequest request,
			ServerHttpResponse response, WebSocketHandler wsHandler,
			Exception ex) {
		log.info("After Handshake");
		super.afterHandshake(request, response, wsHandler, ex);
	}
}
