package com.guolaiwan.bussiness.admin.dao;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.guolaiwan.bussiness.admin.po.ProfessionalLiveDirectorPO;
import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.QueryHql;



@Repository("com.guolaiwan.bussiness.admin.dao.ProfessionalLiveDirectorDAO")
public class ProfessionalLiveDirectorDAO extends AbstractBaseDao<ProfessionalLiveDirectorPO> {
	
	
	public ProfessionalLiveDirectorPO findByLiveId(long liveId) {
		QueryHql hql = newQueryHql();
		hql.andBy("liveId", Condition.eq, liveId);
		List<ProfessionalLiveDirectorPO> professionalLiveDirectorPOList = findByHql(hql);
		return professionalLiveDirectorPOList.get(0);
	}
	
	
	public ProfessionalLiveDirectorPO findById(long id) {
		QueryHql hql = newQueryHql();
		hql.andBy("id", Condition.eq, id);
		List<ProfessionalLiveDirectorPO> professionalLiveDirectorPOList = findByHql(hql);
		return professionalLiveDirectorPOList.get(0);
	}
}
