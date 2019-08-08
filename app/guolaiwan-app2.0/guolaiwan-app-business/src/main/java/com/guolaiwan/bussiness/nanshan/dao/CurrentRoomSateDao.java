package com.guolaiwan.bussiness.nanshan.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bytedeco.javacpp.RealSense.intrinsics;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;

import com.guolaiwan.bussiness.admin.po.AddTheRoomPO;
import com.guolaiwan.bussiness.nanshan.po.CurrentRoomSatePO;

import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.QueryHql;

@Repository("com.guolaiwan.bussiness.nanshan.dao.CurrentRoomSateDao")
public class CurrentRoomSateDao extends AbstractBaseDao<CurrentRoomSatePO> {
	
	public CurrentRoomSatePO findByRoomId(Long roomId) {
		QueryHql hql = this.newQueryHql();
		hql.andBy("roomId", Condition.eq, roomId);
		List<CurrentRoomSatePO> currentRoomSatePOs = findByHql(hql);
		return currentRoomSatePOs.get(0);
	}
	
  
}
