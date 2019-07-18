package com.guolaiwan.bussiness.admin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.guolaiwan.bussiness.admin.po.JudgesPo;

import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.QueryHql;

@Repository("com.guolaiwan.bussiness.admin.dao.JudgesDao")
public class JudgesDao extends AbstractBaseDao<JudgesPo> {

	public JudgesPo getJudgesUserId(String userId) {
		QueryHql hql = this.newQueryHql();
		hql.andBy("userId", Condition.eq, userId);
		List<JudgesPo> product = findByHql(hql);
		if (product.size() == 0) {
			return null;
		} else {
			return product.get(0);
		}
	}
}
