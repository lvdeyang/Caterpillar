package com.guolaiwan.bussiness.admin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.guolaiwan.bussiness.admin.po.MerchantChildrenPO;

import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.QueryHql;

@Repository("com.guolaiwan.bussiness.admin.dao.MerchantChildrenDao")
public class MerchantChildrenDao extends AbstractBaseDao<MerchantChildrenPO> {
	// 通过模块和类获取商品
	public List<MerchantChildrenPO> getCate(long merchantId) {
		QueryHql hql = this.newQueryHql();
		hql.andBy("merchantId", Condition.eq, merchantId);
		List<MerchantChildrenPO> merchants = findByHql(hql);
		if (merchants == null || merchants.size() <= 0)
			return null;
		return merchants;
	}
}
