package com.guolaiwan.app.web.admin.vo;

import com.guolaiwan.bussiness.admin.enumeration.LiveProductType;

public class LiveProductVO {

	private long productId;
	private long liveId;
	private long merchantId;
	private LiveProductType liveProductType;
	private long price;
	private boolean locked;
	private long dealPrice;
	private long auctionId;
	// 是否已支付
	private boolean paid;
	
	private String productName;
	private String userName;
	//是否删除
		private String productIsDel;
		public String getProductIsDel() {
			return productIsDel;
		}
		public void setProductIsDel(String productIsDel) {
			this.productIsDel = productIsDel;
		}
	//商品图片
		private String headPic;
		public String getHeadPic() {
			return headPic;
		}
		public void setHeadPic(String headPic) {
			this.headPic = headPic;
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
	public boolean isLocked() {
		return locked;
	}
	public void setLocked(boolean locked) {
		this.locked = locked;
	}
	public long getDealPrice() {
		return dealPrice;
	}
	public void setDealPrice(long dealPrice) {
		this.dealPrice = dealPrice;
	}
	public long getAuctionId() {
		return auctionId;
	}
	public void setAuctionId(long auctionId) {
		this.auctionId = auctionId;
	}
	public boolean isPaid() {
		return paid;
	}
	public void setPaid(boolean paid) {
		this.paid = paid;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
