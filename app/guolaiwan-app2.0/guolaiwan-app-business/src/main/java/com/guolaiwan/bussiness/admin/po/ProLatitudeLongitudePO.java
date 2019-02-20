package com.guolaiwan.bussiness.admin.po;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import pub.caterpillar.orm.po.AbstractBasePO;

/**
 * 景区地图经纬度属性封装类
 * @author Liw
 */

@Entity
@Table(name = "tourism_latitudelongitude")
public class ProLatitudeLongitudePO extends AbstractBasePO {
	
	private static final long serialVersionUID = 1L;
	
	private String centerLongitude;//中心点经度
	
	private String centerLatitude;//中心点纬度
	
	private ProductPO product;//商品id
	
	private String topLongitude;//上点经度
	
	private String topLatitude;//上点纬度
	
	private String leftLongitude;//左点经度
	
	private String leftLatitude;//左点纬度
	
	private String rightLongitude;//右点经度
	
	private String rightLatitude;//右点纬度
	
	private String buttomLongitude;//下点经度
	
	private String buttomLatitude;//下点纬度

	@OneToOne
	@JoinColumn(name = "pid")
	public ProductPO getProduct() {
		return product;
	}

	public void setProduct(ProductPO product) {
		this.product = product;
	}

	public String getCenterLongitude() {
		return centerLongitude;
	}

	public void setCenterLongitude(String centerLongitude) {
		this.centerLongitude = centerLongitude;
	}

	public String getCenterLatitude() {
		return centerLatitude;
	}

	public void setCenterLatitude(String centerLatitude) {
		this.centerLatitude = centerLatitude;
	}

	public String getTopLongitude() {
		return topLongitude;
	}

	public void setTopLongitude(String topLongitude) {
		this.topLongitude = topLongitude;
	}

	public String getTopLatitude() {
		return topLatitude;
	}

	public void setTopLatitude(String topLatitude) {
		this.topLatitude = topLatitude;
	}

	public String getLeftLongitude() {
		return leftLongitude;
	}

	public void setLeftLongitude(String leftLongitude) {
		this.leftLongitude = leftLongitude;
	}

	public String getLeftLatitude() {
		return leftLatitude;
	}

	public void setLeftLatitude(String leftLatitude) {
		this.leftLatitude = leftLatitude;
	}

	public String getRightLongitude() {
		return rightLongitude;
	}

	public void setRightLongitude(String rightLongitude) {
		this.rightLongitude = rightLongitude;
	}

	public String getRightLatitude() {
		return rightLatitude;
	}

	public void setRightLatitude(String rightLatitude) {
		this.rightLatitude = rightLatitude;
	}

	public String getButtomLongitude() {
		return buttomLongitude;
	}

	public void setButtomLongitude(String buttomLongitude) {
		this.buttomLongitude = buttomLongitude;
	}

	public String getButtomLatitude() {
		return buttomLatitude;
	}

	public void setButtomLatitude(String buttomLatitude) {
		this.buttomLatitude = buttomLatitude;
	}
	
}