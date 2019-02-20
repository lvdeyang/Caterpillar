
package com.guolaiwan.bussiness.admin.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import com.guolaiwan.bussiness.admin.po.ModularClassPO;
import com.guolaiwan.bussiness.admin.po.ModularPO;

import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;
import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.CountHql;
import pub.caterpillar.orm.hql.QueryHql;

@Repository("com.guolaiwan.bussiness.admin.dao.ModularClassDAO")
public class ModularClassDAO extends AbstractBaseDao<ModularClassPO>{
	public ModularClassPO getModularByCode(String classcode){
		QueryHql hql = this.newQueryHql();
		hql.andBy("modularclass", Condition.eq, classcode);
		List<ModularClassPO> modularclasss = findByHql(hql);
		if(modularclasss==null || modularclasss.size()<=0) return null;
		return modularclasss.get(0);
	}
	public List<ModularClassPO> GetListbyPage(int pageNum,int pageSize,String mCode,String cName) {
		QueryHql hql = this.newQueryHql();
		hql.andBy("className", Condition.lk, cName);
		hql.andBy("classmodularCode", Condition.eq, mCode);
		hql.orderBy("classmodularCode", true);
		List<ModularClassPO> modularclasss =findByHqlPage(hql,pageNum,pageSize);
		if(modularclasss==null || modularclasss.size()<=0) return null;
		return modularclasss;
	}
	//统计总数
	public int  GetCountByPage() {
		CountHql cHql=this.newCountHql();

		int allcount=this.countByHql(cHql);
		return allcount;
	}
	
	public int  countBySelect(String mCode,String cName) {
		CountHql cHql=this.newCountHql();
		cHql.andBy("className", Condition.lk, cName);
		cHql.andBy("classmodularCode", Condition.eq, mCode);
		int allcount=this.countByHql(cHql);
		return allcount;
	}



	//获取模块下的有效的分类
	public List<ModularClassPO> findByModular(String modularCode) {
		QueryHql hql = newQueryHql();
		hql.andBy("classmodularCode", Condition.eq, modularCode);
		hql.andBy("classIsv", Condition.eq, 1);
		hql.orderBy("classSort", false);
		List<ModularClassPO> mcs = findByHql(hql);
		return mcs;
	}


	//获取当前表中的最大的Id
	public long  getMaxId() {
		StringBufferWrapper sqlWrapper = new StringBufferWrapper()
				.append("select max(a.id) maxid from t_Sys_Modular_Class a");
		SQLQuery query = getCurrentSession().createSQLQuery(sqlWrapper.toString()).addScalar("maxid",StandardBasicTypes.LONG);
		if(query.uniqueResult()==null) return 0l;
		long maxId = (long)query.uniqueResult();
		return maxId;
	}
	//获取分公司下面的有效的分类
	public List<ModularClassPO> findByCom(long comId,int pageNum,int pageSize) {
		QueryHql hql = newQueryHql();
		if(comId!=1l){
			hql.andBy("comId", Condition.eq, comId);
		}
		hql.orderBy("updateTime", true);
		List<ModularClassPO> mcs = findByHqlPage(hql, pageNum, pageSize);
		return mcs;
	}
	//获取分公司下面的有效的分类个数
	public int countByCom(long comId) {
		CountHql cHql=this.newCountHql();
		if(comId!=1l){
			cHql.andBy("comId", Condition.eq, comId);
		}
		int allcount=this.countByHql(cHql);
		return allcount;
	}

	/**
	 * 获取模块下的分类
	 * 
	 */
	public List<ModularClassPO> appFindByModular(String modularCode) {
		QueryHql hql = newQueryHql();
		hql.andBy("classmodularCode", Condition.eq, modularCode);
		hql.andBy("classIsv", Condition.eq, 1);
		hql.orderBy("classSort", false);
		List<ModularClassPO> mcs = findByHql(hql);
		return mcs;
	}
}
