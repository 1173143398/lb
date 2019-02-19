package com.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.context.TransactionContext;
import com.message.client.WxPayInMessage;
import com.message.client.WxPayOutMessage;
import com.service.ClientMessageService;
import com.service.WxPayService;
import com.util.MathUtil;
import com.wxpay.WXPay;

@Controller
public class PayController {

	private static Log log = LogFactory.getLog(PayController.class);
	
	@Autowired
	@Qualifier("wxPayMessageService")
	private ClientMessageService payService;
	
	@Autowired
	private WXPay wxPay;
	
	@RequestMapping("/pay")
	public String pay(WxPayInMessage message,HttpServletRequest request){
		WxPayOutMessage wxPayOutMessage = (WxPayOutMessage)payService.doService(null, message);
		log.info(wxPayOutMessage.getErrCode() + "|" + wxPayOutMessage.getReturnCode() + "|" +
				wxPayOutMessage.getReturnMsg());
		request.setAttribute("error", wxPayOutMessage.getReturnMsg());
		String appId = TransactionContext.getWxPayConfig().getAppId();
		String timestamp = String.valueOf(System.currentTimeMillis());
		String nonceStr = MathUtil.getRandomStr();
		String packageStr = "prepay_id=" + wxPayOutMessage.getPrepayId();
		request.setAttribute("appId", appId);
		request.setAttribute("timeStamp", timestamp);
		request.setAttribute("nonceStr", nonceStr);
		request.setAttribute("packageStr", packageStr);
		//appId、timeStamp、nonceStr、package、signType
		Map<String,String> data = new HashMap<String,String>();
		data.put("appId", appId);
		data.put("timeStamp", timestamp);
		data.put("nonceStr", nonceStr);
		data.put("package", packageStr);
		WxPayService wxPayService = (WxPayService)payService;
		String signature = wxPayService.sign(data);
		request.setAttribute("paySign", signature);
		request.setAttribute("signType", data.get("signType"));
		return "pay";
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
}
