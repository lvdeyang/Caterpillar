package com.guolaiwan.bussiness.admin.dao;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.guolaiwan.bussiness.admin.po.NhEticketsPo;
import com.guolaiwan.bussiness.admin.po.OrderInfoPO;

import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.QueryHql;

@Repository("com.guolaiwan.bussiness.admin.dao.NhEticketsDao")
public class NhEticketsDao extends AbstractBaseDao<NhEticketsPo> {
	// 后台验单
	public List<NhEticketsPo> getByOrderId(long orderId) {
		QueryHql hql = newQueryHql();
		hql.andBy("orderId", Condition.eq, orderId);
		List<NhEticketsPo> orders = findByHql(hql);
		if (orders == null || orders.size() == 0)
			return null;
		return orders;
	}

	public List<NhEticketsPo> getBySn(String sn) {
		QueryHql hql = newQueryHql();
		hql.andBy("sn", Condition.eq, sn);
		List<NhEticketsPo> orders = findByHql(hql);
		if (orders == null || orders.size() == 0)
			return null;
		return orders;
	}

}
