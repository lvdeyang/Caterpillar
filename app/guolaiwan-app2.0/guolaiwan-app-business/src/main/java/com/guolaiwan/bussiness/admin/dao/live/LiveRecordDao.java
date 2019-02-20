package com.guolaiwan.bussiness.admin.dao.live;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.guolaiwan.bussiness.admin.po.live.LiveRecordPO;

import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.DeleteHql;
import pub.caterpillar.orm.hql.QueryHql;

@Repository("com.guolaiwan.bussiness.admin.dao.live.LiveRecordDao")
public class LiveRecordDao extends AbstractBaseDao<LiveRecordPO>{
	public LiveRecordPO findByLiveAndIng(long liveId){
		QueryHql hql = this.newQueryHql();
		hql.andBy("liveId", Condition.eq, liveId);
		hql.andBy("endTime", Condition.eq, null);
		List<LiveRecordPO> liveRecordPOs= this.findByHql(hql);
		if(liveRecordPOs.isEmpty()) return null;
		return liveRecordPOs.get(0);
	}
	public List<LiveRecordPO> findByLiveAndEnd(long liveId){
		QueryHql hql = this.newQueryHql();
		hql.andBy("liveId", Condition.eq, liveId);
		hql.andBy("endTime", Condition.ne, null);
		List<LiveRecordPO> liveRecordPOs= this.findByHql(hql);
		return liveRecordPOs;
	}
}
