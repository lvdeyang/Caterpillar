package com.chenxi.web.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.chenxi.web.po.ArticlePo;
import com.chenxi.web.po.ProductPo;

import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.QueryHql;

@Repository("ProductDao")
public class ProductDao extends AbstractBaseDao<ProductPo> {

	public boolean ifhasBysContent(String shortContent){
		QueryHql hql=this.newQueryHql();
		hql.andBy("shortContent",Condition.eq,shortContent);
		List<ProductPo> productPos=this.findByHql(hql);
		return (!productPos.isEmpty()&&productPos.size()!=0);
	}
	
	
}
