package com.guolaiwan.bussiness.merchant.car.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.guolaiwan.bussiness.merchant.car.po.FlightPO;
import com.guolaiwan.bussiness.merchant.car.po.RoutePO;
import com.guolaiwan.bussiness.merchant.car.po.SitePO;

import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.CountHql;
import pub.caterpillar.orm.hql.QueryHql;

@Repository("com.guolaiwan.bussiness.admin.dao.SiteDAO")
public class SiteDAO extends AbstractBaseDao<SitePO>{
	public List<SitePO> GetListbyPage(int pageNum,int pageSize) {
		 QueryHql hql = this.newQueryHql();
		
		 hql.orderBy("id", true);
	   	 List<SitePO> sites =findByHqlPage(hql,pageNum,pageSize);
	   	 if(sites==null || sites.size()<=0) return null;
	   	 return sites;
	}
	//统计总数
	public int  GetCountByPage() {
		CountHql cHql=this.newCountHql();
		
		int allcount=this.countByHql(cHql);
		return allcount;
	
	}
	
	public List<SitePO> getListByRoute(RoutePO route,int pageNum,int pageSize) {
		 QueryHql hql = this.newQueryHql();
		 hql.andBy("route", Condition.eq, route);
	   	 List<SitePO> sites =findByHqlPage(hql,pageNum,pageSize);
	   	 if(sites==null || sites.size()<=0) return null;
	   	 return sites;
	}

}
