package com.guolaiwan.bussiness.admin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.guolaiwan.bussiness.admin.po.VoicePO;
import com.guolaiwan.bussiness.admin.po.ZbiaoPO;

import javassist.runtime.Desc;
import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.CountHql;
import pub.caterpillar.orm.hql.DeleteHql;
import pub.caterpillar.orm.hql.QueryHql;


@Repository("com.guolaiwan.bussiness.admin.dao.ZbiaoDAO")
public class ZbiaoDAO extends AbstractBaseDao<ZbiaoPO> {
	public List<ZbiaoPO> findAll(final int page, final int pageSize) {
		
		QueryHql hql = newQueryHql();
		hql.orderBy("modular", true);
		return findByHql(hql, page, pageSize);
	}
	
}
