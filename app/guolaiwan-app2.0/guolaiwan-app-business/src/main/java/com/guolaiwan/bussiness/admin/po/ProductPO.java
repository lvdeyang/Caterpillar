package com.guolaiwan.bussiness.admin.po;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.bytedeco.javacpp.RealSense.intrinsics;

import com.guolaiwan.bussiness.admin.enumeration.ProductSaleType;
import com.guolaiwan.bussiness.admin.enumeration.ShopAuditStateType;
import com.guolaiwan.bussiness.distribute.po.DistributeProduct;

import pub.caterpillar.orm.po.AbstractBasePO;

@Entity
@Table(name = "t_sys_modular_product")
public class ProductPO extends AbstractBasePO {

	private static final long serialVersionUID = 1L;

	// 绑定模块
	private String productModularCode;

	// 绑定模块名称
	private String productModularCodeName;

	private List<DistributeProduct> distributeProduct;
	
	
	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = false)
	
	
	public List<DistributeProduct> getDistributeProduct() {
		return distributeProduct;
	}

	public void setDistributeProduct(List<DistributeProduct> distributeProduct) {
		this.distributeProduct = distributeProduct;
	}

	// 分类标识
	private String productClassCode;
	
	
	// 积分商品  0是 1不是
	private int IntegralGoods;
	
	// 积分商品自领取地址  0是 1不是
	private String site;

	// 分类名称
	private String productClassName;

	// 商户ID
	private long productMerchantID;

	// 商户名称
	private String productMerchantName;

	// 传入参数
	@Column(name = "productMerchantJson", length = 2000)
	private String productMerchantJson;

	// 商品名称
	private String productName;

	// 产品副标题
	private String productSubtitle;

	// 开始时间
	private Date productBeginDate;

	// 结束时间
	private Date productEnddate;

	// 有效期
	private Date productEctivedate;

	// 原价(分)s
	private long productOldPrice;

	// 现价（分）
	private long productPrice;

	// 提成模式（0：佣金1：比例）
	private int productCommissionCode;

	// 提成金额（佣金：分。比例百分比：10，20。。）
	private long productCommissionPrice;

	// 前端显示图片（路径）
	private String productShowPic;

	// 产品多图显示（路径）,
	private String productMorePic;

	// 首页/平台推荐（0，1）
	private int productIndexRecommend;

	// 列表/右侧推荐（0，1）
	private int productListRecommend;

	// 浏览量
	private long productShowNum;

	// 销量
	private long productSaleNum;

	// 排序
	private long productSort;

	// 审核状态(初审：T复审：R)
	private ShopAuditStateType productAuditstatus;

	// 审核意见
	private String productAuditAdvice;

	// 库存
	private long productStock;

	// 是否限购（0，1）
	private int productLimitType;

	// 是否人脸（0，1）
	private int ifFace;
	
	// 限购数量
	private long productLimitNum;

	// 产品介绍 || 描述 forPhoneInterfac
	private String productIntroduce;

	// 兑换积分
	private long productntegral;

	// 是否显示
	private int productIsShow;

	// 城市编码
	private String productCityCode;

	// 城市名称
	private String productCityName;

	// 商家分类Id
	private long merMId;

	// 商家分类
	private String merMName;

	// 公司Id
	private long comId;
	// 公司名称
	private String comName;
	// 产品销售类型
	private ProductSaleType psType;
	// 金币数
	private long goldNum;
	// 类型（有关是否配送）0是门票类型的 1是商品类型的
	private Integer productType;
	// 快递费
	private long sent;

	// 关联套餐
	private List<ProductPackPO> propacks;

	// 是否通知客户端
	private boolean flag;

	private boolean distri;

	
	
	private long regionId;
	
	//张羽 新增退款限制 4/28
	private int productIsRefund;
	//张羽 新增 5/1 商品购买最低数量限制
	private int productRestrictNumber;
	//是否可拼团
	private int isgroup;
	//分销商品ID（过来玩代理其他家）
	private String distributeId;
	
	private String videourl;
	
	
	
	public String getVideourl() {
		return videourl;
	}

	public void setVideourl(String videourl) {
		this.videourl = videourl;
	}

	public String getDistributeId() {
		return distributeId;
	}

	public void setDistributeId(String distributeId) {
		this.distributeId = distributeId;
	}

	public int getIsgroup() {
		return isgroup;
	}

	public void setIsgroup(int isgroup) {
		this.isgroup = isgroup;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public int getIntegralGoods() {
		return IntegralGoods;
	}

	public void setIntegralGoods(int integralGoods) {
		IntegralGoods = integralGoods;
	}

	public int getProductRestrictNumber() {
		return productRestrictNumber;
	}

	public void setProductRestrictNumber(int productRestrictNumber) {
		this.productRestrictNumber = productRestrictNumber;
	}

	public int getProductIsRefund() {
		return productIsRefund;
	}

	public void setProductIsRefund(int productIsRefund) {
		this.productIsRefund = productIsRefund;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	@OneToMany(mappedBy = "product", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = false)
	public List<ProductPackPO> getPropacks() {
		return propacks;
	}

	public void setPropacks(List<ProductPackPO> propacks) {
		this.propacks = propacks;
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

	public String getProductMerchantJson() {
		return productMerchantJson;
	}

	public void setProductMerchantJson(String productMerchantJson) {
		this.productMerchantJson = productMerchantJson;
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

	public Date getProductEctivedate() {
		return productEctivedate;
	}

	public void setProductEctivedate(Date productEctivedate) {
		this.productEctivedate = productEctivedate;
	}

	public void setProductEnddate(Date productEnddate) {
		this.productEnddate = productEnddate;
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

	public int getProductCommissionCode() {
		return productCommissionCode;
	}

	public void setProductCommissionCode(int productCommissionCode) {
		this.productCommissionCode = productCommissionCode;
	}

	public long getProductCommissionPrice() {
		return productCommissionPrice;
	}

	public void setProductCommissionPrice(long productCommissionPrice) {
		this.productCommissionPrice = productCommissionPrice;
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

	public void setProductntegral(long productntegral) {
		this.productntegral = productntegral;
	}

	@Enumerated(EnumType.STRING)
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

	@Enumerated(EnumType.STRING)
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

	public Integer getProductType() {
		return productType;
	}

	public void setProductType(Integer productType) {
		this.productType = productType;
	}

	public boolean isDistri() {
		return distri;
	}

	public void setDistri(boolean distri) {
		this.distri = distri;
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

	public void setProductDayCount(int productDayCount) {
		this.productDayCount = productDayCount;
	}

	public String getDatesOn() {
		return datesOn;
	}

	public void setDatesOn(String datesOn) {
		this.datesOn = datesOn;
	}

	public String getCostMessage() {
		return costMessage;
	}

	public void setCostMessage(String costMessage) {
		this.costMessage = costMessage;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	private String traffic;

	public String getTraffic() {
		return traffic;
	}

	public void setTraffic(String traffic) {
		this.traffic = traffic;
	}

	public long getRegionId() {
		return regionId;
	}

	public void setRegionId(long regionId) {
		this.regionId = regionId;
	}

	public int getIfFace() {
		return ifFace;
	}

	public void setIfFace(int ifFace) {
		this.ifFace = ifFace;
	}
	
	//室内导览新增
	//经度
	private String x;

	private String y;
	
	private String voiceUrl;
	
	private String mapUrl;
	
	private int isInner;//1室内，0室外


	public String getX() {
		return x;
	}

	public void setX(String x) {
		this.x = x;
	}

	public String getY() {
		return y;
	}

	public void setY(String y) {
		this.y = y;
	}

	public String getVoiceUrl() {
		return voiceUrl;
	}

	public void setVoiceUrl(String voiceUrl) {
		this.voiceUrl = voiceUrl;
	}

	public String getMapUrl() {
		return mapUrl;
	}

	public void setMapUrl(String mapUrl) {
		this.mapUrl = mapUrl;
	}

	public int getIsInner() {
		return isInner;
	}

	public void setIsInner(int isInner) {
		this.isInner = isInner;
	}
	
	
	//预约购票新增
	//是否限制每天预约门票数（0：不限制，1：限制）
	private int numTicketsByDayType;
	
	public int getNumTicketsByDayType() {
		return numTicketsByDayType;
	}

	public void setNumTicketsByDayType(int numTicketsByDayType) {
		this.numTicketsByDayType = numTicketsByDayType;
	}

	//每天可预约门票数量
	private int numTicketsByDay;
	
	public int getNumTicketsByDay() {
		return numTicketsByDay;
	}

	public void setNumTicketsByDay(int numTicketsByDay) {
		this.numTicketsByDay = numTicketsByDay;
	}
	
	//限制单人一次购买票数
//	private int numByOnePerson;
//
//	public int getNumByOnePerson() {
//		return numByOnePerson;
//	}
//
//	public void setNumByOnePerson(int numByOnePerson) {
//		this.numByOnePerson = numByOnePerson;
//	}
	
}
