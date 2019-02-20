package com.guolaiwan.bussiness.admin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.guolaiwan.bussiness.admin.po.MerchantBusinessPO;

import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.QueryHql;

@Repository(value = "com.guolaiwan.bussiness.admin.dao.MerchantBusinessDAO")
public class MerchantBusinessDAO extends AbstractBaseDao<MerchantBusinessPO> {
	
	public MerchantBusinessPO getMerchantBusinessBymerchantId(long merchantId){
		QueryHql hql = this.newQueryHql();
		hql.andBy("merchantId", Condition.eq, merchantId);
		List<MerchantBusinessPO> merchantBusiness = findByHql(hql);
		if (merchantBusiness.size()<=0) {
			return null;
		}else {
			return merchantBusiness.get(0);
		}
	}
	
}