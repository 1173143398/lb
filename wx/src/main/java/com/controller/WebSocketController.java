package com.controller;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.websocket.WsPushMessageService;

@Controller
public class WebSocketController {

	private static Log log = LogFactory.getLog(WebSocketController.class);
	@Autowired
	private WsPushMessageService wsPushMessageService;
	
	@RequestMapping("/ws/push")
	@ResponseBody
	public String sevice(String uid,String message){
		wsPushMessageService.pushMessage(uid, message);
		return "";
	}
	
	@RequestMapping("/ws/page")
	public String page(HttpSession session){
		log.info("id==>"+session.getId());
		return "websocket";
	}
	
	
}
