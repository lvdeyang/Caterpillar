package com.guolaiwan.app.web.admin.vo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.guolaiwan.bussiness.admin.enumeration.ShopAuditStateType;
import com.guolaiwan.bussiness.admin.po.DistributorPO;
import com.guolaiwan.bussiness.admin.po.MerchantPO;
import com.guolaiwan.bussiness.admin.po.UserInfoPO;

import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class DistributorVO extends AbstractBaseVO<DistributorVO, DistributorPO> {

	// 商户名称 1
	private String shopName;
	// 登录名称1
	private String shopLoginName;
	// 登录密码 
	private String shopLoginPwd;
	// 头像图片地址
	private String shopHeading;
	// 企业资质图片地址
	private String shopQualifications;
	// QQ
	private String shopQQ;
	// 联系电话1
	private String shopTel;
	// 商户地址1
	private String shopAddress;
	// 银行卡号
	private String shopBankId;
	// 开户行
	private String shopOpenBank;
	// 联系人1
	private String shopLinkperson;
	// 显示图片
	private String shopPic;
	// 商户多图片
	private String shopMpic;
	// 商户简介
	private String shopIntroduction;
	// 坐标经度
	private String shopLongitude;
	// 坐标纬度
	private String shopLatitude;
	// 账户付款
	private Long shopAllMoney;
	// 账户验单
	private Long shopActualMoney;
	// 审核状态1
	//	private String shopAuditState;
	private ShopAuditStateType shopAuditState;
	// 审核意见1
	private String shopAuditopinion;

	// 城市标识
	private String cityCode;
	// 城市名
	private String cityName;
	//关联用户
	private UserInfoPO user;

	// 角色关联id
	private int roleId;
	
	//公司Id
	private long comId;
	//公司名称
	private String comName;
	//商品的经销商价格
	private String distributorPrice;
	
	private int check;
	
	//评分
	private int distributorScore;
	
	private String averagePrice;
	
	//普通商品的积分比率
	private long proportion;
	
	
	
	public int getCheck() {
		return check;
	}
	public void setCheck(int check) {
		this.check = check;
	}
	public String getShopName() {
		return shopName;
	}
	public DistributorVO setShopName(String shopName) {
		this.shopName = shopName;
		return this;
	}
	public String getShopLoginName() {
		return shopLoginName;
	}
	public DistributorVO setShopLoginName(String shopLoginName) {
		this.shopLoginName = shopLoginName;
		return this;
	}
	public String getShopLoginPwd() {
		return shopLoginPwd;
	}
	public DistributorVO setShopLoginPwd(String shopLoginPwd) {
		this.shopLoginPwd = shopLoginPwd;
		return this;
	}
	public String getShopHeading() {
		return shopHeading;
	}
	public DistributorVO setShopHeading(String shopHeading) {
		this.shopHeading = shopHeading;
		return this;
	}
	public String getShopQualifications() {
		return shopQualifications;
	}
	public DistributorVO setShopQualifications(String shopQualifications) {
		this.shopQualifications = shopQualifications;
		return this;
	}
	public String getShopQQ() {
		return shopQQ;
	}
	public DistributorVO setShopQQ(String shopQQ) {
		this.shopQQ = shopQQ;
		return this;
	}
	public String getShopTel() {
		return shopTel;
	}
	public DistributorVO setShopTel(String shopTel) {
		this.shopTel = shopTel;
		return this;
	}
	public String getShopAddress() {
		return shopAddress;
	}
	public DistributorVO setShopAddress(String shopAddress) {
		this.shopAddress = shopAddress;
		return this;
	}
	public String getShopBankId() {
		return shopBankId;
	}
	public DistributorVO setShopBankId(String shopBankId) {
		this.shopBankId = shopBankId;
		return this;
	}
	public String getShopOpenBank() {
		return shopOpenBank;
	}
	public DistributorVO setShopOpenBank(String shopOpenBank) {
		this.shopOpenBank = shopOpenBank;
		return this;
	}
	public String getShopLinkperson() {
		return shopLinkperson;
	}
	public DistributorVO setShopLinkperson(String shopLinkperson) {
		this.shopLinkperson = shopLinkperson;
		return this;
	}
	public String getShopPic() {
		return shopPic;
	}
	public DistributorVO setShopPic(String shopPic) {
		this.shopPic = shopPic;
		return this;
	}
	public String getShopMpic() {
		return shopMpic;
	}
	public DistributorVO setShopMpic(String shopMpic) {
		this.shopMpic = shopMpic;
		return this;
	}
	public String getShopIntroduction() {
		return shopIntroduction;
	}
	public DistributorVO setShopIntroduction(String shopIntroduction) {
		this.shopIntroduction = shopIntroduction;
		return this;
	}
	public String getShopLongitude() {
		return shopLongitude;
	}
	public DistributorVO setShopLongitude(String shopLongitude) {
		this.shopLongitude = shopLongitude;
		return this;
	}
	public String getShopLatitude() {
		return shopLatitude;
	}
	public DistributorVO setShopLatitude(String shopLatitude) {
		this.shopLatitude = shopLatitude;
		return this;
	}
	public Long getShopAllMoney() {
		return shopAllMoney;
	}
	public DistributorVO setShopAllMoney(Long shopAllMoney) {
		this.shopAllMoney = shopAllMoney;
		return this;
	}
	public Long getShopActualMoney() {
		return shopActualMoney;
	}
	public DistributorVO setShopActualMoney(Long shopActualMoney) {
		this.shopActualMoney = shopActualMoney;
		return this;
	}
	public ShopAuditStateType getShopAuditState() {
		return shopAuditState;
	}
	public DistributorVO setShopAuditState(ShopAuditStateType shopAuditState) {
		this.shopAuditState = shopAuditState;
		return this;
	}
	public String getShopAuditopinion() {
		return shopAuditopinion;
	}
	public DistributorVO setShopAuditopinion(String shopAuditopinion) {
		this.shopAuditopinion = shopAuditopinion;
		return this;
	}
	public String getCityCode() {
		return cityCode;
	}
	public DistributorVO setCityCode(String cityCode) {
		this.cityCode = cityCode;
		return this;
	}
	public String getCityName() {
		return cityName;
	}
	public DistributorVO setCityName(String cityName) {
		this.cityName = cityName;
		return this;
	}
	public UserInfoPO getUser() {
		return user;
	}
	public DistributorVO setUser(UserInfoPO user) {
		this.user = user;
		return this;
	}
	public int getRoleId() {
		return roleId;
	}
	public DistributorVO setRoleId(int roleId) {
		this.roleId = roleId;
		return this;
	}
	public long getComId() {
		return comId;
	}
	public DistributorVO setComId(long comId) {
		this.comId = comId;
		return this;
	}
	public String getComName() {
		return comName;
	}
	public DistributorVO setComName(String comName) {
		this.comName = comName;
		return this;
	}
	public int getDistributorScore() {
		return distributorScore;
	}
	public void setDistributorScore(int distributorScore) {
		this.distributorScore = distributorScore;
	}
	
	public String getAveragePrice() {
		return averagePrice;
	}
	public void setAveragePrice(String averagePrice) {
		this.averagePrice = averagePrice;
	}
	
	
	public String getDistributorPrice() {
		return distributorPrice;
	}
	public void setDistributorPrice(String distributorPrice) {
		this.distributorPrice = distributorPrice;
	}
	
	public long getProportion() {
		return proportion;
	}
	public DistributorVO setProportion(long proportion) {
		this.proportion = proportion;
		return this;
	}
	@Override
	public DistributorVO set(DistributorPO entity) throws Exception {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.setId(entity.getId())
		.setUpdateTime(df.format(entity.getUpdateTime()))
		.setUuid(entity.getUuid())
		.setShopName(entity.getShopName())
		.setShopLoginName(entity.getShopLoginName())
		.setShopLoginPwd(entity.getShopLoginPwd())
		.setShopHeading(entity.getShopHeading())
		.setShopQualifications(entity.getShopQualifications())
		.setShopQQ(entity.getShopQQ())
		.setShopTel(entity.getShopTel())
		.setShopAddress(entity.getShopAddress())
		.setShopBankId(entity.getShopBankId())
		.setShopOpenBank(entity.getShopOpenBank())
		.setShopLinkperson(entity.getShopLinkperson())
		.setShopPic(entity.getShopPic())
		.setShopMpic(entity.getShopMpic())
		.setShopIntroduction(entity.getShopIntroduction())
		.setShopLongitude(entity.getShopLongitude())
		.setShopLatitude(entity.getShopLatitude())
		.setShopAllMoney(entity.getShopAllMoney())
		.setShopActualMoney(entity.getShopActualMoney())
		.setShopAuditopinion(entity.getShopAuditopinion())
		.setCityCode(entity.getCityCode())
		.setCityName(entity.getCityName())
		.setComId(entity.getComId())
		.setComName(entity.getCityName())
		.setProportion(entity.getProportion());
		return this;
	}

}
