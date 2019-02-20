package com.guolaiwan.bussiness.Parking.dao;

import java.text.ParseException;
import java.util.List;

import org.bytedeco.javacpp.RealSense.intrinsics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.guolaiwan.bussiness.Parking.po.VehiclePO;
import com.guolaiwan.bussiness.operation.po.WebsiteRecord;

import pub.caterpillar.commons.util.date.DateUtil;
import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.CountHql;
import pub.caterpillar.orm.hql.QueryHql;


@Component
public class VehicleDao extends AbstractBaseDao<VehiclePO> {
	
	/*
	*//**
	 * 添加  用户车型
	 * @param userId 用户id 
	 * @param number  用户车牌
	 * @param type 用户车型
	 * @throws ParseException
	 *//*
       public void  setNumber(Long userId,String number,String type) throws ParseException{
    	   vehiclePO.setVehicleid(userId);
    	   vehiclePO.setNumber(number);
    	   vehiclePO.setType(type);
    	   this.save(vehiclePO);
	}*/
	
	
	
    /**
     * 通过用户id 查询    用户信息
     * @param userId 用户id
     * @param number 用户车牌
     * @param type 用户车型
     * @return
     * @throws ParseException
     */
       public List<VehiclePO>  getNumber(Long userId) throws ParseException{
    	   QueryHql hql =   this.newQueryHql();
    	   hql.andBy("vehicleid", Condition.eq,userId);
    	  return findByHql(hql);
	}
       
    
       
       
       
       
      
       
       
       
       
       
       
	
}
