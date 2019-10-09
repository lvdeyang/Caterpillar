package com.guolaiwan.app.web.business.vo;
import com.guolaiwan.bussiness.Parking.po.CarPositionPO;
import com.guolaiwan.bussiness.Parking.po.MoneyPO;
import com.guolaiwan.bussiness.admin.po.MealListPo;

import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class MealListVo  extends AbstractBaseVO<MealListVo, MealListPo>{
	private String  mealName; //菜品名称
	private long  productId; //产品Id
    private int  mealAmount; //菜品数量
	private long userId;   // 用户ID
	private long merchantId; //商家id
	private long tableId; //订桌表id
	private String money;
	private String picture;//菜品图片
	@Override
	public MealListVo set(MealListPo entity) throws Exception {
		this.setMealName(entity.getMealName())
		.setProductId(entity.getProductId())
		.setMealAmount(entity.getMealAmount())
		.setUserId(entity.getUserId())
		.setMerchantId(entity.getMerchantId())
		.setTableId(entity.getTableId());
		return this;
	}


	public String getPicture() {
		return picture;
	}


	public void setPicture(String picture) {
		this.picture = picture;
	}


	public String getMoney() {
		return money;
	}


	public void setMoney(String money) {
		this.money = money;
	}


	public String getMealName() {
		return mealName;
	}
	public MealListVo setMealName(String mealName) {
		this.mealName = mealName;
		return this;
	}
	public long getProductId() {
		return productId;
	}
	public MealListVo setProductId(long productId) {
		this.productId = productId;
		return this;
	}
	public int getMealAmount() {
		return mealAmount;
	}
	public MealListVo setMealAmount(int mealAmount) {
		this.mealAmount = mealAmount;
		return this;
	}
	public long getUserId() {
		return userId;
	}
	public MealListVo setUserId(long userId) {
		this.userId = userId;
		return this;
	}
	public long getMerchantId() {
		return merchantId;
	}
	public MealListVo setMerchantId(long merchantId) {
		this.merchantId = merchantId;
		return this;
	}
	public long getTableId() {
		return tableId;
	}
	public MealListVo setTableId(long tableId) {
		this.tableId = tableId;
		return this;
	}





	

}
