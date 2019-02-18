package com.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.config.ClientConfig;
import com.execpt.WxException;
import com.message.IMessage;
import com.message.client.WxPayInMessage;
import com.message.client.WxPayOutMessage;
import com.service.OrderNoService;
import com.service.WxPayService;
import com.wxpay.WXPay;
import com.wxpay.WXPayUtil;

@Service("wxPayMessageService")
public class WxPayMessageService extends AbstractClientMessageService implements WxPayService {

	private static Log log = LogFactory.getLog(WxPayMessageService.class);
	@Autowired
	private WXPay wxPay;
	@Autowired
	private OrderNoService orderNoService;
	
	@Override
	public IMessage doService(ClientConfig clientConfig, IMessage message) {
		try {
			WxPayInMessage wxPayInMessage = (WxPayInMessage)message;
			Map<String,String> reqData = new HashMap<String,String>();
			reqData.put("device_info", "");
			reqData.put("body", "微信支付-测试");
			reqData.put("detail", "");
			reqData.put("attach", "");
			reqData.put("out_trade_no",orderNoService.getOrderNo());
			reqData.put("fee_type", wxPayInMessage.getFeeType());
			reqData.put("total_fee", wxPayInMessage.getTotalFee().toString());
			reqData.put("spbill_create_ip", wxPayInMessage.getSpbillCreateIp());
			reqData.put("time_start", "");
			reqData.put("time_expire", "");
			reqData.put("goods_tag", "");
			reqData.put("trade_type", wxPayInMessage.getTradeType());
			reqData.put("product_id", wxPayInMessage.getProductId());
			reqData.put("limit_pay", "");
			reqData.put("openid", wxPayInMessage.getOpenid());
			reqData.put("receipt", "");
			reqData.put("scene_info", "");
//			reqData.put("appid", value);
//			reqData.put("mch_id", value);
//			reqData.put("nonce_str", value);
//			reqData.put("sign", value);
//			reqData.put("sign_type", value);
//			reqData.put("notify_url", value);
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
		} catch (Exception e) {
			log.error("微信支付异常",e);
			throw new WxException("微信支付异常");
		}
	}

	@Override
	public String sign(Map<String, String> data) {
		try {
			data.put("signType", wxPay.getSignType());
			return WXPayUtil.generateSignature(data, wxPay.getWXPayConfig().getKey());
		} catch (Exception e) {
			log.error("微信支付签名异常",e);
			throw new WxException("微信支付签名异常");
		}
	}

}
