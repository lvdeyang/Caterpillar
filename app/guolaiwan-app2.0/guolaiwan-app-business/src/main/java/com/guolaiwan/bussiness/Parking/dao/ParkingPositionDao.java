package com.guolaiwan.bussiness.Parking.dao;

import java.text.ParseException;
import java.util.List;

import org.springframework.stereotype.Component;

import com.guolaiwan.bussiness.Parking.po.ParkingPositionPO;

import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.QueryHql;

/**
 * 景区车位
 * 
 * @author Administrator
 *
 */
@Component
public class ParkingPositionDao extends AbstractBaseDao<ParkingPositionPO> {

	public List<ParkingPositionPO> getInformation(int pageNum, int pageSize) throws ParseException {
		QueryHql hql = this.newQueryHql();
		List<ParkingPositionPO> products = findByHqlPage(hql, pageNum, pageSize);
		if (products == null || products.size() <= 0)
			return null;
		return products;
	}

	public List<ParkingPositionPO> getMessage(String name) throws ParseException {
		QueryHql hql = this.newQueryHql();
		hql.andBy("parkingName", Condition.eq, name);
		return findByHql(hql);
	}

	public List<ParkingPositionPO> getUid(Long uid) throws ParseException {
		QueryHql hql = this.newQueryHql();
		hql.andBy("id", Condition.eq, uid);
		return findByHql(hql);
	}

	public List<ParkingPositionPO> getQuery(String name) throws ParseException {
		QueryHql hql = this.newQueryHql();
		hql.andBy("parkingName", Condition.lk, name);
		List<ParkingPositionPO> products = findByHqlPage(hql, 0, 10);
		if (products == null || products.size() <= 0)
			return null;
		return products;
	}

	public List<ParkingPositionPO> getBusinessHours(Long uid) throws ParseException {
		QueryHql hql = this.newQueryHql();
		hql.andBy("id", Condition.eq, uid);
		return findByHql(hql);
	}

	public List<ParkingPositionPO> pageByPositionId(int pageNum, int pageSize, long attractionId) {
		QueryHql hql = this.newQueryHql();
		hql.andBy("positionId", Condition.eq, attractionId);
		hql.orderBy("updateTime", true);
		List<ParkingPositionPO> poList = findByHqlPage(hql, pageNum, pageSize);
		return poList;
	}

	public List<ParkingPositionPO> getByPositionId(Long positionId) {
		QueryHql hql = this.newQueryHql();
		hql.andBy("positionId", Condition.eq, positionId);
		return findByHql(hql);
	}

}
