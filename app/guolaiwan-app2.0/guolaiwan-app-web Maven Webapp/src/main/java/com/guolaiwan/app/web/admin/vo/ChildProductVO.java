package com.guolaiwan.app.web.admin.vo;

import java.text.SimpleDateFormat;
import java.util.List;

import com.guolaiwan.bussiness.admin.po.AdminPO;
import com.guolaiwan.bussiness.admin.po.ChildProductPO;
import com.guolaiwan.bussiness.admin.po.PicturePO;
import com.sun.tools.classfile.Opcode.Set;

import pub.caterpillar.commons.util.date.DateUtil;
import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class ChildProductVO extends AbstractBaseVO<ChildProductVO, ChildProductPO> {

	// 子产品名称
	private String childName;

	// 图片
	private String childPic;

	// 中文女声
	private String chineseGirl;

	// 中文男声
	private String chineseBoy;

	// 英文女声
	private String englishGirl;

	// 英文男声
	private String englishBoy;

	// 经度
	private String childLongitude;

	// 纬度
	private String childLatitude;
    
	//微信经度
    private String WxChildLongitude;
		
	//微信纬度
	private String WxChildLatitude;
	
	// 产品ID
	private long productID;

	// 图片对象
	private String pics;

	// 系统图片路径
	private String webUrl;
	
	private long lanId;

	private String content;

	private String childScale;// 景点规模大小

	private String childRoad;// 路线

	private int isCen;// 判断是否是小导览点(1:是,0:不是)
	
	private String chineseContent;//中文介绍
	
	private String englishContent;//英文介绍
	
	private String scope; // 讲解范围
	
	private int isTaught; // 是否讲解(Taught)
	
	private String linkedPoint;
	
	
	
	
	public String getWxChildLongitude() {
		return WxChildLongitude;
	}

	public ChildProductVO setWxChildLongitude(String wxChildLongitude) {
		WxChildLongitude = wxChildLongitude;
		return this;
	}

	public String getWxChildLatitude() {
		return WxChildLatitude;
	}

	public ChildProductVO setWxChildLatitude(String wxChildLatitude) {
		WxChildLatitude = wxChildLatitude;
		return this;
	}

	public String getChineseContent() {
		return chineseContent;
	}

	public ChildProductVO setChineseContent(String chineseContent) {
		this.chineseContent = chineseContent;
		return this;
	}

	public String getEnglishContent() {
		return englishContent;
	}

	public ChildProductVO setEnglishContent(String englishContent) {
		this.englishContent = englishContent;
		return this;
	}

	public String getChineseGirl() {
		return chineseGirl;
	}

	public ChildProductVO setChineseGirl(String chineseGirl) {
		this.chineseGirl = chineseGirl;
		return this;
	}

	public String getChineseBoy() {
		return chineseBoy;
	}

	public ChildProductVO setChineseBoy(String chineseBoy) {
		this.chineseBoy = chineseBoy;
		return this;
	}

	public String getEnglishGirl() {
		return englishGirl;
	}

	public ChildProductVO setEnglishGirl(String englishGirl) {
		this.englishGirl = englishGirl;
		return this;
	}

	public String getEnglishBoy() {
		return englishBoy;
	}

	public ChildProductVO setEnglishBoy(String englishBoy) {
		this.englishBoy = englishBoy;
		return this;
	}

	public int getIsCen() {
		return isCen;
	}

	public ChildProductVO setIsCen(int isCen) {
		this.isCen = isCen;
		return this;
	}

	public String getChildRoad() {
		return childRoad;
	}

	public ChildProductVO setChildRoad(String childRoad) {
		this.childRoad = childRoad;
		return this;
	}

	public String getChildScale() {
		return childScale;
	}

	public ChildProductVO setChildScale(String childScale) {
		this.childScale = childScale;
		return this;
	}

	public String getWebUrl() {
		return webUrl;
	}

	public ChildProductVO setWebUrl(String webUrl) {
		this.webUrl = webUrl;
		return this;
	}

	public String getPics() {
		return pics;
	}

	public ChildProductVO setPics(String pics) {
		this.pics = pics;
		return this;
	}

	public String getChildName() {
		return childName;
	}

	public ChildProductVO setChildName(String childName) {
		this.childName = childName;
		return this;
	}

	public String getChildPic() {
		return childPic;
	}

	public ChildProductVO setChildPic(String childPic) {
		this.childPic = childPic;
		return this;
	}

	public String getChildLongitude() {
		return childLongitude;
	}

	public ChildProductVO setChildLongitude(String childLongitude) {
		this.childLongitude = childLongitude;
		return this;
	}

	public String getChildLatitude() {
		return childLatitude;
	}

	public ChildProductVO setChildLatitude(String childLatitude) {
		this.childLatitude = childLatitude;
		return this;
	}

	public long getProductID() {
		return productID;
	}

	public ChildProductVO setProductID(long productID) {
		this.productID = productID;
		return this;
	}

	public long getLanId() {
		return lanId;
	}

	public ChildProductVO setLanId(long lanId) {
		this.lanId = lanId;
		return this;
	}

	public String getContent() {
		return content;
	}
	
	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public int getIsTaught() {
		return isTaught;
	}

	public void setIsTaught(int isTaught) {
		this.isTaught = isTaught;
	}

	public ChildProductVO setContent(String content) {
		this.content = content;
		return this;
	}

	@Override
	public ChildProductVO set(ChildProductPO entity) throws Exception {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.setId(entity.getId()).setUuid(entity.getUuid())
				.setUpdateTime(DateUtil.format(entity.getUpdateTime(), DateUtil.dateTimePattern))
				.setChildName(entity.getChildName()).setChildPic(entity.getChildPic())
				.setChineseGirl(entity.getChineseGirl())
				.setChineseBoy(entity.getChineseBoy())
				.setEnglishGirl(entity.getEnglishGirl())
				.setEnglishBoy(entity.getEnglishBoy())
				.setChildLongitude(entity.getChildLongitude())
				.setChildLatitude(entity.getChildLatitude()).setLanId(entity.getLanId())
				.setChineseContent(entity.getChineseContent())
				.setEnglishContent(entity.getEnglishContent())
				.setLinkedPoint(entity.getLinkedPoint())
                .setWxChildLatitude(entity.getWxChildLatitude())
                .setWxChildLongitude(entity.getWxChildLongitude())
				.setChildPic(entity.getChildPic()).setContent(entity.getContent()).setChildScale(entity.getChildScale())
				.setChildRoad(entity.getChildRoad()).setIsCen(entity.getIsCen()).setProductID(entity.getProductID());
		this.setScope(entity.getScope());
		this.setIsTaught(entity.getIsTaught());
		return this;
	}

	public String getLinkedPoint() {
		return linkedPoint;
	}

	public ChildProductVO setLinkedPoint(String linkedPoint) {
		this.linkedPoint = linkedPoint;
		return this;
	}

}