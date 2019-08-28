package com.guolaiwan.bussiness.admin.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import com.guolaiwan.bussiness.admin.po.ModularPO;
import com.guolaiwan.bussiness.admin.po.QuestionModularPO;

import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;
import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.QueryHql;

@Repository("com.guolaiwan.bussiness.admin.dao.QuestionModularDao")
public class QuestionModularDao extends AbstractBaseDao<QuestionModularPO>{
 
	//获取最大的Id
	public long  getMaxId() {
		StringBufferWrapper sqlWrapper = new StringBufferWrapper()
				.append("select max(a.id) maxid from t_sys_questionmodularpo a");
		SQLQuery query = getCurrentSession().createSQLQuery(sqlWrapper.toString()).addScalar("maxid",StandardBasicTypes.LONG);
		if(query.uniqueResult()==null) return 0l;
		long maxId = (long)query.uniqueResult();
		return maxId;
	}
	
    	public List<QuestionModularPO> GetListbyPage(int pageNum,int pageSize) {
		QueryHql hql = this.newQueryHql();

		hql.orderBy("id", true);
		List<QuestionModularPO> modulars =findByHqlPage(hql,pageNum,pageSize);
		if(modulars==null || modulars.size()<=0) return null;
		return modulars;
	}
    	public QuestionModularPO getModularByCode(String modularcode){
    		QueryHql hql = this.newQueryHql();
    		hql.andBy("modularCode", Condition.eq, modularcode);
    		List<QuestionModularPO> modulars = findByHql(hql);
    		if(modulars==null || modulars.size()<=0) return null;
    		return modulars.get(0);
    	}
	
}
