package com.guolaiwan.bussiness.admin.po;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.guolaiwan.bussiness.admin.enumeration.LiveProductType;

import pub.caterpillar.orm.po.AbstractBasePO;

@Entity
@Table(name="t_sys_liveproduct")
public class LiveProductPO extends AbstractBasePO {


	private static final long serialVersionUID = 8771062832933939049L;
	
	//商品Id
	private long productId;
	//直播Id
	private long liveId;
	//商家Id
	private long merchantId;
	//商品类型（FIXEDPRICE("固定价格");AUCTIONPRICE("拍卖价格")）
	private LiveProductType liveProductType;
	//价格
	private long price;
	//锁定
	private boolean locked;
	
	private long dealPrice;
	private long auctionId;
	// 是否已支付
	private boolean paid;
	// 是否通知客户端
	private boolean flag;
	//商家名称
	private String merchantName;
	
	//商品图片
	private String headPic;
	//商品名称
	private String productName;
	//是否删除
	private String productIsDel;
	//生成订单号
	private String orderId;
	//生成用户ID
	private String userId;
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getProductIsDel() {
		return productIsDel;
	}
	public void setProductIsDel(String productIsDel) {
		this.productIsDel = productIsDel;
	}
	public String getHeadPic() {
		return headPic;
	}
	public void setHeadPic(String headPic) {
		this.headPic = headPic;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}

	
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public boolean isPaid() {
		return paid;
	}
	public void setPaid(boolean paid) {
		this.paid = paid;
	}
	public long getAuctionId() {
		return auctionId;
	}
	public void setAuctionId(long auctionId) {
		this.auctionId = auctionId;
	}
	public long getDealPrice() {
		return dealPrice;
	}
	public void setDealPrice(long dealPrice) {
		this.dealPrice = dealPrice;
	}
	public boolean isLocked() {
		return locked;
	}
	public void setLocked(boolean locked) {
		this.locked = locked;
	}
	public long getProductId() {
		return productId;
	}
	public void setProductId(long productId) {
		this.productId = productId;
	}
	public long getLiveId() {
		return liveId;
	}
	public void setLiveId(long liveId) {
		this.liveId = liveId;
	}
	public long getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(long merchantId) {
		this.merchantId = merchantId;
	}
	public LiveProductType getLiveProductType() {
		return liveProductType;
	}
	public void setLiveProductType(LiveProductType liveProductType) {
		this.liveProductType = liveProductType;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	public String getMerchantName() {
		return merchantName;
	}
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}
	
	
	
}
