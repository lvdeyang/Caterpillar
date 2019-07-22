package com.guolaiwan.bussiness.admin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.guolaiwan.bussiness.admin.po.VoteModularPO;
import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.CountHql;
import pub.caterpillar.orm.hql.QueryHql;

@Repository("com.guolaiwan.bussiness.admin.dao.VoteModularDAO")
public class VoteModularDAO extends AbstractBaseDao<VoteModularPO>{
	
	public List<VoteModularPO> getByOptionId(Long optionId){
		QueryHql hql = this.newQueryHql();
		hql.andBy("optionId", Condition.eq, optionId);
		List<VoteModularPO> list = findByHql(hql);
		return list;
	}

	public int getCountByOptionId(Long optionId){
		CountHql hql = newCountHql();
		hql.andBy("optionId", Condition.eq, optionId);
		int count = countByHql(hql);
		return count;
	}
}
