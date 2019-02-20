package com.guolaiwan.bussiness.admin.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.guolaiwan.bussiness.admin.po.AuctionPO;
import com.guolaiwan.bussiness.admin.po.LiveMessagePO;

import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.QueryHql;

@Repository("com.guolaiwan.bussiness.admin.dao.AuctionDAO")
public class AuctionDAO extends AbstractBaseDao<AuctionPO>{
	public List<AuctionPO> findByFlag(long liveId) {
		QueryHql hql = newQueryHql();
		hql.andBy("liveId", Condition.eq, liveId);
		hql.andBy("flag", Condition.eq, false);
		hql.orderBy("updateTime", true);
		return findByHql(hql);
	}
	public int deleteByProductID(long productID)
	{
		String str_sql="delete from t_sys_auction where liveProductId="+productID;
		
		Query query = getCurrentSession().createQuery(str_sql);
		
        return  query.executeUpdate();
	}
	
	public AuctionPO findByProductId(long liveId) {
		QueryHql hql = newQueryHql();
		hql.andBy("liveId", Condition.eq, liveId);
		hql.orderBy("updateTime", true);
		return findByHql(hql).get(0);
	}
}
