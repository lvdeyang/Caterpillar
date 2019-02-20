package com.guolaiwan.bussiness.admin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.guolaiwan.bussiness.admin.po.ActivityPO;
import com.guolaiwan.bussiness.admin.po.ActivityRelPO;
import com.guolaiwan.bussiness.admin.po.AdminPO;
import com.sun.xml.internal.xsom.impl.parser.DelayedRef;

import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.CountHql;
import pub.caterpillar.orm.hql.DeleteHql;
import pub.caterpillar.orm.hql.QueryHql;


@Repository("com.guolaiwan.bussiness.admin.dao.ActivityRelDAO")
public class ActivityRelDAO extends AbstractBaseDao<ActivityRelPO> {
	
	
	public List<ActivityRelPO> findByAcId(long activityId,int pageNum,int pageSize){
		QueryHql hql = this.newQueryHql(); 
		hql.andBy("activityId", Condition.eq, activityId);
		hql.orderBy("updateTime", true);
		List<ActivityRelPO> activityRels = this.findByHqlPage(hql, pageNum, pageSize);
		return activityRels;
	}
	public List<ActivityRelPO> findByAcId(long activityId,String pName,Long pId, int pageNum,int pageSize){
		QueryHql hql = this.newQueryHql(); 
		hql.andBy("activityId", Condition.eq, activityId);
		hql.andBy("productId", Condition.eq, pId);
		hql.andBy("productName", Condition.lk, pName);
		hql.orderBy("updateTime", true);
		List<ActivityRelPO> activityRels = this.findByHqlPage(hql, pageNum, pageSize);
		return activityRels;
	}
	
	public int countByAcId(long activityId){
		CountHql cHql = this.newCountHql(); 
		cHql.andBy("activityId", Condition.eq, activityId);
		int count = this.countByHql(cHql);
		return count;
	}
	public int countByAcId(long activityId,String pName){
		CountHql cHql = this.newCountHql(); 
		cHql.andBy("activityId", Condition.eq, activityId);
		cHql.andBy("productName", Condition.lk, pName);
		int count = this.countByHql(cHql);
		return count;
	}
	public int countByPA(long pId ,long acId ){
		CountHql cHql = this.newCountHql(); 
		cHql.andBy("activityId", Condition.eq, acId);
		cHql.andBy("productId", Condition.eq, pId);
		int count = this.countByHql(cHql);
		return count;
	}
	public int countByPro(long pId){
		CountHql cHql = this.newCountHql(); 
		cHql.andBy("productId", Condition.eq, pId);
		int count = this.countByHql(cHql);
		return count;
	}
	
	public void deleteByAcId(long acId ){
		DeleteHql dHql = this.newDeleteHql(); 
		dHql.andBy("activityId", Condition.eq, acId);
		this.deleteByHql(dHql);
	}
	
	/**活动下商品的数量(app专用)
	 * @param activityId
	 * @return count 
	 */
	public int appCountByAct(long activityId){
		CountHql cHql = this.newCountHql(); 
		cHql.andBy("activityId", Condition.eq, activityId);
		int count = this.countByHql(cHql);
		return count;
	}
	
	public ActivityRelPO getById(long id){
		QueryHql hql = this.newQueryHql();
		hql.andBy("id", Condition.eq, id);
		List<ActivityRelPO> list = findByHql(hql);
		return list.get(0);
	}
	
	public ActivityRelPO getActivityRelByProductId(long productId){
		QueryHql hql = this.newQueryHql();
		hql.andBy("productId", Condition.eq, productId);
		List<ActivityRelPO> product = findByHql(hql);
		if (product.size()==0) {
			return null;
		}else {
			return product.get(0);
		}
	}
	
}
