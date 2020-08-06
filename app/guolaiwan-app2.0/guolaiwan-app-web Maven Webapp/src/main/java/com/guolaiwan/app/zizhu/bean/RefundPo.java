package com.guolaiwan.app.zizhu.bean;

public class RefundPo {
	//商户订单号
	String trade_no;
	//商户退款订单号
	String refund_no;
	//退款金额 分
	int amount;
	public String getTrade_no() {
		return trade_no;
	}
	public void setTrade_no(String trade_no) {
		this.trade_no = trade_no;
	}
	public String getRefund_no() {
		return refund_no;
	}
	public void setRefund_no(String refund_no) {
		this.refund_no = refund_no;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
}
