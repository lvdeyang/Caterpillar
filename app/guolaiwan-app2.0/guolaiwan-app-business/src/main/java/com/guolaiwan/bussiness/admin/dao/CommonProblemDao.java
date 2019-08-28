package com.guolaiwan.bussiness.admin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.guolaiwan.bussiness.admin.po.CommonProblemPO;
import com.guolaiwan.bussiness.admin.po.QuestionSonModularPO;

import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.QueryHql;

@Repository("com.guolaiwan.bussiness.admin.dao.CommonProblemDao")
public class CommonProblemDao extends AbstractBaseDao<CommonProblemPO> {
	
	public List<CommonProblemPO> GetListbyPage(int pageNum,int pageSize,Integer mCode,Integer mclassCode) {
		QueryHql hql = this.newQueryHql();
		hql.andBy("modularCode", Condition.eq, mCode);
		hql.andBy("modularClassCode", Condition.eq, mclassCode);
		hql.orderBy("classmodularCode", true);
		List<CommonProblemPO> modularclasss =findByHqlPage(hql,pageNum,pageSize);
		if(modularclasss==null || modularclasss.size()<=0) return null;
		return modularclasss;
	}
	

}
