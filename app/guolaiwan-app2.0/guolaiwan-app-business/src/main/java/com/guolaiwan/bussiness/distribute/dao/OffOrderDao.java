package com.guolaiwan.bussiness.distribute.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.guolaiwan.bussiness.distribute.po.DistributorOrder;
import com.guolaiwan.bussiness.distribute.po.OffOrder;

import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.QueryHql;
@Repository
public class OffOrderDao extends AbstractBaseDao<OffOrder> {
	public List<OffOrder> findByProid(Long proId){
		QueryHql hql=this.newQueryHql();
		hql.andBy("proId",Condition.eq,proId);
		return this.findByHql(hql);
	}
}
