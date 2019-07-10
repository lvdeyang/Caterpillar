package com.chenxi.web.yueba.admin.po;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.chenxi.web.classes.WorkerStatus;
import com.chenxi.web.classes.Moudular;

import pub.caterpillar.orm.po.AbstractBasePO;
@Entity
@Table(name = "worker")
public class WorkerPo extends AbstractBasePO{
	private String userName;
	private String openId;
	private String realName;
	private String photo;
	private String idCard;
	private String idCardPhoto;
	private String healthPhoto;
	private String expertPhoto;
	private double price;
	private String address;
	private int age;
	private String phone;
	private WorkerStatus status;
	private String checkMsg;
	private String lan;//位置信息，经纬度
	private String lon;
	private int ration;//百分比
	private int orderCount;
	private int baseOrderCount;
	private String level;
	private String moreMsg;
	private int sindex;
	

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getIdCardPhoto() {
		return idCardPhoto;
	}
	public void setIdCardPhoto(String idCardPhoto) {
		this.idCardPhoto = idCardPhoto;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Enumerated(EnumType.STRING)
	public WorkerStatus getStatus() {
		return status;
	}
	public void setStatus(WorkerStatus status) {
		this.status = status;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCheckMsg() {
		return checkMsg;
	}
	public void setCheckMsg(String checkMsg) {
		this.checkMsg = checkMsg;
	}
	public String getHealthPhoto() {
		return healthPhoto;
	}
	public void setHealthPhoto(String healthPhoto) {
		this.healthPhoto = healthPhoto;
	}
	public String getExpertPhoto() {
		return expertPhoto;
	}
	public void setExpertPhoto(String expertPhoto) {
		this.expertPhoto = expertPhoto;
	}
	public String getLan() {
		return lan;
	}
	public void setLan(String lan) {
		this.lan = lan;
	}
	public String getLon() {
		return lon;
	}
	public void setLon(String lon) {
		this.lon = lon;
	}
	public int getRation() {
		return ration;
	}
	public void setRation(int ration) {
		this.ration = ration;
	}
	@Transient
	public int getOrderCount() {
		return orderCount;
	}
	public void setOrderCount(int orderCount) {
		this.orderCount = orderCount;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getMoreMsg() {
		return moreMsg;
	}
	public void setMoreMsg(String moreMsg) {
		this.moreMsg = moreMsg;
	}
	public int getBaseOrderCount() {
		return baseOrderCount;
	}
	public void setBaseOrderCount(int baseOrderCount) {
		this.baseOrderCount = baseOrderCount;
	}
	public int getSindex() {
		return sindex;
	}
	public void setSindex(int sindex) {
		this.sindex = sindex;
	}
	
	
}
