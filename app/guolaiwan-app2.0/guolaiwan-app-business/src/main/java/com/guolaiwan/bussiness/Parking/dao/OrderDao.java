package com.guolaiwan.bussiness.Parking.dao;

import java.text.ParseException;
import java.util.List;

import org.springframework.stereotype.Component;

import com.guolaiwan.bussiness.Parking.po.AttractionsParkingPO;
import com.guolaiwan.bussiness.Parking.po.OrderPO;
import com.guolaiwan.bussiness.Parking.po.VehiclePO;
import com.guolaiwan.bussiness.merchant.car.po.RoutePO;

import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.QueryHql;

@Component
public class OrderDao  extends AbstractBaseDao<OrderPO>{

	

    /**
     * 通过用户id 查询    订单信息
     * @param userId 用户id
     * @param  
     * @param 
     * @return
     * @throws ParseException
     */
       public List<OrderPO>  getOrder(Long userId) throws ParseException{
        	  QueryHql hql = newQueryHql();
        	  hql.andBy("orderId", Condition.eq,userId);
        	  return findByHql(hql);
           
	}
       
     /*  *//**
        * 通过用户id 查询    订单信息
        * @param userId 用户id
        * @param  
        * @param 
        * @return
        * @throws ParseException
        *//*
       public List<OrderPO>  getStatus(Long userId) throws ParseException{
    	   QueryHql hql =   this.newQueryHql();
    	   hql.andBy("orderId", Condition.eq,userId);
    	   return findByHql(hql);
       }
	
	*/
	
	
	
	
	
	
	
}
