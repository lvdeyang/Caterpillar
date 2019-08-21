package com.guolaiwan.app.web.admin.vo;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.guolaiwan.app.web.publicnum.vo.RoomVo;
import com.guolaiwan.bussiness.admin.enumeration.OrderSource;
import com.guolaiwan.bussiness.admin.po.OrderInfoPO;
import com.guolaiwan.bussiness.website.po.AddressPO;

import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class OrderInfoVO extends AbstractBaseVO<OrderInfoVO,OrderInfoPO>{

	//订单号
	private String orderNO;
	//验单码
	private String ydNO;
	//下单时间
	private String createDate;
	//验单时间
	private String ydDate;
	//付款时间
	private String payDate;
	//发货时间
	private String sendDate;
	//成交时间
	private String dealDate;
	//供应商ID
	private long shopId;
	//供应商名称
	private String shopName;
	//站点ID
	private int siteCode;
	//站点名称
	private String siteName;
	//产品ID
	private long productId;
	//产品名称
	private String productName;
	//产品数量
	private long productNum;
	//商品单价
	private String productPrice;
	//所属板块ID
	private String bkCode;
	//所属板块名称
	private String bkName;
	//套餐ID
	private String attributeID;
	//套餐名称
	private String attributeName;
	//会员ID
	private long userId;
	//会员手机号
	private String userTel;
	//会员信息
	private long userInfo;
	//会员信息
	private String userName;
	//会员坐标经度
	private String userlongitude;
	//会员坐标维度
	private String userlatitude;
	//订单总金额
	private String orderAllMoney;
	//提成方式
	private int royaltyName;
	//提成比例
	private long proportion;
	//积分数
	private long integralNum;

	//订单佣金金额
	private String proportionMoney;
	//支付金额
	private String payMoney;
	//订单说明
	private String orderRemark;

	//支付方式
	private String payMode;

	//订单状态
	private String orderState;


	//商品图片
	private String productPic;
	//有效期
	private String validityDate;
	//是否评价
	private long commentIs;
	//预定日期
	private String orderBookDate;
	private String endBookDate;
	//收货地址
	private long mailAddress;
	//退货原因
	private String refundReason;
	//订单来源36
	private String source;

	//收货地址
	private AddressPO mailAddressPO;
	//收货地址
	private String orderType;
	// 坐标经度
	private String shopLongitude;
	// 坐标纬度
	private String shopLatitude;
	
	private String settleDate;
	
	private long activityId;
	
    private long roomId;
	
	private String roomName;
	
	private long roomStatusId;
	
    private long logisticsId;
	
	private String logisticsContent;
	
	private long comboId;
	
	private String comboName;
	
	private String logisticsName;
	
	private String photo;
	private String idNum;
	
	//4/23 新增拒绝理由
	private String justification;
	
	//4/28 新增退款限制 ----张羽
	private int productIsRefund;
	
	//张羽 新增 5/1 商品购买最低数量限制
	private int productRestrictNumber;
	
	// 新增快递单号--------------Dongsuyan
	private String trackingnumber;
	//分销商品的ID
	private String distributeId;
	//分销上回调二维码
	private String distributeQcode;

	
	public String getDistributeId() {
		return distributeId;
	}

	public OrderInfoVO setDistributeId(String distributeId) {
		this.distributeId = distributeId;
		return this;
	}

	public String getDistributeQcode() {
		return distributeQcode;
	}

	public OrderInfoVO setDistributeQcode(String distributeQcode) {
		this.distributeQcode = distributeQcode;
		return this;
	}

	public String getTrackingnumber() {
		return trackingnumber;
	}

	public void setTrackingnumber(String trackingnumber) {
		this.trackingnumber = trackingnumber;
	}

	public int getProductRestrictNumber() {
		return productRestrictNumber;
	}

	public void setProductRestrictNumber(int productRestrictNumber) {
		this.productRestrictNumber = productRestrictNumber;
	}

	public int getProductIsRefund() {
		return productIsRefund;
	}

	public void setProductIsRefund(int productIsRefund) {
		this.productIsRefund = productIsRefund;
	}

	public String getJustification() {
		return justification;
	}

	public void setJustification(String justification) {
		this.justification = justification;
	}

	public String getPhoto() {
		return photo;
	}

	public OrderInfoVO setPhoto(String photo) {
		this.photo = photo;
		return this;
	}

	public String getIdNum() {
		return idNum;
	}

	public OrderInfoVO setIdNum(String idNum) {
		this.idNum = idNum;
		return this;
	}

	public String getComboName() {
		return comboName;
	}

	public void setComboName(String comboName) {
		this.comboName = comboName;
	}

	public String getLogisticsName() {
		return logisticsName;
	}

	public void setLogisticsName(String logisticsName) {
		this.logisticsName = logisticsName;
	}

	public long getLogisticsId() {
		return logisticsId;
	}

	public OrderInfoVO setLogisticsId(long logisticsId) {
		this.logisticsId = logisticsId;
		return this;
	}

	public String getLogisticsContent() {
		return logisticsContent;
	}

	public OrderInfoVO setLogisticsContent(String logisticsContent) {
		this.logisticsContent = logisticsContent;
		return this;
	}

	public long getComboId() {
		return comboId;
	}

	public OrderInfoVO setComboId(long comboId) {
		this.comboId = comboId;
		return this;
	}

	public String getOrderNO() {
		return orderNO;
	}

	public OrderInfoVO setOrderNO(String orderNO) {
		this.orderNO = orderNO;
		return this;
	}

	public String getYdNO() {
		return ydNO;
	}

	public OrderInfoVO setYdNO(String ydNO) {
		this.ydNO = ydNO;
		return this;
	}

	public String getCreateDate() {
		return createDate;
	}

	public OrderInfoVO setCreateDate(String createDate) {
		this.createDate = createDate;
		return this;
	}

	public String getYdDate() {
		return ydDate;
	}

	public OrderInfoVO setYdDate(String ydDate) {
		this.ydDate = ydDate;
		return this;
	}

	public long getShopId() {
		return shopId;
	}

	public OrderInfoVO setShopId(long shopId) {
		this.shopId = shopId;
		return this;
	}

	public String getShopName() {
		return shopName;
	}

	public OrderInfoVO setShopName(String shopName) {
		this.shopName = shopName;
		return this;
	}

	public int getSiteCode() {
		return siteCode;
	}

	public OrderInfoVO setSiteCode(int siteCode) {
		this.siteCode = siteCode;
		return this;
	}

	public String getSiteName() {
		return siteName;
	}

	public OrderInfoVO setSiteName(String siteName) {
		this.siteName = siteName;
		return this;
	}


	public long getProductId() {
		return productId;
	}

	public OrderInfoVO setProductId(long productId) {
		this.productId = productId;
		return this;
	}

	public String getProductName() {
		return productName;
	}

	public OrderInfoVO setProductName(String productName) {
		this.productName = productName;
		return this;
	}

	public long getProductNum() {
		return productNum;
	}

	public OrderInfoVO setProductNum(long productNum) {
		this.productNum = productNum;
		return this;
	}



	public String getProductPrice() {
		return productPrice;
	}

	public OrderInfoVO setProductPrice(String productPrice) {
		this.productPrice = productPrice;
		return this;
	}

	public String getBkCode() {
		return bkCode;
	}

	public OrderInfoVO setBkCode(String bkCode) {
		this.bkCode = bkCode;
		return this;
	}

	public String getBkName() {
		return bkName;
	}

	public OrderInfoVO setBkName(String bkName) {
		this.bkName = bkName;
		return this;
	}

	public String getAttributeID() {
		return attributeID;
	}


	public OrderInfoVO setAttributeID(String attributeID) {
		this.attributeID = attributeID;
		return this;
	}

	public String getAttributeName() {
		return attributeName;
	}

	public OrderInfoVO setAttributeName(String attributeName) {
		this.attributeName = attributeName;
		return this;
	}

	public long getUserId() {
		return userId;
	}

	public OrderInfoVO setUserId(long userId) {
		this.userId = userId;
		return this;
	}

	public String getUserTel() {
		return userTel;
	}

	public OrderInfoVO setUserTel(String userTel) {
		this.userTel = userTel;
		return this;
	}

	public long getUserInfo() {
		return userInfo;
	}

	public OrderInfoVO setUserInfo(long userInfo) {
		this.userInfo = userInfo;
		return this;
	}

	public String getUserlongitude() {
		return userlongitude;
	}

	public OrderInfoVO setUserlongitude(String userlongitude) {
		this.userlongitude = userlongitude;
		return this;
	}

	public String getUserlatitude() {
		return userlatitude;
	}

	public OrderInfoVO setUserlatitude(String userlatitude) {
		this.userlatitude = userlatitude;
		return this;
	}



	public long getProportion() {
		return proportion;
	}

	public OrderInfoVO setProportion(long proportion) {
		this.proportion = proportion;
		return this;
	}


	public int getRoyaltyName() {
		return royaltyName;
	}

	public OrderInfoVO setRoyaltyName(int royaltyName) {
		this.royaltyName = royaltyName;
		return this;
	}

	public long getIntegralNum() {
		return integralNum;
	}

	public OrderInfoVO setIntegralNum(long integralNum) {
		this.integralNum = integralNum;
		return this;
	}

	public String getValidityDate() {
		return validityDate;
	}

	public OrderInfoVO setValidityDate(String validityDate) {
		this.validityDate = validityDate;
		return this;
	}

	public long getCommentIs() {
		return commentIs;
	}

	public OrderInfoVO setCommentIs(long commentIs) {
		this.commentIs = commentIs;
		return this;
	}

	public String getOrderBookDate() {
		return orderBookDate;
	}

	public OrderInfoVO setOrderBookDate(String orderBookDate) {
		this.orderBookDate = orderBookDate;
		return this;
	}






	public String getOrderRemark() {
		return orderRemark;
	}

	public OrderInfoVO setOrderRemark(String orderRemark) {
		this.orderRemark = orderRemark;
		return this;
	}

	public String getPayMode() {
		return payMode;
	}

	public OrderInfoVO setPayMode(String payMode) {
		this.payMode = payMode;
		return this;
	}

	public String getOrderState() {
		return orderState;
	}

	public OrderInfoVO setOrderState(String orderState) {
		this.orderState = orderState;
		return this;
	}

	public String getProductPic() {
		return productPic;
	}

	public OrderInfoVO setProductPic(String productPic) {
		this.productPic = productPic;
		return this;
	}


	public long getMailAddress() {
		return mailAddress;
	}

	public OrderInfoVO setMailAddress(long mailAddress) {
		this.mailAddress = mailAddress;
		return this;
	}


	public String getProportionMoney() {
		return proportionMoney;
	}

	public OrderInfoVO setProportionMoney(String proportionMoney) {
		this.proportionMoney = proportionMoney;
		return this;
	}

	public String getOrderAllMoney() {
		return orderAllMoney;
	}

	public OrderInfoVO setOrderAllMoney(String orderAllMoney) {
		this.orderAllMoney = orderAllMoney;
		return this;
	}

	public String getPayMoney() {
		return payMoney;
	}

	public OrderInfoVO setPayMoney(String payMoney) {
		this.payMoney = payMoney;
		return this;
	}



	public String getUserName() {
		return userName;
	}

	public OrderInfoVO setUserName(String userName) {
		this.userName = userName;
		return this;
	}




	public String getPayDate() {
		return payDate;
	}

	public OrderInfoVO setPayDate(String payDate) {
		this.payDate = payDate;
		return this;
	}

	public String getSendDate() {
		return sendDate;
	}

	public OrderInfoVO setSendDate(String sendDate) {
		this.sendDate = sendDate;
		return this;
	}

	public String getDealDate() {
		return dealDate;
	}

	public OrderInfoVO setDealDate(String dealDate) {
		this.dealDate = dealDate;
		return this;
	}


	public String getRefundReason() {
		return refundReason;
	}

	public OrderInfoVO setRefundReason(String refundReason) {
		this.refundReason = refundReason;
		return this;
	}


	public String getSource() {
		return source;
	}

	public OrderInfoVO setSource(String source) {
		this.source = source;
		return this;
	}



	public AddressPO getMailAddressPO() {
		return mailAddressPO;
	}

	public void setMailAddressPO(AddressPO mailAddressPO) {
		this.mailAddressPO = mailAddressPO;
	}
	

	public String getOrderType() {
		return orderType;
	}

	public OrderInfoVO setOrderType(String orderType) {
		this.orderType = orderType;
		return this;
	}
	
	

	public String getShopLongitude() {
		return shopLongitude;
	}

	public void setShopLongitude(String shopLongitude) {
		this.shopLongitude = shopLongitude;
	}

	public String getShopLatitude() {
		return shopLatitude;
	}

	public void setShopLatitude(String shopLatitude) {
		this.shopLatitude = shopLatitude;
	}
	
	public String getSettleDate() {
		return settleDate;
	}

	public OrderInfoVO setSettleDate(String settleDate) {
		this.settleDate = settleDate;
		return this;
	}

	@Override
	public OrderInfoVO set(OrderInfoPO entity)throws Exception{
		DateFormat df = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");	
//		DateFormat df1 = new SimpleDateFormat("yyyy年MM月dd日");	
		String ydDate =entity.getYdDate()==null?"":df.format(entity.getYdDate());
		String payDate =entity.getPayDate()==null?"":df.format(entity.getPayDate());
		String sendDate =entity.getSendDate()==null?"":df.format(entity.getSendDate());
		String dealDate =entity.getDealDate()==null?"":df.format(entity.getDealDate());
		String validityDate =entity.getValidityDate()==null?"":df.format(entity.getValidityDate());
		String OrderBookDate =entity.getOrderBookDate()==null?"":df.format(entity.getOrderBookDate());
		String EndBookDate=entity.getEndBookDate()==null?"":df.format(entity.getEndBookDate());
		String SettleDate = entity.getSettleDate()==null?"":df.format(entity.getSettleDate());
		String updateDate = entity.getUpdateTime()==null?"":df.format(entity.getUpdateTime());
		String payMode = entity.getPayMode()==null?"":entity.getPayMode().getName();
		this.setId(entity.getId())
		.setUuid(entity.getUuid())
		.setUpdateTime(updateDate)
		.setOrderNO(entity.getOrderNO())
		.setYdNO(entity.getYdNO())
		.setCreateDate(df.format(entity.getCreateDate()))
		.setYdDate(ydDate)
		.setShopId(entity.getShopId())
		.setShopName(entity.getShopName())
		.setSiteCode(entity.getSiteCode())
		.setSiteName(entity.getSiteName())
		.setProductId(entity.getProductId())
		.setProductName(entity.getProductName())
		.setProductNum(entity.getProductNum())
		.setProductPrice(new DecimalFormat("0.00").format(((double)entity.getProductPrice())/100))
		.setBkCode(entity.getBkCode())
		.setBkName(entity.getBkName())
		.setAttributeID(entity.getAttributeID())
		.setAttributeName(entity.getAttributeName())
		.setUserId(entity.getUserId())
		.setUserTel(entity.getUserTel())
		.setUserInfo(entity.getUserInfo())
		.setUserlongitude(entity.getUserlongitude())
		.setUserlatitude(entity.getUserlatitude())
		.setOrderAllMoney(new DecimalFormat("0.00").format(((double)entity.getOrderAllMoney())/100))
		.setRoyaltyName(entity.getRoyaltyName())
		.setIntegralNum(entity.getIntegralNum())
		.setValidityDate(validityDate)
		.setCommentIs(entity.getCommentIs())
		.setOrderBookDate(OrderBookDate)
		.setEndBookDate(EndBookDate)
		.setProportion(entity.getProportion())
		.setProportionMoney(new DecimalFormat("0.00").format(((double)entity.getProportionMoney())/100))
		.setPayMoney(new DecimalFormat("0.00").format(((double)entity.getPayMoney())/100))
		.setOrderRemark(entity.getOrderRemark())
		.setPayMode(payMode)
		.setOrderState(entity.getOrderState().getName())
		.setProductPic(entity.getProductPic())
		.setMailAddress(entity.getMailAddress())
		.setPayDate(payDate)
		.setDealDate(dealDate)
		.setSendDate(sendDate)
		.setRefundReason(entity.getRefundReason())
		.setSource(entity.getSource().getName())
		.setUserName(entity.getUserName())
		.setOrderType(entity.getOrderType().getName())
		.setActivityId(entity.getActivityId())
		.setRoomId(entity.getRoomId())
		.setRoomName(entity.getRoomName())
		.setRoomStatusId(entity.getRoomStatusId())
		.setLogisticsContent(entity.getLogisticsContent())
		.setLogisticsId(entity.getLogisticsId())
		.setComboId(entity.getComboId())
		.setPhoto(entity.getPhoto())
		.setIdNum(entity.getIdNum())
		.setSettleDate(SettleDate)
		.setDistributeId(entity.getDistributeId())
		.setDistributeQcode(entity.getDistributeQcode())
		.setTrackingnumber(entity.getTrackingnumber());
		return this;

	}

	
	
	public long getRoomId() {
		return roomId;
	}

	public OrderInfoVO setRoomId(long roomId) {
		this.roomId = roomId;
		return this;
	}

	public String getRoomName() {
		return roomName;
	}

	public OrderInfoVO setRoomName(String roomName) {
		this.roomName = roomName;
		return this;
	}

	public long getActivityId() {
		return activityId;
	}

	public OrderInfoVO setActivityId(long activityId) {
		this.activityId = activityId;
		return this;
	}

	public String getEndBookDate() {
		return endBookDate;
	}

	public OrderInfoVO setEndBookDate(String endBookDate) {
		this.endBookDate = endBookDate;
		return this;
	}

	public long getRoomStatusId() {
		return roomStatusId;
	}

	public OrderInfoVO setRoomStatusId(long roomStatusId) {
		this.roomStatusId = roomStatusId;
		return this;
	}
}


