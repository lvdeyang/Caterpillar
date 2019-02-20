package com.guolaiwan.bussiness.admin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.guolaiwan.bussiness.admin.po.SysConfigPO;

import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.QueryHql;


@Repository("com.guolaiwan.bussiness.admin.dao.SysConfigDAO")
public class SysConfigDAO extends AbstractBaseDao<SysConfigPO> {
	public SysConfigPO getSysConfig(){
		QueryHql hql = this.newQueryHql();
		List<SysConfigPO> sysConfigs = findByHql(hql);
		if(sysConfigs.size()<=0||sysConfigs ==null) return null;
		return sysConfigs.get(0);
	}
}
