package com.guolaiwan.bussiness.admin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.guolaiwan.bussiness.admin.po.LivePO;
import com.guolaiwan.bussiness.admin.po.LiveProductPO;

import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.CountHql;
import pub.caterpillar.orm.hql.QueryHql;

@Repository("com.guolaiwan.bussiness.admin.dao.LiveProductDAO")
public class LiveProductDAO extends AbstractBaseDao<LiveProductPO>{

	public List<LiveProductPO> findAll(final int page, final int pageSize) {
		QueryHql hql = newQueryHql();
		hql.orderBy("updateTime", true);
		return findByHql(hql, page, pageSize);
	}
	
	public LiveProductPO findById(long liveProductId) {
		QueryHql hql = newQueryHql();
		hql.andBy("id", Condition.eq, liveProductId);
		List<LiveProductPO> liveProducts = findByHql(hql);
		if(liveProducts==null || liveProducts.size()<=0) return null;
		return liveProducts.get(0);
	}
	
	public List<LiveProductPO> findByFlag(long liveId) {
		QueryHql hql = newQueryHql();
		hql.andBy("flag", Condition.eq, false);
		hql.andBy("liveId", Condition.eq, liveId);
		hql.orderBy("updateTime", true);
		return findByHql(hql);
	}
	//删除列表
	public List<LiveProductPO> findByDeLFlag(long liveId) {
		QueryHql hql = newQueryHql();
		hql.andBy("flag", Condition.eq, false);
		hql.andBy("liveId", Condition.eq, liveId);
		hql.andBy("productIsDel",Condition.eq,"1");
		hql.orderBy("updateTime", true);
		return findByHql(hql);
	}
	//成交
	public List<LiveProductPO> findByCJFlag(long liveId) {
		QueryHql hql = newQueryHql();
		hql.andBy("flag", Condition.eq, false);
		hql.andBy("liveId", Condition.eq, liveId);
		hql.andBy("locked",Condition.eq,true);
		hql.orderBy("updateTime", true);
		return findByHql(hql);
	}
	
	
	public int findByCount(long liveId) {
		CountHql cHql=this.newCountHql();
		cHql.andBy("liveId",Condition.eq,liveId);
		int allcount=this.countByHql(cHql);
		return allcount;
	}
	
	/**
	 * app专用
	 * @param liveId
	 * @return
	 */
	public List<LiveProductPO> findByLiveId(long liveId) {
		QueryHql hql = newQueryHql();
		hql.andBy("liveId", Condition.eq, liveId);
		List<LiveProductPO> liveProducts = findByHql(hql);
		if(liveProducts==null || liveProducts.size()<=0) return null;
		return liveProducts;
	}
	/**
	 * app专用
	 * @param liveId
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public List<LiveProductPO> findByLiveId(long liveId,int pageNum,int pageSize) {
		QueryHql hql = newQueryHql();
		hql.andBy("liveId", Condition.eq, liveId);
		List<LiveProductPO> liveProducts = findByHqlPage(hql, pageNum, pageSize);
		if(liveProducts==null || liveProducts.size()<=0) return null;
		return liveProducts;
	}
	
	/**
	 * app专用
	 * @param liveId
	 * @return
	 */
	public int countByLiveId(long liveId) {
		CountHql cHql = newCountHql();
		cHql.andBy("liveId", Condition.eq, liveId);
		int count = countByHql(cHql);
		return count;
	}
	
	
}
