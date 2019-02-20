package com.guolaiwan.bussiness.admin.dao;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.guolaiwan.bussiness.admin.po.RoomStatusPO;

import pub.caterpillar.commons.util.date.DateUtil;
import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.QueryHql;

@Repository("com.guolaiwan.bussiness.admin.dao.RoomStatusDao")
public class RoomStatusDao extends AbstractBaseDao<RoomStatusPO>{

	public List<RoomStatusPO> findlStatusByRoomAndDate(long roomId,String date) throws ParseException{
		QueryHql hql=this.newQueryHql();
		hql.andBy("roomId",Condition.eq,roomId);
		hql.andBy("startDate",Condition.le,DateUtil.parse(date+" 00:00:00","yyyy-MM-dd HH:mm:ss"));
		hql.andBy("startDate",Condition.ge,DateUtil.parse(date+" 23:59:59","yyyy-MM-dd HH:mm:ss"));
		return this.findByHql(hql);
	}
	public List<RoomStatusPO> findrStatusByRoomAndDate(long roomId,String date) throws ParseException{
		QueryHql hql=this.newQueryHql();
		hql.andBy("roomId",Condition.eq,roomId);
		hql.andBy("endDate",Condition.ge,DateUtil.parse(date+" 00:00:00","yyyy-MM-dd HH:mm:ss"));
		hql.andBy("endDate",Condition.le,DateUtil.parse(date+" 23:59:59","yyyy-MM-dd HH:mm:ss"));
		return this.findByHql(hql);
	}
	public List<RoomStatusPO> findcStatusByRoomAndDate(long roomId,String date) throws ParseException{
		QueryHql hql=this.newQueryHql();
		hql.andBy("roomId",Condition.eq,roomId);
		hql.andBy("startDate",Condition.le,DateUtil.parse(date+" 00:00:00","yyyy-MM-dd HH:mm:ss"));
		hql.andBy("endDate",Condition.ge,DateUtil.parse(date+" 23:59:59","yyyy-MM-dd HH:mm:ss"));
		return this.findByHql(hql);
	}
	
	
	
	public List<RoomStatusPO> findulStatusByRoomAndDate(long roomId,String date) throws ParseException{
		QueryHql hql=this.newQueryHql();
		hql.andBy("roomId",Condition.eq,roomId);
		hql.andBy("status",Condition.eq,1);
		hql.andBy("startDate",Condition.le,DateUtil.parse(date+" 00:00:00","yyyy-MM-dd HH:mm:ss"));
		hql.andBy("startDate",Condition.ge,DateUtil.parse(date+" 23:59:59","yyyy-MM-dd HH:mm:ss"));
		return this.findByHql(hql);
	}
	public List<RoomStatusPO> findurStatusByRoomAndDate(long roomId,String date) throws ParseException{
		QueryHql hql=this.newQueryHql();
		hql.andBy("roomId",Condition.eq,roomId);
		hql.andBy("status",Condition.eq,1);
		hql.andBy("endDate",Condition.ge,DateUtil.parse(date+" 00:00:00","yyyy-MM-dd HH:mm:ss"));
		hql.andBy("endDate",Condition.le,DateUtil.parse(date+" 23:59:59","yyyy-MM-dd HH:mm:ss"));
		return this.findByHql(hql);
	}
	public List<RoomStatusPO> finducStatusByRoomAndDate(long roomId,String date) throws ParseException{
		QueryHql hql=this.newQueryHql();
		hql.andBy("roomId",Condition.eq,roomId);
		hql.andBy("status",Condition.eq,1);
		hql.andBy("startDate",Condition.le,DateUtil.parse(date+" 00:00:00","yyyy-MM-dd HH:mm:ss"));
		hql.andBy("endDate",Condition.ge,DateUtil.parse(date+" 23:59:59","yyyy-MM-dd HH:mm:ss"));
		return this.findByHql(hql);
	}
	
	
	public List<RoomStatusPO> findlStatusByRoomAndUsed(long roomId,String start,String end) throws ParseException{
		QueryHql hql=this.newQueryHql();
		hql.andBy("roomId",Condition.eq,roomId);
		hql.andBy("status",Condition.eq,1);
		hql.andBy("startDate",Condition.le,DateUtil.parse(start,"yyyy-MM-dd HH:mm:ss"));
		hql.andBy("endDate",Condition.ge,DateUtil.parse(start,"yyyy-MM-dd HH:mm:ss"));
		return this.findByHql(hql);
	}
	public List<RoomStatusPO> findrStatusByRoomAndUsed(long roomId,String start,String end) throws ParseException{
		QueryHql hql=this.newQueryHql();
		hql.andBy("roomId",Condition.eq,roomId);
		hql.andBy("status",Condition.eq,1);
		hql.andBy("startDate",Condition.le,DateUtil.parse(end,"yyyy-MM-dd HH:mm:ss"));
		hql.andBy("endDate",Condition.ge,DateUtil.parse(end,"yyyy-MM-dd HH:mm:ss"));
		return this.findByHql(hql);
	}
	public List<RoomStatusPO> findcStatusByRoomAndUsed(long roomId,String start,String end) throws ParseException{
		QueryHql hql=this.newQueryHql();
		hql.andBy("roomId",Condition.eq,roomId);
		hql.andBy("status",Condition.eq,1);
		hql.andBy("startDate",Condition.ge,DateUtil.parse(start,"yyyy-MM-dd HH:mm:ss"));
		hql.andBy("endDate",Condition.le,DateUtil.parse(end,"yyyy-MM-dd HH:mm:ss"));
		return this.findByHql(hql);
	}
	
}
