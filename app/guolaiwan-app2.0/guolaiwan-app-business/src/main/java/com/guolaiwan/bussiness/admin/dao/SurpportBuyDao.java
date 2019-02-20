package com.guolaiwan.bussiness.admin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.guolaiwan.bussiness.admin.po.SurpportBuyPo;

import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.CountHql;
import pub.caterpillar.orm.hql.DeleteHql;
import pub.caterpillar.orm.hql.QueryHql;

@Repository("com.guolaiwan.bussiness.admin.dao.SurpportBuyDao")
public class SurpportBuyDao extends AbstractBaseDao<SurpportBuyPo>{
	public int getSurpportCount(long userId,long proId){
		CountHql hql=this.newCountHql();
		hql.andBy("userId",Condition.eq,userId);
		hql.andBy("productId",Condition.eq,proId);
		return this.countByHql(hql);
	}
	public List<SurpportBuyPo> getSurpports(long userId,long proId){
		QueryHql hql=this.newQueryHql();
		hql.andBy("userId",Condition.eq,userId);
		hql.andBy("productId",Condition.eq,proId);
		return this.findByHql(hql);
	}
	public int checkSupport(long userId,long proId,long friendId){
		CountHql hql=this.newCountHql();
		hql.andBy("userId",Condition.eq,userId);
		hql.andBy("productId",Condition.eq,proId);
		hql.andBy("friendId",Condition.eq,friendId);
		return this.countByHql(hql);
	}
	
	public void delSurpport(long userId,long proId){
		DeleteHql hql=this.newDeleteHql();
		hql.andBy("userId",Condition.eq,userId);
		hql.andBy("productId",Condition.eq,proId);
		this.deleteByHql(hql);
	}

}
