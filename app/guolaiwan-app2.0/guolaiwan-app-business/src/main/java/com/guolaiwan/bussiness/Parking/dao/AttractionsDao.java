package com.guolaiwan.bussiness.Parking.dao;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.guolaiwan.bussiness.Parking.po.AttractionsParkingPO;
import com.guolaiwan.bussiness.Parking.po.OrderPO;
import com.guolaiwan.bussiness.Parking.po.VehiclePO;
import com.guolaiwan.bussiness.admin.po.ProductPO;

import pub.caterpillar.commons.util.date.DateUtil;
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
    	  return products;
	}
      
       
       /**
        * 根据名称 查询  景区信息  
        * @param userId 用户id
        * @param  
        * @param 
        * @return
        * @throws ParseException
        */
       public List<AttractionsParkingPO>  getMessage(String name) throws ParseException{
    	   QueryHql hql =   this.newQueryHql();
    	   hql.andBy("parkingName",Condition.eq,name );
    	   return findByHql(hql);
       }
	
       
       /**
        *  根据Id查询景区条例 所有
        * @param userId 用户id
        * @param  
        * @param 
        * @return
        * @throws ParseException
        */
       public List<AttractionsParkingPO>  getUid(Long uid) throws ParseException{
    	   QueryHql hql =   this.newQueryHql();
    		hql.andBy("id",Condition.eq, uid);
    	   return findByHql(hql);
       }
       
       /**
        *  根据输入 模糊 查询景区名称  
        * @param userId 用户id
        * @param  
        * @param 
        * @return
        * @throws ParseException
        */
       public List<AttractionsParkingPO>  getQuery(String name) throws ParseException{
    	   QueryHql hql =   this.newQueryHql();
    	   hql.andBy("parkingName",Condition.lk, name);
    	   List<AttractionsParkingPO> products =findByHqlPage(hql,0,10);
    	   if(products == null || products.size() <= 0) return null;
    	   return products;
       }
       
       /**
        *  根据id  查询景点时间 电话
        * @param userId 用户id
        * @param  
        * @param 
        * @return
        * @throws ParseException
        */
       public List<AttractionsParkingPO>  getBusinessHours(Long uid) throws ParseException{
    	   QueryHql hql =   this.newQueryHql();
    	   hql.andBy("id",Condition.eq, uid);
    	   return findByHql(hql);
       }
       
      

	
	
	
	
	
	
	
	
}
