package com.security.impl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import org.springframework.stereotype.Service;

import com.context.TransactionContext;
import com.execpt.WxException;
import com.security.SecurityService;
import com.util.StringUtil;

@Service
public class SecurityServiceImpl implements SecurityService {

	@Override
	public String encrypt(String message,String timeStamp,String nonce,String encryptType,String sign) {
		if(StringUtil.isNull(encryptType) || StringUtil.isNull(sign)){
			return message;
		}
		try {
			WXBizMsgCrypt c = new WXBizMsgCrypt(TransactionContext.getSystemConfig().getToken(),
					TransactionContext.getSystemConfig().getEncodingAeskey(),TransactionContext.getSystemConfig().getAppId());
			return c.encryptMsg(message, timeStamp, nonce);
		} catch (AesException e) {
			throw new WxException("加密失败");
		}
	}

	@Override
	public String decrypt(String message,String timeStamp,String nonce,String encryptType,String sign) {
		if(StringUtil.isNull(encryptType) || StringUtil.isNull(sign)){
			return message;
		}
		try {
			WXBizMsgCrypt c = new WXBizMsgCrypt(TransactionContext.getSystemConfig().getToken(),
					TransactionContext.getSystemConfig().getEncodingAeskey(),TransactionContext.getSystemConfig().getAppId());
			return c.decryptMsg(sign, timeStamp, nonce, message);
		} catch (AesException e) {
			throw new WxException("解密失败");
		}
	}
	
	@Override
	public boolean checkSign(String signature, String timestamp, String nonce) {
		if(StringUtil.isNull(signature) || StringUtil.isNull(timestamp) || StringUtil.isNull(nonce)){
			return false;
		}
		String sortStr = sort(TransactionContext.getSystemConfig().getToken(), timestamp, nonce);
		String mySignature = shal(sortStr);
		if (!"".equals(signature) && !"".equals(mySignature)
				&& signature.equals(mySignature)) {
			return true;
		}
		return false;
	}

	private String sort(String token, String timestamp, String nonce) {
		String[] strArray = { token, timestamp, nonce };
		Arrays.sort(strArray);
		StringBuilder sb = new StringBuilder();
		for (String str : strArray) {
			sb.append(str);
		}
		return sb.toString();
	}
	
	private String shal(String str) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-1");
			digest.update(str.getBytes());
			byte messageDigest[] = digest.digest();

			StringBuffer hexString = new StringBuffer();
			// 字节数组转换为 十六进制 数
			for (int i = 0; i < messageDigest.length; i++) {
				String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
				if (shaHex.length() < 2) {
					hexString.append(0);
				}
				hexString.append(shaHex);
			}
			return hexString.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}

	@Override
	public String sign(String jsapiTicket, String noncestr, String timestamp,
			String url) {
		String signStr = "jsapi_ticket="+jsapiTicket+"&noncestr="+noncestr
				+"&timestamp="+timestamp+"&url="+url;
		return this.shal(signStr);
	}

}
