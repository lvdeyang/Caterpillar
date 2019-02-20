package com.guolaiwan.bussiness.admin.po;

import javax.persistence.Entity;
import javax.persistence.Table;

import pub.caterpillar.orm.po.AbstractBasePO;

@Entity
@Table(name = "t_sys_share")
public class SharePO extends AbstractBasePO {
	
	private static final long serialVersionUID = 1L;

	private String userId;//用户id
	
	private String productId;//景区id
	
	private String childIds;//浏览过的导览点id
	
	private String unchildIds;//未浏览的导览点id
	
	private String step;//步数
	
	private String km;//公里数
	
	private String calorie;//卡路里
	
	private String weather;//天气
	
	private String proName;//景区名称

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getChildIds() {
		return childIds;
	}

	public void setChildIds(String childIds) {
		this.childIds = childIds;
	}

	public String getUnchildIds() {
		return unchildIds;
	}

	public void setUnchildIds(String unchildIds) {
		this.unchildIds = unchildIds;
	}

	public String getStep() {
		return step;
	}

	public void setStep(String step) {
		this.step = step;
	}

	public String getKm() {
		return km;
	}

	public void setKm(String km) {
		this.km = km;
	}

	public String getCalorie() {
		return calorie;
	}

	public void setCalorie(String calorie) {
		this.calorie = calorie;
	}

	public String getWeather() {
		return weather;
	}

	public void setWeather(String weather) {
		this.weather = weather;
	}

}