package com.guolaiwan.bussiness.admin.dao;



import java.util.List;

import org.springframework.stereotype.Repository;

import com.guolaiwan.bussiness.admin.po.LiveMessagePO;
import com.guolaiwan.bussiness.admin.po.ProfessionalLiveMessagePO;
import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.QueryHql;


@Repository("com.guolaiwan.bussiness.admin.dao.ProfessionalLiveMessageDAO")
public class ProfessionalLiveMessageDAO extends AbstractBaseDao<ProfessionalLiveMessagePO> {
	public List<ProfessionalLiveMessagePO> findAll(final int page, final int pageSize) {
		QueryHql hql = newQueryHql();
		hql.orderBy("updateTime", true);
		return findByHql(hql, page, pageSize);
	}
	
	
	public List<ProfessionalLiveMessagePO> findByFlag(long liveId) {
		QueryHql hql = newQueryHql();
		hql.andBy("liveId", Condition.eq, liveId);
		hql.andBy("flag", Condition.eq, false);
		hql.orderBy("updateTime", true);
		return findByHql(hql);
	}
	
	
	public List<ProfessionalLiveMessagePO> findByLive(long liveId) {
		QueryHql hql = newQueryHql();
		hql.andBy("liveId", Condition.eq, liveId);
		hql.orderBy("updateTime", true);
		return findByHqlPage(hql, 1, 200);
	}
}
