package com.wxpay;

import java.io.InputStream;

import org.springframework.stereotype.Service;

import com.context.TransactionContext;

@Service
public class DefaultWXPayConfig extends WXPayConfig {

	@Override
	public String getAppID() {
		return TransactionContext.getWxPayConfig().getAppId();
	}

	@Override
	public String getMchID() {
		return TransactionContext.getWxPayConfig().getMchId();
	}

	@Override
	public String getKey() {
		return TransactionContext.getWxPayConfig().getKey();
	}

	@Override
	public InputStream getCertStream() {
		return Thread.currentThread().getContextClassLoader().getResourceAsStream(TransactionContext.getWxPayConfig().getCertPath());
	}

	@Override
	public IWXPayDomain getWXPayDomain() {
		return new IWXPayDomain(){

			@Override
			public void report(String domain, long elapsedTimeMillis,
					Exception ex) {
				
			}

			@Override
			public DomainInfo getDomain(WXPayConfig config) {
				boolean primaryDomain = "true".equalsIgnoreCase(TransactionContext.getWxPayConfig().getPrimaryDomain());
				return new DomainInfo(TransactionContext.getWxPayConfig().getWxDomain(),
						primaryDomain);
			}
			
		};
	}

	public String getCertPath() {
		return TransactionContext.getWxPayConfig().getCertPath();
	}

	public String getDomain() {
		return TransactionContext.getWxPayConfig().getWxDomain();
	}

	

	@Override
	public String getSandboxSignkey() {
		return TransactionContext.getWxPayConfig().getSandboxSignKey();
	}

	@Override
	public boolean getUseSandbox() {
		return "true".equalsIgnoreCase(TransactionContext.getWxPayConfig().getUseSandbox());
	}

	@Override
	public String getNotifyUrl() {
		return TransactionContext.getWxPayConfig().getNotifyUrl();
	}

	@Override
	public boolean getAutoReport() {
		return "true".equalsIgnoreCase(TransactionContext.getWxPayConfig().getAutoReport());
	}

	
}
