package com.guolaiwan.app.web.admin.vo;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import com.guolaiwan.bussiness.admin.po.ProductPO;
import com.guolaiwan.bussiness.admin.po.ProductPackPO;

import pub.caterpillar.mvc.converter.AbstractBaseVO;

/**
 * @author Administrator
 *
 */
public class ProductPackVO extends AbstractBaseVO<ProductPackVO, ProductPackPO> {


	//名称
	private String name;
	//现价
	private String price;
	//原价
	private String oldPrice;
	//图片
	private String pic;






	public String getName() {
		return name;
	}






	public ProductPackVO setName(String name) {
		this.name = name;
		return this;
	}






	public String getPrice() {
		return price;
	}






	public ProductPackVO setPrice(String price) {
		this.price = price;
		return this;
	}






	public String getOldPrice() {
		return oldPrice;
	}






	public void setOldPrice(String oldPrice) {
		this.oldPrice = oldPrice;
	}






	public String getPic() {
		return pic;
	}






	public ProductPackVO setPic(String pic) {
		this.pic = pic;
		return this;
	}






	@Override
	public ProductPackVO set(ProductPackPO entity) throws Exception {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		DecimalFormat def = new DecimalFormat("0.00");
		this.setId(entity.getId())
		.setUuid(entity.getUuid())
		.setUpdateTime(df.format(entity.getUpdateTime()))
		.setName(entity.getName())
		.setPic(entity.getPic())
		.setPrice(def.format((double)entity.getPrice()/100))
		.setOldPrice(def.format((double)entity.getOldPrice()/100));
		return this;
	}

}
