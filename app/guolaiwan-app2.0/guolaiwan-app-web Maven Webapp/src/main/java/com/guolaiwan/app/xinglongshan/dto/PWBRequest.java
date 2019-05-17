package com.guolaiwan.app.xinglongshan.dto;





/**
 * 请求传输对象
 * @author crazy_cabbage
 *
 */
public class PWBRequest extends BaseDto  {
	/**
	 * 
	 */
	private static final long serialVersionUID = -862557333429953746L;
	/**
	 * 调用方法
	 */
	private String transactionName;

	/**
	 * 请求�?
	 */
	private Header header;
	/**
	 * 身份认证信息
	 */
	private IdentityInfo identityInfo;
	/**
	 * 订单请求对象
	 */
	private OrderRequest orderRequest;


	public String getTransactionName() {
		return transactionName;
	}

	public void setTransactionName(String transactionName) {
		this.transactionName = transactionName;
	}
	
	public Header getHeader() {
		return header;
	}
	public void setHeader(Header header) {
		this.header = header;
	}
	public IdentityInfo getIdentityInfo() {
		return identityInfo;
	}
	public void setIdentityInfo(IdentityInfo identityInfo) {
		this.identityInfo = identityInfo;
	}
	public OrderRequest getOrderRequest() {
		return orderRequest;
	}
	public void setOrderRequest(OrderRequest orderRequest) {
		this.orderRequest = orderRequest;
	}

	
}
