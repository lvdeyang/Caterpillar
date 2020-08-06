package com.guolaiwan.app.zizhu.bean;

public class PayCodePo {
	//支付订单
	String trade_no;
	//支付方式 取值 wxpay ，alipay ，allinpay，jlpay，lcswpay
	String pay_type;
	//支付金额
	int amount;
	//商品描述
	String subject;
	//商品描述（非必填）
	String body;
	//非必填，1：POS终端，2：自助终端，3：消费终端
	int ter_type;
	public String getTrade_no() {
		return trade_no;
	}
	public void setTrade_no(String trade_no) {
		this.trade_no = trade_no;
	}
	public String getPay_type() {
		return pay_type;
	}
	public void setPay_type(String pay_type) {
		this.pay_type = pay_type;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public int getTer_type() {
		return ter_type;
	}
	public void setTer_type(int ter_type) {
		this.ter_type = ter_type;
	}
}
