package com.guolaiwan.app.zizhu.bean;


public class ProductPo {
	//签名
	public String sign;
	//产品Id
	public int id;
	//销售渠道	0 - 人工窗口、1 - 自助售取票终端、2 - 微信公众号、3 - 景区官网、4 - 淘宝旗舰店
	public int salesChannel;
	//窗口Id	人工窗口、自助售取票终端的窗口ID
	public int salesWinId;
	//用户Id	人工窗口、自助售取票终端指定的用户
	public int userId;
	//分页页码	默认值1
	public int page;
	//分页大小	默认值100
	public int size;
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSalesChannel() {
		return salesChannel;
	}
	public void setSalesChannel(int salesChannel) {
		this.salesChannel = salesChannel;
	}
	public int getSalesWinId() {
		return salesWinId;
	}
	public void setSalesWinId(int salesWinId) {
		this.salesWinId = salesWinId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	
}
