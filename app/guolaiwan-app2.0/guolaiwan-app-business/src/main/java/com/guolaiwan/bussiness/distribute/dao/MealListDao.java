package com.guolaiwan.bussiness.distribute.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Component;

import com.guolaiwan.bussiness.Parking.po.CarPositionPO;
import com.guolaiwan.bussiness.distribute.po.DistributorOrder;
import com.guolaiwan.bussiness.distribute.po.MealListPo;
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
		List<MealListPo> findByHql = findByHql(hql);
		if(findByHql == null || findByHql.size() ==0) return null;
		return findByHql.get(0);
		
	}
}
