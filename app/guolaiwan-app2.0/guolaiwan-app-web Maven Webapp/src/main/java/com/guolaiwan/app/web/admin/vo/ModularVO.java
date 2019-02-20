package com.guolaiwan.app.web.admin.vo;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import com.guolaiwan.bussiness.admin.enumeration.ModularType;
import com.guolaiwan.bussiness.admin.po.ModularClassPO;
import com.guolaiwan.bussiness.admin.po.ModularPO;
import com.guolaiwan.bussiness.admin.po.ProductPO;

import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class ModularVO extends AbstractBaseVO<ModularVO, ModularPO>{
	//标识
	private String modularCode;

	//模块名称
	private String modularName;

	//是否显示
	private int modularIsv;

	// 模块图片
	private String modularPic;

	// 排序
	private long sort;

	// 排序
	private long comId;

	// 排序
	private String comName;

	// 类型：0活动，1普通
	private String type;



	public String getModularPic() {
		return modularPic;
	}

	public ModularVO setModularPic(String modularPic) {
		this.modularPic = modularPic;
		return this;
	}

	//guanl
	private List<ModularClassPO> modularClasses;

	//guanl
	private List<ProductVO> products;

	//merchants
	private List<MerchantVO> merchants;


	public List<ModularClassPO> getModularClasses() {
		return modularClasses;
	}

	public void setModularClasses(List<ModularClassPO> modularClasses) {
		this.modularClasses = modularClasses;
	}

	public List<ProductVO> getProducts() {
		return products;
	}

	public void setProducts(List<ProductVO> products) {
		this.products = products;
	}

	public String getModularCode() {
		return modularCode;
	}

	public ModularVO setModularCode(String modularCode) {
		this.modularCode = modularCode;
		return this;
	}

	public String getModularName() {
		return modularName;
	}

	public ModularVO setModularName(String modularName) {
		this.modularName = modularName;
		return this;
	}

	public int getModularIsv() {
		return modularIsv;
	}

	public ModularVO setModularIsv(int modularIsv) {
		this.modularIsv = modularIsv;
		return this;
	}



	public long getSort() {
		return sort;
	}

	public ModularVO setSort(long sort) {
		this.sort = sort;
		return this;
	}

	public List<MerchantVO> getMerchants() {
		return merchants;
	}

	public void setMerchants(List<MerchantVO> merchants) {
		this.merchants = merchants;
	}

	public long getComId() {
		return comId;
	}

	public ModularVO setComId(long comId) {
		this.comId = comId;
		return this;
	}

	public String getComName() {
		return comName;
	}

	public ModularVO setComName(String comName) {
		this.comName = comName;
		return this;
	}



	public String getType() {
		return type;
	}

	public ModularVO setType(String type) {
		this.type = type;
		return this;
	}

	@Override
	public ModularVO set(ModularPO entity) throws Exception {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.setId(entity.getId())
		.setUuid(entity.getUuid())
		.setUpdateTime(df.format(entity.getUpdateTime()))
		.setModularCode(entity.getModularCode())
		.setModularName(entity.getModularName())
		.setModularIsv(entity.getModularIsv())
		.setModularPic(entity.getModularPic())
		.setSort(entity.getSort())
		.setComId(entity.getComId())
		.setComName(entity.getComName());
		return this;
	}
}
