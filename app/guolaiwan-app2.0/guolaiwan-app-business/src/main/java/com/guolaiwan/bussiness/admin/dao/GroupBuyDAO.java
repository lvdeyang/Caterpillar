package com.guolaiwan.bussiness.admin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.guolaiwan.bussiness.admin.po.GroupBuyPO;

import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.QueryHql;

@Repository("com.guolaiwan.bussiness.admin.dao.GroupBuyDAO")
public class GroupBuyDAO extends AbstractBaseDao<GroupBuyPO> {

	public GroupBuyPO findByProductId(long product) {
		QueryHql hql = newQueryHql();
		hql.andBy("productid", Condition.eq, product);
		List<GroupBuyPO> lives = findByHql(hql);
		if(lives==null || lives.size()<=0) return null;
		return lives.get(0);
	}

}
