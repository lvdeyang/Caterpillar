package com.guolaiwan.bussiness.admin.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Repository;
import com.guolaiwan.bussiness.admin.po.FilterPO;
import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.QueryHql;

@Repository
public class FilterDAO extends AbstractBaseDao<FilterPO> {

	//获取版块下特定的过滤器
	public List<FilterPO> getFilterBySection(Long sectionId, Collection<Long> filterIds){
		if(filterIds==null || filterIds.size()<=0) return null;
		QueryHql hql = this.newQueryHql();
		hql.andBy("section.id", Condition.eq, sectionId);
		hql.andBy("id", Condition.in, filterIds);
		return this.findByHql(hql);
	}
	
}
