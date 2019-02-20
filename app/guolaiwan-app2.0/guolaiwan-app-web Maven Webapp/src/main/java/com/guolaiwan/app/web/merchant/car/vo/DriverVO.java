package com.guolaiwan.app.web.merchant.car.vo;

import com.guolaiwan.bussiness.merchant.car.po.DriverPO;

import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class DriverVO extends AbstractBaseVO<DriverVO,DriverPO>{
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
	public String getPhoto() {
		return photo;
	}
	public DriverVO setPhoto(String photo) {
		this.photo = photo;
		return this;
	}
	public String getName() {
		return name;
	}
	public DriverVO setName(String name) {
		this.name = name;
		return this;
	}
	
	public String getiDnumber() {
		return iDnumber;
	}
	public DriverVO setiDnumber(String iDnumber) {
		this.iDnumber = iDnumber;
		return this;
	}
	public String getDrivingBook() {
		return drivingBook;
	}
	public DriverVO setDrivingBook(String drivingBook) {
		this.drivingBook = drivingBook;
		return this;
	}
	public String getBankNumber() {
		return bankNumber;
	}
	public DriverVO setBankNumber(String bankNumber) {
		this.bankNumber = bankNumber;
		return this;
	}
	public String getCarNumber() {
		return carNumber;
	}
	public DriverVO setCarNumber(String carNumber) {
		this.carNumber = carNumber;
		return this;
	}
	public String getCarModel() {
		return carModel;
	}
	public DriverVO setCarModel(String carModel) {
		this.carModel = carModel;
		return this;
	}
	public int getPassenger() {
		return passenger;
	}
	public DriverVO setPassenger(int passenger) {
		this.passenger = passenger;
		return this;
	}
     /* (non-Javadoc)
     * @see pub.caterpillar.mvc.converter.AbstractBaseVO#set(java.lang.Object)
     */
    @Override
     public DriverVO set(DriverPO entity)throws Exception{
    	 this.setId(entity.getId())
    	 	 .setUuid(entity.getUuid())
    	     .setPhoto(entity.getPhoto())
    	     .setName(entity.getName())
    	     .setiDnumber(entity.getiDnumber())
    	     .setDrivingBook(entity.getDrivingBook())
    	     .setBankNumber(entity.getBankNumber())
    	     .setCarNumber(entity.getCarNumber())
    	     .setCarModel(entity.getCarModel())
    	     .setPassenger(entity.getPassenger());
    	 return this;
    	     
     }
}

