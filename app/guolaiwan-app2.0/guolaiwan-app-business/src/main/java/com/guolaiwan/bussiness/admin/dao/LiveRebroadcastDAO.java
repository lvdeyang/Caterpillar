package com.guolaiwan.bussiness.admin.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.guolaiwan.bussiness.admin.po.LiveRebroadcastPO;

import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.CountHql;
import pub.caterpillar.orm.hql.DeleteHql;
import pub.caterpillar.orm.hql.QueryHql;

@Repository(value = "com.guolaiwan.bussiness.admin.dao.LiveRebroadcastDAO")
public class LiveRebroadcastDAO extends AbstractBaseDao<LiveRebroadcastPO> {

	public List<LiveRebroadcastPO> getRebroadcastByPage(int pageNum, int pageSize) {
		QueryHql hql = this.newQueryHql();
		hql.orderBy("updateTime", true);
		List<LiveRebroadcastPO> rebroadcast = findByHqlPage(hql, pageNum, pageSize);
		if (rebroadcast == null || rebroadcast.size() <= 0)
			return null;
		return rebroadcast;
	}

	// 统计总数
	public int getCountByPage() {
		CountHql cHql = this.newCountHql();
		int allcount = this.countByHql(cHql);
		return allcount;
	}

	//
	public void deleteByUuid(String uuid) {
		DeleteHql hql = this.newDeleteHql();
		hql.andBy("uuid", Condition.eq, uuid);
		deleteByHql(hql);
	}

	public List<LiveRebroadcastPO> findByOrder() {
		QueryHql hql = this.newQueryHql();
		hql.orderBy("updateTime", true);
		List<LiveRebroadcastPO> rebroadcast = findByHql(hql);
		if (rebroadcast == null || rebroadcast.size() <= 0)
			return null;
		return rebroadcast;
	}
	
	public List<LiveRebroadcastPO> findByLiveIdOrder(Long liveId) {
		QueryHql hql = this.newQueryHql();
		hql.andBy("liveId",Condition.eq,liveId);
		hql.orderBy("updateTime", true);
		List<LiveRebroadcastPO> rebroadcast = findByHql(hql);
		if (rebroadcast == null || rebroadcast.size() <= 0)
			return null;
		return rebroadcast;
	}

}
