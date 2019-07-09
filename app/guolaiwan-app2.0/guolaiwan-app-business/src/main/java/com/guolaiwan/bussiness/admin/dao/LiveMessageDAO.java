package com.guolaiwan.bussiness.admin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.guolaiwan.bussiness.admin.po.LiveMessagePO;
import com.guolaiwan.bussiness.admin.po.LiveProductPO;

import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.DeleteHql;
import pub.caterpillar.orm.hql.QueryHql;

@Repository("com.guolaiwan.bussiness.admin.dao.LiveMessageDAO")
public class LiveMessageDAO extends AbstractBaseDao<LiveMessagePO> {

	public List<LiveMessagePO> findAll(final int page, final int pageSize) {
		QueryHql hql = newQueryHql();
		hql.orderBy("updateTime", true);
		return findByHql(hql, page, pageSize);
	}
	
	
	public List<LiveMessagePO> findByFlag(long liveId) {
		QueryHql hql = newQueryHql();
		hql.andBy("liveId", Condition.eq, liveId);
		hql.andBy("flag", Condition.eq, false);
		hql.orderBy("updateTime", true);
		return findByHql(hql);
	}
	
	
	public List<LiveMessagePO> findByLive(long liveId) {
		QueryHql hql = newQueryHql();
		hql.andBy("liveId", Condition.eq, liveId);
		hql.orderBy("updateTime", true);
		return findByHqlPage(hql, 1, 200);
	}
	
	public List<LiveMessagePO> findByMerchant(long MerchantId) {
		QueryHql hql = newQueryHql();
		hql.andBy("merchantId", Condition.eq, MerchantId);
		hql.orderBy("updateTime", true);
		return findByHqlPage(hql, 1, 200);
	}
	
	//4/20新增删除评论
	public void delectById(long Id){
		DeleteHql hql =newDeleteHql();
		hql.andBy("id",Condition.eq,Id);
		this.deleteByHql(hql);
	}
}
