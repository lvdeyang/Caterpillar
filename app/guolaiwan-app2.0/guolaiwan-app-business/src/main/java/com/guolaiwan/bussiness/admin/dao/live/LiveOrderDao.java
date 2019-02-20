package com.guolaiwan.bussiness.admin.dao.live;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.guolaiwan.bussiness.admin.enumeration.OrderStateType;
import com.guolaiwan.bussiness.admin.po.ActivityPO;
import com.guolaiwan.bussiness.admin.po.live.LiveOrderPO;
import com.guolaiwan.bussiness.admin.po.live.SubLivePO;

import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.DeleteHql;
import pub.caterpillar.orm.hql.QueryHql;

@Repository("com.guolaiwan.bussiness.admin.dao.live.LiveOrderDao")
public class LiveOrderDao extends AbstractBaseDao<LiveOrderPO> {
	
	public LiveOrderPO getCurrentLiveOrder(long liveId){
		QueryHql hql=this.newQueryHql();
		hql.andBy("liveId",Condition.eq,liveId);
		Date now=new Date();
		hql.andBy("startTime",Condition.lt,now);
		hql.andBy("endTime",Condition.gt,now);
		hql.andBy("status",Condition.eq,OrderStateType.PAYSUCCESS);
		return this.getByHql(hql);
	}
	
	/**
	 * 通过liveId获取t_sys_liveOrder表的订单信息
	 * @param liveId
	 * @return
	 */
	public LiveOrderPO getCurrentLiveOrders(long liveId){
		QueryHql hql = this.newQueryHql();
		hql.andBy("liveId",Condition.eq,liveId);
		return this.getByHql(hql);
	}
	
	/**
	 * 通过liveId删除t_sys_liveOrder表的订单信息
	 * 
	 * @param liveId
	 */
	public void deleteByLiveId(long liveId) {
		DeleteHql hql = this.newDeleteHql();
		hql.andBy("liveId", Condition.eq, liveId);
		this.deleteByHql(hql);
	}
	
	public LiveOrderPO findLiveOrderByLiveId(long liveId){
		QueryHql hql = this.newQueryHql();
		hql.andBy("liveId",Condition.eq,liveId);
		return this.getByHql(hql);
	}
	
}
