package com.guolaiwan.bussiness.merchant.car.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.guolaiwan.bussiness.merchant.car.po.RoutePO;

import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.CountHql;
import pub.caterpillar.orm.hql.QueryHql;

@Repository("com.guolaiwan.bussiness.admin.dao.RouteDAO")
public class RouteDAO extends AbstractBaseDao<RoutePO>{
	
	//统计车队下路线的数量
	public int countByMerchant(String merchant){
		CountHql hql = this.newCountHql();
		hql.andBy("merchant.uuid", Condition.eq, merchant);
		return this.countByHql(hql);
	}
	
	//获取车队的所有路线
	public List<RoutePO> queryByMerchant(String merchant, int pageNum, int pageSize){
		QueryHql hql = this.newQueryHql();
		hql.andBy("merchant.uuid", Condition.eq, merchant);
		return this.findByHql(hql, pageNum, pageSize);
	}
	
	public List<RoutePO> GetListbyPage(int pageNum,int pageSize) {
		 QueryHql hql = this.newQueryHql();
		
		 hql.orderBy("id", true);
	   	 List<RoutePO> Routes =findByHqlPage(hql,pageNum,pageSize);
	   	 if(Routes==null || Routes.size()<=0) return null;
	   	 return Routes;
	}
	
	//统计总数
	public int  GetCountByPage() {
		CountHql cHql=this.newCountHql();
		
		int allcount=this.countByHql(cHql);
		return allcount;
	}
	
	/***
	 * 
	 * @param lab
	 * @return route
	 */
	public RoutePO getRouteByLab(String lab) {
		 QueryHql hql = this.newQueryHql();
		 hql.andBy("lab", Condition.eq, lab);
	   	 RoutePO route =getByHql(hql);
	   	 return route;
	}

}
