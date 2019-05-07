package com.guolaiwan.bussiness.admin.po;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.guolaiwan.bussiness.admin.enumeration.MerchantSpecialBusiness;
import com.guolaiwan.bussiness.admin.enumeration.ShopAuditStateType;
import com.guolaiwan.bussiness.merchant.car.po.DriverPO;
import com.guolaiwan.bussiness.merchant.car.po.RoutePO;

import pub.caterpillar.orm.po.AbstractBasePO;

/**
 * 
 * <p>Title: Merchant</p>
 * <p>Description: 商户PO</p>
 * <p>Company: guolaiwan</p> 
 * @author Mr.Sun
 * @date 2017年11月27日 下午1:14:32
 */

@Entity
@Table(name = "t_sys_merchant")
public class MerchantPO extends AbstractBasePO {

	private static final long serialVersionUID = 1L;

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
	// 板块名称1
	private String modularName;
	// 板块Code
	private String modularCode;
	// 板块分类1
	private String modularClass;
	// 板块分类Id
	private String modularClassId;
	
	
	// 板块名称1
	private String modularName1;
	// 板块Code
	private String modularCode1;
	// 板块分类1
	private String modularClass1;
	// 板块分类Id
	private String modularClassId1;
	
	
	// 板块名称1
	private String modularName2;
	// 板块Code
	private String modularCode2;
	// 板块分类1
	private String modularClass2;
	// 板块分类Id
	private String modularClassId2;
	

	// 城市标识
	private String cityCode;
	// 城市名
	private String cityName;
	//关联用户
	private UserInfoPO user;
	//关联司机
	private List<DriverPO> drivers; 
	//关联路线
	private List<RoutePO> routes;

	//关联路线
	private List<MerModularPO> merModulars;
	// 角色关联idclasscd
	private int roleId;

	//公司Id
	private long comId;
	//公司名称
	private String comName; 
	//标记点图片
	private String signPic; 

	//人均消费
	private String averagePrice;
	
	
	// 导览贴图X经度
	private String Xlongitude;
	// 导览贴图X纬度
	private String Xlatitude;
	
	// 导览贴图Y经度
	private String Ylongitude;
	// 导览贴图y纬度
	private String Ylatitude;
	//导览图片
	private String chartletPictures;
	//导览  初始显示位置经度
	private String  locationLongitude;
	//导览  初始显示位置经度
	private String  locationLatitude;
	
	private long isGuide;//是否支持导览
	
	private String userName;//业务人员名字
	
	private int shopyd=1;
	
	
	
	
	
	
	
	
	
	
	

	public String getXlongitude() {
		return Xlongitude;
	}

	public void setXlongitude(String xlongitude) {
		Xlongitude = xlongitude;
	}

	public String getXlatitude() {
		return Xlatitude;
	}

	public void setXlatitude(String xlatitude) {
		Xlatitude = xlatitude;
	}

	public String getYlongitude() {
		return Ylongitude;
	}

	public void setYlongitude(String ylongitude) {
		Ylongitude = ylongitude;
	}

	public String getYlatitude() {
		return Ylatitude;
	}

	public void setYlatitude(String ylatitude) {
		Ylatitude = ylatitude;
	}

	public String getLocationLongitude() {
		return locationLongitude;
	}

	public void setLocationLongitude(String locationLongitude) {
		this.locationLongitude = locationLongitude;
	}

	public String getLocationLatitude() {
		return locationLatitude;
	}

	public void setLocationLatitude(String locationLatitude) {
		this.locationLatitude = locationLatitude;
	}

	public String getChartletPictures() {
		return chartletPictures;
	}

