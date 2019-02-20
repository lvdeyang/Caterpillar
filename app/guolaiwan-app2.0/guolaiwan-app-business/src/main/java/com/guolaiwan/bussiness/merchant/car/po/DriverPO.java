package com.guolaiwan.bussiness.merchant.car.po;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.guolaiwan.bussiness.admin.po.MerchantPO;

import pub.caterpillar.orm.po.AbstractBasePO;

@Entity
@Table(name="t_Sys_car_Driver")
public class DriverPO extends AbstractBasePO{
	//正面照片
	private String photo;
	//真实姓名
	private String name; 
	//身份证号
    private String iDnumber;
	//行驶本正面照
    private String drivingBook;
	//农行卡号
    private String bankNumber;
	//车牌号码
	private String carNumber;
    //车辆型号
	private String carModel;
	//载客数量
	private int passenger;
	//绑定商户
	private MerchantPO merchant;
	
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getiDnumber() {
		return iDnumber;
	}
	public void setiDnumber(String iDnumber) {
		this.iDnumber = iDnumber;
	}
	public String getDrivingBook() {
		return drivingBook;
	}
	public void setDrivingBook(String drivingBook) {
		this.drivingBook = drivingBook;
	}
	public String getBankNumber() {
		return bankNumber;
	}
	public void setBankNumber(String bankNumber) {
		this.bankNumber = bankNumber;
	}
	public String getCarNumber() {
		return carNumber;
	}
	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}
	public String getCarModel() {
		return carModel;
	}
	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}
	public int getPassenger() {
		return passenger;
	}
	public void setPassenger(int passenger) {
		this.passenger = passenger;
	}
	
	@ManyToOne
	@JoinColumn(name = "merchant_id")
	public MerchantPO getMerchant() {
		return merchant;
	}
	public void setMerchant(MerchantPO merchant) {
		this.merchant = merchant;
	}

}
