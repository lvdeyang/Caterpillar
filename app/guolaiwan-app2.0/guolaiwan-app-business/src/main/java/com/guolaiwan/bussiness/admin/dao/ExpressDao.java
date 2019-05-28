package com.guolaiwan.bussiness.admin.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.guolaiwan.bussiness.admin.po.ExpressPO;

import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.QueryHql;

@Component
public class ExpressDao extends AbstractBaseDao<ExpressPO> {

	public ExpressPO findByUserId(String anchorId) {
		QueryHql hql = newQueryHql();
		hql.andBy("f1", Condition.eq, anchorId);
		List<ExpressPO> lives = findByHql(hql);
		if (lives == null || lives.size() <= 0)
			return null;
		return lives.get(0);
	}

}