	public void setChartletPictures(String chartletPictures) {
		this.chartletPictures = chartletPictures;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public long getIsGuide() {
		return isGuide;
	}

	public void setIsGuide(long isGuide) {
		this.isGuide = isGuide;
	}

	public String getAveragePrice() {
		return averagePrice;
	}

	public void setAveragePrice(String averagePrice) {
		this.averagePrice = averagePrice;
	}

	/** 特殊的商户业务  默认是基本业务，商户：产品模式*/
	private MerchantSpecialBusiness business;

	@OneToOne
	@JoinColumn(name = "user_id")
	public UserInfoPO getUser() {
		return user;
	}

	public void setUser(UserInfoPO user) {
		this.user = user;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getShopLoginName() {
		return shopLoginName;
	}

	public void setShopLoginName(String shopLoginName) {
		this.shopLoginName = shopLoginName;
	}

	public String getShopLoginPwd() {
		return shopLoginPwd;
	}

	public void setShopLoginPwd(String shopLoginPwd) {
		this.shopLoginPwd = shopLoginPwd;
	}

	public String getShopHeading() {
		return shopHeading;
	}

	public void setShopHeading(String shopHeading) {
		this.shopHeading = shopHeading;
	}

	public String getShopQualifications() {
		return shopQualifications;
	}

	public void setShopQualifications(String shopQualifications) {
		this.shopQualifications = shopQualifications;
	}

	public String getShopQQ() {
		return shopQQ;
	}

	public void setShopQQ(String shopQQ) {
		this.shopQQ = shopQQ;
	}

	public String getShopTel() {
		return shopTel;
	}

	public void setShopTel(String shopTel) {
		this.shopTel = shopTel;
	}

	public String getShopAddress() {
		return shopAddress;
	}

	public void setShopAddress(String shopAddress) {
		this.shopAddress = shopAddress;
	}

	public String getShopBankId() {
		return shopBankId;
	}

	public void setShopBankId(String shopBankId) {
		this.shopBankId = shopBankId;
	}

	public String getShopOpenBank() {
		return shopOpenBank;
	}

	public void setShopOpenBank(String shopOpenBank) {
		this.shopOpenBank = shopOpenBank;
	}

	public String getShopLinkperson() {
		return shopLinkperson;
	}

	public void setShopLinkperson(String shopLinkperson) {
		this.shopLinkperson = shopLinkperson;
	}

	public String getShopPic() {
		return shopPic;
	}

	public void setShopPic(String shopPic) {
		this.shopPic = shopPic;
	}

	public String getShopMpic() {
		return shopMpic;
	}

	public void setShopMpic(String shopMpic) {
		this.shopMpic = shopMpic;
	}

	public String getShopIntroduction() {
		return shopIntroduction;
	}

	public void setShopIntroduction(String shopIntroduction) {
		this.shopIntroduction = shopIntroduction;
	}

	public String getShopLongitude() {
		return shopLongitude;
	}

	public void setShopLongitude(String shopLongitude) {
		this.shopLongitude = shopLongitude;
	}

	public String getShopLatitude() {
		return shopLatitude;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public void setShopLatitude(String shopLatitude) {
		this.shopLatitude = shopLatitude;
	}

	public Long getShopAllMoney() {
		return shopAllMoney;
	}

	public void setShopAllMoney(Long shopAllMoney) {
		this.shopAllMoney = shopAllMoney;
	}

	public Long getShopActualMoney() {
		return shopActualMoney;
	}

	public void setShopActualMoney(Long shopActualMoney) {
		this.shopActualMoney = shopActualMoney;
	}

	@Enumerated(EnumType.STRING)
	public ShopAuditStateType getShopAuditState() {
		return shopAuditState;
	}

	public void setShopAuditState(ShopAuditStateType shopAuditState) {
		this.shopAuditState = shopAuditState;
	}

	/*public String getShopAuditState() {
		return shopAuditState;
	}
	public void setShopAuditState(String shopAuditState) {
		this.shopAuditState = shopAuditState;
	}*/

	public String getShopAuditopinion() {
		return shopAuditopinion;
	}

	public void setShopAuditopinion(String shopAuditopinion) {
		this.shopAuditopinion = shopAuditopinion;
	}

	public String getModularName() {
		return modularName;
	}

	public void setModularName(String modularName) {
		this.modularName = modularName;
	}

	public String getModularCode() {
		return modularCode;
	}

	public void setModularCode(String modularCode) {
		this.modularCode = modularCode;
	}

	public String getModularClass() {
		return modularClass;
	}

	public void setModularClass(String modularClass) {
		this.modularClass = modularClass;
	}

	public String getModularClassId() {
		return modularClassId;
	}

	public void setModularClassId(String modularClassId) {
		this.modularClassId = modularClassId;
	}

	@OneToMany(mappedBy="merchant", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = false)
	public List<DriverPO> getDrivers() {
		return drivers;
	}

	public void setDrivers(List<DriverPO> drivers) {
		this.drivers = drivers;
	}

	@OneToMany(mappedBy="merchant", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = false)
	public List<RoutePO> getRoutes() {
		return routes;
	}

	public void setRoutes(List<RoutePO> routes) {
		this.routes = routes;
	}

	@OneToMany(mappedBy="merchant", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = false)
	@OrderBy("level ASC")
	public List<MerModularPO> getMerModulars() {
		return merModulars;
	}

	public void setMerModulars(List<MerModularPO> merModulars) {
		this.merModulars = merModulars;
	}

	public long getComId() {
		return comId;
	}

	public void setComId(long comId) {
		this.comId = comId;
	}

	public String getComName() {
		return comName;
	}

	public void setComName(String comName) {
		this.comName = comName;
	}

	/** 特殊的商户业务  默认是基本业务，商户：产品模式*/
	@Enumerated(EnumType.STRING)
	public MerchantSpecialBusiness getBusiness() {
		return business;
	}

	public void setBusiness(MerchantSpecialBusiness business) {
		this.business = business;
	}

	public String getSignPic() {
		return signPic;
	}

	public void setSignPic(String signPic) {
		this.signPic = signPic;
	}

	public int getShopyd() {
		return shopyd;
	}

	public void setShopyd(int shopyd) {
		this.shopyd = shopyd;
	}

	public String getModularName1() {
		return modularName1;
	}

	public void setModularName1(String modularName1) {
		this.modularName1 = modularName1;
	}

	public String getModularCode1() {
		return modularCode1;
	}

	public void setModularCode1(String modularCode1) {
		this.modularCode1 = modularCode1;
	}

	public String getModularClass1() {
		return modularClass1;
	}

	public void setModularClass1(String modularClass1) {
		this.modularClass1 = modularClass1;
	}

	public String getModularClassId1() {
		return modularClassId1;
	}

	public void setModularClassId1(String modularClassId1) {
		this.modularClassId1 = modularClassId1;
	}

	public String getModularName2() {
		return modularName2;
	}

	public void setModularName2(String modularName2) {
		this.modularName2 = modularName2;
	}

	public String getModularCode2() {
		return modularCode2;
	}

	public void setModularCode2(String modularCode2) {
		this.modularCode2 = modularCode2;
	}

	public String getModularClass2() {
		return modularClass2;
	}

	public void setModularClass2(String modularClass2) {
		this.modularClass2 = modularClass2;
	}

	public String getModularClassId2() {
		return modularClassId2;
	}

	public void setModularClassId2(String modularClassId2) {
		this.modularClassId2 = modularClassId2;
	}
	
	
	

}
