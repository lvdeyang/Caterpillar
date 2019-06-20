package com.guolaiwan.bussiness.admin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.guolaiwan.bussiness.admin.po.MerchantUser;
import com.guolaiwan.bussiness.admin.po.live.DistributorUser;

import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.DeleteHql;
import pub.caterpillar.orm.hql.QueryHql;

@Repository("com.guolaiwan.bussiness.admin.dao.DistributorUserDao")
public class DistributorUserDao extends AbstractBaseDao<DistributorUser> {
	
	public DistributorUser  getDistrUserByIds(Long userId,Long distributorId){
		QueryHql hql = this.newQueryHql();
		hql.andBy("userId", Condition.eq, userId);
		hql.andBy("distributorId", Condition.eq, distributorId);
		List<DistributorUser> distributorUsers =  findByHql(hql);
		if(distributorUsers==null || distributorUsers.size()<=0)return null;
		return distributorUsers.get(0);
	}
	
	public List<DistributorUser> getDistrUserByIds ( Long distributorId ){
		QueryHql hql = this.newQueryHql();
		hql.andBy("distributorId", Condition.eq, distributorId);
		List<DistributorUser> distributorUsers =  findByHql(hql);
		if(distributorUsers==null || distributorUsers.size()<=0)return null;
		return distributorUsers;
	}
	
	
	public void  delDistrUserByIds(Long userId,Long distributorId){
		DeleteHql hql = this.newDeleteHql();
		hql.andBy("userId", Condition.eq, userId);
		hql.andBy("distributorId", Condition.eq, distributorId);
		this.deleteByHql(hql);
	}
	
	public DistributorUser  getUserByDistributorId(Long distributorId){
		QueryHql hql = this.newQueryHql();
		hql.andBy("distributorId", Condition.eq, distributorId);
		List<DistributorUser> distributorUsers =  findByHql(hql);
		if(distributorUsers==null || distributorUsers.size()<=0) return null;
		return distributorUsers.get(0);
	}
	
	
}
