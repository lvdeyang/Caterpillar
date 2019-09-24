package com.guolaiwan.bussiness.admin.dao;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.guolaiwan.bussiness.admin.po.VoteImposePo;

import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.CountHql;
import pub.caterpillar.orm.hql.DeleteHql;
import pub.caterpillar.orm.hql.QueryHql;

@Component
@Repository("com.guolaiwan.bussiness.admin.dao.VoteImposeDao")
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

	
	/**
	 * 按照Uid Pid 查每天的投票数
	 * @param userId
	 * @param productId
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public int countByUidPid(String userId, String productId,Date startTime,Date endTime) {
		CountHql cHql = this.newCountHql();
		cHql.andBy("userId", Condition.eq, userId);
		cHql.andBy("productId", Condition.eq, productId);
		cHql.andBy("updateTime", Condition.gt, startTime);
		cHql.andBy("updateTime", Condition.lt, endTime);
		cHql.andBy("poll", Condition.eq, 1);
		int count = this.countByHql(cHql);
		return count;
	}
	
	/**
	 * 按照Uid Pid 查购买数
	 * @param userId
	 * @param productId
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public int buyCountByUidPid(String userId, String productId) {
		CountHql cHql = this.newCountHql();
		cHql.andBy("userId", Condition.eq, userId);
		cHql.andBy("productId", Condition.eq, productId);
		cHql.andBy("buy", Condition.eq, 1);
		int count = this.countByHql(cHql);
		return count;
	}
	
	
	/**
	 * 统计每个商品的投票数
	 * @param productId
	 * @return
	 */
	public int countByPid(String productId) {
		CountHql cHql = this.newCountHql();
		cHql.andBy("productId", Condition.eq, productId);
		cHql.andBy("poll", Condition.eq, 1);
		int count = this.countByHql(cHql);
		return count;
	}
	
	/**
	 * 统计每个商品的购买数
	 * @param productId
	 * @return
	 */
	public int buyCountByPid(String productId) {
		CountHql cHql = this.newCountHql();
		cHql.andBy("productId", Condition.eq, productId);
		cHql.andBy("buy", Condition.eq, 1);
		int count = this.countByHql(cHql);
		return count;
	}
	
	/**
	 * 统计每个商品的评委票数
	 * @param productId
	 * @return
	 */
	public int countByjudges(String productId) {
		CountHql cHql = this.newCountHql();
		cHql.andBy("productId", Condition.eq, productId);
		cHql.andBy("poll", Condition.eq, 1);
		cHql.andBy("isjudges", Condition.eq, 1);
		int count = this.countByHql(cHql);
		return count;
	}
	
	/**
	 * 按照productId删除投票记录
	 * @param productId
	 * @return
	 */
	public void deleteByProduct(String productId) {
		DeleteHql cHql = this.newDeleteHql();
		cHql.andBy("productId", Condition.eq, productId);
		this.deleteByHql(cHql);
	}
	
	/**
	 * 按照productId删除投票记录
	 * @param productId
	 * @return
	 */
	public void delByProduct(String productId) {
		DeleteHql cHql = this.newDeleteHql();
		cHql.andBy("productId", Condition.eq, productId);
		cHql.andBy("poll", Condition.eq, 1);
		this.deleteByHql(cHql);
	}
}
