package com.guolaiwan.bussiness.admin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.guolaiwan.bussiness.admin.po.BewritePO;
import com.guolaiwan.bussiness.admin.po.ChildProductPO;
import com.guolaiwan.bussiness.admin.po.PicproRelationPO;

import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.CountHql;
import pub.caterpillar.orm.hql.QueryHql;
@Repository("com.guolaiwan.bussiness.admin.dao.PicproRelationDAO")
public class PicproRelationDAO extends AbstractBaseDao<PicproRelationPO>{
	
	/**
	 * 获取子产品Id对应的所有的图片关联对象（App接口调用）
	 * @param childID 子产品Id
	 * @return
	 */
	public List<PicproRelationPO>  getPprByChildID(
			long childID,
			int page,
			int pageSize){
		QueryHql hql = this.newQueryHql();
		hql.andBy("childProID", Condition.eq, childID);	
		List<PicproRelationPO> pprs =findByHqlPage(hql, page, pageSize);
		return pprs;
	}
	
	
	/**
	 * 获取子产品Id对应的所有的图片关联对象（App接口调用）
	 * @param childID 子产品Id
	 * @return
	 */
	public List<PicproRelationPO>  getPprByChild(
			long childID
			){
		QueryHql hql = this.newQueryHql();
		hql.andBy("childProID", Condition.eq, childID);	
		List<PicproRelationPO> pprs =findByHql(hql);
		return pprs;
	}

}
