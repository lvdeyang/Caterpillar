package com.guolaiwan.bussiness.admin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.guolaiwan.bussiness.admin.po.BewritePO;
import com.guolaiwan.bussiness.admin.po.BundleOrder;
import com.guolaiwan.bussiness.admin.po.VoicePO;
import com.guolaiwan.bussiness.admin.po.ZbiaoPO;

import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.CountHql;
import pub.caterpillar.orm.hql.DeleteHql;
import pub.caterpillar.orm.hql.QueryHql;


@Repository("com.guolaiwan.bussiness.admin.dao.BundleOrderDAO")
public class BundleOrderDAO extends AbstractBaseDao<BundleOrder> {
	public BundleOrder getBundleByOrderId(long orderId) {
		QueryHql hql = this.newQueryHql();
		hql.orBy("orderStr",Condition.lk,orderId+"A");
		hql.orBy("orderStr",Condition.lk,"A"+orderId);
		hql.orBy("orderStr",Condition.eq,""+orderId);
		List<BundleOrder> bundleOrders=this.findByHql(hql);
		if(bundleOrders.isEmpty()){
			return null;
		}else{
			return bundleOrders.get(0);
		}
	
	}
}
