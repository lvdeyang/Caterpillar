package com.guolaiwan.bussiness.admin.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import com.guolaiwan.bussiness.admin.po.CityInfoPO;

import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;
import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.CountHql;
import pub.caterpillar.orm.hql.QueryHql;

@Repository("com.guolaiwan.bussiness.admin.dao.CityInfoDAO")
public class CityInfoDAO extends AbstractBaseDao<CityInfoPO>{
	public CityInfoPO getCityByName(String cityName){
		QueryHql hql = this.newQueryHql();
		hql.andBy("cityinfo", Condition.eq, cityName);
		List<CityInfoPO> cityinfos = findByHql(hql);
		if(cityinfos==null || cityinfos.size()<=0) return null;
		return cityinfos.get(0);
	}
	public List<CityInfoPO> GetListbyPage(int pageNum,int pageSize) {
		QueryHql hql = this.newQueryHql();
		hql.orderBy("id", true);
		List<CityInfoPO> cityinfos =findByHqlPage(hql,pageNum,pageSize);
		if(cityinfos==null || cityinfos.size()<=0) return null;
		return cityinfos;
	}
	//统计总数
	public int  GetCountByPage() {
		CountHql cHql=this.newCountHql();

		int allcount=this.countByHql(cHql);
		return allcount;
	}

	//获取启用的城市
	public List<CityInfoPO> getCitys() {
		QueryHql hql = this.newQueryHql();

		hql.andBy("enable", Condition.eq, 1);
		List<CityInfoPO> cityinfos =findByHql(hql);
		if(cityinfos==null || cityinfos.size()<=0) return null;
		return cityinfos;
	}
	
	
	public Long getMaxId(){
		StringBufferWrapper sqlWrapper = new StringBufferWrapper()
				.append("select max(a.id) maxid from t_sys_cityinfo a");
		SQLQuery query = getCurrentSession().createSQLQuery(sqlWrapper.toString()).addScalar("maxid",StandardBasicTypes.LONG);
		if(query.uniqueResult()==null) return 0l;
		long maxId = (long)query.uniqueResult();
		return maxId;
	}
}
