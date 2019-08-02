package com.guolaiwan.app.web.admin.vo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.guolaiwan.bussiness.admin.enumeration.MerchantSpecialBusiness;
import com.guolaiwan.bussiness.admin.po.MerchantPO;

import pub.caterpillar.commons.util.date.DateUtil;
import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class MerchantVO extends AbstractBaseVO<MerchantVO, MerchantPO> {

	// 商户名称
	private String shopName;
	// 登录名称
	private String shopLoginName;
	// 登录密码 
	private String shopLoginPwd;
	// 头像图片地址
	private String shopHeading;
	// 企业资质
	private String shopQualifications;
	// QQ
	private String shopQQ;
	// 联系电话
	private String shopTel;
	// 商户地址
	private String shopAddress;
	// 银行卡号
	private String shopBankId;
	// 开户行
	private String shopOpenBank;
	// 联系人
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
	// 审核状态
	private String shopAuditState;
	// 审核意见
	private String shopAuditopinion;
	// 板块名称
	private String modularName;
	// 板块Code
	private String modularCode;
	// 板块分类
	private String modularClass;
	// 板块分类Id
	private String modularClassId;
	// 城市标识
	private String cityCode;
	// 城市名
	private String cityName;
	//商品的数量
	private long productCount; 
	//公司Id
	private long comId;
	//公司名称
	private String comName;
	//最小价格
	private String minPrice;
	//最大价格
	private String maxPrice;
	//平均价格
	private String averagePrice;
	//分
	private int shopScore;
	
	private String business ;
	//标记点图片
	private String signPic ;
	
	//是否收藏0是不收藏1是收藏
	private int ifcollection;
	
	private long isGuide;//是否支持导览
	
	private String userName;//业务人员名字
	//客服
	private long chatUserId;
	
	//商户开放开始时间
    private String beginTimeDate;	
	
	//商户开放结束时间
    private String endTimeDate;
	
    //商户特色
    private String feature;
    
    //商户营业
    private String businessDate;
    
   //排序索引
  	private long productSortIndex;
    
    
	public long getProductSortIndex() {
		return productSortIndex;
	}


	public void setProductSortIndex(long productSortIndex) {
		this.productSortIndex = productSortIndex;
	}


	public String getFeature() {
		return feature;
	}


	public MerchantVO setFeature(String feature) {
		this.feature = feature;
		return this;
	}


	public String getBusinessDate() {
		return businessDate;
	}


	public MerchantVO setBusinessDate(String businessDate) {
		this.businessDate = businessDate;
		return this;
	}


	public String getBeginTimeDate() {
		return beginTimeDate;
	}


	public void setBeginTimeDate(String beginTimeDate) {
		this.beginTimeDate = beginTimeDate;
	}


	public String getEndTimeDate() {
		return endTimeDate;
	}


	public void setEndTimeDate(String endTimeDate) {
		this.endTimeDate = endTimeDate;
	}
	
	public long getChatUserId() {
		return chatUserId;
	}

	public void setChatUserId(long chatUserId) {
		this.chatUserId = chatUserId;
	}

	public String getUserName() {
		return userName;
	}

	public MerchantVO setUserName(String userName) {
		this.userName = userName;
		return this;
	}

	public long getIsGuide() {
		return isGuide;
	}

	public MerchantVO setIsGuide(long isGuide) {
		this.isGuide = isGuide;
		return this;
	}

	public int getIfcollection() {
		return ifcollection;
	}

	public void setIfcollection(int ifcollection) {
		this.ifcollection = ifcollection;
	}

	public String getCityCode() {
		return cityCode;
	}

	public MerchantVO setCityCode(String cityCode) {
		this.cityCode = cityCode;
		return this;
	}

	public String getCityName() {
		return cityName;
	}

	public MerchantVO setCityName(String cityName) {
		this.cityName = cityName;
		return this;
	}

	public String getShopName() {
		return shopName;
	}

	public MerchantVO setShopName(String shopName) {
		this.shopName = shopName;
		return this;
	}

	public String getShopLoginName() {
		return shopLoginName;
	}

	public MerchantVO setShopLoginName(String shopLoginName) {
		this.shopLoginName = shopLoginName;
		return this;
	}

	public String getShopLoginPwd() {
		return shopLoginPwd;
	}

	public MerchantVO setShopLoginPwd(String shopLoginPwd) {
		this.shopLoginPwd = shopLoginPwd;
		return this;
	}

	public String getShopHeading() {
		return shopHeading;
	}

	public MerchantVO setShopHeading(String shopHeading) {
		this.shopHeading = shopHeading;
		return this;
	}

	public String getShopQualifications() {
		return shopQualifications;
	}

	public MerchantVO setShopQualifications(String shopQualifications) {
		this.shopQualifications = shopQualifications;
		return this;
	}

	public String getShopQQ() {
		return shopQQ;
	}

	public MerchantVO setShopQQ(String shopQQ) {
		this.shopQQ = shopQQ;
		return this;
	}

	public String getShopTel() {
		return shopTel;
	}

	public MerchantVO setShopTel(String shopTel) {
		this.shopTel = shopTel;
		return this;
	}

	public String getShopAddress() {
		return shopAddress;
	}

	public MerchantVO setShopAddress(String shopAddress) {
		this.shopAddress = shopAddress;
		return this;
	}

	public String getShopBankId() {
		return shopBankId;
	}

	public MerchantVO setShopBankId(String shopBankId) {
		this.shopBankId = shopBankId;
		return this;
	}
	
	public String getShopOpenBank() {
		return shopOpenBank;
	}

	public MerchantVO setShopOpenBank(String shopOpenBank) {
		this.shopOpenBank = shopOpenBank;
		return this;
	}

	public String getShopLinkperson() {
		return shopLinkperson;
	}

	public MerchantVO setShopLinkperson(String shopLinkperson) {
		this.shopLinkperson = shopLinkperson;
		return this;
	}

	public String getShopPic() {
		return shopPic;
	}

	public MerchantVO setShopPic(String shopPic) {
		this.shopPic = shopPic;
		return this;
	}

	public String getShopMpic() {
		return shopMpic;
	}

	public MerchantVO setShopMpic(String shopMpic) {
		this.shopMpic = shopMpic;
		return this;
	}

	public String getShopIntroduction() {
		return shopIntroduction;
	}

	public MerchantVO setShopIntroduction(String shopIntroduction) {
		this.shopIntroduction = shopIntroduction;
		return this;
	}

	public String getShopLongitude() {
		return shopLongitude;
	}

	public MerchantVO setShopLongitude(String shopLongitude) {
		this.shopLongitude = shopLongitude;
		return this;
	}

	public String getShopLatitude() {
		return shopLatitude;
	}

	public MerchantVO setShopLatitude(String shopLatitude) {
		this.shopLatitude = shopLatitude;
		return this;
	}

	public Long getShopAllMoney() {
		return shopAllMoney;
	}

	public MerchantVO setShopAllMoney(Long shopAllMoney) {
		this.shopAllMoney = shopAllMoney;
		return this;
	}

	public Long getShopActualMoney() {
		return shopActualMoney;
	}

	public MerchantVO setShopActualMoney(Long shopActualMoney) {
		this.shopActualMoney = shopActualMoney;
		return this;
	}

	public String getShopAuditState() {
		return shopAuditState;
	}

	public MerchantVO setShopAuditState(String shopAuditState) {
		this.shopAuditState = shopAuditState;
		return this;
	}

	public String getShopAuditopinion() {
		return shopAuditopinion;
	}

	public MerchantVO setShopAuditopinion(String shopAuditopinion) {
		this.shopAuditopinion = shopAuditopinion;
		return this;
	}

	public String getModularName() {
		return modularName;
	}

	public MerchantVO setModularName(String modularName) {
		this.modularName = modularName;
		return this;
	}

	public String getModularCode() {
		return modularCode;
	}

	public MerchantVO setModularCode(String modularCode) {
		this.modularCode = modularCode;
		return this;
	}

	public String getModularClass() {
		return modularClass;
	}

	public MerchantVO setModularClass(String modularClass) {
		this.modularClass = modularClass;
		return this;
	}

	public String getModularClassId() {
		return modularClassId;
	}

	public MerchantVO setModularClassId(String modularClassId) {
		this.modularClassId = modularClassId;
		return this;
	}
	
	

	public long getProductCount() {
		return productCount;
	}

	public MerchantVO setProductCount(long productCount) {
		this.productCount = productCount;
		return this;
	}
	
	

	public long getComId() {
		return comId;
	}

	public MerchantVO setComId(long comId) {
		this.comId = comId;
		return this;
	}

	public String getComName() {
		return comName;
	}

	public MerchantVO setComName(String comName) {
		this.comName = comName;
		return this;
	}
	

	public String getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(String minPrice) {
		this.minPrice = minPrice;
	}

	public String getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(String maxPrice) {
		this.maxPrice = maxPrice;
	}
	

	public String getAveragePrice() {
		return averagePrice;
	}

	public MerchantVO setAveragePrice(String averagePrice) {
		this.averagePrice = averagePrice;
		return this;
	}
	

	public int getShopScore() {
		return shopScore;
	}

	public void setShopScore(int shopScore) {
		this.shopScore = shopScore;
	}
	
	

	public String getBusiness() {
		return business;
	}

	public MerchantVO setBusiness(String business) {
		this.business = business;
		return this;
	}
	
	public String getSignPic() {
		return signPic;
	}

	public MerchantVO setSignPic(String signPic) {
		this.signPic = signPic;
		return this;
	}

	@Override
	public MerchantVO set(MerchantPO entity) throws Exception {
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
		.setShopAuditState(entity.getShopAuditState().getName())
		.setShopAuditopinion(entity.getShopAuditopinion())
		.setModularName(entity.getModularName())
		.setModularCode(entity.getModularCode())
		.setModularClass(entity.getModularClass())
		.setModularClassId(entity.getModularClassId())
		.setModularName1(entity.getModularName1())
		.setModularCode1(entity.getModularCode1())
		.setModularClass1(entity.getModularClass1())
		.setFeature(entity.getFeature())
		.setBusinessDate(entity.getBusinessDate())
		.setModularClassId1(entity.getModularClassId1())
		.setModularName2(entity.getModularName2())
		.setModularCode2(entity.getModularCode2())
		.setModularClass2(entity.getModularClass2())
		.setModularClassId2(entity.getModularClassId2())
		.setCityCode(entity.getCityCode())
		.setCityName(entity.getCityName())
		.setComId(entity.getComId())
		.setComName(entity.getCityName())
		.setBusiness(entity.getBusiness().toString())
		.setAveragePrice(entity.getAveragePrice())
		.setUserName(entity.getUserName())
		.setIsGuide(entity.getIsGuide())
		.setSignPic(entity.getSignPic())
		.setChatUserId(entity.getChatUserId());
		return this;
	}
	
	
	// 板块名称
	private String modularName1;
	// 板块Code
	private String modularCode1;
	// 板块分类
	private String modularClass1;
	// 板块分类Id
	private String modularClassId1;
	// 板块名称
	private String modularName2;
	// 板块Code
	private String modularCode2;
	// 板块分类
	private String modularClass2;
	// 板块分类Id
	private String modularClassId2;

	public String getModularName1() {
		return modularName1;
	}

	public MerchantVO setModularName1(String modularName1) {
		this.modularName1 = modularName1;
		return this;
	}

	public String getModularCode1() {
		return modularCode1;
	}

	public MerchantVO setModularCode1(String modularCode1) {
		this.modularCode1 = modularCode1;
		return this;
	}

	public String getModularClass1() {
		return modularClass1;
	}

	public MerchantVO setModularClass1(String modularClass1) {
		this.modularClass1 = modularClass1;
		return this;
	}

	public String getModularClassId1() {
		return modularClassId1;
	}

	public MerchantVO setModularClassId1(String modularClassId1) {
		this.modularClassId1 = modularClassId1;
		return this;
	}

	public String getModularName2() {
		return modularName2;
	}

	public MerchantVO setModularName2(String modularName2) {
		this.modularName2 = modularName2;
		return this;
	}

	public String getModularCode2() {
		return modularCode2;
	}

	public MerchantVO setModularCode2(String modularCode2) {
		this.modularCode2 = modularCode2;
		return this;
	}

	public String getModularClass2() {
		return modularClass2;
	}

	public MerchantVO setModularClass2(String modularClass2) {
		this.modularClass2 = modularClass2;
		return this;
	}

	public String getModularClassId2() {
		return modularClassId2;
	}

	public MerchantVO setModularClassId2(String modularClassId2) {
		this.modularClassId2 = modularClassId2;
		return this;
	}
	
	

}
