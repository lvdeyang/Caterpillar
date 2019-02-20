package com.guolaiwan.bussiness.admin.dto;

import java.util.Date;

import com.guolaiwan.bussiness.admin.enumeration.ProductSaleType;
import com.guolaiwan.bussiness.admin.enumeration.ShopAuditStateType;


public class ProductDTO {

	//id
	private long id;
	//uuid
	private String uuid;
	//update
	private Date updateTime;
	//绑定模块
	private String productModularCode;

	//绑定模块名称
	private String productModularCodeName;


	//分类标识
	private String productClassCode; 

	//分类名称
	private String productClassName;

	//商户ID
	private long productMerchantID;

	//商户名称
	private String productMerchantName;

	//商品名称
	private String productName;

	//产品副标题
	private String productSubtitle;

	//开始时间
	private Date productBeginDate;

	//结束时间
	private Date productEnddate;

	//有效期
	private Date productEctivedate;

	//原价(分)s
	private long productOldPrice;

	//现价（分）
	private long productPrice;

	//原价(分)s
	private String productOldPriceStr;

	//现价（分）
	private String productPricesStr;

	//提成模式（0：佣金1：比例）
	private int productCommissionCode;

	//提成金额（佣金：分。比例百分比：10，20。。）
	private long productCommissionPrice;

	private String productCommissionPriceStr;

	//前端显示图片（路径）
	private String productShowPic;

	//产品多图显示（路径）,
	private String productMorePic;

	//首页/平台推荐（0，1）
	private int productIndexRecommend; 

	//列表/右侧推荐（0，1）
	private int productListRecommend;

	//浏览量
	private long productShowNum;

	//销量
	private long productSaleNum;

	//排序
	private long productSort;

	//审核状态(初审：T复审：R)
	private ShopAuditStateType productAuditstatus;

	//审核意见
	private String productAuditAdvice;

	//审核意见
	private String productMerchantJson;

	//库存
	private long productStock;

	//是否限购（0，1）
	private int productLimitType;

	//限购数量
	private long productLimitNum;

	private String productIntroduce;

	//兑换积分
	private long productntegral;

	//是否显示
	private int productIsShow;

	//城市编码
	private String productCityCode;

	//城市名称
	private String productCityName;

	//商家分类Id
	private long merMId;

	//商家分类
	private String merMName;

	//公司Id
	private long comId;
	//公司名称
	private String comName;  
	//产品销售类型
	private ProductSaleType psType; 
	//金币数
	private long goldNum; 
	//快递费
	private long sent;

	//评分
	private int productScore;

	// 坐标经度
	private String shopLongitude;
	// 坐标纬度
	private String shopLatitude;
	// 经销商进价
	private long distributorPrice;
	// 经销商售价
	private long sellPrice;
	// 经销商品Id
	private String distributorProductUUID;

	// 经销商进价
	private String distributorPriceStr;
	// 经销商售价
	private String sellPriceStr;

	private String activityName;//活动名称
	
	private long activityId;//活动id
	
	private String nowDate;//服务器当前时间
	
