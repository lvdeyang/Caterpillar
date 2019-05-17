package com.guolaiwan.app.xinglongshan.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


public class SubOrder extends BaseDto {
	/**
	 * 
	 */
	private static final long serialVersionUID = -845074216062332795L;
	/**
	 * 订单详情主键用过传输检票
	 */
	private Long orderDetailId;
	/**
	 * 订单主键
	 */
	private Long orderInfoId;
	/**
	 * 订单编码
	 */
	private String orderInfoCode;
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
	 * 检票号
	 */
	private String checkNo;
	/**
	 * 线下编码
	 */
	private String outCode;
	/**
	 * 商品名称
	 */
	private String name;
	/**
	 * 开始日期
	 */
	private Date startDate;
	/**
	 * 结束日期
	 */
	private Date endDate;
	/**
	 * 总价
	 */
	private BigDecimal totalPrice;
	/**
	 * 单价
	 */
	private BigDecimal price;
	/**
	 * 数量
	 */
	private Integer quantity;

	/**
	 * 需已检票数量
	 */
	private Integer needCheckNum;
	/**
	 * 已检票数量
	 */
	private Integer alreadyCheckNum;
	/**
	 * 退票数量
	 */
	private Integer returnNum;

	/**
	 * 线下检票数量
	 */
	private Integer checkNum;
	/**
	 * 线上检票数量
	 */
	private Integer onlineCheckNum;
	/**
	 * 成人数量
	 */
	private Integer adult;
	/**
	 * 儿童数量
	 */
	private Integer child;
	/**
	 * 订单类型
	 */
	private String orderType;
	/**
	 * 状态
	 */
	private String status;
	/**
	 * 检票状态
	 */
	private String checkStatus;
	/**
	 * 购买企业码
	 */
	private String buyCorpCode;
	/**
	 * 购买人
	 */
	private String buyBy;
	/**
	 * 购买企业名称
	 */
	private String buyCorpName;
	/**
	 * 下单日期
	 */
	private Date orderDate;
	/**
	 * 游玩日期
	 */
	private Date occDate;
	/**
	 * 取票联系人
	 */
	private String linkName;
	/**
	 * 取票联系电话
	 */
	private String tel;
	/**
	 * 订单编码
	 */
	private String orderCode;
	/**
	 * 支付状态
	 */
	private String payStatus;
	/**
	 * 商品名称
	 */
	private String goodsName;
   /**
    * 最后一次检票时间
    */
	private String lastCheckTime;
	/**
	 * 卖方企业编码
	 */
	private String sellCorpCode;
	
	/**检票记录**/
	private List<SubOrderCheckRecord> checkRecords;
	
	public Long getOrderDetailId() {
		return orderDetailId;
	}

	public void setOrderDetailId(Long orderDetailId) {
		this.orderDetailId = orderDetailId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getCheckNum() {
		return checkNum;
	}

	public void setCheckNum(Integer checkNum) {
		this.checkNum = checkNum;
	}

	public Integer getAdult() {
		return adult;
	}

	public void setAdult(Integer adult) {
		this.adult = adult;
	}

	public Integer getChild() {
		return child;
	}

	public void setChild(Integer child) {
		this.child = child;
	}

	public String getOutCode() {
		return outCode;
	}

	public void setOutCode(String outCode) {
		this.outCode = outCode;
	}

	public Integer getNeedCheckNum() {
		return needCheckNum;
	}

	public void setNeedCheckNum(Integer needCheckNum) {
		this.needCheckNum = needCheckNum;
	}

	public Integer getAlreadyCheckNum() {
		return alreadyCheckNum;
	}

	public void setAlreadyCheckNum(Integer alreadyCheckNum) {
		this.alreadyCheckNum = alreadyCheckNum;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getOrderInfoId() {
		return orderInfoId;
	}

	public void setOrderInfoId(Long orderInfoId) {
		this.orderInfoId = orderInfoId;
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

	public String getCheckNo() {
		return checkNo;
	}

	public void setCheckNo(String checkNo) {
		this.checkNo = checkNo;
	}

	public String getOrderInfoCode() {
		return orderInfoCode;
	}

	public void setOrderInfoCode(String orderInfoCode) {
		this.orderInfoCode = orderInfoCode;
	}

	public String getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}

	public String getBuyCorpCode() {
		return buyCorpCode;
	}

	public void setBuyCorpCode(String buyCorpCode) {
		this.buyCorpCode = buyCorpCode;
	}

	public String getBuyBy() {
		return buyBy;
	}

	public void setBuyBy(String buyBy) {
		this.buyBy = buyBy;
	}



	public String getLinkName() {
		return linkName;
	}

	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}



	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public Integer getOnlineCheckNum() {
		return onlineCheckNum;
	}

	public void setOnlineCheckNum(Integer onlineCheckNum) {
		this.onlineCheckNum = onlineCheckNum;
	}

	public Integer getReturnNum() {
		return returnNum;
	}

	public void setReturnNum(Integer returnNum) {
		this.returnNum = returnNum;
	}

	public String getLastCheckTime() {
		return lastCheckTime;
	}

	public void setLastCheckTime(String lastCheckTime) {
		this.lastCheckTime = lastCheckTime;
	}

	public String getSellCorpCode() {
		return sellCorpCode;
	}

	public void setSellCorpCode(String sellCorpCode) {
		this.sellCorpCode = sellCorpCode;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Date getOccDate() {
		return occDate;
	}

	public void setOccDate(Date occDate) {
		this.occDate = occDate;
	}

	public String getBuyCorpName() {
		return buyCorpName;
	}

	public void setBuyCorpName(String buyCorpName) {
		this.buyCorpName = buyCorpName;
	}

	public List<SubOrderCheckRecord> getCheckRecords() {
		return checkRecords;
	}

	public void setCheckRecords(List<SubOrderCheckRecord> checkRecords) {
		this.checkRecords = checkRecords;
	}
	
}
