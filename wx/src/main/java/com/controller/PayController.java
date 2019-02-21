package com.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.context.TransactionContext;
import com.message.client.WxJsapiPayMessage;
import com.message.client.WxPayInMessage;
import com.message.client.WxPayOutMessage;
import com.service.WxPayService;
import com.util.MathUtil;
import com.wxpay.WXPay;

@Controller
public class PayController {

	private static Log log = LogFactory.getLog(PayController.class);
	
	@Autowired
	private WxPayService payService;
	
	@Autowired
	private WXPay wxPay;
	
	@RequestMapping("/pay/jsapipay")
	public String pay(WxPayInMessage wxPayInMessage,HttpServletRequest request){
		WxPayOutMessage wxPayOutMessage = payService.unifiedOrder(wxPayInMessage);
		log.info(wxPayOutMessage.getErrCode() + "|" + wxPayOutMessage.getReturnCode() + "|" +
				wxPayOutMessage.getReturnMsg());
		request.setAttribute("error", wxPayOutMessage.getReturnMsg());
		String appId = TransactionContext.getWxPayConfig().getAppId();
		String timeStamp = String.valueOf(System.currentTimeMillis());
		String nonceStr = MathUtil.getRandomStr();
		String packageStr = "prepay_id=" + wxPayOutMessage.getPrepayId();
		
		WxJsapiPayMessage wxJsapiPayMessage = new WxJsapiPayMessage();
		wxJsapiPayMessage.setAppId(appId);
		wxJsapiPayMessage.setNonceStr(nonceStr);
		wxJsapiPayMessage.setPackageStr(packageStr);
		wxJsapiPayMessage.setTimeStamp(timeStamp);
		String signature = payService.sign(wxJsapiPayMessage);
		
		request.setAttribute("appId", appId);
		request.setAttribute("timeStamp", timeStamp);
		request.setAttribute("nonceStr", nonceStr);
		request.setAttribute("packageStr", packageStr);
		request.setAttribute("paySign", signature);
		request.setAttribute("signType",wxJsapiPayMessage.getSignType());
		return "jsapipay";
	}
	
	@RequestMapping("/pay/receive")
	@ResponseBody
	public Object receive(@RequestBody String message) throws Exception{
		Map<String, String> receive = wxPay.processResponseXml(message);
		log.info(receive);
		Map<String,String> ret = new HashMap<String,String>();
		ret.put("return_code", "SUCCESS");
		ret.put("return_msg", "OK");
		return ret;
	}
	
	@RequestMapping("/pay/h5paypage")
	public String h5PayPage(){
		return "h5pay";
	}
	@RequestMapping("/pay/h5pay")
	public String h5pay(WxPayInMessage wxPayInMessage){
		WxPayOutMessage wxPayOutMessage = payService.unifiedOrder(wxPayInMessage);
		log.info(wxPayOutMessage.getErrCode() + "|" + wxPayOutMessage.getReturnCode() + "|" +
				wxPayOutMessage.getReturnMsg());
		return "redirect:" + wxPayOutMessage.getMwebUrl();
	}
	
	@RequestMapping("/pay/nativepay")
	public String nativePay(WxPayInMessage wxPayInMessage,HttpServletRequest request){
		WxPayOutMessage wxPayOutMessage = payService.unifiedOrder(wxPayInMessage);
		log.info(wxPayOutMessage.getErrCode() + "|" + wxPayOutMessage.getReturnCode() + "|" +
				wxPayOutMessage.getReturnMsg());
		String codeUrl = wxPayOutMessage.getCodeUrl();
		request.setAttribute("code_url", codeUrl);
		return "nativepay";
	}
	
	@RequestMapping("/pay/authorize")
	public String authorize(HttpServletRequest request){
		log.info("==>"+request.getQueryString());
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + path;
		if(!(80 == request.getServerPort())){
			basePath = request.getScheme() + "://" + request.getServerName() 
					+ ":" + request.getServerPort() + path;
		}
		String uri = basePath  + "/pay/redirect";
		return "redirect:"+payService.getJsapiAuthorizeOpenIdUrl(uri);
	}
	
	@RequestMapping("/pay/redirect")
	public String authorizeRedirect(HttpSession session,HttpServletRequest request,String code,String state) throws Exception{
		String openId = payService.getJsapiAuthorizeOpenId(code, state);
		session.setAttribute("openid", openId);
		return "authorize";
	}
	
	@RequestMapping("/pay")
	public String pay(HttpServletRequest request){
		return "pay";
	}
	@RequestMapping("/pay/getuserinfo")
	public String getUserInfo(HttpServletRequest request){
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + path;
		if(!(80 == request.getServerPort())){
			basePath = request.getScheme() + "://" + request.getServerName() 
					+ ":" + request.getServerPort() + path;
		}
		String uri = basePath  + "/pay/redirect";
		String url = payService.getJsapiUserInfo(uri);
		request.setAttribute("url", url);
		return "getuserinfo";
	}
}
