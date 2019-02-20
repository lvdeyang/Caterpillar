package com.guolaiwan.bussiness.common.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.guolaiwan.bussiness.common.enumeration.CodeType;
import com.guolaiwan.bussiness.common.po.CodeListPO;
import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.QueryHql;

@Repository
public class CodeListDAO extends AbstractBaseDao<CodeListPO> {

	//获取所有标签
	public List<CodeListPO> getTags(){
		QueryHql hql = this.newQueryHql();
		hql.andBy("level_1", Condition.eq, CodeType.TAG);
		return this.findByHql(hql);
	}
	
	//获取所有栏目
	public List<CodeListPO> getCategories(){
		QueryHql hql = this.newQueryHql();
		hql.andBy("level_1", Condition.eq, CodeType.CATEGORY);
		return this.findByHql(hql);
	}
	
}
