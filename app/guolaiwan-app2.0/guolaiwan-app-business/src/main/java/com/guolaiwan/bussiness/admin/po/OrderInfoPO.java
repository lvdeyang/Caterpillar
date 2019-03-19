package com.guolaiwan.bussiness.admin.po;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.guolaiwan.bussiness.admin.enumeration.OrderSource;
import com.guolaiwan.bussiness.admin.enumeration.OrderStateType;
import com.guolaiwan.bussiness.admin.enumeration.OrderType;
import com.guolaiwan.bussiness.admin.enumeration.PayType;

import pub.caterpillar.orm.po.AbstractBasePO;

@Entity
@Table(name = "t_Sys_OrderInfo")
public class OrderInfoPO extends AbstractBasePO {

	private static final long serialVersionUID = 1L;
	// 订单号1
	private String orderNO;
	// 验单码2
	private String ydNO;
	// 下单时间3
	private Date createDate;
	// 验单时间4
	private Date ydDate;
	// 付款时间
	private Date payDate;
	// 发货时间
	private Date sendDate;
	// 成交时间
	private Date dealDate;
	// 供应商ID5
	private long shopId;
	// 供应商名称6
	private String shopName;
	// 站点ID7
	private int siteCode;
	// 站点名称8
	private String siteName;
	// 产品ID9
	private long productId;
	// 产品名称10
	private String productName;
	// 产品数量11
	private long productNum;
	// 商品单价12
	private long productPrice;
	// 所属板块ID13
	private String bkCode;
	// 所属板块名称14
	private String bkName;
	// 套餐ID15
	private String attributeID;
	// 套餐名称16
	private String attributeName;
	// 会员ID17
	private long userId;
	// 会员手机号18
	private String userTel;
	// 会员信息19
	private long userInfo;
	// 会员坐标经度20
	private String userlongitude;
	// 会员坐标维度21
	private String userlatitude;
	// 订单总金额22
	private long orderAllMoney;
	// 提成比例23
	private long proportion;
	// 提成方式24
	private int royaltyName;
	// 积分数25

	private long integralNum;

	// 订单佣金金额26
	private long proportionMoney;
	// 支付金额27
	private long payMoney;
	// 订单说明28
	private String orderRemark;

	// 支付方式29
	private PayType payMode;

	// 订单状态30
	private OrderStateType orderState;

	// 商品图片31
	private String productPic;
	// 有效期32
	private Date validityDate;
	// 是否评价33
	private long commentIs;
	// 预定日期34
	private Date orderBookDate;
	// 预定日期34
	private Date endBookDate;
	// 收货地址35ID
	private long mailAddress;
	// 订单来源36
	private OrderSource source;
	private long liveId;

	// 这个字段用于扩展除product以外的数据下订单 定义特殊前缀+id
	private String targetId;

	//
	private long tableStatusId;

	// 退款原因
	private String refundReason;

	// 订单类型
	private OrderType orderType;

	private int balance;// 0未结算，1已结算

	private Date settleDate;

	private String userName;
	//这是活动商品关联Id，不是活动Id
	private long activityId;
	
	private long roomId;
	
	private String roomName;
	
	private long roomStatusId;
	
	private long logisticsId;
	
	private String logisticsContent;
	
	private long comboId;
	
	private String idNum;
	
	private String photo;
	
	
	public long getLogisticsId() {
		return logisticsId;
	}

	public void setLogisticsId(long logisticsId) {
		this.logisticsId = logisticsId;
	}

	public String getLogisticsContent() {
		return logisticsContent;
	}

	public void setLogisticsContent(String logisticsContent) {
		this.logisticsContent = logisticsContent;
	}

	public long getComboId() {
		return comboId;
	}

	public void setComboId(long comboId) {
		this.comboId = comboId;
	}

	public long getActivityId() {
		return activityId;
	}

