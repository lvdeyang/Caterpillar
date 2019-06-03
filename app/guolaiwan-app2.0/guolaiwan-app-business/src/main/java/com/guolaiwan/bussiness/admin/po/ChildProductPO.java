package com.guolaiwan.bussiness.admin.po;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import org.bytedeco.javacpp.RealSense.intrinsics;

import com.guolaiwan.bussiness.admin.enumeration.ShopAuditStateType;

import pub.caterpillar.orm.po.AbstractBasePO;

@Entity
@Table(name = "t_sys_product_child")
public class ChildProductPO extends AbstractBasePO {

	private static final long serialVersionUID = 1L;

	//子产品名称
	private String childName;

	//图片
	private String childPic;

	//中文女声
	private String chineseGirl;

	//中文男声
	private String chineseBoy;
	
	//英文女声
	private String englishGirl;
	
	//英文男声
	private String englishBoy;
	
	//经度
	private String childLongitude;

	//纬度
	private String childLatitude;

	//微信经度
	private String wxChildLongitude;
	
	//微信纬度
	private String wxChildLatitude;
	
	//产品id
	private long productID;
	//关联语言id
	private long lanId;
	//文字描述
	private String content;

	private String childScale;//导览点规模(大中小)
	
	private String childRoad;//路线
	
	private int isCen;//判断是否是小导览点(1:是,0:不是)

	private String chineseContent;//中文介绍
	
	private String englishContent;//英文介绍
	
	private String scope; // 讲解范围
	
	private int isTaught; // 是否讲解(Taught)
	
	private String linkedPoint;  //关联点
	
	private String relevance; //下一个浏览点 
	
	

	

	public String getRelevance() {
		return relevance;
	}

	public void setRelevance(String relevance) {
		this.relevance = relevance;
	}

	public String getWxChildLongitude() {
		return wxChildLongitude;
	}

	public void setWxChildLongitude(String wxChildLongitude) {
		this.wxChildLongitude = wxChildLongitude;
	}

	public String getWxChildLatitude() {
		return wxChildLatitude;
	}

	public void setWxChildLatitude(String wxChildLatitude) {
		this.wxChildLatitude = wxChildLatitude;
	}

	public String getEnglishContent() {
		return englishContent;
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

	public void setEnglishContent(String englishContent) {
		this.englishContent = englishContent;
	}

	public String getChineseContent() {
		return chineseContent;
	}

	public void setChineseContent(String chineseContent) {
		this.chineseContent = chineseContent;
	}

	public String getChineseBoy() {
		return chineseBoy;
	}

	public void setChineseBoy(String chineseBoy) {
		this.chineseBoy = chineseBoy;
	}

	public String getEnglishGirl() {
		return englishGirl;
	}

	public void setEnglishGirl(String englishGirl) {
		this.englishGirl = englishGirl;
	}

	public String getEnglishBoy() {
		return englishBoy;
	}

	public void setEnglishBoy(String englishBoy) {
		this.englishBoy = englishBoy;
	}

	public String getChineseGirl() {
		return chineseGirl;
	}

	public void setChineseGirl(String chineseGirl) {
		this.chineseGirl = chineseGirl;
	}

	public int getIsCen() {
		return isCen;
	}

	public void setIsCen(int isCen) {
		this.isCen = isCen;
	}

	public String getChildRoad() {
		return childRoad;
	}

	public void setChildRoad(String childRoad) {
		this.childRoad = childRoad;
	}

	public String getChildScale() {
		return childScale;
	}

	public void setChildScale(String childScale) {
		this.childScale = childScale;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getChildName() {
		return childName;
	}

	public void setChildName(String childName) {
		this.childName = childName;
	}

	public String getChildPic() {
		return childPic;
	}

	public void setChildPic(String childPic) {
		this.childPic = childPic;
	}

	public String getChildLongitude() {
		return childLongitude;
	}

	public void setChildLongitude(String childLongitude) {
		this.childLongitude = childLongitude;
	}

	public String getChildLatitude() {
		return childLatitude;
	}

	public void setChildLatitude(String childLatitude) {
		this.childLatitude = childLatitude;
	}

	public long getProductID() {
		return productID;
	}

	public void setProductID(long productID) {
		this.productID = productID;
	}

	public long getLanId() {
		return lanId;
	}

	public void setLanId(long lanId) {
		this.lanId = lanId;
	}

	public String getLinkedPoint() {
		return linkedPoint;
	}

	public void setLinkedPoint(String linkedPoint) {
		this.linkedPoint = linkedPoint;
	}



	
	
}
