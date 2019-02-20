package com.guolaiwan.app.web.admin.vo;

import com.guolaiwan.bussiness.admin.po.ProLatitudeLongitudePO;

import pub.caterpillar.mvc.converter.AbstractBaseVO;

/**
 * 景区地图经纬度显示封装类
 * @author Liw
 */
public class ProLatitudeLongitudeVO extends AbstractBaseVO<ProLatitudeLongitudeVO, ProLatitudeLongitudePO> {
	
	private String centerLongitude;//中心点经度
	
	private String centerLatitude;//中心点纬度
	
	private long pid;//商品id
	
	private String topLongitude;//上点经度
	
	private String topLatitude;//上点纬度
	
	private String leftLongitude;//左点经度
	
	private String leftLatitude;//左点纬度
	
	private String rightLongitude;//右点经度
	
	private String rightLatitude;//右点纬度
	
	private String buttomLongitude;//下点经度
	
	private String buttomLatitude;//下点纬度

	public String getCenterLongitude() {
		return centerLongitude;
	}

	public ProLatitudeLongitudeVO setCenterLongitude(String centerLongitude) {
		this.centerLongitude = centerLongitude;
		return this;
	}

	public String getCenterLatitude() {
		return centerLatitude;
	}

	public ProLatitudeLongitudeVO setCenterLatitude(String centerLatitude) {
		this.centerLatitude = centerLatitude;
		return this;
	}

	public long getPid() {
		return pid;
	}

	public ProLatitudeLongitudeVO setPid(long pid) {
		this.pid = pid;
		return this;
	}

	public String getTopLongitude() {
		return topLongitude;
	}

	public ProLatitudeLongitudeVO setTopLongitude(String topLongitude) {
		this.topLongitude = topLongitude;
		return this;
	}

	public String getTopLatitude() {
		return topLatitude;
	}

	public ProLatitudeLongitudeVO setTopLatitude(String topLatitude) {
		this.topLatitude = topLatitude;
		return this;
	}

	public String getLeftLongitude() {
		return leftLongitude;
	}

	public ProLatitudeLongitudeVO setLeftLongitude(String leftLongitude) {
		this.leftLongitude = leftLongitude;
		return this;
	}

	public String getLeftLatitude() {
		return leftLatitude;
	}

	public ProLatitudeLongitudeVO setLeftLatitude(String leftLatitude) {
		this.leftLatitude = leftLatitude;
		return this;
	}

	public String getRightLongitude() {
		return rightLongitude;
	}

	public ProLatitudeLongitudeVO setRightLongitude(String rightLongitude) {
		this.rightLongitude = rightLongitude;
		return this;
	}

	public String getRightLatitude() {
		return rightLatitude;
	}

	public ProLatitudeLongitudeVO setRightLatitude(String rightLatitude) {
		this.rightLatitude = rightLatitude;
		return this;
	}

	public String getButtomLongitude() {
		return buttomLongitude;
	}

	public ProLatitudeLongitudeVO setButtomLongitude(String buttomLongitude) {
		this.buttomLongitude = buttomLongitude;
		return this;
	}

	public String getButtomLatitude() {
		return buttomLatitude;
	}

	public ProLatitudeLongitudeVO setButtomLatitude(String buttomLatitude) {
		this.buttomLatitude = buttomLatitude;
		return this;
	}

	@Override
	public ProLatitudeLongitudeVO set(ProLatitudeLongitudePO entity) throws Exception {
		this.setId(entity.getId())
		.setUuid(entity.getUuid())
		.setUpdateTime(entity.getUpdateTime())
		.setButtomLatitude(entity.getButtomLatitude())
		.setButtomLongitude(entity.getButtomLongitude())
		.setCenterLatitude(entity.getCenterLatitude())
		.setCenterLongitude(entity.getCenterLongitude())
		.setLeftLatitude(entity.getLeftLatitude())
		.setLeftLongitude(entity.getLeftLongitude())
		.setPid(entity.getProduct().getId())
		.setRightLatitude(entity.getRightLatitude())
		.setRightLongitude(entity.getRightLongitude())
		.setTopLatitude(entity.getTopLatitude())
		.setTopLongitude(entity.getTopLongitude());
		return this;
	}
	
}