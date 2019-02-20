package com.guolaiwan.bussiness.Parking.dao;

import java.text.ParseException;
import java.util.List;

import org.springframework.stereotype.Component;

import com.guolaiwan.bussiness.Parking.po.AttractionsParkingPO;
import com.guolaiwan.bussiness.Parking.po.OrderPO;
import com.guolaiwan.bussiness.Parking.po.VehiclePO;
import com.guolaiwan.bussiness.admin.po.ProductPO;

import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.QueryHql;
/**
 * 景区信息查询
 * @author Administrator
 *
 */
@Component
public class AttractionsDao  extends AbstractBaseDao<AttractionsParkingPO> {

	

    /**
     * 查询所有 景区信息  查询开始 结束
     * @param userId 用户id
     * @param  
     * @param 
     * @return
     * @throws ParseException
     */
       public List<AttractionsParkingPO>  getInformation( int pageNum, int pageSize) throws ParseException{
    	   QueryHql hql =   this.newQueryHql();
    	   List<AttractionsParkingPO> products =findByHqlPage(hql,pageNum,pageSize);
    	   if(products == null || products.size() <= 0) return null;
    	  return findByHql(hql);
	}
	
	
	
	
	
	
	
	
	
}
