package com.guolaiwan.app.web.admin.vo;

import com.guolaiwan.bussiness.admin.po.SharePO;

import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class ShareVO extends AbstractBaseVO<ShareVO, SharePO> {

	private String userId;// 用户id

	private String productId;// 景区id

	private String childIds;// 浏览过的导览点id

	private String unchildIds;// 未浏览的导览点id

	private String step;// 步数

	private String km;// 公里数

	private String calorie;// 卡路里

	private String weather;// 天气
	
	private String proName;//景区名称

	public String getProName() {
		return proName;
	}

	public ShareVO setProName(String proName) {
		this.proName = proName;
		return this;
	}

	public String getUserId() {
		return userId;
	}

	public ShareVO setUserId(String userId) {
		this.userId = userId;
		return this;
	}

	public String getProductId() {
		return productId;
	}

	public ShareVO setProductId(String productId) {
		this.productId = productId;
		return this;
	}

	public String getChildIds() {
		return childIds;
	}

	public ShareVO setChildIds(String childIds) {
		this.childIds = childIds;
		return this;
	}

	public String getUnchildIds() {
		return unchildIds;
	}

	public ShareVO setUnchildIds(String unchildIds) {
		this.unchildIds = unchildIds;
		return this;
	}

	public String getStep() {
		return step;
	}

	public ShareVO setStep(String step) {
		this.step = step;
		return this;
	}

	public String getKm() {
		return km;
	}

	public ShareVO setKm(String km) {
		this.km = km;
		return this;
	}

	public String getCalorie() {
		return calorie;
	}

	public ShareVO setCalorie(String calorie) {
		this.calorie = calorie;
		return this;
	}

	public String getWeather() {
		return weather;
	}

	public ShareVO setWeather(String weather) {
		this.weather = weather;
		return this;
	}

	@Override
	public ShareVO set(SharePO entity) throws Exception {
		this.setId(entity.getId())
		.setUuid(entity.getUuid())
		.setUpdateTime(entity.getUpdateTime())
		.setCalorie(entity.getCalorie())
		.setChildIds(entity.getChildIds())
		.setKm(entity.getKm())
		.setProductId(entity.getProductId())
		.setStep(entity.getStep())
		.setUnchildIds(entity.getUnchildIds())
		.setWeather(entity.getWeather())
		.setProName(entity.getProName());
		return this;
	}

}