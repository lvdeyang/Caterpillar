package com.guolaiwan.bussiness.admin.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Repository;
import com.guolaiwan.bussiness.admin.po.SectionPO;
import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.QueryHql;

@Repository
public class SectionDAO extends AbstractBaseDao<SectionPO> {

	//获取页面特定板块
	public List<SectionPO> getSectionByPage(Long pageId, Collection<Long> sectionIds){
		if(sectionIds==null || sectionIds.size()<=0) return null;
		QueryHql hql = this.newQueryHql();
		hql.andBy("page.id", Condition.eq, pageId);
		hql.andBy("id", Condition.in, sectionIds);
		return this.findByHql(hql);
	}
	
}
