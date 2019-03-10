package com.guolaiwan.bussiness.Parking.dao;

import java.text.ParseException;
import java.util.List;

import org.springframework.stereotype.Component;

import com.guolaiwan.bussiness.Parking.po.AttractionsParkingPO;
import com.guolaiwan.bussiness.Parking.po.OrderPO;
import com.guolaiwan.bussiness.Parking.po.ParkingPositionPO;
import com.guolaiwan.bussiness.Parking.po.VehiclePO;
import com.guolaiwan.bussiness.merchant.car.po.RoutePO;

import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.QueryHql;

@Component
public class ParkingPositionDao extends AbstractBaseDao<ParkingPositionPO>{

	

	 /**
     *  根据查询车位信息
     * @param userId 用户id
     * @param  
     * @param 
     * @return
     * @throws ParseException
     */
    public List<ParkingPositionPO>  getTruck(Long uid) throws ParseException{
 	   QueryHql hql = this.newQueryHql();
 	   hql.andBy("positionId",Condition.eq, uid);
 	   return findByHql(hql);
    }
    
       
    
	
	
}
