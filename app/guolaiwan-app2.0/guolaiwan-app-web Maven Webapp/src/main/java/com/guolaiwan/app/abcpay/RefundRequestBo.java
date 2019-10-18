package com.guolaiwan.app.abcpay;

public class RefundRequestBo {

	private String OrderDate;                   //订单日期 
	private String OrderTime;                   //订单时间 
	private String OrderNo;                     //原交易编号
	private String NewOrderNo;                	//交易编号
	private String CurrencyCode;      			//交易币种
	private String TrxAmount;                   //退货金额
	private String RefundType;                  //退货类型
	private String MerchantRemarks;    			//附言
	public String getOrderDate() {
		return OrderDate;
	}
	public void setOrderDate(String orderDate) {
		OrderDate = orderDate;
	}
	public String getOrderTime() {
		return OrderTime;
	}
	public void setOrderTime(String orderTime) {
		OrderTime = orderTime;
	}
	public String getOrderNo() {
		return OrderNo;
	}
	public void setOrderNo(String orderNo) {
		OrderNo = orderNo;
	}
	public String getNewOrderNo() {
		return NewOrderNo;
	}
	public void setNewOrderNo(String newOrderNo) {
		NewOrderNo = newOrderNo;
	}
	public String getCurrencyCode() {
		return CurrencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		CurrencyCode = currencyCode;
	}
	public String getTrxAmount() {
		return TrxAmount;
	}
	public void setTrxAmount(String trxAmount) {
		TrxAmount = trxAmount;
	}
	public String getRefundType() {
		return RefundType;
	}
	public void setRefundType(String refundType) {
		RefundType = refundType;
	}
	public String getMerchantRemarks() {
		return MerchantRemarks;
	}
	public void setMerchantRemarks(String merchantRemarks) {
		MerchantRemarks = merchantRemarks;
	}
	
}
