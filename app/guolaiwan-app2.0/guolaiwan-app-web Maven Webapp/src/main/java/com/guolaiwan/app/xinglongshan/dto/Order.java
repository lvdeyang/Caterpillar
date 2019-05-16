package com.guolaiwan.app.xinglongshan.dto;

import java.math.BigDecimal;
import java.util.List;



/**
 * 订单传输对象
 * 
 * @author crazy_cabbage
 * 
 */
public class Order extends BaseDto {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2936244448029145788L;
	/**
	 * 地区信息码
	 */
	private String sysAreaCode;
	/**
	 * 证件类型
	 */
	private String certificateType;
	/**
	 * 证件号码
	 */
	private String certificateNo;
    /**
     * 票量
     */
	private Integer quantity;
	/**
	 * 已检票数量
	 */
	private Integer alreadyCheckNum;
	/**
	 * 联系人姓名
	 */
	private String linkName;
	/**
	 * 联系人手机
	 */
	private String linkMobile;
	/**
	 * 订单号
	 */
	private String orderCode;
	/**
	 * 订单模式
	 */
	private String orderModel;
	/**
	 * 订单价格
	 */
	private BigDecimal orderPrice;

	/**
	 * 订单状态
	 */
	private String orderStatus;
	/**
	 * 支付方法
	 */
	private String payMethod;
	/**
	 * 团号
	 */
	private String groupNo;
	/**
	 * 辅助检票号
	 */
	private String assistCheckNo;
	/**
	 * 支付状态
	 */
	private String payStatus;
	/**
	 * 返回路径
	 */
	private String returnUrl;
	/**
	 * 通知路径
	 */
	private String notifyUrl;
	
	/**
	 * 检票号
	 */
	private String checkNo;
	
	/**银行号**/
	private String bankCode;
	/**
	 * 银行名称
	 */
	private String bankName;
	
	private String type;
	/**
	 * 订单状态
	 * 
	 * @author crazy_cabbage
	 * 
	 */
	/***景区编码**/
	private String scenicCode;
	
	/***统一票型*/
	private List<TicketOrder> ticketOrders;	
	/**批次号**/
	private String retreatBatchNo;
	
	public interface TYPE {
	    /**
	     * 当天订单数
	     */
		String DAY_ALL="day_order_num";
		 /**
	     * 当天检票数量
	     */
		String DAY_ALREADY_CHECK_NUM="day_already_check_num";
		 /**
	     * 年订单数量
	     */
		String YEAR_ALL="year_order_num";
	}
	
	public String getCheckNo() {
		return checkNo;
	}

	public void setCheckNo(String checkNo) {
		this.checkNo = checkNo;
	}

	/**
	 * 帐号
	 */
	private String subject;
	/**
	 * 来源
	 */
	private String src;
	/**
	 * 模板号
	 * **/
	private String tplCode;

	
	
	private String buildName;
	
	/**
	 * 第三方支付方式
	 */
	private String thirdPayMethod;
	/**
	 * 子来源
	 */
	private String subSrc;
	
	private String photoUrl;
	/**
	 * 人脸地址
	 */
	private String faceSrc;
	

	public String getFaceSrc() {
		return faceSrc;
	}

	public void setFaceSrc(String faceSrc) {
		this.faceSrc = faceSrc;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public String getSysAreaCode() {
		return sysAreaCode;
	}

	public void setSysAreaCode(String sysAreaCode) {
		this.sysAreaCode = sysAreaCode;
	}

	public String getCertificateType() {
		return certificateType;
	}

	public void setCertificateType(String certificateType) {
		this.certificateType = certificateType;
	}

	public String getCertificateNo() {
		return certificateNo;
	}

	public void setCertificateNo(String certificateNo) {
		this.certificateNo = certificateNo;
	}

	
	public String getLinkName() {
		return linkName;
	}

	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}

	public String getLinkMobile() {
		return linkMobile;
	}

	public void setLinkMobile(String linkMobile) {
		this.linkMobile = linkMobile;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getOrderModel() {
		return orderModel;
	}

	public void setOrderModel(String orderModel) {
		this.orderModel = orderModel;
	}

	public BigDecimal getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(BigDecimal orderPrice) {
		this.orderPrice = orderPrice;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public Integer getAlreadyCheckNum() {
		return alreadyCheckNum;
	}
	public void setAlreadyCheckNum(Integer alreadyCheckNum) {
		this.alreadyCheckNum = alreadyCheckNum;
	}
	public String getPayMethod() {
		return payMethod;
	}
	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}
	public String getGroupNo() {
		return groupNo;
	}
	public void setGroupNo(String groupNo) {
		this.groupNo = groupNo;
	}
	public String getAssistCheckNo() {
		return assistCheckNo;
	}
	public void setAssistCheckNo(String assistCheckNo) {
		this.assistCheckNo = assistCheckNo;
	}
	public String getReturnUrl() {
		return returnUrl;
	}

	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public String getTplCode() {
		return tplCode;
	}

	public void setTplCode(String tplCode) {
		this.tplCode = tplCode;
	}

//	public List<MoreOrderTicket> getMoreOrderTickets() {
//		return moreOrderTickets;
//	}
//
//	public void setMoreOrderTickets(List<MoreOrderTicket> moreOrderTickets) {
//		this.moreOrderTickets = moreOrderTickets;
//	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBuildName() {
		return buildName;
	}

	public void setBuildName(String buildName) {
		this.buildName = buildName;
	}

	public String getScenicCode() {
		return scenicCode;
	}

	public void setScenicCode(String scenicCode) {
		this.scenicCode = scenicCode;
	}

	public List<TicketOrder> getTicketOrders() {
		return ticketOrders;
	}

	public void setTicketOrders(List<TicketOrder> ticketOrders) {
		this.ticketOrders = ticketOrders;
	}

	public String getRetreatBatchNo() {
		return retreatBatchNo;
	}

	public void setRetreatBatchNo(String retreatBatchNo) {
		this.retreatBatchNo = retreatBatchNo;
	}

	public String getThirdPayMethod() {
		return thirdPayMethod;
	}

	public void setThirdPayMethod(String thirdPayMethod) {
		this.thirdPayMethod = thirdPayMethod;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getSubSrc() {
		return subSrc;
	}

	public void setSubSrc(String subSrc) {
		this.subSrc = subSrc;
	}
	/**************剧院*************************/
	/**
	 * 节目编号
	 */
	private String showNo;
	/**
	 * 场次编号
	 */
	private String sessionCode;
	
	/**
	 * 场次时间
	 */
	private String palyTime;

	public String getShowNo() {
		return showNo;
	}

	public void setShowNo(String showNo) {
		this.showNo = showNo;
	}

	public String getSessionCode() {
		return sessionCode;
	}

	public void setSessionCode(String sessionCode) {
		this.sessionCode = sessionCode;
	}

	public String getPalyTime() {
		return palyTime;
	}

	public void setPalyTime(String palyTime) {
		this.palyTime = palyTime;
	}
	
}
