package com.guolaiwan.bussiness.chapman.product.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Repository;
import com.guolaiwan.bussiness.chapman.product.po.ShelveProductPO;
import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.QueryHql;

@Repository
public class ShelveProductDAO extends AbstractBaseDao<ShelveProductPO> {

	//根据标签获取货架商品
	public List<ShelveProductPO> getShelveProductByTag(Collection<String> tags){
		if(tags==null || tags.size()<=0) return null;
		QueryHql hql = this.newQueryHql();
		hql.andBy("tag", Condition.in, tags);
		return this.findByHql(hql);
	}
	
	//根据栏目获取货架商品
	public List<ShelveProductPO> getShelveProductByCategory(Collection<String> categories){
		if(categories==null || categories.size()<=0) return null;
		QueryHql hql = this.newQueryHql();
		hql.andBy("type", Condition.in, categories);
		return this.findByHql(hql);
	}
	
	//根据菜单获取货架商品
	public List<ShelveProductPO> getShelveProductByMenu(Collection<Long> menuIds) {
		if(menuIds==null || menuIds.size()<=0) return null;
		QueryHql hql = this.newQueryHql();
		hql.andBy("menu.id", Condition.in, menuIds);
		return this.findByHql(hql);
	}
	
}
