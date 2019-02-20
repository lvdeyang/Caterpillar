package com.guolaiwan.bussiness.admin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.guolaiwan.bussiness.admin.enumeration.LiveStatusType;
import com.guolaiwan.bussiness.admin.enumeration.LiveType;
import com.guolaiwan.bussiness.admin.po.LivePO;

import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.CountHql;
import pub.caterpillar.orm.hql.QueryHql;

@Repository("com.guolaiwan.bussiness.admin.dao.LiveDAO")
public class LiveDAO extends AbstractBaseDao<LivePO> {

	public List<LivePO> findAll(final int page, final int pageSize) {
		QueryHql hql = newQueryHql();
		hql.orderBy("updateTime", true);
		return findByHql(hql, page, pageSize);
	}
	
	public LivePO findByUserId(long anchorId) {
		QueryHql hql = newQueryHql();
		hql.andBy("userId", Condition.eq, anchorId);
		List<LivePO> lives = findByHql(hql);
		if(lives==null || lives.size()<=0) return null;
		return lives.get(0);
	}
	
	public List<LivePO> findByStatus(LiveStatusType lStatus) {
		QueryHql hql = newQueryHql();
		hql.andBy("liveStatusType", Condition.eq, lStatus);
		List<LivePO> lives = findByHql(hql);
		return lives;
	}
	
	public LivePO findByMerchantId(long anchorId) {
		QueryHql hql = newQueryHql();
		hql.andBy("merchantId", Condition.eq, anchorId);
		List<LivePO> lives = findByHql(hql);
		if(lives==null || lives.size()<=0) return null;
		return lives.get(0);
	}
	
	/**
	 * app专用
	 * @param liveType
	 * @return
	 * @throws Exception
	 */
	public List<LivePO> appFindByLiveType(String liveType) throws Exception {
		QueryHql hql = newQueryHql();
		hql.andBy("liveType", Condition.eq, LiveType.fromString(liveType));
		List<LivePO> lives = findByHql(hql);
		if(lives==null||lives.size()==0) return null;
		return lives;
	}
	/**
	 * app专用
	 * @param liveType
	 * @return
	 * @throws Exception
	 */
	public List<LivePO> appFindByLiveType(String liveType,int pageNum,int pageSize) throws Exception {
		QueryHql hql = newQueryHql();
		hql.andBy("liveType", Condition.eq, LiveType.fromString(liveType));
		hql.andBy("liveStatusType", Condition.eq, LiveStatusType.LIVING);
		List<LivePO> lives = findByHqlPage(hql, pageNum, pageSize);
		if(lives==null||lives.size()==0) return null;
		return lives;
	}
	/**
	 * app专用
	 * @param liveType
	 * @return
	 * @throws Exception
	 */
	public int countByLiveType(String liveType) throws Exception {
		CountHql cHql = newCountHql();
		cHql.andBy("liveType", Condition.eq, LiveType.fromString(liveType));
		int count = countByHql(cHql);
		return count;
	}
	

}
