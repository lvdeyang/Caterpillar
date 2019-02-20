package com.guolaiwan.bussiness.merchant.car.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.guolaiwan.bussiness.admin.po.ProductPO;
import com.guolaiwan.bussiness.merchant.car.po.DriverPO;

import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.CountHql;
import pub.caterpillar.orm.hql.QueryHql;

@Repository("com.guolaiwan.bussiness.admin.dao.DriverDAO")
public class DriverDAO extends AbstractBaseDao<DriverPO>{
	public List<DriverPO> GetListbyPage(int pageNum,int pageSize) {
		QueryHql hql = this.newQueryHql();

		hql.orderBy("id", true);
		List<DriverPO> Drivers =findByHqlPage(hql,pageNum,pageSize);
		if(Drivers==null || Drivers.size()<=0) return null;
		return Drivers;
	}
	//统计总数
	public int  GetCountByPage() {
		CountHql cHql=this.newCountHql();

		int allcount=this.countByHql(cHql);
		return allcount;

	}

}
