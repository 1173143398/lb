package com.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.context.TransactionContext;
import com.http.NetWorkManager;
import com.message.client.AuthorizeMessage;
import com.security.SecurityService;
import com.util.Constants;
import com.util.JsonUtil;
import com.util.MathUtil;
import com.util.StringUtil;

@Controller
public class PageController {

	@Autowired
	private SecurityService securityService;
	
	@Autowired
	private NetWorkManager netWorkManager;
	
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
	
	@RequestMapping("/authorize")
	public String authorize(HttpServletRequest request){
		String url = "https://open.weixin.qq.com/connect/oauth2/authorize?"
				+ "appid=%s&redirect_uri=%s&response_type=code"
				+ "&scope=%s&state=%s#wechat_redirect";
		String appId = TransactionContext.getSystemConfig().getAppId();
		String scope = "snsapi_base";
		String state = String.valueOf(System.currentTimeMillis());
		
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + path;
		if(!(80 == request.getServerPort())){
			basePath = request.getScheme() + "://" + request.getServerName() 
					+ ":" + request.getServerPort() + path;
		}
		String uri = basePath  + "/redirect";
		url = String.format(url, appId,uri,scope,state);
		return "redirect:"+url;
	}
	
	@RequestMapping("/redirect")
	public String authorizeRedirect(HttpServletRequest request,String code,String state){
		String url = "https://api.weixin.qq.com/sns/oauth2/access_token?"
				+ "appid=%s&secret=%s&code=%s"
				+ "&grant_type=authorization_code";
		String appId = TransactionContext.getSystemConfig().getAppId();
		String secret = TransactionContext.getSystemConfig().getAppSecret();
		url = String.format(url, appId,secret,code);
		String message = netWorkManager.getClient(Constants.GET).send(url, null);
		AuthorizeMessage authorizeMessage = JsonUtil.jsonToBean(message, AuthorizeMessage.class);
		request.setAttribute("openid", authorizeMessage.getOpenid());
		return "authorize";
	}
	
}
