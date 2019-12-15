package com.guolaiwan.app.web.admin.vo;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.bytedeco.javacpp.RealSense.intrinsics;

import com.guolaiwan.bussiness.admin.enumeration.ProductSaleType;
import com.guolaiwan.bussiness.admin.po.PicturePO;
import com.guolaiwan.bussiness.admin.po.ProductPO;

import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class ProductVO extends AbstractBaseVO<ProductVO, ProductPO> implements Cloneable{


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

	//传入参数
	private String productMerchantJson;

	//商品名称
	private String productName;

	//产品副标题
	private String productSubtitle;

	//开始时间
	private String productBeginDate;

	//结束时间
	private String productEnddate;

	//有效期
	private String productEctivedate;

	//原价
	private String productOldPrice;

	//现价
	private String productPrice;
	// 积分商品  0是 1不是
	private int IntegralGoods;
	// 积分商品自领取地址  0是 1不是
	private String site;
	
	//提成模式（0：佣金1：比例）
	private int productCommissionCode;

	//提成金额（佣金：分。比例百分比：10，20。。）
	private long productCommissionPrice;

	//前端显示图片（路径）
	private String productShowPic;

	//产品多图显示（路径）Json形式
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
	private String productAuditstatus;

	//审核意见
	private String productAuditAdvice;

	//库存
	private long productStock;

	//是否限购（0，1）
	private int productLimitType;

	//限购数量
	private long productLimitNum;

	//产品介绍
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

	//类型（有关是否配送）0是门票类型的  1是商品类型的
	private Integer productType;

	//经销商价格
	private String distributorPrice;

	//是否收藏0是不收藏1是收藏
	private int ifcollection;
	//产品销售类型
	private String psType; 
	//金币数
	private long goldNum; 
	// 坐标经度
	private String shopLongitude;
	
	private long activityReId;
	
	private int isEffective;//是否在有效时间内(0:还没开始,1:在,2:已经结束)
	
	private int productIsRefund;
	
	private int ifFace;
	
	private int isSurpport;
	
	private int isactivityproduct;
	
	//张羽 新增 5/1 商品购买最低数量限制
	private int productRestrictNumber;
	
	//是否可拼团
	private int isgroup;
	//拼团人数
	private long groupnum;
	//拼团价格
	private String groupprice;
	//分销商品ID（过来玩代理其他家）
	private String distributeId;
    private int  mealAmount; //菜品数量
	
    private String videourl;
	 
	public int getMealAmount() {
		return mealAmount;
	}


	public void setMealAmount(int mealAmount) {
		this.mealAmount = mealAmount;
	}


	public String getDistributeId() {
		return distributeId;
	}


	public ProductVO setDistributeId(String distributeId) {
		this.distributeId = distributeId;
		return this;
	}


	public long getGroupnum() {
		return groupnum;
	}


	public void setGroupnum(long groupnum) {
		this.groupnum = groupnum;
	}


	public String getGroupprice() {
		return groupprice;
	}


	public void setGroupprice(long groupprice) {
		this.groupprice = new DecimalFormat("0.00").format(((double)groupprice/100));
	}


	public int getIsgroup() {
		return isgroup;
	}


	public ProductVO setIsgroup(int isgroup) {
		this.isgroup = isgroup;
		return this;
	}


	public int getIntegralGoods() {
		return IntegralGoods;
	}


	public ProductVO setIntegralGoods(int integralGoods) {
		this.IntegralGoods = integralGoods;
		return this;
	}


	public int getProductRestrictNumber() {
		return productRestrictNumber;
	}


	public void setProductRestrictNumber(int productRestrictNumber) {
		this.productRestrictNumber = productRestrictNumber;
	}


	@Override
    public Object clone() throws CloneNotSupportedException {
        ProductVO productVO = (ProductVO)super.clone();
        return productVO;
    }
	
	
	public long getActivityReId() {
		return activityReId;
	}





	public void setActivityReId(long activityReId) {
		this.activityReId = activityReId;
	}





	public int getIsEffective() {
		return isEffective;
	}





	public ProductVO setIsEffective(int isEffective) {
		this.isEffective = isEffective;
		return this;
	}





	public String getShopLongitude() {
		return shopLongitude;
	}

	//商品价格
	private String productPricesStr;



	public String getProductPricesStr() {
		return productPricesStr;
	}





	public ProductVO setProductPricesStr(String productPricesStr) {
		this.productPricesStr = productPricesStr;
		return this;
	}





	public ProductVO setShopLongitude(String shopLongitude) {
		this.shopLongitude = shopLongitude;
		return this;
	}





	public String getShopLatitude() {
		return shopLatitude;
	}





	public ProductVO setShopLatitude(String shopLatitude) {
		this.shopLatitude = shopLatitude;
		return this;
	}





	// 坐标纬度
	private String shopLatitude;

	public String getProductModularCode() {
		return productModularCode;
	}





	public ProductVO setProductModularCode(String productModularCode) {
		this.productModularCode = productModularCode;
		return this;
	}





	public String getProductModularCodeName() {
		return productModularCodeName;
	}





	public ProductVO setProductModularCodeName(String productModularCodeName) {
		this.productModularCodeName = productModularCodeName;
		return this;
	}





	public String getProductClassCode() {
		return productClassCode;
	}





	public ProductVO setProductClassCode(String productClassCode) {
		this.productClassCode = productClassCode;
		return this;
	}





	public String getProductClassName() {
		return productClassName;
	}





	public ProductVO setProductClassName(String productClassName) {
		this.productClassName = productClassName;
		return this;
	}


















	public long getProductMerchantID() {
		return productMerchantID;
	}





	public ProductVO setProductMerchantID(long productMerchantID) {
		this.productMerchantID = productMerchantID;	
		return this;
	}





	public String getProductMerchantName() {
		return productMerchantName;
	}





	public ProductVO setProductMerchantName(String productMerchantName) {
		this.productMerchantName = productMerchantName;
		return this;
	}


     


	public String getSite() {
		return site;
	}


	public ProductVO setSite(String site) {
		this.site = site;
		return this;
	}


	public String getProductMerchantJson() {
		return productMerchantJson;
	}





	public ProductVO setProductMerchantJson(String productMerchantJson) {
		this.productMerchantJson = productMerchantJson;
		return this;
	}





	public String getProductName() {
		return productName;
	}





	public ProductVO setProductName(String productName) {
		this.productName = productName;
		return this;
	}





	public String getProductSubtitle() {
		return productSubtitle;
	}





	public ProductVO setProductSubtitle(String productSubtitle) {
		this.productSubtitle = productSubtitle;
		return this;
	}








	public String getProductBeginDate() {
		return productBeginDate;
	}





	public ProductVO setProductBeginDate(String productBeginDate) {
		this.productBeginDate = productBeginDate;
		return this;
	}





	public String getProductEnddate() {
		return productEnddate;
	}





	public ProductVO setProductEnddate(String productEnddate) {
		this.productEnddate = productEnddate;
		return this;
	}





	public String getProductEctivedate() {
		return productEctivedate;
	}





	public ProductVO setProductEctivedate(String productEctivedate) {
		this.productEctivedate = productEctivedate;
		return this;
	}





	public String getProductOldPrice() {
		return productOldPrice;
	}





	public ProductVO setProductOldPrice(String productOldPrice) {
		this.productOldPrice = productOldPrice;
		return this;
	}





	public String getProductPrice() {
		return productPrice;
	}





	public ProductVO setProductPrice(String productPrice) {
		this.productPrice = productPrice;
		return this;
	}






	public int getProductCommissionCode() {
		return productCommissionCode;
	}





	public ProductVO setProductCommissionCode(int productCommissionCode) {
		this.productCommissionCode = productCommissionCode;
		return this;
	}





	public long getProductCommissionPrice() {
		return productCommissionPrice;
	}





	public ProductVO setProductCommissionPrice(long productCommissionPrice) {
		this.productCommissionPrice = productCommissionPrice;
		return this;
	}





	public String getProductShowPic() {
		return productShowPic;
	}





	public ProductVO setProductShowPic(String productShowPic) {
		this.productShowPic = productShowPic;
		return this;
	}





	public String getProductMorePic() {
		return productMorePic;
	}





	public ProductVO setProductMorePic(String productMorePic) {
		this.productMorePic = productMorePic;
		return this;
	}





	public int getProductIndexRecommend() {
		return productIndexRecommend;
	}





	public ProductVO setProductIndexRecommend(int productIndexRecommend) {
		this.productIndexRecommend = productIndexRecommend;
		return this;
	}





	public int getProductListRecommend() {
		return productListRecommend;
	}





	public ProductVO setProductListRecommend(int productListRecommend) {
		this.productListRecommend = productListRecommend;
		return this;
	}



	public long getProductShowNum() {
		return productShowNum;
	}





	public ProductVO setProductShowNum(long productShowNum) {
		this.productShowNum = productShowNum;
		return this;
	}





	public long getProductSaleNum() {
		return productSaleNum;
	}





	public ProductVO setProductSaleNum(long productSaleNum) {
		this.productSaleNum = productSaleNum;
		return this;
	}





	public long getProductSort() {
		return productSort;
	}





	public ProductVO setProductSort(long productSort) {
		this.productSort = productSort;
		return this;
	}





	public ProductVO setProductntegral(long productntegral) {
		this.productntegral = productntegral;
		return this;
	}





	public long getProductntegral() {
		return productntegral;
	}





	public String getProductAuditstatus() {
		return productAuditstatus;
	}





	public ProductVO setProductAuditstatus(String productAuditstatus) {
		this.productAuditstatus = productAuditstatus;
		return this;
	}





	public String getProductAuditAdvice() {
		return productAuditAdvice;
	}





	public ProductVO setProductAuditAdvice(String productAuditAdvice) {
		this.productAuditAdvice = productAuditAdvice;
		return this;
	}





	public long getProductStock() {
		return productStock;
	}





	public ProductVO setProductStock(long productStock) {
		this.productStock = productStock;
		return this;
	}





	public int getProductLimitType() {
		return productLimitType;
	}





	public ProductVO setProductLimitType(int productLimitType) {
		this.productLimitType = productLimitType;
		return this;
	}





	public long getProductLimitNum() {
		return productLimitNum;
	}





	public ProductVO setProductLimitNum(long productLimitNum) {
		this.productLimitNum = productLimitNum;
		return this;
	}





	public String getProductIntroduce() {
		return productIntroduce;
	}





	public ProductVO setProductIntroduce(String productIntroduce) {
		this.productIntroduce = productIntroduce;
		return this;
	}









	public int getProductIsShow() {
		return productIsShow;
	}





	public ProductVO setProductIsShow(int productIsShow) {
		this.productIsShow = productIsShow;
		return this;
	}





	public String getProductCityCode() {
		return productCityCode;
	}





	public ProductVO setProductCityCode(String productCityCode) {
		this.productCityCode = productCityCode;
		return this;
	}











	public String getProductCityName() {
		return productCityName;
	}





	public ProductVO setProductCityName(String productCityName) {
		this.productCityName = productCityName;
		return this;
	}









	public long getMerMId() {
		return merMId;
	}





	public ProductVO setMerMId(long merMId) {
		this.merMId = merMId;
		return this;
	}





	public String getMerMName() {
		return merMName;
	}





	public ProductVO setMerMName(String merMName) {
		this.merMName = merMName;
		return this;
	}


	public long getComId() {
		return comId;
	}





	public ProductVO setComId(long comId) {
		this.comId = comId;
		return this;
	}





	public String getComName() {
		return comName;
	}





	public ProductVO setComName(String comName) {
		this.comName = comName;
		return this;
	}








	public Integer getProductType() {
		return productType;
	}





	public ProductVO setProductType(Integer productType) {
		this.productType = productType;
		return this;
	}





	public String getDistributorPrice() {
		return distributorPrice;
	}





	public void setDistributorPrice(String distributorPrice) {
		this.distributorPrice = distributorPrice;
	}





	public int getIfcollection() {
		return ifcollection;
	}





	public void setIfcollection(int ifcollection) {
		this.ifcollection = ifcollection;
	}
	
	





	public String getPsType() {
		return psType;
	}





	public ProductVO setPsType(String psType) {
		this.psType = psType;
		return this;
	}





	public long getGoldNum() {
		return goldNum;
	}





	public ProductVO setGoldNum(long goldNum) {
		this.goldNum = goldNum;
		return this;
	}



	
	
	
	// 线路相关
	private int productDayCount;
	private String datesOn;//可用日期，1-31日数字用逗号分隔
	private String costMessage;
	private String remarks;
	private String notes;
	
	


	public int getProductDayCount() {
		return productDayCount;
	}





	public ProductVO setProductDayCount(int productDayCount) {
		this.productDayCount = productDayCount;
		return this;
	}





	public String getDatesOn() {
		return datesOn;
	}





	public ProductVO setDatesOn(String datesOn) {
		this.datesOn = datesOn;
		return this;
	}





	public String getCostMessage() {
		return costMessage;
	}





	public ProductVO setCostMessage(String costMessage) {
		this.costMessage = costMessage;
		return this;
	}





	public String getRemarks() {
		return remarks;
	}





	public ProductVO setRemarks(String remarks) {
		this.remarks = remarks;
		return this;
	}





	public String getNotes() {
		return notes;
	}





	public ProductVO setNotes(String notes) {
		this.notes = notes;
		return this;
	}










	public int getIsSurpport() {
		return isSurpport;
	}





	public void setIsSurpport(int isSurpport) {
		this.isSurpport = isSurpport;
	}
	private String traffic;

	public String getTraffic() {
		return traffic;
	}

	public ProductVO setTraffic(String traffic) {
		this.traffic = traffic;
		return this;
	}


	public int getIfFace() {
		return ifFace;
	}


	public ProductVO setIfFace(int ifFace) {
		this.ifFace = ifFace;
		return this;
	}


	public int getProductIsRefund() {
		return productIsRefund;
	}


	public ProductVO setProductIsRefund(int productIsRefund) {
		this.productIsRefund = productIsRefund;
		return this;
	}
	
	@Override
	public ProductVO set(ProductPO entity) throws Exception {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.setId(entity.getId())
		.setUuid(entity.getUuid())
		.setUpdateTime(df.format(entity.getUpdateTime()))
		.setProductModularCode(entity.getProductModularCode())
		.setProductModularCodeName(entity.getProductModularCodeName())
		.setProductClassCode(entity.getProductClassCode())
		.setProductClassName(entity.getProductClassName())
		.setProductMerchantID(entity.getProductMerchantID())
		.setProductMerchantName((entity.getProductMerchantName()))
		.setProductMerchantJson(entity.getProductMerchantJson())
		.setProductName(entity.getProductName())
		.setProductSubtitle(entity.getProductSubtitle())
		.setProductBeginDate(df.format(entity.getProductBeginDate()))
		.setProductEnddate(df.format(entity.getProductEnddate()))
		.setProductEctivedate(df.format(entity.getProductEctivedate()))
		.setProductOldPrice(new DecimalFormat("0.00").format(((double)entity.getProductOldPrice()/100)))
		.setProductPrice(new DecimalFormat("0.00").format(((double)entity.getProductPrice()/100)))
		.setProductCommissionCode(entity.getProductCommissionCode())
		.setProductCommissionPrice(entity.getProductCommissionPrice())
		.setProductShowPic(entity.getProductShowPic())
		.setProductMorePic(entity.getProductMorePic())
		.setProductIndexRecommend(entity.getProductIndexRecommend())
		.setProductListRecommend(entity.getProductListRecommend())
		.setProductShowNum(entity.getProductShowNum())
		.setProductSaleNum(entity.getProductSaleNum())
		.setProductSort(entity.getProductSort())
		.setProductAuditstatus(entity.getProductAuditstatus().getName())
		.setProductAuditAdvice(entity.getProductAuditAdvice())
		.setProductStock(entity.getProductStock())
		.setProductLimitType(entity.getProductLimitType())
		.setProductLimitNum(entity.getProductLimitNum())
		.setProductIntroduce(entity.getProductIntroduce())
		.setProductDayCount(entity.getProductDayCount())
		.setDatesOn(entity.getDatesOn())
		.setCostMessage(entity.getCostMessage())
		.setRemarks(entity.getRemarks())
		.setNotes(entity.getNotes())
		.setProductntegral(entity.getProductntegral())
		.setProductIsShow(entity.getProductIsShow())
		.setProductCityCode(entity.getProductCityCode())
		.setProductCityName(entity.getProductCityName())
		.setMerMId(entity.getMerMId())
		.setMerMName(entity.getMerMName())
		.setComId(entity.getComId())
		.setComName(entity.getComName())
		.setProductType(entity.getProductType())
		.setPsType(entity.getPsType().getName())
		.setGoldNum(entity.getGoldNum())
		.setShopLatitude(entity.getProductMerchantJson())
		.setShopLongitude(entity.getProductMerchantJson())
		.setTraffic(entity.getTraffic())
		.setIfFace(entity.getIfFace())
		.setIntegralGoods(entity.getIntegralGoods())
		.setProductIsRefund(entity.getProductIsRefund())
		.setSite(entity.getSite())
		.setProductPricesStr(new DecimalFormat("0.00").format(((double)entity.getProductPrice()/100)))
		.setIsgroup(entity.getIsgroup())
		.setVideourl(entity.getVideourl())
		.setX(entity.getX())
		.setY(entity.getY())
		.setVoiceUrl(entity.getVoiceUrl())
		.setMapUrl(entity.getMapUrl())
		.setIsInner(entity.getIsInner())
		.setDistributeId(entity.getDistributeId());
		
		if(entity.getIntegralGoods()==1){
			this.setProductPrice(entity.getProductPrice()+"").setProductPricesStr(entity.getProductPrice()+"");
		}
		return this;
	}


	public String getVideourl() {
		return videourl;
	}


	public ProductVO setVideourl(String videourl) {
		this.videourl = videourl;
		return this;
	}


	//室内导览新增
	//经度
	private String x;

	private String y;
	
	private String voiceUrl;
	
	private String mapUrl;
	
	private int isInner;//1室内，0室外

	public int getIsactivityproduct() {
		return isactivityproduct;
	}


	public ProductVO setIsactivityproduct(int isactivityproduct) {
		this.isactivityproduct = isactivityproduct;
		return this;
	}


	public String getX() {
		return x;
	}


	public ProductVO setX(String x) {
		this.x = x;
		return this;
	}


	public String getY() {
		return y;
	}


	public ProductVO setY(String y) {
		this.y = y;
		return this;
	}


	public String getVoiceUrl() {
		return voiceUrl;
	}


	public ProductVO setVoiceUrl(String voiceUrl) {
		this.voiceUrl = voiceUrl;
		return this;
	}


	public String getMapUrl() {
		return mapUrl;
	}


	public ProductVO setMapUrl(String mapUrl) {
		this.mapUrl = mapUrl;
		return this;
	}


	public int getIsInner() {
		return isInner;
	}


	public ProductVO setIsInner(int isInner) {
		this.isInner = isInner;
		return this;
	}
	
	
}
