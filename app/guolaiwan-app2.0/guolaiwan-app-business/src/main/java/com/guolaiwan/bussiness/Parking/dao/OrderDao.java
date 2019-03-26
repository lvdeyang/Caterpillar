package com.guolaiwan.bussiness.Parking.dao;

import java.text.ParseException;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Component;

import com.guolaiwan.bussiness.Parking.po.AttractionsParkingPO;
import com.guolaiwan.bussiness.Parking.po.CarPositionPO;
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
       /**
        * 通过用户id 车牌 查询  订单信息
        * @param userId 用户id
        * @param  
        * @param 
        * @return
        * @throws ParseException
        */
       public List<OrderPO>  getOrder(Long userId,String vehicle) throws ParseException{
    	   QueryHql hql = newQueryHql();
    	   hql.andBy("orderId", Condition.eq,userId);
    	   hql.andBy("platenumber", Condition.eq,vehicle);
    	   return findByHql(hql);
    	   
       }
       
       /**
        * 通过用户id  景区id 查询信息
        * @param userId 用户id
        * @param  
        * @param 
        * @return
        * @throws ParseException
        */
       public List<OrderPO>  getOrderform(Long userId,Long attractionsId) throws ParseException{
    	   QueryHql hql = newQueryHql();
    	   hql.andBy("orderId", Condition.eq,userId);
    	   hql.andBy("attractionsId", Condition.eq,attractionsId);
    	   return findByHql(hql);
    	   
       }
       /**
        * 通过用户id  景区id 查询信息
        * @param userId 用户id
        * @param  
        * @param 
        * @return
        * @throws ParseException
        */
       public List<OrderPO>  getOrderform(Long userId,Long attractionsId,String vehicle) throws ParseException{
    	   QueryHql hql = newQueryHql();
    	   hql.andBy("orderId", Condition.eq,userId);
    	   hql.andBy("attractionsId", Condition.eq,attractionsId);
    	   hql.andBy("platenumber", Condition.eq,vehicle);
    	   return findByHql(hql);
       }
       
       /**
        * 通过用户id  景区id 查询信息
        * @param userId 用户id
        * @param  
        * @param 
        * @return
        * @throws ParseException
        */
       public OrderPO  getform(Long userId,Long attractionsId,String vehicle) throws ParseException{
    	   QueryHql hql = newQueryHql();
    	   hql.andBy("orderId", Condition.eq,userId);
    	   hql.andBy("attractionsId", Condition.eq,attractionsId);
    	   hql.andBy("platenumber", Condition.eq,vehicle);
    	   List<OrderPO> findByHql = findByHql(hql);
           if(findByHql == null || findByHql.size() ==0) return null;
      	    return findByHql.get(0);
       }
       
       
       
       
       
       
       
       
       
       
       /**
        * 通过用户id 已支付  正在停车  景区id 查询信息
        * @param userId 用户id
        * @param  
        * @param 
        * @return
        * @throws ParseException
        */
       public List<OrderPO>  getOrderform(Long userId,Collection<String> sal,String vehicle) throws ParseException{
    	   QueryHql hql = newQueryHql();
    	   hql.andBy("orderId", Condition.eq,userId);
    	   hql.andBy("orderStatus", Condition.in,sal);
    	   hql.andBy("platenumber", Condition.eq,vehicle);
    	   return findByHql(hql);
    	   
       }
     
  
       
       
       /**
        * 通过用户id 景区id   订单状态  查询订单信息
        * @param userId 用户id
        * @param  
        * @param 
        * @return
        * @throws ParseException
        */
       public List<OrderPO>  getOrderform(Long userId,Long attractionsId,Collection<String> sal,String vehicle) throws ParseException{
    	   QueryHql hql = newQueryHql();
    	   hql.andBy("orderId", Condition.eq,userId);
    	   hql.andBy("attractionsId", Condition.eq,attractionsId);
    	   hql.andBy("orderStatus", Condition.in,sal);
    	   hql.andBy("platenumber", Condition.eq,vehicle);
    	   return findByHql(hql);
    	   
       }
      
       
       
   
       /**
        * 通过用户id   订单状态  查询订单信息
        * @param userId 用户id
        * @param  
        * @param 
        * @return
        * @throws ParseException
        */
       public List<OrderPO>  getOrderform(Long userId,String sal,String vehicle) throws ParseException{
    	   QueryHql hql = newQueryHql();
    	   hql.andBy("orderId", Condition.eq,userId);
    	   hql.andBy("orderStatus", Condition.eq,sal);
    	   hql.andBy("platenumber", Condition.eq,vehicle);
    	   return findByHql(hql);
    	   
       }
       
       /**
        * 通过用户id   订单状态   车牌       停车场名  查询订单信息
        * @param userId 用户id
        * @param  
        * @param 
        * @return
        * @throws ParseException
        */
       public List<OrderPO>  getOrderName(Long userId,Collection<String> sal,String vehicle,String name) throws ParseException{
    	   QueryHql hql = newQueryHql();
    	   hql.andBy("orderId", Condition.eq,userId);
    	   hql.andBy("orderStatus", Condition.in,sal);
    	   hql.andBy("platenumber", Condition.eq,vehicle);
    	   hql.andBy("parkingName", Condition.eq,name);
    	   return findByHql(hql);
    	   
       }
       
       
       
       /**
        * 通过用户id 状态    查询 正在停车信息
        * @param userId 用户id
        * @param  
        * @param 
        * @return
        * @throws ParseException
        */
       public OrderPO  getOrder(Long userId,String sal,String vehicle) throws ParseException{
    	   QueryHql hql = newQueryHql();
    	   hql.andBy("orderId", Condition.eq,userId);
    	   hql.andBy("orderStatus", Condition.eq,sal);
    	   hql.andBy("platenumber", Condition.eq,vehicle);
    	   List<OrderPO> findByHql = findByHql(hql);
    	   if(findByHql == null || findByHql.size() ==0) return null;
    	   return findByHql.get(0);
    	   
       }
       
       /**
        * 通过用户id 信息
        * @param userId 用户id
        * @param  
        * @param 
        * @return
        * @throws ParseException
        */
       public OrderPO  getOrderform(Long Id) throws ParseException{
    	   QueryHql hql = newQueryHql();
    	   hql.andBy("id", Condition.eq,Id);
    	   List<OrderPO> findByHql = findByHql(hql);
    	   if(findByHql == null || findByHql.size() ==0) return null;
    	   return findByHql.get(0);
    	   
       }
       
       
       
       
       
       
       /**
        * 通过用户id  车牌  查询订单信息
        * @param userId 用户id
        * @param  
        * @param 
        * @return
        * @throws ParseException
        */
       public List<OrderPO>  getOrderfor(Long userId,String vehicle) throws ParseException{
    	   QueryHql hql = newQueryHql();
    	   hql.andBy("orderId", Condition.eq,userId);
    	   hql.andBy("platenumber", Condition.eq,vehicle);
    	   return findByHql(hql);
    	   
       }
       
    
	
	
}
