package com.guolaiwan.bussiness.admin.po;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.bytedeco.javacpp.RealSense.intrinsics;

import com.guolaiwan.bussiness.admin.enumeration.ActivityType;

import pub.caterpillar.orm.po.AbstractBasePO;

@Entity
@Table(name = "t_sys_activity")
public class ActivityPO extends AbstractBasePO {

	private static final long serialVersionUID = 1L;
	//活动名称
	private String  name;
	//活动类型
	private ActivityType type;
	//满
	private long ceil;
	//减
	private long cut;
	//折扣
	private int discount;
	//积分比率
	private int ratio;
	//固定价格
	private long fixedPrice;
	//照片
	private long picId;
	private String pic;
	//公司
	private long comId;
	private String comName;
	private int recommend;//0,1推荐
	private String activityRule;
	//规则图片
	private long rulepicId;
	private String rulepic;
	
	public long getRulepicId() {
		return rulepicId;
	}
	public void setRulepicId(long rulepicId) {
		this.rulepicId = rulepicId;
	}
	public String getRulepic() {
		return rulepic;
	}
	public void setRulepic(String rulepic) {
		this.rulepic = rulepic;
	}

	public String getActivityRule() {
		return activityRule;
	}
	public void setActivityRule(String activityRule) {
		this.activityRule = activityRule;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Enumerated(EnumType.STRING)
	public ActivityType getType() {
		return type;
	}
	public void setType(ActivityType type) {
		this.type = type;
	}
	public long getCeil() {
		return ceil;
	}
	public void setCeil(long ceil) {
		this.ceil = ceil;
	}
	public long getCut() {
		return cut;
	}
	public void setCut(long cut) {
		this.cut = cut;
	}
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	public int getRatio() {
		return ratio;
	}
	public void setRatio(int ratio) {
		this.ratio = ratio;
	}
	public long getFixedPrice() {
		return fixedPrice;
	}
	public void setFixedPrice(long fixedPrice) {
		this.fixedPrice = fixedPrice;
	}
	public long getPicId() {
		return picId;
	}
	public void setPicId(long picId) {
		this.picId = picId;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
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
	public int getRecommend() {
		return recommend;
	}
	public void setRecommend(int recommend) {
		this.recommend = recommend;
	}

}
