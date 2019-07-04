package com.guolaiwan.bussiness.Parking.dao;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.guolaiwan.bussiness.Parking.po.AttractionsParkingPO;
import com.guolaiwan.bussiness.Parking.po.CarPositionPO;

import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.CountHql;
import pub.caterpillar.orm.hql.QueryHql;

/**
 * 景区信息查询
 * 
 * @author Administrator
 *
 */
@Component
public class AttractionsDao extends AbstractBaseDao<AttractionsParkingPO> {

	/**
	 * 查询所有 景区信息 查询开始 结束
	 * 
	 * @param userId
	 *            用户id
	 * @param
	 * @param
	 * @return
	 * @throws ParseException
	 */
	public List<AttractionsParkingPO> getInformation(int pageNum, int pageSize) throws ParseException {
		QueryHql hql = this.newQueryHql();
		List<AttractionsParkingPO> products = findByHqlPage(hql, pageNum, pageSize);
		if (products == null || products.size() <= 0)
			return null;
		return products;
	}

	/**
	 * 根据名称 查询 景区信息
	 * 
	 * @param userId
	 *            用户id
	 * @param
	 * @param
	 * @return
	 * @throws ParseException
	 */
	public List<AttractionsParkingPO> getMessage(String name) throws ParseException {
		QueryHql hql = this.newQueryHql();
		hql.andBy("parkingName", Condition.eq, name);
		return findByHql(hql);
	}

	/**
	 * 根据Id查询景区 信息
	 * 
	 * @param userId
	 *            用户id
	 * @param
	 * @param
	 * @return
	 * @throws ParseException
	 */
	public AttractionsParkingPO getUid(Long uid) throws ParseException {
		QueryHql hql = this.newQueryHql();
		hql.andBy("id", Condition.eq, uid);
	    List<AttractionsParkingPO> findByHql = findByHql(hql);
	    if(findByHql == null || findByHql.size() ==0) return null;
	    return findByHql.get(0);
	}

	
	/**
	 * 根据Id查询景区条例 所有 和车牌
	 * 
	 * @param userId
	 *            用户id
	 * @param
	 * @param
	 * @return
	 * @throws ParseException
	 */
	public List<AttractionsParkingPO> getUid(Long uid,String vehicle) throws ParseException {
		QueryHql hql = this.newQueryHql();
		hql.andBy("id", Condition.eq, uid);
		hql.andBy("platenumber", Condition.eq,vehicle);
		return findByHql(hql);
	}
	
	
	
	
	/**
	 * 根据输入 模糊 查询景区名称
	 * 
	 * @param userId
	 *            用户id
	 * @param
	 * @param
	 * @return
	 * @throws ParseException
	 */
	public List<AttractionsParkingPO> getQuery(String name) throws ParseException {
		QueryHql hql = this.newQueryHql();
		hql.andBy("parkingName", Condition.lk, name);
		List<AttractionsParkingPO> products = findByHqlPage(hql, 0, 10);
		if (products == null || products.size() <= 0)
			return null;
		return products;
	}

	/**
	 * 根据id 查询景点时间 电话
	 * 
	 * @param userId
	 *            用户id
	 * @param
	 * @param
	 * @return
	 * @throws ParseException
	 */
	public List<AttractionsParkingPO> getBusinessHours(Long uid) throws ParseException {
		QueryHql hql = this.newQueryHql();
		hql.andBy("id", Condition.eq, uid);
		return findByHql(hql);
	}

	/**
	 * 景区所属停车场
	 * 
	 * @param names
	 * @param values
	 * @return
	 */
	public int getCountByPageA(String[] names, Object[] values) {
		CountHql cHql = this.newCountHql();
		for (int i = 0; i < names.length; i++) {
			cHql.andBy(names[i], Condition.eq, values[i]);
		}
		int allcount = this.countByHql(cHql);
		return allcount;
	}

	/**
	 * 景区停车场分页
	 * 
	 * @param strMap
	 * @param page
	 * @param limit
	 * @return
	 */
	public List<AttractionsParkingPO> findByPageC( int pageNum, int pageSize) {
		QueryHql hql = this.newQueryHql();
		hql.orderBy("updateTime", true);
		List<AttractionsParkingPO> poList = findByHqlPage(hql, pageNum, pageSize);
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

	/**
	 * 获取单一停车场下的所有车位
	 * 
	 * @param attractionsId
	 * @return
	 */
	public List<AttractionsParkingPO> getByAttractionsId(Long attractionsId) {
		QueryHql hql = this.newQueryHql();
		hql.andBy("attractionsId", Condition.eq, attractionsId);
		return findByHql(hql);
	}

	/**
	 * 获取所有停车场
	 * 
	 * @return
	 */
	public List<AttractionsParkingPO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
