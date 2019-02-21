package com.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.context.TransactionContext;
import com.execpt.WxException;
import com.http.GetRequestExecutor;
import com.message.client.AuthorizeMessage;
import com.message.client.WxJsapiPayMessage;
import com.message.client.WxPayInMessage;
import com.message.client.WxPayOutMessage;
import com.parse.impl.ParserManager;
import com.service.OrderNoService;
import com.service.WxPayService;
import com.util.Constants;
import com.wxpay.WXPay;

@Service
public class WxPayMessageService implements WxPayService {

	private static Log log = LogFactory.getLog(WxPayMessageService.class);
	@Autowired
	private WXPay wxPay;
	
	@Autowired
	private OrderNoService orderNoService;
	
	@Autowired
	private ParserManager parserManager;
	
	@Autowired
	private CloseableHttpClient httpClient;
	@Override
	public String sign(WxJsapiPayMessage wxJsapiPayMessage) {
		try {
			Map<String,String> data = new HashMap<String,String>();
			data.put("appId", wxJsapiPayMessage.getAppId());
			data.put("timeStamp", wxJsapiPayMessage.getTimeStamp());
			data.put("nonceStr", wxJsapiPayMessage.getNonceStr());
			data.put("package", wxJsapiPayMessage.getPackageStr());
			wxJsapiPayMessage.setSignType(data.get("signType"));
			return wxPay.jsapiSign(data);
		} catch (Exception e) {
			log.error("微信支付签名异常",e);
			throw new WxException("微信支付签名异常");
		}
	}

	@Override
	public WxPayOutMessage unifiedOrder(WxPayInMessage wxPayInMessage) {
		try{
		Map<String,String> reqData = new HashMap<String,String>();
		reqData.put("body", wxPayInMessage.getBody());
		reqData.put("out_trade_no",orderNoService.getOrderNo());
		reqData.put("fee_type", wxPayInMessage.getFeeType());
		reqData.put("total_fee", wxPayInMessage.getTotalFee().toString());
		reqData.put("spbill_create_ip", wxPayInMessage.getSpbillCreateIp());
		reqData.put("trade_type", wxPayInMessage.getTradeType());
		reqData.put("product_id", wxPayInMessage.getProductId());
		reqData.put("openid", wxPayInMessage.getOpenid());
//		reqData.put("appid", value);
//		reqData.put("mch_id", value);
//		reqData.put("nonce_str", value);
//		reqData.put("sign", value);
//		reqData.put("sign_type", value);
//		reqData.put("notify_url", value);
		Map<String, String> unifiedOrder = wxPay.unifiedOrder(reqData);
		String returnCode = unifiedOrder.get("return_code");
		String returnMsg = unifiedOrder.get("return_msg");
		String errCode = unifiedOrder.get("err_code");
		String errCodeDes = unifiedOrder.get("err_code_des");
		String prepayId = unifiedOrder.get("prepay_id") ;
		String codeUrl = unifiedOrder.get("code_url"); 
		WxPayOutMessage wxPayOutMessage = new WxPayOutMessage();
		wxPayOutMessage.setCodeUrl(codeUrl);
		wxPayOutMessage.setPrepayId(prepayId);
		wxPayOutMessage.setReturnCode(returnCode);
		wxPayOutMessage.setReturnMsg(returnMsg);
		wxPayOutMessage.setErrCode(errCode);
		wxPayOutMessage.setErrCodeDes(errCodeDes);
		return wxPayOutMessage;
		}catch(Exception e){
			log.error("支付统一下单异常",e);
			throw new WxException("支付统一下单异常");
		}
	}

	@Override
	public String getJsapiAuthorizeOpenIdUrl(String redirectUrl) {
		String url = "https://open.weixin.qq.com/connect/oauth2/authorize?"
				+ "appid=%s&redirect_uri=%s&response_type=code"
				+ "&scope=%s&state=%s#wechat_redirect";
		String appId = TransactionContext.getSystemConfig().getAppId();
		String scope = "snsapi_base";
		String state = String.valueOf(System.currentTimeMillis());
		url = String.format(url, appId,redirectUrl,scope,state);
		return url;
	}

	@Override
	public String getJsapiAuthorizeOpenId(String code,String state) throws Exception{
		String url = "https://api.weixin.qq.com/sns/oauth2/access_token?"
				+ "appid=%s&secret=%s&code=%s"
				+ "&grant_type=authorization_code";
		String appId = TransactionContext.getSystemConfig().getAppId();
		String secret = TransactionContext.getSystemConfig().getAppSecret();
		url = String.format(url, appId,secret,code);
		GetRequestExecutor getRequestExecutor = new GetRequestExecutor();
		String message = getRequestExecutor.execute(httpClient, url, null);
		AuthorizeMessage authorizeMessage = (AuthorizeMessage)parserManager.getParser(Constants.JSON).messageToBean(message, AuthorizeMessage.class);
		return authorizeMessage.getOpenid();
	}

	@Override
	public String getJsapiUserInfo(String redirectUrl) {
		String url = "https://open.weixin.qq.com/connect/oauth2/authorize?"
				+ "appid=%s&redirect_uri=%s&response_type=code"
				+ "&scope=%s&state=%s#wechat_redirect";
		String appId = TransactionContext.getSystemConfig().getAppId();
		String scope = "snsapi_userinfo";
		String state = String.valueOf(System.currentTimeMillis());
		url = String.format(url, appId,redirectUrl,scope,state);
		return url;
	}

}
