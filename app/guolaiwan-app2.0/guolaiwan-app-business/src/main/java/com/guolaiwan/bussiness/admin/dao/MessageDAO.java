package com.guolaiwan.bussiness.admin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.guolaiwan.bussiness.admin.po.MessagePO;

import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.QueryHql;

@Repository("com.guolaiwan.bussiness.admin.dao.MessageDAO")
public class MessageDAO extends AbstractBaseDao<MessagePO> {

	public MessagePO getByOderId(String oderId) {
		QueryHql hql = newQueryHql();
		hql.andBy("oderId", Condition.eq, oderId);
		List<MessagePO> Messagepos = findByHql(hql);
		if (Messagepos == null || Messagepos.size() == 0)
			return null;
		return Messagepos.get(0);
	}

	public List<MessagePO> getmerchantid(String merchantid) {
		QueryHql hql = newQueryHql();
		hql.andBy("merchantid", Condition.eq, merchantid);
		hql.andBy("state", Condition.eq, "1");
		List<MessagePO> Messagepos = findByHql(hql);
		if (Messagepos == null || Messagepos.size() == 0)
			return null;
		return Messagepos;
	}
}
