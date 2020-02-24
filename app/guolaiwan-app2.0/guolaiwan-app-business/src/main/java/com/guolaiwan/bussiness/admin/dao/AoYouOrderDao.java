package com.guolaiwan.bussiness.admin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.guolaiwan.bussiness.admin.po.AoYouOrderPO;

import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.QueryHql;

@Repository("com.guolaiwan.bussiness.admin.dao.AoYouOrderDao")
public class AoYouOrderDao extends AbstractBaseDao<AoYouOrderPO> {
	// 后台验单
	public AoYouOrderPO getByOrderNo(String glwOrderNO) {
		QueryHql hql = newQueryHql();
		hql.andBy("glwOrderNO", Condition.eq, glwOrderNO);
		List<AoYouOrderPO> list = findByHql(hql);
		if(list != null && list.size() == 1){
			return list.get(0);
		}
		return null;
	}
}
