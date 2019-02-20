package com.guolaiwan.bussiness.distribute.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.guolaiwan.bussiness.distribute.po.DistributePolicy;

import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.CountHql;
import pub.caterpillar.orm.hql.DeleteHql;
import pub.caterpillar.orm.hql.QueryHql;

@Component
public class DistributePolicyDao extends AbstractBaseDao<DistributePolicy> {
	
	public long deleteByProduct(long productId){
		DeleteHql dHql = this.newDeleteHql();
		dHql.andBy("distributeProduct.id", Condition.eq, productId);
		return this.deleteByHql(dHql);
	}
	public List<DistributePolicy> queryByProduct(long productId){
		QueryHql hql = this.newQueryHql();
		hql.andBy("distributeProduct.id", Condition.eq, productId);
		return this.findByHql(hql);
	}

	public int countByDProduct(long dProId) {
		CountHql chql = this.newCountHql();
		chql.andBy("distributeProduct.id", Condition.eq, dProId);
		int count = countByHql(chql);
		return count;
	}

}
