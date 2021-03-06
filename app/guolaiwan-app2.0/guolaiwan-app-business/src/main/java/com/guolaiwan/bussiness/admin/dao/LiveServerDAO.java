package com.guolaiwan.bussiness.admin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.guolaiwan.bussiness.admin.po.LivePO;
import com.guolaiwan.bussiness.admin.po.LiveServerPO;

import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.QueryHql;

@Repository("com.guolaiwan.bussiness.admin.dao.LiveServerDAO")
public class LiveServerDAO extends AbstractBaseDao<LiveServerPO>{
	public List<LiveServerPO> findAll(final int page, final int pageSize) {
		QueryHql hql = newQueryHql();
		hql.orderBy("updateTime", true);
		return findByHql(hql, page, pageSize);
	}
}
