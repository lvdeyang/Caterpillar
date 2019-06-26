package com.guolaiwan.bussiness.admin.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import com.guolaiwan.bussiness.admin.po.ModularPO;

import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;
import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.CountHql;
import pub.caterpillar.orm.hql.QueryHql;

@Repository("com.guolaiwan.bussiness.admin.dao.ModularDAO")

public class ModularDAO extends AbstractBaseDao<ModularPO>{

	//获取显示有序的modulars
	public List<ModularPO> getModulars(){
		QueryHql hql = this.newQueryHql();
		hql.andBy("modularIsv", Condition.eq, 1);
		hql.orderBy("sort", false);
		List<ModularPO> modulars = findByHql(hql);
		if(modulars==null || modulars.size()<=0) return null;
		return modulars;
	}
 
	public List<ModularPO> getfindAll(final int page, final int pageSize) {
		QueryHql hql = newQueryHql();
		hql.orderBy("sortindex", false);
		return findByHql(hql, page, pageSize);
	}



	public ModularPO getModularByCode(String modularcode){
		QueryHql hql = this.newQueryHql();
		hql.andBy("modularCode", Condition.eq, modularcode);
		List<ModularPO> modulars = findByHql(hql);
		if(modulars==null || modulars.size()<=0) return null;
		return modulars.get(0);
	}
	public List<ModularPO> GetListbyPage(int pageNum,int pageSize) {
		QueryHql hql = this.newQueryHql();

		hql.orderBy("id", true);
		List<ModularPO> modulars =findByHqlPage(hql,pageNum,pageSize);
		if(modulars==null || modulars.size()<=0) return null;
		return modulars;
	}
	//统计总数
	public int  GetCountByPage() {
		CountHql cHql=this.newCountHql();

		int allcount=this.countByHql(cHql);
		return allcount;
	}


	//获取最大的Id
	public long  getMaxId() {
		StringBufferWrapper sqlWrapper = new StringBufferWrapper()
				.append("select max(a.id) maxid from t_sys_modular a");
		SQLQuery query = getCurrentSession().createSQLQuery(sqlWrapper.toString()).addScalar("maxid",StandardBasicTypes.LONG);
		if(query.uniqueResult()==null) return 0l;
		long maxId = (long)query.uniqueResult();
		return maxId;
	}


	//获取公司下面的所有分类
	public List<ModularPO> findByCom(long comId,int pageNum,int pageSize) {
		QueryHql hql = this.newQueryHql();
		if(comId!=1l){
			hql.andBy("comId", Condition.eq, comId);
		}
		hql.orderBy("updateTime", true);
		List<ModularPO> modulars =findByHqlPage(hql,pageNum,pageSize);
		if(modulars==null || modulars.size()<=0) return null;
		return modulars;
	}
	//获取公司下面的所有分类
	public List<ModularPO> findByCom(long comId) {
		QueryHql hql = this.newQueryHql();
		if(comId!=1l){
			hql.andBy("comId", Condition.eq, comId);
		}
		hql.orderBy("updateTime", true);
		List<ModularPO> modulars =findByHql(hql);
		if(modulars==null || modulars.size()<=0) return null;
		return modulars;
	}


	//获取公司下面的所有分类的个数
	public int  countByCom(long comId) {
		CountHql cHql=this.newCountHql();
		if(comId!=1l){
			cHql.andBy("comId", Condition.eq, comId);
		}
		int allcount=this.countByHql(cHql);
		return allcount;
	}


	//获取公司下面的所有有效分类
	public List<ModularPO> findByComIsv(long comId) {
		QueryHql hql = this.newQueryHql();
		hql.andBy("comId", Condition.eq, comId);
		hql.andBy("modularIsv", Condition.eq, 1);
		hql.orderBy("updateTime", true);
		List<ModularPO> modulars =findByHql(hql);
		if(modulars==null || modulars.size()<=0) return null;
		return modulars;
	}
	//获取公司下面的所有有效分类
	public List<ModularPO> findByComIsv(long comId,int pageNum,int pageSize) {
		QueryHql hql = this.newQueryHql();
		if(comId!=1l){
			hql.andBy("comId", Condition.eq, comId);
		}
		hql.andBy("modularIsv", Condition.eq, 1);
		hql.orderBy("updateTime", true);
		List<ModularPO> modulars =findByHqlPage(hql,pageNum,pageSize);
		if(modulars==null || modulars.size()<=0) return null;
		return modulars;
	}
	
	/**
	 * 获取所有首页推荐商品（App接口调用）
	 * @param count 推荐数量
	 * @return 首页推荐的商品
	 */
	public ModularPO getByCode(String modularcode){
		QueryHql hql = this.newQueryHql();
		hql.andBy("modularCode", Condition.eq, modularcode);
		List<ModularPO> modulars = findByHql(hql);
		if(modulars==null || modulars.size()<=0) return null;
		return modulars.get(0);
	}
	
	
	/**
	 * 获取所有首页推荐商品（App接口调用）
	 * @param count 推荐数量
	 * @return 首页推荐的商品
	 */
	public List<ModularPO> appFindBycomId(long comId){
		QueryHql hql = this.newQueryHql();
		hql.andBy("comId", Condition.eq, comId);
		hql.andBy("modularIsv", Condition.eq, 1);
		hql.orderBy("sortindex", false);
		List<ModularPO> modulars = findByHql(hql);
		if(modulars==null || modulars.size()<=0) return null;
		return modulars;
	}

	public ModularPO getModularById(long modularId){
		QueryHql hql = this.newQueryHql();
		hql.andBy("id", Condition.eq, modularId);
		List<ModularPO> list = findByHql(hql);
		return list.get(0);
	}
	
}
