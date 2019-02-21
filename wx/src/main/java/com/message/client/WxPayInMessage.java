package com.message.client;

import com.alibaba.fastjson.annotation.JSONField;
import com.message.IMessage;

public class WxPayInMessage implements IMessage {

	@JSONField(name = "fee_type")
	private String feeType;
	
	@JSONField(name = "total_fee")
	private Integer totalFee;

	@JSONField(name = "spbill_create_ip")
	private String spbillCreateIp;
	
	//JSAPI 
	@JSONField(name = "trade_type")
	private String tradeType;
	
	@JSONField(name = "product_id")
	private String productId;
	
	@JSONField(name = "@JSONField")
	private String openid;
	
	private String body;
	
	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getFeeType() {
		return feeType;
	}

	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}

	public Integer getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(Integer totalFee) {
		this.totalFee = totalFee;
	}

	public String getSpbillCreateIp() {
		return spbillCreateIp;
	}

	public void setSpbillCreateIp(String spbillCreateIp) {
		this.spbillCreateIp = spbillCreateIp;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}
	
}
