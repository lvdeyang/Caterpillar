package com.guolaiwan.bussiness.distribute.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.guolaiwan.bussiness.distribute.po.DistributorOrder;

import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.QueryHql;

@Component
public class DistributorOrderDao extends AbstractBaseDao<DistributorOrder> {
	
	public List<DistributorOrder> findByDistributor(Long distributorId){
		QueryHql hql=this.newQueryHql();
		hql.andBy("distributorId",Condition.eq,distributorId);
		return this.findByHql(hql);
	}
	public List<DistributorOrder> findByParent(Long distributorId){
		QueryHql hql=this.newQueryHql();
		hql.andBy("parentId",Condition.eq,distributorId);
		return this.findByHql(hql);
	}
}
