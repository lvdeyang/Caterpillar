package com.guolaiwan.app.web.admin.vo;

import com.guolaiwan.bussiness.admin.po.ProTourismPicturePO;

import pub.caterpillar.mvc.converter.AbstractBaseVO;

/**
 * 景区图片显示封装类
 * @author Liw
 */
public class ProTourismPictureVO extends AbstractBaseVO<ProTourismPictureVO, ProTourismPicturePO> {
	
	private String topLongitude;// 上点经度

	private String topLatitude;// 上点纬度

	private String leftLongitude;// 左点经度

	private String leftLatitude;// 左点纬度

	private String rightLongitude;// 右点经度

	private String rightLatitude;// 右点纬度

	private String buttomLongitude;// 下点经度

	private String buttomLatitude;// 下点纬度
	
	private String address;//服务器图片保存地址
	
	private long pid;//商品id

	public long getPid() {
		return pid;
	}

	public ProTourismPictureVO setPid(long pid) {
		this.pid = pid;
		return this;
	}

	public String getTopLongitude() {
		return topLongitude;
	}

	public ProTourismPictureVO setTopLongitude(String topLongitude) {
		this.topLongitude = topLongitude;
		return this;
	}

	public String getTopLatitude() {
		return topLatitude;
	}

	public ProTourismPictureVO setTopLatitude(String topLatitude) {
		this.topLatitude = topLatitude;
		return this;
	}

	public String getLeftLongitude() {
		return leftLongitude;
	}

	public ProTourismPictureVO setLeftLongitude(String leftLongitude) {
		this.leftLongitude = leftLongitude;
		return this;
	}

	public String getLeftLatitude() {
		return leftLatitude;
	}

	public ProTourismPictureVO setLeftLatitude(String leftLatitude) {
		this.leftLatitude = leftLatitude;
		return this;
	}

	public String getRightLongitude() {
		return rightLongitude;
	}

	public ProTourismPictureVO setRightLongitude(String rightLongitude) {
		this.rightLongitude = rightLongitude;
		return this;
	}

	public String getRightLatitude() {
		return rightLatitude;
	}

	public ProTourismPictureVO setRightLatitude(String rightLatitude) {
		this.rightLatitude = rightLatitude;
		return this;
	}

	public String getButtomLongitude() {
		return buttomLongitude;
	}

	public ProTourismPictureVO setButtomLongitude(String buttomLongitude) {
		this.buttomLongitude = buttomLongitude;
		return this;
	}

	public String getButtomLatitude() {
		return buttomLatitude;
	}

	public ProTourismPictureVO setButtomLatitude(String buttomLatitude) {
		this.buttomLatitude = buttomLatitude;
		return this;
	}

	public String getAddress() {
		return address;
	}

	public ProTourismPictureVO setAddress(String address) {
		this.address = address;
		return this;
	}

	@Override
	public ProTourismPictureVO set(ProTourismPicturePO entity) throws Exception {
		this.setId(entity.getId())
		.setUuid(entity.getUuid())
		.setUpdateTime(entity.getUpdateTime())
		.setPid(entity.getProduct().getId())
		.setAddress(entity.getAddress())
		.setButtomLatitude(entity.getButtomLatitude())
		.setButtomLongitude(entity.getButtomLongitude())
		.setLeftLatitude(entity.getLeftLatitude())
		.setLeftLongitude(entity.getLeftLongitude())
		.setRightLatitude(entity.getRightLatitude())
		.setRightLongitude(entity.getRightLongitude())
		.setTopLatitude(entity.getTopLatitude())
		.setTopLongitude(entity.getTopLongitude());
		return this;
	}
	
}