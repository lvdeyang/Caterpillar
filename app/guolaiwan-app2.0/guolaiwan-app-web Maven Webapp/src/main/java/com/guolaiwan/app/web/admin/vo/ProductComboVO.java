package com.guolaiwan.app.web.admin.vo;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import org.aspectj.weaver.ast.Var;

import com.guolaiwan.bussiness.admin.dto.ProductDTO;
import com.guolaiwan.bussiness.admin.po.ActivityPO;
import com.guolaiwan.bussiness.admin.po.AdminPO;
import com.guolaiwan.bussiness.admin.po.ProductComboPO;

import pub.caterpillar.commons.util.date.DateUtil;
import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class ProductComboVO extends AbstractBaseVO<ProductComboVO, ProductComboPO> {

	private long  productId;
	
	private String productName;
	
	private String combo;
	
	public Long getProductId() {
		return productId;
	}

	public ProductComboVO setProductId(Long productId) {
		this.productId = productId;
		return this;
	}

	public String getProductName() {
		return productName;
	}

	public ProductComboVO setProductName(String productName) {
		this.productName = productName;
		return this;
	}
	
	public String getCombo() {
		return combo;
	}

	public ProductComboVO setCombo(String combo) {
		this.combo = combo;
		return this;
	}	
	
	@Override
	public ProductComboVO set(ProductComboPO entity) throws Exception {
		// TODO Auto-generated method stub
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		this.setId(entity.getId())
		.setUuid(entity.getUuid())
		.setProductId(entity.getProductId())
		.setProductName(entity.getProductName())
		.setCombo(entity.getCombo())
		.setUpdateTime(formatter.format(entity.getUpdateTime()));
		
		return this;
	}
	

}
