package com.guolaiwan.bussiness.admin.dao;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.guolaiwan.bussiness.admin.po.CompanyPO;
import com.guolaiwan.bussiness.admin.po.VotePicsPo;

import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.CountHql;
import pub.caterpillar.orm.hql.DeleteHql;
import pub.caterpillar.orm.hql.QueryHql;

@Repository("com.guolaiwan.bussiness.admin.dao.VotePicsDao")
public class VotePicsDao extends AbstractBaseDao<VotePicsPo> {

	
	public List<VotePicsPo> getByOptionId(Long optionId) {
		QueryHql hql = newQueryHql();
		hql.andBy("optionId", Condition.eq, optionId);
		hql.orderBy("ranking", true);
		List<VotePicsPo> VotePics = findByHql(hql);
		if (VotePics == null || VotePics.size() == 0)
			return null;
		return VotePics;
	}
	
	public int getCountByOptionId(Long optionId){
		CountHql hql = newCountHql();
		hql.andBy("optionId", Condition.eq, optionId);
		int count = countByHql(hql);
		return count;
	}
	
	public void deleteByOptionId(long optionId) {
		DeleteHql dHql = this.newDeleteHql();
		dHql.andBy("optionId", Condition.eq, optionId);
		this.deleteByHql(dHql);
	}
}
