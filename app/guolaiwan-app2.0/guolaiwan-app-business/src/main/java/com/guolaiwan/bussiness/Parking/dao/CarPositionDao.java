package com.guolaiwan.bussiness.Parking.dao;

import java.text.ParseException;
import java.util.List;

import org.springframework.stereotype.Component;

import com.guolaiwan.bussiness.Parking.po.AttractionsParkingPO;
import com.guolaiwan.bussiness.Parking.po.CarPositionPO;
import com.guolaiwan.bussiness.Parking.po.OrderPO;

import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.QueryHql;

/**
 * 景区车位查询
 * @author Administrator
 *
 */
@Component
public class CarPositionDao  extends AbstractBaseDao<CarPositionPO>  {

	
	  
    /**
     * 根据id 查询 车位 信息
     * @param userId 用户id
     * @param  
     * @param 
     * @return
     * @throws ParseException
     */
    public List<CarPositionPO>  getInformation(long uid) throws ParseException{
 	   QueryHql hql =   this.newQueryHql();
 	   hql.andBy("attractionsId",Condition.eq,uid );
 	   return findByHql(hql);
    }
    
   
    
    
    /**
     * 根据id 查询 选择车位 图
     * @param userId 用户id
     * @param  
     * @param 
     * @return
     * @throws ParseException
     */
    public List<CarPositionPO>  getInfor(long uid,String ceng,String qu) throws ParseException{
    	QueryHql hql =   this.newQueryHql();
    	hql.andBy("attractionsId",Condition.eq,uid );
    	hql.andBy("number",Condition.eq,ceng);
    	hql.andBy("district",Condition.eq,qu);
    	return findByHql(hql);
    }
    
    /**
     * 根据id   层数  区数   获取id
     * @param userId 用户id
     * @param  
     * @param 
     * @return
     * @throws ParseException
     */
    public CarPositionPO  getAmend(long uid,String parkingLayer,String district) throws ParseException{
    	QueryHql hql =   this.newQueryHql();
    	hql.andBy("attractionsId",Condition.eq,uid );
    	hql.andBy("number",Condition.eq,parkingLayer);
    	hql.andBy("district",Condition.eq,district);
    	 List<CarPositionPO> findByHql = findByHql(hql);
        if(findByHql == null || findByHql.size() ==0) return null;
   	    return findByHql.get(0);
    }
    
    
    
    
    
    /**
     * 根据id  层数查询  几区 
     * @param userId 用户id
     * @param  
     * @param 
     * @return
     * @throws ParseException
     */
    public List<CarPositionPO>  getTier(long uid,String Tier) throws ParseException{
    	QueryHql hql =   this.newQueryHql();
    	hql.andBy("attractionsId",Condition.eq,uid );
    	hql.andBy("number",Condition.eq,Tier );
    	return findByHql(hql);
    }
	
	
    
    
    
    
	
	
}
