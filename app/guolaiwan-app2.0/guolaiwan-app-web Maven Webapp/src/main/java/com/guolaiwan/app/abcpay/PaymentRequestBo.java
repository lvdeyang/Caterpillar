package com.guolaiwan.app.abcpay;

public class PaymentRequestBo {

	private String PayTypeID;                   //交易类型
	private String OrderDate;                   //订单日期 （必要信息 - YYYY/MM/DD）
	private String OrderTime;                   //订单时间 （必要信息 - HH:MM:SS）
	private String orderTimeoutDate;     		//订单有效期
	private String OrderNo;                     //订单编号 （必要信息）
	private String CurrencyCode;                //交易币种
	private String OrderAmount;      			//交易金额
	private String Fee;                         //手续费金额
	private String AccountNo;                   //支付账户
	private String OrderDesc;                   //订单说明
	private String OrderURL;                    //订单地址
	private String ReceiverAddress;             //收货地址
	private String InstallmentMark;       		//分期标识
	private String CommodityType;           	//商品种类
	private String BuyIP;                       //客户交易IP
	private String ExpiredDate;                 //订单保存时间
	private String PaymentType;					//支付账户类型
	private String PaymentLinkType;				//交易渠道
	private String ReceiveAccount;      		//收款方账号
	private String ReceiveAccName;      		//收款方户名
	private String NotifyType;              	//通知方式
	private String ResultNotifyURL;    			//通知URL地址
	private String MerchantRemarks;    			//附言
	private String ReceiveMark;             	//交易是否直接入二级商户账户
	private String ReceiveMerchantType; 		//收款方账户类型
	private String IsBreakAccount;      		//交易是否分账、交易是否支持向二级商户入账
	private String SplitAccTemplate;  			//分账模版编号
	public String getPayTypeID() {
		return PayTypeID;
	}
	public void setPayTypeID(String payTypeID) {
		PayTypeID = payTypeID;
	}
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
	public String getOrderTimeoutDate() {
		return orderTimeoutDate;
	}
	public void setOrderTimeoutDate(String orderTimeoutDate) {
		this.orderTimeoutDate = orderTimeoutDate;
	}
	public String getOrderNo() {
		return OrderNo;
	}
	public void setOrderNo(String orderNo) {
		OrderNo = orderNo;
	}
	public String getCurrencyCode() {
		return CurrencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		CurrencyCode = currencyCode;
	}
	public String getOrderAmount() {
		return OrderAmount;
	}
	public void setOrderAmount(String orderAmount) {
		OrderAmount = orderAmount;
	}
	public String getFee() {
		return Fee;
	}
	public void setFee(String fee) {
		Fee = fee;
	}
	public String getAccountNo() {
		return AccountNo;
	}
	public void setAccountNo(String accountNo) {
		AccountNo = accountNo;
	}
	public String getOrderDesc() {
		return OrderDesc;
	}
	public void setOrderDesc(String orderDesc) {
		OrderDesc = orderDesc;
	}
	public String getOrderURL() {
		return OrderURL;
	}
	public void setOrderURL(String orderURL) {
		OrderURL = orderURL;
	}
	public String getReceiverAddress() {
		return ReceiverAddress;
	}
	public void setReceiverAddress(String receiverAddress) {
		ReceiverAddress = receiverAddress;
	}
	public String getInstallmentMark() {
		return InstallmentMark;
	}
	public void setInstallmentMark(String installmentMark) {
		InstallmentMark = installmentMark;
	}
	public String getCommodityType() {
		return CommodityType;
	}
	public void setCommodityType(String commodityType) {
		CommodityType = commodityType;
	}
	public String getBuyIP() {
		return BuyIP;
	}
	public void setBuyIP(String buyIP) {
		BuyIP = buyIP;
	}
	public String getExpiredDate() {
		return ExpiredDate;
	}
	public void setExpiredDate(String expiredDate) {
		ExpiredDate = expiredDate;
	}
	public String getPaymentType() {
		return PaymentType;
	}
	public void setPaymentType(String paymentType) {
		PaymentType = paymentType;
	}
	public String getPaymentLinkType() {
		return PaymentLinkType;
	}
	public void setPaymentLinkType(String paymentLinkType) {
		PaymentLinkType = paymentLinkType;
	}
	public String getReceiveAccount() {
		return ReceiveAccount;
	}
	public void setReceiveAccount(String receiveAccount) {
		ReceiveAccount = receiveAccount;
	}
	public String getReceiveAccName() {
		return ReceiveAccName;
	}
	public void setReceiveAccName(String receiveAccName) {
		ReceiveAccName = receiveAccName;
	}
	public String getNotifyType() {
		return NotifyType;
	}
	public void setNotifyType(String notifyType) {
		NotifyType = notifyType;
	}
	public String getResultNotifyURL() {
		return ResultNotifyURL;
	}
	public void setResultNotifyURL(String resultNotifyURL) {
		ResultNotifyURL = resultNotifyURL;
	}
	public String getMerchantRemarks() {
		return MerchantRemarks;
	}
	public void setMerchantRemarks(String merchantRemarks) {
		MerchantRemarks = merchantRemarks;
	}
	public String getReceiveMark() {
		return ReceiveMark;
	}
	public void setReceiveMark(String receiveMark) {
		ReceiveMark = receiveMark;
	}
	public String getReceiveMerchantType() {
		return ReceiveMerchantType;
	}
	public void setReceiveMerchantType(String receiveMerchantType) {
		ReceiveMerchantType = receiveMerchantType;
	}
	public String getIsBreakAccount() {
		return IsBreakAccount;
	}
	public void setIsBreakAccount(String isBreakAccount) {
		IsBreakAccount = isBreakAccount;
	}
	public String getSplitAccTemplate() {
		return SplitAccTemplate;
	}
	public void setSplitAccTemplate(String splitAccTemplate) {
		SplitAccTemplate = splitAccTemplate;
	}
	
}
