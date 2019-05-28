package com.guolaiwan.bussiness.admin.po;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import pub.caterpillar.orm.po.AbstractBasePO;

@Entity
@Table(name = "t_sys_userinfo")
public class UserInfoPO extends AbstractBasePO {
	
	private static final long serialVersionUID = 1L;
	
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
    //来源(0是网页注册，1是App注册)
    private Integer source;
    //关联商户
    private MerchantPO merchant;
    // 关联分销商
    private DistributorPO distributor;
    //评论
    private List<CommentPO> comments;
    //收藏
    private List<CollectionPO> collections;
    //真实姓名
    private String trueName;
    //工作单位
    private String companyName;
    
    private String childId;//景区导览点id
    
    private int firstTime;//0表示是，1表示不是
    //钱包余额
    private long wallet;
    
    
	public long getWallet() {
		return wallet;
	}
	public void setWallet(long wallet) {
		this.wallet = wallet;
	}
	public String getChildId() {
		return childId;
	}
	public void setChildId(String childId) {
		this.childId = childId;
	}
	public String getTrueName() {
		return trueName;
	}
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
    
    @OneToOne(mappedBy="user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = false)
    public DistributorPO getDistributor() {
		return distributor;
	}
	public void setDistributor(DistributorPO distributor) {
		this.distributor = distributor;
	}
	@OneToOne(mappedBy="user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = false)
    public MerchantPO getMerchant() {
		return merchant;
	}
	public void setMerchant(MerchantPO merchant) {
		this.merchant = merchant;
	}
	   
	
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserOpenID() {
		return userOpenID;
	}
	public void setUserOpenID(String userOpenID) {
		this.userOpenID = userOpenID;
	}

	public long getUserIntegral() {
		return userIntegral;
	}
	public void setUserIntegral(long userIntegral) {
		this.userIntegral = userIntegral;
	}
	public String getUserHeadimg() {
		return userHeadimg;
	}
	public void setUserHeadimg(String userHeadimg) {
		this.userHeadimg = userHeadimg;
	}
	public String getUserNickname() {
		return userNickname;
	}
	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}
	
	@OneToMany(mappedBy="user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = false)
	public List<CommentPO> getComments() {
		return comments;
	}
	public void setComments(List<CommentPO> comments) {
		this.comments = comments;
	}
	public Integer getSource() {
		return source;
	}
	public void setSource(Integer source) {
		this.source = source;
	}
	
	@OneToMany(mappedBy="user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = false)
	public List<CollectionPO> getCollections() {
		return collections;
	}
	public void setCollections(List<CollectionPO> collections) {
		this.collections = collections;
	}
	public int getFirstTime() {
		return firstTime;
	}
	public void setFirstTime(int firstTime) {
		this.firstTime = firstTime;
	}
	
	
	
	
}
	