package com.guolaiwan.bussiness.distribute.po;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.guolaiwan.bussiness.distribute.classify.DistributorType;

import pub.caterpillar.orm.po.AbstractBasePO;

@Entity
@Table(name = "retail_policy")
public class MealListPo extends AbstractBasePO {

	private String  mealName; //菜品名称
	private String  productId; //产品Id
    private int  mealAmount; //菜品数量
	private long userId;   // 用户ID
	private long merchantId; //商家id
	
	
	
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getMealName() {
		return mealName;
	}
	public void setMealName(String mealName) {
		this.mealName = mealName;
	}
	public int getMealAmount() {
		return mealAmount;
	}
	public void setMealAmount(int mealAmount) {
		this.mealAmount = mealAmount;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public long getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(long merchantId) {
		this.merchantId = merchantId;
	}
	
	
	
}
