package com.guolaiwan.bussiness.admin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.guolaiwan.bussiness.admin.po.JudgesVoteMsgPO;

import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.CountHql;
import pub.caterpillar.orm.hql.DeleteHql;
import pub.caterpillar.orm.hql.QueryHql;

@Repository("com.guolaiwan.bussiness.admin.dao.JudgesvotemsgDao")
public class JudgesVoteMsgDAO extends AbstractBaseDao<JudgesVoteMsgPO> {

	public JudgesVoteMsgPO getByUIdPId(long userId,long productId) {
		QueryHql hql = this.newQueryHql();
		hql.andBy("userId", Condition.eq, userId);
		hql.andBy("productId", Condition.eq, productId);
		List<JudgesVoteMsgPO> product = findByHql(hql);
		if (product.size() == 0) {
			return null;
		} else {
			return product.get(0);
		}
	}
	
	public List<JudgesVoteMsgPO> getByPId(long productId) {
		QueryHql hql = this.newQueryHql();
		hql.andBy("productId", Condition.eq, productId);
		List<JudgesVoteMsgPO> product = findByHql(hql);
		if (product.size() == 0) {
			return null;
		} else {
			return product;
		}
	}
	
	public List<JudgesVoteMsgPO> getByVotePId(long productId) {
		QueryHql hql = this.newQueryHql();
		hql.andBy("voteproductId", Condition.eq, productId);
		List<JudgesVoteMsgPO> product = findByHql(hql);
		if (product.size() == 0) {
			return null;
		} else {
			return product;
		}
	}
	
	public int countByPId(long pId) {
		CountHql cHql = this.newCountHql();
		cHql.andBy("productId", Condition.eq, pId);
		int count = this.countByHql(cHql);
		return count;
	}
	
	public int countByVotePId(long pId) {
		CountHql cHql = this.newCountHql();
		cHql.andBy("voteproductId", Condition.eq, pId);
		int count = this.countByHql(cHql);
		return count;
	}
	
	public int deleteByUId(Long userId){
		DeleteHql hql = this.newDeleteHql();
		hql.andBy("userId", Condition.eq, userId);
		int list = deleteByHql(hql);
		return list;
	}
}