	public String getNowDate() {
		return nowDate;
	}
	public void setNowDate(String nowDate) {
		this.nowDate = nowDate;
	}
	public long getActivityId() {
		return activityId;
	}
	public void setActivityId(long activityId) {
		this.activityId = activityId;
	}
	public String getActivityName() {
		return activityName;
	}
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getProductModularCode() {
		return productModularCode;
	}
	public void setProductModularCode(String productModularCode) {
		this.productModularCode = productModularCode;
	}
	public String getProductModularCodeName() {
		return productModularCodeName;
	}
	public void setProductModularCodeName(String productModularCodeName) {
		this.productModularCodeName = productModularCodeName;
	}
	public String getProductClassCode() {
		return productClassCode;
	}
	public void setProductClassCode(String productClassCode) {
		this.productClassCode = productClassCode;
	}
	public String getProductClassName() {
		return productClassName;
	}
	public void setProductClassName(String productClassName) {
		this.productClassName = productClassName;
	}
	public long getProductMerchantID() {
		return productMerchantID;
	}
	public void setProductMerchantID(long productMerchantID) {
		this.productMerchantID = productMerchantID;
	}
	public String getProductMerchantName() {
		return productMerchantName;
	}
	public void setProductMerchantName(String productMerchantName) {
		this.productMerchantName = productMerchantName;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductSubtitle() {
		return productSubtitle;
	}
	public void setProductSubtitle(String productSubtitle) {
		this.productSubtitle = productSubtitle;
	}
	public Date getProductBeginDate() {
		return productBeginDate;
	}
	public void setProductBeginDate(Date productBeginDate) {
		this.productBeginDate = productBeginDate;
	}
	public Date getProductEnddate() {
		return productEnddate;
	}
	public void setProductEnddate(Date productEnddate) {
		this.productEnddate = productEnddate;
	}
	public Date getProductEctivedate() {
		return productEctivedate;
	}
	public void setProductEctivedate(Date productEctivedate) {
		this.productEctivedate = productEctivedate;
	}

	public int getProductCommissionCode() {
		return productCommissionCode;
	}
	public void setProductCommissionCode(int productCommissionCode) {
		this.productCommissionCode = productCommissionCode;
	}

	public String getProductShowPic() {
		return productShowPic;
	}
	public void setProductShowPic(String productShowPic) {
		this.productShowPic = productShowPic;
	}
	public String getProductMorePic() {
		return productMorePic;
	}
	public void setProductMorePic(String productMorePic) {
		this.productMorePic = productMorePic;
	}
	public int getProductIndexRecommend() {
		return productIndexRecommend;
	}
	public void setProductIndexRecommend(int productIndexRecommend) {
		this.productIndexRecommend = productIndexRecommend;
	}
	public int getProductListRecommend() {
		return productListRecommend;
	}
	public void setProductListRecommend(int productListRecommend) {
		this.productListRecommend = productListRecommend;
	}
	public long getProductShowNum() {
		return productShowNum;
	}
	public void setProductShowNum(long productShowNum) {
		this.productShowNum = productShowNum;
	}
	public long getProductSaleNum() {
		return productSaleNum;
	}
	public void setProductSaleNum(long productSaleNum) {
		this.productSaleNum = productSaleNum;
	}
	public long getProductSort() {
		return productSort;
	}
	public void setProductSort(long productSort) {
		this.productSort = productSort;
	}
	public ShopAuditStateType getProductAuditstatus() {
		return productAuditstatus;
	}
	public void setProductAuditstatus(ShopAuditStateType productAuditstatus) {
		this.productAuditstatus = productAuditstatus;
	}
	public String getProductAuditAdvice() {
		return productAuditAdvice;
	}
	public void setProductAuditAdvice(String productAuditAdvice) {
		this.productAuditAdvice = productAuditAdvice;
	}
	public String getProductMerchantJson() {
		return productMerchantJson;
	}
	public void setProductMerchantJson(String productMerchantJson) {
		this.productMerchantJson = productMerchantJson;
	}
	public long getProductStock() {
		return productStock;
	}
	public void setProductStock(long productStock) {
		this.productStock = productStock;
	}
	public int getProductLimitType() {
		return productLimitType;
	}
	public void setProductLimitType(int productLimitType) {
		this.productLimitType = productLimitType;
	}
	public long getProductLimitNum() {
		return productLimitNum;
	}
	public void setProductLimitNum(long productLimitNum) {
		this.productLimitNum = productLimitNum;
	}
	public String getProductIntroduce() {
		return productIntroduce;
	}
	public void setProductIntroduce(String productIntroduce) {
		this.productIntroduce = productIntroduce;
	}
	public long getProductntegral() {
		return productntegral;
	}
	public void setProductntegral(long productntegral) {
		this.productntegral = productntegral;
	}
	public int getProductIsShow() {
		return productIsShow;
	}
	public void setProductIsShow(int productIsShow) {
		this.productIsShow = productIsShow;
	}
	public String getProductCityCode() {
		return productCityCode;
	}
	public void setProductCityCode(String productCityCode) {
		this.productCityCode = productCityCode;
	}
	public String getProductCityName() {
		return productCityName;
	}
	public void setProductCityName(String productCityName) {
		this.productCityName = productCityName;
	}
	public long getMerMId() {
		return merMId;
	}
	public void setMerMId(long merMId) {
		this.merMId = merMId;
	}
	public String getMerMName() {
		return merMName;
	}
	public void setMerMName(String merMName) {
		this.merMName = merMName;
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
	public ProductSaleType getPsType() {
		return psType;
	}
	public void setPsType(ProductSaleType psType) {
		this.psType = psType;
	}
	public long getGoldNum() {
		return goldNum;
	}
	public void setGoldNum(long goldNum) {
		this.goldNum = goldNum;
	}
	public long getSent() {
		return sent;
	}
	public void setSent(long sent) {
		this.sent = sent;
	}
	public long getProductOldPrice() {
		return productOldPrice;
	}
	public void setProductOldPrice(long productOldPrice) {
		this.productOldPrice = productOldPrice;
	}
	public long getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(long productPrice) {
		this.productPrice = productPrice;
	}
	public String getProductOldPriceStr() {
		return productOldPriceStr;
	}
	public void setProductOldPriceStr(String productOldPriceStr) {
		this.productOldPriceStr = productOldPriceStr;
	}
	public String getProductPricesStr() {
		return productPricesStr;
	}
	public void setProductPricesStr(String productPricesStr) {
		this.productPricesStr = productPricesStr;
	}
	public long getProductCommissionPrice() {
		return productCommissionPrice;
	}
	public void setProductCommissionPrice(long productCommissionPrice) {
		this.productCommissionPrice = productCommissionPrice;
	}
	public String getProductCommissionPriceStr() {
		return productCommissionPriceStr;
	}
	public void setProductCommissionPriceStr(String productCommissionPriceStr) {
		this.productCommissionPriceStr = productCommissionPriceStr;
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
	public void setShopLatitude(String shopLatitude) {
		this.shopLatitude = shopLatitude;
	}
	public int getProductScore() {
		return productScore;
	}
	public void setProductScore(int productScore) {
		this.productScore = productScore;
	}
	public long getDistributorPrice() {
		return distributorPrice;
	}
	public void setDistributorPrice(long distributorPrice) {
		this.distributorPrice = distributorPrice;
	}
	public String getDistributorProductUUID() {
		return distributorProductUUID;
	}
	public void setDistributorProductUUID(String distributorProductUUID) {
		this.distributorProductUUID = distributorProductUUID;
	}
	public long getSellPrice() {
		return sellPrice;
	}
	public void setSellPrice(long sellPrice) {
		this.sellPrice = sellPrice;
	}
	public String getDistributorPriceStr() {
		return distributorPriceStr;
	}
	public void setDistributorPriceStr(String distributorPriceStr) {
		this.distributorPriceStr = distributorPriceStr;
	}
	public String getSellPriceStr() {
		return sellPriceStr;
	}
	public void setSellPriceStr(String sellPriceStr) {
		this.sellPriceStr = sellPriceStr;
	}


}
