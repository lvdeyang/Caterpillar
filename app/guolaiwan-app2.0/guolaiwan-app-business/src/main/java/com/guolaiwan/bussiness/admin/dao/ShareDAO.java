package com.guolaiwan.bussiness.admin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.guolaiwan.bussiness.admin.po.SharePO;

import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.QueryHql;

@Repository("com.guolaiwan.bussiness.admin.dao.ShareDAO")
public class ShareDAO extends AbstractBaseDao<SharePO> {
	
	public List<SharePO> getShareByUserId(String userId){
		QueryHql hql = newQueryHql();
		hql.andBy("userId", Condition.eq, userId);
		List<SharePO> list = findByHql(hql);
		return list;
	}
	
	public List<SharePO> getShareById(long id){
		QueryHql hql = newQueryHql();
		hql.andBy("id", Condition.eq, id);
		List<SharePO> list = findByHql(hql);
		return list;
	}
	
}