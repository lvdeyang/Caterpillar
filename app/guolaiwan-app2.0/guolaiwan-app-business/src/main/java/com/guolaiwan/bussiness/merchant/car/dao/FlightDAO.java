package com.guolaiwan.bussiness.merchant.car.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.guolaiwan.bussiness.merchant.car.enumeration.FlightType;
import com.guolaiwan.bussiness.merchant.car.po.FlightPO;
import com.guolaiwan.bussiness.merchant.car.po.RoutePO;

import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.CountHql;
import pub.caterpillar.orm.hql.QueryHql;

@Repository("com.guolaiwan.bussiness.admin.dao.FlightDAO")
public class FlightDAO extends AbstractBaseDao<FlightPO>{

	//统计路线下的班次
	public int countByRouteAndType(String routeUuid, FlightType type){
		CountHql hql = this.newCountHql();
		hql.andBy("route.uuid", Condition.eq, routeUuid);
		hql.andBy("flightType", Condition.eq, type);
		return this.countByHql(hql);
	}
	
	//根据路线获取班次
	public List<FlightPO> queryByRouteAndType(String routeUuid, FlightType type, int pageNum, int pageSize){
		QueryHql hql = this.newQueryHql();
		hql.andBy("route.uuid", Condition.eq, routeUuid);
		hql.andBy("flightType", Condition.eq, type);
		return this.findByHql(hql, pageNum, pageSize);
	}
	
	//获取所有
	public List<FlightPO> GetListbyPage(int pageNum,int pageSize) {
		QueryHql hql = this.newQueryHql();
		hql.orderBy("id", true);
		List<FlightPO> flights =findByHqlPage(hql,pageNum,pageSize);
		if(flights==null || flights.size()<=0) return null;
		return flights;
	}

	//获取总数
	public int  GetCountByPage() {
		CountHql cHql=this.newCountHql();
		int allcount=this.countByHql(cHql);
		return allcount;

	}

	//获取所有班次
	public List<FlightPO> findAllCT(int pageNum,int pageSize) {
		QueryHql hql = this.newQueryHql();
		hql.andBy("flightType", Condition.eq, FlightType.CARTIME);
		hql.orderBy("goTime", false);
		List<FlightPO> flights =findByHqlPage(hql,pageNum,pageSize);
		if(flights==null || flights.size()<=0) return null;
		return flights;
	}


	//获取所有包车
	public List<FlightPO> findAllCR(int pageNum,int pageSize) {
		QueryHql hql = this.newQueryHql();
		hql.andBy("flightType", Condition.eq, FlightType.CONTRACT);
		hql.orderBy("goTime", false);
		List<FlightPO> flights =findByHqlPage(hql,pageNum,pageSize);
		if(flights==null || flights.size()<=0) return null;
		return flights;
	}




	//该路线下的所有flight
	public List<FlightPO> findAllR(RoutePO route,int pageNum,int pageSize) {
		QueryHql hql = this.newQueryHql();
		hql.andBy("route", Condition.eq, route);
		hql.orderBy("goTime", false);
		List<FlightPO> flights =findByHqlPage(hql,pageNum,pageSize);
		if(flights==null || flights.size()<=0) return null;
		return flights;
	}

	//该路线下的所有flight班次
	public List<FlightPO> findAllRCT(RoutePO route,int pageNum,int pageSize) {
		if(route==null){
			return null;
		}
		QueryHql hql = this.newQueryHql();
		hql.andBy("route", Condition.eq, route);
		hql.andBy("flightType", Condition.eq, FlightType.CARTIME);
		hql.orderBy("goTime", false);
		List<FlightPO> flights =findByHqlPage(hql,pageNum,pageSize);
		if(flights==null || flights.size()<=0) return null;
		return flights;
	}

	//该路线下的所有flight包车
	public List<FlightPO> findAllRCR(RoutePO route,int pageNum,int pageSize) {
		if(route==null){
			return null;
		}
		QueryHql hql = this.newQueryHql();
		hql.andBy("route", Condition.eq, route);
		hql.andBy("flightType", Condition.eq, FlightType.CONTRACT);
		hql.orderBy("goTime", false);
		List<FlightPO> flights =findByHqlPage(hql,pageNum,pageSize);
		if(flights==null || flights.size()<=0) return null;
		return flights;
	}







}
