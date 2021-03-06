package com.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.context.TransactionContext;
import com.security.SecurityService;
import com.util.MathUtil;
import com.util.StringUtil;

@Controller
public class PageController {

	@Autowired
	private SecurityService securityService;
	
	@RequestMapping("/index")
	public String index(HttpServletRequest request){
		String appId = TransactionContext.getSystemConfig().getAppId();
		String jsapiTicket = TransactionContext.getSystemConfig().getJsapiTicket();
		String timestamp = String.valueOf(System.currentTimeMillis());
		String nonceStr = MathUtil.getRandomStr();
		
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + path;
		if(!(80 == request.getServerPort())){
			basePath = request.getScheme() + "://" + request.getServerName() 
					+ ":" + request.getServerPort() + path;
		}
		String servletPath=request.getServletPath();
		String qStr = request.getQueryString();
		String url = basePath + servletPath;
		if(StringUtil.isNull(qStr) == false){
			url = url + "?" +qStr;
		}
		String signature = securityService.sign(jsapiTicket, nonceStr, timestamp, url);
		request.setAttribute("appId", appId);
		request.setAttribute("timestamp", timestamp);
		request.setAttribute("nonceStr", nonceStr);
		request.setAttribute("signature", signature);
		return "index";
	}
	
	@RequestMapping("/admin")
	public String admin(){
		return "admin";
	}
	
}
