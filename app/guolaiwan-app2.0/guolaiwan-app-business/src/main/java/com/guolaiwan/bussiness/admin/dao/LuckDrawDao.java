package com.guolaiwan.bussiness.admin.dao;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.guolaiwan.bussiness.admin.po.LuckDrawRecord;

import pub.caterpillar.commons.util.date.DateUtil;
import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.CountHql;
import pub.caterpillar.orm.hql.QueryHql;

@Repository("com.guolaiwan.bussiness.admin.dao.LuckDrawDao")
public class LuckDrawDao extends AbstractBaseDao<LuckDrawRecord> {
	public LuckDrawRecord getLuckRecordByUser(Long userId) {
		QueryHql hql = this.newQueryHql();
		hql.andBy("userId", Condition.eq, userId);
		hql.andBy("drawProductId",Condition.ne,0);
		List<LuckDrawRecord> luckDrawRecords = findByHql(hql);
		if (luckDrawRecords == null || luckDrawRecords.size() <= 0)
			return null;
		return luckDrawRecords.get(0);
	}
	public LuckDrawRecord getuserTodayRecord(Long userId) throws ParseException {
		QueryHql hql = this.newQueryHql();
		hql.andBy("userId", Condition.eq, userId);
		hql.andBy("updateTime",Condition.gt,DateUtil.parse(DateUtil.format(new Date(),"yyyy-MM-dd")+" 00:00:00"));
		hql.andBy("updateTime",Condition.lt,DateUtil.parse(DateUtil.format(new Date(),"yyyy-MM-dd")+" 23:59:59"));
		List<LuckDrawRecord> luckDrawRecords = findByHql(hql);
		if (luckDrawRecords == null || luckDrawRecords.size() <= 0)
			return null;
		return luckDrawRecords.get(0);
	}
	public List<LuckDrawRecord> getAllRecord(int prizeid,int page,int limit){
		QueryHql hql = this.newQueryHql();
		if (prizeid==1) {
			hql.andBy("drawProductId", Condition.eq, 1);
		}else if(prizeid==2){
			hql.andBy("drawProductId", Condition.eq, 2);
		}else if (prizeid==3) {
			hql.andBy("drawProductId", Condition.eq, 1);
			hql.andBy("useit", Condition.eq, 1);
		}
		hql.andBy("drawProductId",Condition.ne,0);
		List<LuckDrawRecord> luckDrawRecords = findByHqlPage(hql,page,limit);
		if (luckDrawRecords == null || luckDrawRecords.size() <= 0)
			return null;
		return luckDrawRecords;
	}
	public List<LuckDrawRecord> getAllRecordByPrize(String sName,int page,int limit){
		QueryHql hql = this.newQueryHql();
		if (sName.equals("电影票")) {
			hql.andBy("drawProductId",Condition.eq,1);
		}else if (sName.equals("眼镜")) {
			hql.andBy("drawProductId",Condition.eq,2);
		}
		List<LuckDrawRecord> luckDrawRecords = findByHqlPage(hql,page,limit);
		if (luckDrawRecords == null || luckDrawRecords.size() <= 0)
			return null;
		return luckDrawRecords;
	}
	public int countGodlike(int productId) throws ParseException{
		QueryHql hql = this.newQueryHql();
		hql.andBy("drawProductId", Condition.eq, productId);
		hql.andBy("updateTime",Condition.gt,DateUtil.parse(DateUtil.format(new Date(),"yyyy-MM-dd")+" 00:00:00"));
		hql.andBy("updateTime",Condition.lt,DateUtil.parse(DateUtil.format(new Date(),"yyyy-MM-dd")+" 23:59:59"));
		List<LuckDrawRecord> luckDrawRecords = findByHql(hql);
		return luckDrawRecords.size();
	}
	
	public int countAllPrizeNum(String sName) {
		CountHql cHql = this.newCountHql();
		if (sName.equals("电影票")) {
			cHql.andBy("drawProductId",Condition.eq,1);
		}else if (sName.equals("眼镜")) {
			cHql.andBy("drawProductId",Condition.eq,2);
		}else if (sName=="") {
			cHql.andBy("drawProductId", Condition.ne, 0);
		}
		
		int count =countByHql(cHql);
		return count;
	}
}
