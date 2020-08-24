package com.guolaiwan.bussiness.admin.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Component;

import com.guolaiwan.bussiness.Parking.po.CarPositionPO;
import com.guolaiwan.bussiness.admin.enumeration.ShopAuditStateType;
import com.guolaiwan.bussiness.admin.po.MealListPo;
import com.guolaiwan.bussiness.distribute.po.DistributorOrder;
import com.guolaiwan.bussiness.distribute.po.RegionPo;

import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;
import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.CountHql;
import pub.caterpillar.orm.hql.QueryHql;

@Component
public class MealListDao extends AbstractBaseDao<MealListPo> {
	
	public MealListPo findByDistributor(long productId,long userId,long merchantId){
		QueryHql hql=this.newQueryHql();
		hql.andBy("productId",Condition.eq,productId);
		hql.andBy("userId",Condition.eq,userId);
		hql.andBy("merchantId",Condition.eq,merchantId);
		hql.andBy("tableId",Condition.eq,Long.parseLong("0"));
		List<MealListPo> findByHql = findByHql(hql);
		if(findByHql == null || findByHql.size() ==0) return null;
		return findByHql.get(0);
		
	}
	
	public  List<MealListPo>  getMealList(long id,long userId,long merchantId){
		QueryHql hql=this.newQueryHql();
		hql.andBy("tableId",Condition.eq,id);
		hql.andBy("userId",Condition.eq,userId);
		hql.andBy("merchantId",Condition.eq,merchantId);
		List<MealListPo> findByHql = findByHql(hql);
		return findByHql;
		
	}
	
	public  List<MealListPo>  getNotableMealList(long statusid,long userId,long merchantId){
		QueryHql hql=this.newQueryHql();
		hql.andBy("tableStatusId",Condition.eq,statusid);
		hql.andBy("userId",Condition.eq,userId);
		hql.andBy("merchantId",Condition.eq,merchantId);
		List<MealListPo> findByHql = findByHql(hql);
		return findByHql;
		
	}
	
	public  List<MealListPo>  getCurMealList(long userId,long merchantId){
		QueryHql hql=this.newQueryHql();
		hql.andBy("tableId",Condition.eq,0l);
		hql.andBy("tableStatusId",Condition.eq,0l);
		hql.andBy("userId",Condition.eq,userId);
		hql.andBy("merchantId",Condition.eq,merchantId);
		List<MealListPo> findByHql = findByHql(hql);
		return findByHql;
		
	}
	
	
	public  List<MealListPo>  getMealList(long id,long userId,long merchantId,int pageNum,int pageSize){
		QueryHql hql=this.newQueryHql();
		hql.andBy("tableId",Condition.eq,id);
		hql.andBy("userId",Condition.eq,userId);
		hql.andBy("merchantId",Condition.eq,merchantId);
		List<MealListPo> findByHql = findByHql(hql, pageNum, pageSize);
		return findByHql;
		
	}
	
	public List<MealListPo> findByDistributor(long userId,long merchantId){
		QueryHql hql=this.newQueryHql();
		hql.andBy("userId",Condition.eq,userId);
		hql.andBy("merchantId",Condition.eq,merchantId);
		hql.andBy("tableId",Condition.eq,Long.parseLong("0"));
		List<MealListPo> findByHql = findByHql(hql);
		return findByHql;
		
	}
	
	public int countByTidUidMid(long id,long userId,long merchantId) {
		CountHql cHql = this.newCountHql();
		cHql.andBy("tableId", Condition.eq, id);
		cHql.andBy("userId", Condition.eq, userId);
		cHql.andBy("merchantId", Condition.eq, merchantId);
		int count = countByHql(cHql);
		return count;
	}
}
