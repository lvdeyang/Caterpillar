package com.guolaiwan.bussiness.admin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.guolaiwan.bussiness.admin.po.VoteProductPO;

import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.CountHql;
import pub.caterpillar.orm.hql.DeleteHql;
import pub.caterpillar.orm.hql.QueryHql;

@Repository("com.guolaiwan.bussiness.admin.dao.VoteProductDAO")
public class VoteProductDAO extends AbstractBaseDao<VoteProductPO> {

	public int countByMoId(long modularId, String pName) {
		CountHql cHql = this.newCountHql();
		cHql.andBy("modularcode", Condition.eq, modularId);
		cHql.andBy("productName", Condition.lk, pName);
		int count = this.countByHql(cHql);
		return count;
	}

	public List<VoteProductPO> findByAcId(long modularId, String pName, Long pId, int pageNum, int pageSize) {
		QueryHql hql = this.newQueryHql();
		hql.andBy("modularcode", Condition.eq, modularId);
		hql.andBy("productId", Condition.eq, pId);
		hql.andBy("productName", Condition.lk, pName);
		hql.orderBy("updateTime", true);
		List<VoteProductPO> VoteProducts = this.findByHqlPage(hql, pageNum, pageSize);
		return VoteProducts;
	}

	public int countByPro(long pId) {
		CountHql cHql = this.newCountHql();
		cHql.andBy("productId", Condition.eq, pId);
		int count = this.countByHql(cHql);
		return count;
	}

	public void deleteByMoId(long moId) {
		DeleteHql dHql = this.newDeleteHql();
		dHql.andBy("modularcode", Condition.eq, moId);
		this.deleteByHql(dHql);
	}

	public VoteProductPO getVoteProduct(long productid) {
		QueryHql hql = this.newQueryHql();
		hql.andBy("productId", Condition.eq, productid);
		List<VoteProductPO> findByHql = findByHql(hql);
		if (findByHql.size() == 0) {
			return null;
		} else {
			return findByHql.get(0);
		}

	}

	public List<VoteProductPO> getvoteproduct(long moId) {
		QueryHql hql = this.newQueryHql();
		hql.andBy("modularcode", Condition.eq, moId);
		hql.orderBy("peoplevotenum", true);
		List<VoteProductPO> findByHql = findByHql(hql);
		if (findByHql.size() == 0) {
			return null;
		} else {
			return findByHql;
		}
	}
	
	public List<VoteProductPO> getvoteproduct(String name) {
		QueryHql hql = this.newQueryHql();
		hql.andBy("productName", Condition.lk, name);
		List<VoteProductPO> findByHql = findByHql(hql);
		if (findByHql.size() == 0) {
			return null;
		} else {
			return findByHql;
		}
	}

}
