package com.guolaiwan.app.xinglongshan.dto;

import java.util.List;



/**
 * 智游宝响应对象
 * 
 * @author crazy_cabbage
 * 
 */
public class PWBResponse extends BaseDto {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1545912118998137380L;
	/**
	 * 返回方法
	 */
	private String transactionName;
	/**
	 * sessionId
	 */
	private String sessionId;
	/**
	 * 编码
	 */
	private Integer code;
	/**
	 * 错误描述
	 */
	private String description;
	/**
	 * 图片
	 */
	private String img;
	/**
	 * 订单响应
	 */
	private OrderResponse orderResponse;
	/**
	 * 总数
	 */
	private Integer totalNum;
	

	/**
	 * 订单状态
	 */
	private String orderStatus;
	
	/**
	 * 子订单
	 */
	private List<SubOrder> subOrders;

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	/**
	 * ota异步订单查询凭证
	 */
	private String credence;

	public String getCredence() {
		return credence;
	}

	public void setCredence(String credence) {
		this.credence = credence;
	}

	/** 退票批次号 **/
	private String retreatBatchNo;
	/**
	 * 
	 */

	/** 检票号 */
	private String checkNo;
	/**
	 * 剩余数量 <br />
	 * 允许为空 Y <br />
	 * 数据长度 8<br />
	 */
	private Integer leftNum;
	/**
	 * 供应商可售商品
	 */

	private Object contect;

	private Order order;

	public Object getContect() {
		return contect;
	}

	// 支付宝支付返回的串
	private String alipayStr;
	
	/**
	 * 票型编码
	 * 此接口已经提供出去了，不能再加s了 
	 */
	private List<String> goodsCode;
	
	
	public String getAlipayStr() {
		return alipayStr;
	}

	public void setAlipayStr(String alipayStr) {
		this.alipayStr = alipayStr;
	}

	public void setContect(Object contect) {
		this.contect = contect;
	}

	/**
	 * 总数
	 */

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public OrderResponse getOrderResponse() {
		return orderResponse;
	}

	public void setOrderResponse(OrderResponse orderResponse) {
		this.orderResponse = orderResponse;
	}

	public String getTransactionName() {
		return transactionName;
	}

	public void setTransactionName(String transactionName) {
		this.transactionName = transactionName;
	}


	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public Integer getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}
	public String getCheckNo() {
		return checkNo;
	}

	public void setCheckNo(String checkNo) {
		this.checkNo = checkNo;
	}

	public Integer getLeftNum() {
		return leftNum;
	}

	public void setLeftNum(Integer leftNum) {
		this.leftNum = leftNum;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public String getRetreatBatchNo() {
		return retreatBatchNo;
	}

	public void setRetreatBatchNo(String retreatBatchNo) {
		this.retreatBatchNo = retreatBatchNo;
	}


	public List<String> getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(List<String> goodsCode) {
		this.goodsCode = goodsCode;
	}

	public List<SubOrder> getSubOrders() {
		return subOrders;
	}

	public void setSubOrders(List<SubOrder> subOrders) {
		this.subOrders = subOrders;
	}


}