	public void setActivityId(long activityId) {
		this.activityId = activityId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getSettleDate() {
		return settleDate;
	}

	public void setSettleDate(Date settleDate) {
		this.settleDate = settleDate;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public long getTableStatusId() {
		return tableStatusId;
	}

	public void setTableStatusId(long tableStatusId) {
		this.tableStatusId = tableStatusId;
	}

	public long getLiveId() {
		return liveId;
	}

	public void setLiveId(long liveId) {
		this.liveId = liveId;
	}

	public String getOrderNO() {
		return orderNO;
	}

	public void setOrderNO(String orderNO) {
		this.orderNO = orderNO;
	}

	public String getYdNO() {
		return ydNO;
	}

	public void setYdNO(String ydNO) {
		this.ydNO = ydNO;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getYdDate() {
		return ydDate;
	}

	public void setYdDate(Date ydDate) {
		this.ydDate = ydDate;
	}

	public long getShopId() {
		return shopId;
	}

	public void setShopId(long shopId) {
		this.shopId = shopId;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public int getSiteCode() {
		return siteCode;
	}

	public void setSiteCode(int siteCode) {
		this.siteCode = siteCode;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public long getProductNum() {
		return productNum;
	}

	public void setProductNum(long productNum) {
		this.productNum = productNum;
	}

	public long getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(long productPrice) {
		this.productPrice = productPrice;
	}

	public String getBkCode() {
		return bkCode;
	}

	public void setBkCode(String bkCode) {
		this.bkCode = bkCode;
	}

	public String getBkName() {
		return bkName;
	}

	public void setBkName(String bkName) {
		this.bkName = bkName;
	}

	public String getAttributeID() {
		return attributeID;
	}

	public void setAttributeID(String attributeID) {
		this.attributeID = attributeID;
	}

	public String getAttributeName() {
		return attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserTel() {
		return userTel;
	}

	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}

	public long getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(long userInfo) {
		this.userInfo = userInfo;
	}

	public String getUserlongitude() {
		return userlongitude;
	}

	public void setUserlongitude(String userlongitude) {
		this.userlongitude = userlongitude;
	}

	public String getUserlatitude() {
		return userlatitude;
	}

	public void setUserlatitude(String userlatitude) {
		this.userlatitude = userlatitude;
	}

	public long getOrderAllMoney() {
		return orderAllMoney;
	}

	public void setOrderAllMoney(long orderAllMoney) {
		this.orderAllMoney = orderAllMoney;
	}

	public long getProportion() {
		return proportion;
	}

	public void setProportion(long proportion) {
		this.proportion = proportion;
	}

	public int getRoyaltyName() {
		return royaltyName;
	}

	public void setRoyaltyName(int royaltyName) {
		this.royaltyName = royaltyName;
	}

	public long getIntegralNum() {
		return integralNum;
	}

	public void setIntegralNum(long integralNum) {
		this.integralNum = integralNum;
	}

	public long getProportionMoney() {
		return proportionMoney;
	}

	public void setProportionMoney(long proportionMoney) {
		this.proportionMoney = proportionMoney;
	}

	public long getPayMoney() {
		return payMoney;
	}

	public void setPayMoney(long payMoney) {
		this.payMoney = payMoney;
	}

	public String getOrderRemark() {
		return orderRemark;
	}

	public void setOrderRemark(String orderRemark) {
		this.orderRemark = orderRemark;
	}

	@Enumerated(EnumType.STRING)
	public PayType getPayMode() {
		return payMode;
	}

	public void setPayMode(PayType payMode) {
		this.payMode = payMode;
	}

	@Enumerated(EnumType.STRING)
	public OrderStateType getOrderState() {
		return orderState;
	}

	public void setOrderState(OrderStateType orderState) {
		this.orderState = orderState;
	}

	public String getProductPic() {
		return productPic;
	}

	public void setProductPic(String productPic) {
		this.productPic = productPic;
	}

	public Date getValidityDate() {
		return validityDate;
	}

	public void setValidityDate(Date validityDate) {
		this.validityDate = validityDate;
	}

	public long getCommentIs() {
		return commentIs;
	}

	public void setCommentIs(long commentIs) {
		this.commentIs = commentIs;
	}

	public Date getOrderBookDate() {
		return orderBookDate;
	}

	public void setOrderBookDate(Date orderBookDate) {
		this.orderBookDate = orderBookDate;
	}

	public long getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(long mailAddress) {
		this.mailAddress = mailAddress;
	}

	@Enumerated(EnumType.STRING)
	public OrderSource getSource() {
		return source;
	}

	public void setSource(OrderSource source) {
		this.source = source;
	}

	public String getTargetId() {
		return targetId;
	}

	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}

	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	public Date getDealDate() {
		return dealDate;
	}

	public void setDealDate(Date dealDate) {
		this.dealDate = dealDate;
	}

	public String getRefundReason() {
		return refundReason;
	}

	public void setRefundReason(String refundReason) {
		this.refundReason = refundReason;
	}

	@Enumerated(EnumType.STRING)
	public OrderType getOrderType() {
		return orderType;
	}

	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
	}
	
	@Override
    public Object clone(){
        OrderInfoPO order = null;
        try {
            order = (OrderInfoPO)super.clone();
        }catch (CloneNotSupportedException e){
            e.printStackTrace();
        }
        return order;
    }

	public Date getEndBookDate() {
		return endBookDate;
	}

	public void setEndBookDate(Date endBookDate) {
		this.endBookDate = endBookDate;
	}

	public long getRoomId() {
		return roomId;
	}

	public void setRoomId(long roomId) {
		this.roomId = roomId;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public long getRoomStatusId() {
		return roomStatusId;
	}

	public void setRoomStatusId(long roomStatusId) {
		this.roomStatusId = roomStatusId;
	}
	@Lob
    @Column(columnDefinition="text")
	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getIdNum() {
		return idNum;
	}

	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}



}
