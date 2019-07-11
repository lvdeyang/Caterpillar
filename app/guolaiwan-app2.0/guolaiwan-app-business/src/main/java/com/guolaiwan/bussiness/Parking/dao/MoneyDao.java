package com.guolaiwan.bussiness.Parking.dao;

import java.text.ParseException;
import java.util.List;

import org.springframework.stereotype.Component;

import com.guolaiwan.bussiness.Parking.po.AttractionsParkingPO;
import com.guolaiwan.bussiness.Parking.po.CarPositionPO;
import com.guolaiwan.bussiness.Parking.po.MoneyPO;
import com.guolaiwan.bussiness.Parking.po.OrderPO;
import com.guolaiwan.bussiness.Parking.po.VehiclePO;
import com.guolaiwan.bussiness.merchant.car.po.RoutePO;

import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.CountHql;
import pub.caterpillar.orm.hql.QueryHql;

@Component
public class MoneyDao  extends AbstractBaseDao<MoneyPO>{

	
	 /**
     * 根据景区 id 车型 区数   查询停车费用
     * @param userId 用户id
     * @param  
     * @param 
     * @return
     * @throws ParseException
     */
    public List<MoneyPO>  getMoney(long uid,String  vehicle,String district,String parkingLayer) throws ParseException{
 	   QueryHql hql =   this.newQueryHql();
 	   hql.andBy("attractionsId",Condition.eq,uid );
 	   hql.andBy("parkingModel",Condition.eq,vehicle );
 	   hql.andBy("area",Condition.eq,district );
 	   hql.andBy("tier",Condition.eq,parkingLayer );
 	   return findByHql(hql);
    }
 
       

	/**
	 * 景区停车场分页
	 * 
	 * @param strMap
	 * @param page
	 * @param limit
	 * @return
	 */
	public List<MoneyPO> findByPageC( int pageNum, int pageSize) {
		QueryHql hql = this.newQueryHql();
		hql.orderBy("updateTime", true);
		List<MoneyPO> poList = findByHqlPage(hql, pageNum, pageSize);
		return poList;
	}
	
	/**
	 * 分页
	 * 
	 * @param strMap
	 * @return
	 */
	public int CountByPageC() {
		CountHql cHql = newCountHql();
		int count = countByHql(cHql);
		return count;
	}
}
