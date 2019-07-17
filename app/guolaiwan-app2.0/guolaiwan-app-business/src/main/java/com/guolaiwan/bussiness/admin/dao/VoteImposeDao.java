package com.guolaiwan.bussiness.admin.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.guolaiwan.bussiness.admin.po.VoteImposePo;

import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.QueryHql;

@Component
public class VoteImposeDao extends AbstractBaseDao<VoteImposePo> {
	public VoteImposePo getVoteImposePo(String userId, String productId) {
		QueryHql hql = this.newQueryHql();
		hql.andBy("userId", Condition.lk, userId);
		hql.andBy("productId", Condition.lk, productId);
		List<VoteImposePo> VoteImposePos = findByHql(hql);
		if (VoteImposePos == null || VoteImposePos.size() <= 0)
			return null;
		return VoteImposePos.get(0);
	}

}
