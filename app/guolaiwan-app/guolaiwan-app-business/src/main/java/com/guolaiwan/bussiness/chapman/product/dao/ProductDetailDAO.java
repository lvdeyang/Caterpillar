package com.guolaiwan.bussiness.chapman.product.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.guolaiwan.bussiness.chapman.product.enumeration.ProductDetailType;
import com.guolaiwan.bussiness.chapman.product.po.ProductDetailPO;
import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.QueryHql;

@Repository
public class ProductDetailDAO extends AbstractBaseDao<ProductDetailPO>{

	//获取产品明细
	public List<ProductDetailPO> getDetails(Long productId){
		QueryHql hql = this.newQueryHql();
		hql.andBy("product.id", Condition.eq, productId);
		return this.findByHql(hql);
	}
	
	//获取产品明细列表
	public List<ProductDetailPO> getDetails(Long productId, Collection<Long> detailIds){
		if(detailIds==null || detailIds.size()<=0) return null;
		QueryHql hql = this.newQueryHql();
		hql.andBy("product.id", Condition.eq, productId);
		hql.andBy("id", Condition.in, detailIds);
		return this.findByHql(hql);
	}
	
	//获取产品详情
	public List<ProductDetailPO> getProductInfo(Long productId){
		QueryHql hql = this.newQueryHql();
		hql.andBy("product.id", Condition.eq, productId);
		hql.andBy("type", Condition.eq, ProductDetailType.INFO);
		return this.findByHql(hql);
	}
	
	//获取产品描述
	public ProductDetailPO getProductIntroduction(Long productId){
		QueryHql hql = this.newQueryHql();
		hql.andBy("product.id", Condition.eq, productId);
		hql.andBy("type", Condition.eq, ProductDetailType.PRODUCT);
		List<ProductDetailPO> productDetails = this.findByHql(hql);
		if(productDetails==null || productDetails.size()<=0) return null;
		return productDetails.get(0);
	}
	
}
