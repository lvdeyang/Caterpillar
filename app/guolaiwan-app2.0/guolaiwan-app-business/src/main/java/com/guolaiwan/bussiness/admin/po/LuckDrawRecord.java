package com.guolaiwan.bussiness.admin.po;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.bytedeco.javacpp.RealSense.intrinsics;

import pub.caterpillar.orm.po.AbstractBasePO;
@Entity
@Table(name = "t_sys_luckdraw")
public class LuckDrawRecord extends AbstractBasePO {
    private String phone;
    private String userName;
    private long userId;
    private int drawProductId;//0未中奖，1电影票，2眼镜
    private int useit;//0未用1已用
    
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public int getDrawProductId() {
		return drawProductId;
	}
	public void setDrawProductId(int drawProductId) {
		this.drawProductId = drawProductId;
	}
	public int getUseit() {
		return useit;
	}
	public void setUseit(int useit) {
		this.useit = useit;
	}
    
	
}
