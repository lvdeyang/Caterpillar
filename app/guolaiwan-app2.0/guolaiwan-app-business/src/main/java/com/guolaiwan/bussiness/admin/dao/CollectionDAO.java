package com.guolaiwan.bussiness.admin.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.guolaiwan.bussiness.admin.po.CollectionPO;
import com.guolaiwan.bussiness.admin.po.UserInfoPO;
import com.sun.tools.example.debug.expr.ParseException;

import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.DeleteHql;
import pub.caterpillar.orm.hql.QueryHql;


@Repository("com.guolaiwan.bussiness.admin.dao.CollectionDAO")
public class CollectionDAO extends AbstractBaseDao<CollectionPO> {


	/**
	 * 
	 * @return
	 */
	public CollectionPO  getByUserProId(UserInfoPO user,Long proId){
		QueryHql hql = newQueryHql();
		hql.andBy("productId", Condition.eq, proId);
		hql.andBy("user", Condition.eq, user);
		List<CollectionPO> collections = findByHql(hql);
		if(collections==null||collections.size()==0) return null;
		return collections.get(0);
	}

	public CollectionPO  getByUserMerId(UserInfoPO user,Long merId){
		QueryHql hql = newQueryHql();
		hql.andBy("merchantId", Condition.eq, merId);
		hql.andBy("user", Condition.eq, user);
		List<CollectionPO> collections = findByHql(hql);
		if(collections==null||collections.size()==0) return null;
		return collections.get(0);
	}

	public void  deleteByUser(UserInfoPO user,Long collId){
		DeleteHql dHql = newDeleteHql();
		dHql.andBy("user", Condition.eq, user);
		dHql.andBy("id", Condition.eq, collId);
		int deleteByHql = deleteByHql(dHql);
		System.out.println(deleteByHql+"条收藏被删除!");
	}

	public CollectionPO  getByUserIdProId(long userId,long proId,long activityproductId){
		QueryHql hql = newQueryHql();
		hql.andBy("productId", Condition.eq, proId);
		hql.andBy("user.id", Condition.eq, userId);
		if (activityproductId!=0) {
			hql.andBy("activityproductId", Condition.eq, activityproductId);
		}
		List<CollectionPO> collections = findByHql(hql);
		if(collections==null||collections.size()==0) return null;
		return collections.get(0);
	}

	public CollectionPO  getByUserIdMerId(long userId,Long merId){
		QueryHql hql = newQueryHql();
		hql.andBy("merchantId", Condition.eq, merId);
		hql.andBy("user.id", Condition.eq, userId);
		List<CollectionPO> collections = findByHql(hql);
		if(collections==null||collections.size()==0) return null;
		return collections.get(0);
	}




	public List<CollectionPO>   getByUserIdProduct(long userId,Collection<Long> prodId) {
		QueryHql hql = newQueryHql();
		hql.andBy("productId", Condition.in, prodId);
		hql.andBy("user.id", Condition.eq, userId);
		return findByHql(hql);
	}
	public List<CollectionPO>   getByUserIdMer(long userId,Collection<Long> prodId) {
		QueryHql hql = newQueryHql();
		hql.andBy("merchantId", Condition.in, prodId);
		hql.andBy("user.id", Condition.eq, userId);
		return findByHql(hql);
	}






}
