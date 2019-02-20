package com.guolaiwan.app.web.admin.vo;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import org.aspectj.weaver.ast.Var;

import com.guolaiwan.bussiness.admin.dto.ProductDTO;
import com.guolaiwan.bussiness.admin.po.ActivityPO;
import com.guolaiwan.bussiness.admin.po.AdminPO;

import pub.caterpillar.commons.util.date.DateUtil;
import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class ActivityVO extends AbstractBaseVO<ActivityVO, ActivityPO> {

	//名称
	private String name;
	//类型
	private String type;
	//满
	private String ceil;
	//减
	private String cut;
	//折扣
	private int discount;
	//积分比率
	private int ratio;
	//固定价格
	private String fixedPrice;
	//图片id
	private long pId;
	//图片
	private String pic;
	
	//规则图片id
	private long rulepId;
	//规则图片
	private String rulepic;
	
	private long comId;
	private String comName;
	
	private int recommend;
	
	//内容
	private String content;
	
	//商品
	private List<ProductDTO> products;

	private String activityRule;


	public String getName() {
		return name;
	}





	public ActivityVO setName(String name) {
		this.name = name;
		return this;
	}





	public String getType() {
		return type;
	}





	public ActivityVO setType(String type) {
		this.type = type;
		return this;
	}





	








	public String getCeil() {
		return ceil;
	}





	public ActivityVO setCeil(String ceil) {
		this.ceil = ceil;
		return this;
	}





	public String getCut() {
		return cut;
	}





	public ActivityVO setCut(String cut) {
		this.cut = cut;
		return this;
	}





	public int getDiscount() {
		return discount;
	}





	public ActivityVO setDiscount(int discount) {
		this.discount = discount;
		return this;
	}





	public int getRatio() {
		return ratio;
	}





	public ActivityVO setRatio(int ratio) {
		this.ratio = ratio;
		return this;
	}





	public long getpId() {
		return pId;
	}





	public ActivityVO setpId(long pId) {
		this.pId = pId;
		return this;
	}





	public String getPic() {
		return pic;
	}





	public ActivityVO setPic(String pic) {
		this.pic = pic;
		return this;
	}
	
	

	public long getrulepId() {
		return rulepId;
	}





	public ActivityVO setrulepId(long rulepId) {
		this.rulepId = rulepId;
		return this;
	}





	public String getrulePic() {
		return rulepic;
	}





	public ActivityVO setrulePic(String rulepic) {
		this.rulepic = rulepic;
		return this;
	}



	public String getContent() {
		return content;
	}





	public ActivityVO setContent(String content) {
		this.content = content;
		return this;
	}
	
	





	public long getComId() {
		return comId;
	}





	public ActivityVO setComId(long comId) {
		this.comId = comId;
		return  this;
	}





	public String getComName() {
		return comName;
	}





	public ActivityVO setComName(String comName) {
		this.comName = comName;
		return  this;
	}


	public List<ProductDTO> getProducts() {
		return products;
	}





	public void setProducts(List<ProductDTO> products) {
		this.products = products;
	}

	
	




	public String getFixedPrice() {
		return fixedPrice;
	}





	public ActivityVO setFixedPrice(String fixedPrice) {
		this.fixedPrice = fixedPrice;
		return this;
	}

	
	public String getActivityRule() {
		return activityRule;
	}





	public ActivityVO setActivityRule(String activityRule) {
		this.activityRule = activityRule;
		return this;
	}



	@Override
	public ActivityVO set(ActivityPO entity) throws Exception {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		DecimalFormat df = new DecimalFormat("0.00");
		String type = entity.getType().getName();
		int ratio = entity.getRatio();
		int discount = entity.getDiscount();
		String ceil = df.format((double)(entity.getCeil()/100));
		String cut = df.format((double)(entity.getCut()/100));
		String fixedPrice = df.format((double)(entity.getFixedPrice()/100));
		
		this.setId(entity.getId())
		.setUuid(entity.getUuid())
		.setUpdateTime(formatter.format(entity.getUpdateTime()))
		.setpId(entity.getPicId())
		.setPic(entity.getPic())
		.setrulepId(entity.getRulepicId())
		.setrulePic(entity.getRulepic())
		.setName(entity.getName())
		.setType(type)
		.setCeil(ceil)
		.setRecommend(entity.getRecommend())
		.setCut(cut)
		.setFixedPrice(fixedPrice)
		.setDiscount(discount)	
		.setRatio(ratio)
		.setComId(entity.getComId())
		.setComName(entity.getComName())
		.setActivityRule(entity.getActivityRule());
		
		
		switch (type) {
		case "积分":
			this.setContent("积分比率："+ratio+"%");
			break;
		case "满减":
			this.setContent("满"+ceil+"减"+cut);
			break;
		case "打折":
			this.setContent("打"+discount+"折");
			break;
		case "固定价格":
			this.setContent("固定价格："+fixedPrice+"元");
			break;
		
		}

		return this;

	}





	public int getRecommend() {
		return recommend;
	}





	public ActivityVO setRecommend(int recommend) {
		this.recommend = recommend;
		return this;
	}
}
