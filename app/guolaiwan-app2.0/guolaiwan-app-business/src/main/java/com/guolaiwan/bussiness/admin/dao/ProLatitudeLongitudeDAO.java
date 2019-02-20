package com.guolaiwan.bussiness.admin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.guolaiwan.bussiness.admin.po.ProLatitudeLongitudePO;

import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.QueryHql;

@Repository("com.guolaiwan.bussiness.admin.dao.ProLatitudeLongitudeDAO")
public class ProLatitudeLongitudeDAO extends AbstractBaseDao<ProLatitudeLongitudePO> {
	
	/**
	 * Liw
	 * 根据景区id查询景区经纬度表所有
	 * @param 页面传过来的景区id
	 * @return 景区经纬度表list
	 */
	public List<ProLatitudeLongitudePO> getProLatitudeLongitudeById(long id){
		QueryHql hql = this.newQueryHql();
		hql.andBy("id", Condition.eq, id);
		List<ProLatitudeLongitudePO> list = findByHql(hql);
		return list;
	}
	
	
	
}