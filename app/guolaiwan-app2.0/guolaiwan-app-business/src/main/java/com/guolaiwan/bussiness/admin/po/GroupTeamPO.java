package com.guolaiwan.bussiness.admin.po;


import javax.persistence.Entity;
import javax.persistence.Table;
import pub.caterpillar.orm.po.AbstractBasePO;

@Entity
@Table(name = "t_sys_groupteam")
public class GroupTeamPO extends AbstractBasePO {

	private static final long serialVersionUID = 8185751348556679850L;
	//用户id
	private long userid;
	//用户昵称
	private String usernickname;
	//用户头像
	private String userheadimg;
	//是否是队长
	private boolean iscaptain;
	//队长id
	private	long captain;
	//拼团规则id
	private	long groupbuyid;
	//拼团商品id
	private long productid;
	//拼团时间限制
	private int grouptime;
	
	
	public int getGrouptime() {
		return grouptime;
	}
	public void setGrouptime(int grouptime) {
		this.grouptime = grouptime;
	}
	public String getUsernickname() {
		return usernickname;
	}
	public void setUsernickname(String usernickname) {
		this.usernickname = usernickname;
	}
	public String getUserheadimg() {
		return userheadimg;
	}
	public void setUserheadimg(String userheadimg) {
		this.userheadimg = userheadimg;
	}
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
	public boolean isIscaptain() {
		return iscaptain;
	}
	public void setIscaptain(boolean iscaptain) {
		this.iscaptain = iscaptain;
	}
	public long getCaptain() {
		return captain;
	}
	public void setCaptain(long captain) {
		this.captain = captain;
	}
	public long getGroupbuyid() {
		return groupbuyid;
	}
	public void setGroupbuyid(long groupbuyid) {
		this.groupbuyid = groupbuyid;
	}
	public long getProductid() {
		return productid;
	}
	public void setProductid(long productid) {
		this.productid = productid;
	}

	
	
	
}
