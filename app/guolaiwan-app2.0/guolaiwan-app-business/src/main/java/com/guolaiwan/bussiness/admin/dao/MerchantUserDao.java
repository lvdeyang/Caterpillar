package com.guolaiwan.bussiness.admin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.guolaiwan.bussiness.admin.po.MerchantPO;
import com.guolaiwan.bussiness.admin.po.MerchantUser;
import com.guolaiwan.bussiness.admin.po.UserInfoPO;

import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.DeleteHql;
import pub.caterpillar.orm.hql.QueryHql;
@Repository("com.guolaiwan.bussiness.admin.dao.MerchantUserDao")
public class MerchantUserDao extends AbstractBaseDao<MerchantUser>{
	public MerchantUser  getMerUserByIds(Long userId,Long merchantId){
		QueryHql hql = this.newQueryHql();
		hql.andBy("userId", Condition.eq, userId);
		hql.andBy("merchantId", Condition.eq, merchantId);
		List<MerchantUser> merchantUsers =  findByHql(hql);
		if(merchantUsers==null || merchantUsers.size()<=0) return null;
		return merchantUsers.get(0);
	}
	
	
	public void  delMerUserByIds(Long userId,Long merchantId){
		DeleteHql hql = this.newDeleteHql();
		hql.andBy("userId", Condition.eq, userId);
		hql.andBy("merchantId", Condition.eq, merchantId);
		this.deleteByHql(hql);
	}
}
