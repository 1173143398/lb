package com.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.config.ClientConfig;
import com.context.TransactionContext;
import com.execpt.WxException;
import com.http.NetWorkManager;
import com.message.IMessage;
import com.message.client.SandboxSignKeyOutMessage;
import com.service.WxPayConfigService;
import com.wxpay.WXPay;
import com.wxpay.WXPayUtil;

@Service("sandboxSignKeyService")
public class SandboxSignKeyService extends AbstractClientMessageService {

	private static Log log = LogFactory.getLog(SandboxSignKeyService.class);
	
	@Autowired
	private WXPay wxPay;
	
	@Autowired
	private WxPayConfigService wxPayConfigService;
	
	@Override
	public IMessage doService(ClientConfig clientConfig, IMessage message) {
		try{
			Map<String, String> data = new HashMap<String, String>();
		    // 商户号
		    data.put("mch_id", TransactionContext.getWxPayConfig().getMchId());
		    // 获取随机字符串
		    data.put("nonce_str", WXPayUtil.generateNonceStr());
		    Map<String, String> rs = wxPay.getSandboxSignkey(data);
			log.info("====>"+rs);
			
			if("SUCCESS".equals(rs.get("return_code"))){
				TransactionContext.getWxPayConfig().setSandboxSignKey(rs.get("sandbox_signkey"));
				wxPayConfigService.update(TransactionContext.getWxPayConfig());
			}
			SandboxSignKeyOutMessage sandboxSignKeyMessage = new SandboxSignKeyOutMessage();
			sandboxSignKeyMessage.setReturnCode(rs.get("return_code"));
			sandboxSignKeyMessage.setReturnMsg(rs.get("return_msg"));
			return sandboxSignKeyMessage;
		}catch(Exception e){
			log.error("沙箱获取KEY异常",e);
			throw new WxException("沙箱获取KEY异常");
		}
	}

}
