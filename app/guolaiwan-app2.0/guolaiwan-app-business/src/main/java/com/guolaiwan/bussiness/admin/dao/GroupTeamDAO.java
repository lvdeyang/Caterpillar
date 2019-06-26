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
		hql.andBy("iscaptain", Condition.eq, true);
		List<GroupTeamPO> lives = findByHql(hql);
		if(lives==null || lives.size()<=0) return null;
		return lives;
	}
	
	public List<GroupTeamPO> findByCaptainAndTeamId(long captain,long teamId) {
		QueryHql hql = newQueryHql();
		hql.andBy("captain", Condition.eq, captain);
		hql.andBy("belongtoteam", Condition.eq, teamId);
		List<GroupTeamPO> lives = findByHql(hql);
		if(lives==null || lives.size()<=0) return null;
		return lives;
	}

}
