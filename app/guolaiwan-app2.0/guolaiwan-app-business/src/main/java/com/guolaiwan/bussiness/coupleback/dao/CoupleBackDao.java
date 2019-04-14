package com.guolaiwan.bussiness.coupleback.dao;

import java.text.ParseException;
import java.util.List;

import org.springframework.stereotype.Component;

import com.guolaiwan.bussiness.Parking.po.AttractionsParkingPO;
import com.guolaiwan.bussiness.Parking.po.CarPositionPO;
import com.guolaiwan.bussiness.Parking.po.OrderPO;
import com.guolaiwan.bussiness.Parking.po.ParkingPositionPO;
import com.guolaiwan.bussiness.coupleback.po.CoupleBackPO;

import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.QueryHql;
@Component
public class CoupleBackDao  extends AbstractBaseDao<CoupleBackPO> {

	/**
	 *  添加回复反馈数据
	 * @param 
	 * @param  
	 * @param 
	 * @return
	 * @throws ParseException
	 */
	public List<CoupleBackPO>  setOrderform(int type) throws ParseException{
		QueryHql hql = newQueryHql();
		hql.andBy("state", Condition.eq, type);
		return findByHql(hql);
	}








	/**
	 *  查询所有订单 分页
	 * @param userId 用户id
	 * @param  
	 * @param 
	 * @return
	 * @throws ParseException
	 */
	public List<CoupleBackPO>  getOrderform(int pageNum, int pageSize) throws ParseException{
		QueryHql hql = newQueryHql();
		hql.orderBy("date", true);
		List<CoupleBackPO> orders = findByHqlPage(hql, pageNum, pageSize);
		return orders;
	}


	/**
	 *  根据状态  查询所有订单 分页 
	 * @param userId 用户id
	 * @param  
	 * @param 
	 * @return
	 * @throws ParseException
	 */
	public List<CoupleBackPO>  getOrderform(int pageNum, int pageSize,int type) throws ParseException{
		QueryHql hql = newQueryHql();
		hql.andBy("state", Condition.eq, type);
		List<CoupleBackPO> orders = findByHqlPage(hql, pageNum, pageSize);
		
		return orders;
	}


	/**
	 *  根据状态  查询 多少条
	 * @param userId 用户id
	 * @param  
	 * @param 
	 * @return
	 * @throws ParseException
	 */
	public List<CoupleBackPO>  getOrderform(int type) throws ParseException{
		QueryHql hql = newQueryHql();
		hql.andBy("state", Condition.eq, type);
		return findByHql(hql);
	}



	/**
	 *  根据订单id 商家回复
	 * @param userId 用户id
	 * @param  
	 * @param 
	 * @return
	 * @throws ParseException
	 */
	public CoupleBackPO  getOrder(long orderid) throws ParseException{
		QueryHql hql = newQueryHql();
		hql.andBy("id", Condition.eq, orderid);
		List<CoupleBackPO> findByHql = findByHql(hql);
		if(findByHql == null || findByHql.size() ==0) return null;
		return findByHql.get(0);
	}













}
