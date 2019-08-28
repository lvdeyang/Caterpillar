package com.guolaiwan.bussiness.admin.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import com.guolaiwan.bussiness.admin.po.QuestionModularPO;
import com.guolaiwan.bussiness.admin.po.QuestionSonModularPO;

import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;
import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.CountHql;
import pub.caterpillar.orm.hql.QueryHql;
@Repository("com.guolaiwan.bussiness.admin.dao.QuestionSonModularDao")
public class QuestionSonModularDao extends AbstractBaseDao<QuestionSonModularPO> {
	
	public List<QuestionSonModularPO> GetListbyPage(int pageNum,int pageSize,String mCode) {
		QueryHql hql = this.newQueryHql();
		hql.andBy("classmodularCode", Condition.eq, mCode);
		hql.orderBy("classmodularCode", true);
		List<QuestionSonModularPO> modularclasss =findByHqlPage(hql,pageNum,pageSize);
		if(modularclasss==null || modularclasss.size()<=0) return null;
		return modularclasss;
	}
	
	public int  countBySelect(String mCode) {
		CountHql cHql=this.newCountHql();
		cHql.andBy("classmodularCode", Condition.eq, mCode);
		int allcount=this.countByHql(cHql);
		return allcount;
	}
	

	//获取当前表中的最大的Id
	public long  getMaxId() {
		StringBufferWrapper sqlWrapper = new StringBufferWrapper()
				.append("select max(a.id) maxid from t_sys_questionsonmodular a");
		SQLQuery query = getCurrentSession().createSQLQuery(sqlWrapper.toString()).addScalar("maxid",StandardBasicTypes.LONG);
		if(query.uniqueResult()==null) return 0l;
		long maxId = (long)query.uniqueResult();
		return maxId;
	}
	
	public QuestionSonModularPO getModularByCode(String modularcode){
		QueryHql hql = this.newQueryHql();
		hql.andBy("classCode", Condition.eq, modularcode);
		List<QuestionSonModularPO> modulars = findByHql(hql);
		if(modulars==null || modulars.size()<=0) return null;
		return modulars.get(0);
	}
    	
}
