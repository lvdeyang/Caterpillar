package com.guolaiwan.bussiness.admin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.guolaiwan.bussiness.admin.po.GroupBuyPO;
import com.guolaiwan.bussiness.admin.po.GroupTeamPO;

import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.QueryHql;

@Repository("com.guolaiwan.bussiness.admin.dao.GroupTeamDAO")
public class GroupTeamDAO extends AbstractBaseDao<GroupTeamPO> {

	public GroupTeamPO findByUserId(long userId) {
		QueryHql hql = newQueryHql();
		hql.andBy("userid", Condition.eq, userId);
		List<GroupTeamPO> lives = findByHql(hql);
		if(lives==null || lives.size()<=0) return null;
		return lives.get(0);
	}
	
	public List<GroupTeamPO> findByProductId(long productId) {
		QueryHql hql = newQueryHql();
		hql.andBy("productid", Condition.eq, productId);
		List<GroupTeamPO> lives = findByHql(hql);
		if(lives==null || lives.size()<=0) return null;
		return lives;
	}

}
