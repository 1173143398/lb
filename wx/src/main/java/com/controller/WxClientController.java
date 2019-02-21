package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.config.ClientConfig;
import com.context.TransactionContext;
import com.parse.impl.ParserManager;
import com.service.ClientConfigService;
import com.service.ClientMessageService;
import com.util.ExceptionUtil;

@Controller
public class WxClientController {

	@Autowired
	private ClientConfigService clientConfigService;
	
	@Autowired
	private ParserManager parserManager;
	
	@RequestMapping("/send")
	@ResponseBody
	public String send(@RequestBody(required = false) String message,@RequestParam("funcno") String funcNo) throws Exception {
		ExceptionUtil.alert(funcNo,"功能号不能为空");
		//获取配置
		ClientConfig clientConfig = clientConfigService.getClientConfig(funcNo);
		ExceptionUtil.alert(clientConfig,"获取配置失败,功能号:" + funcNo);
		
		ClientMessageService messageService = TransactionContext.getBean(clientConfig.getServiceBean(), ClientMessageService.class);
		ExceptionUtil.alert(messageService,"请求报文处理类缺失");
		
		String rs = messageService.doService(clientConfig, message);
		return rs;
	}
	
}
