package com.guolaiwan.bussiness.admin.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import com.guolaiwan.bussiness.admin.enumeration.CompanyType;
import com.guolaiwan.bussiness.admin.po.CompanyPO;
import com.guolaiwan.bussiness.admin.po.VoicePO;
import com.guolaiwan.bussiness.admin.po.ZbiaoPO;

import javassist.runtime.Desc;
import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;
import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.CountHql;
import pub.caterpillar.orm.hql.DeleteHql;
import pub.caterpillar.orm.hql.QueryHql;


@Repository("com.guolaiwan.bussiness.admin.dao.CompanyDAO")
public class CompanyDAO extends AbstractBaseDao<CompanyPO> {
	
	
	/**
	 * app首页获取所有分公司（有效）
	 * @param 
	 * @param 
	 * @return
	 */
	public List<CompanyPO> appfindAll() {
		QueryHql hql = newQueryHql();
		hql.andBy("enable", Condition.eq, 1);
		hql.orderBy("cType", false);
		hql.orderBy("updateTime", true);
		return findByHql(hql);
	}
	
	public CompanyPO  appGetByCode(String code) {
		QueryHql hql = newQueryHql();
		hql.andBy("comCode", Condition.eq, code);
		List<CompanyPO> companys = findByHql(hql);
		if(companys==null||companys.size()==0) return null;
		return companys.get(0);
	}
	
	
	public List<CompanyPO> findAll(final int page, final int pageSize) {
		QueryHql hql = newQueryHql();
		hql.orderBy("cType", false);
		hql.orderBy("updateTime", true);
		return findByHql(hql, page, pageSize);
	}

	//获取当前最大的Id
	public long  getMaxId() {
		StringBufferWrapper sqlWrapper = new StringBufferWrapper()
				.append("select max(a.id) maxid from t_sys_company a");
		SQLQuery query = getCurrentSession().createSQLQuery(sqlWrapper.toString()).addScalar("maxid",StandardBasicTypes.LONG);
		if(query.uniqueResult()==null) return 0l;
		long maxId = (long)query.uniqueResult();
		return maxId;
	}
	
	
	public CompanyPO  getByCode(String code) {
		QueryHql hql = newQueryHql();
		hql.andBy("comCode", Condition.eq, code);
		List<CompanyPO> companys = findByHql(hql);
		if(companys==null||companys.size()==0) return null;
		return companys.get(0);
	}
	
	
	//获取所有有效子公司
	public List<CompanyPO> findSonList() {
		QueryHql hql = newQueryHql();
		hql.andBy("cType", Condition.eq, CompanyType.SON);
		hql.andBy("enable", Condition.eq, 1);
		List<CompanyPO> companys = findByHql(hql);
		if(companys==null||companys.size()==0) return null;
		return companys;
	}
	
	
	

}
