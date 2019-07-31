package com.guolaiwan.bussiness.nanshan.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.guolaiwan.bussiness.nanshan.po.MessageMiddleClientPO;

import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.QueryHql;

@Repository("com.guolaiwan.bussiness.nanshan.dao.MessageMiddleClientDao")
public class MessageMiddleClientDao extends AbstractBaseDao<MessageMiddleClientPO> {
	
	public List<MessageMiddleClientPO> getByOderId(Long oderId) {
		QueryHql hql = newQueryHql();
		hql.andBy("orderId", Condition.eq, oderId);
		List<MessageMiddleClientPO> Messagepos = findByHql(hql);
		if (Messagepos == null || Messagepos.size() == 0)
			return null;
		return Messagepos;
	}
	
}
