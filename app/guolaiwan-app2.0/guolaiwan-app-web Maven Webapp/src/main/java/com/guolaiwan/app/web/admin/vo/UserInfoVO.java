package com.guolaiwan.app.web.admin.vo;

import java.text.SimpleDateFormat;

import com.guolaiwan.bussiness.admin.po.AdminPO;
import com.guolaiwan.bussiness.admin.po.UserInfoPO;

import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class UserInfoVO extends AbstractBaseVO<UserInfoVO,UserInfoPO>{
	//用户手机号
    private String userPhone;
    
    //用户密码
    private String userPassword;
    
    //openID
    private String userOpenID;
    //积分数
    private long userIntegral;
    //用户头像
    private String userHeadimg;
    //昵称
    private String  userNickname;
    
    //真实姓名
    private String trueName;
    //工作单位
    private String companyName;
    
    private String childId;//景区导览点id
    
    private String activitypelId;//活动商品ID用来做限购
    
    public String getActivitypelId() {
		return activitypelId;
	}
	public UserInfoVO setActivitypelId(String activitypelId) {
		this.activitypelId = activitypelId;
		return this;
	}
	public String getChildId() {
		return childId;
	}
	public UserInfoVO setChildId(String childId) {
		this.childId = childId;
		return this;
	}
	public String getTrueName() {
		return trueName;
	}
	public UserInfoVO setTrueName(String trueName) {
		this.trueName = trueName;
		return this;
	}
	public String getCompanyName() {
		return companyName;
	}
	public UserInfoVO setCompanyName(String companyName) {
		this.companyName = companyName;
		return this;
	}
    
	public String getUserPhone() {
		return userPhone;
	}
	public UserInfoVO setUserPhone(String userPhone) {
		this.userPhone = userPhone;
		return this;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public UserInfoVO setUserPassword(String userPassword) {
		this.userPassword = userPassword;
		return this;
	}
	public String getUserOpenID() {
		return userOpenID;
	}
	public UserInfoVO setUserOpenID(String userOpenID) {
		this.userOpenID = userOpenID;
		return this;
	}

	public long getUserIntegral() {
		return userIntegral;
	}
	public UserInfoVO setUserIntegral(long userIntegral) {
		this.userIntegral = userIntegral;
		return this;
	}
	public String getUserHeadimg() {
		return userHeadimg;
	}
	public UserInfoVO setUserHeadimg(String userHeadimg) {
		this.userHeadimg = userHeadimg;
		return this;
	}
	public String getUserNickname() {
		return userNickname;
	}
	public UserInfoVO setUserNickname(String userNickname) {
		this.userNickname = userNickname;
		return this;
	}
	@Override
	public UserInfoVO set(UserInfoPO entity) throws Exception {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.setId(entity.getId())
			.setUuid(entity.getUuid())
			.setUpdateTime(formatter.format(entity.getUpdateTime()))
			.setUserPhone(entity.getUserPhone())
			.setUserPassword(entity.getUserPassword())
			.setUserOpenID(entity.getUserOpenID())
			.setUserIntegral(entity.getUserIntegral())
			.setUserHeadimg(entity.getUserHeadimg())
			.setUserNickname(entity.getUserNickname())
			.setTrueName(entity.getTrueName())
			.setChildId(entity.getChildId())
			.setCompanyName(entity.getCompanyName());
		return this;
	}
}
