package com.guolaiwan.bussiness.admin.dao;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Repository;

import com.guolaiwan.bussiness.admin.po.BalancePO;

import pub.caterpillar.commons.util.date.DateUtil;
import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.CountHql;
import pub.caterpillar.orm.hql.QueryHql;


@Repository("com.guolaiwan.bussiness.admin.dao.BalanceDAO")
public class BalanceDAO extends AbstractBaseDao<BalancePO> {
	
	public List<BalancePO> getBalanceList(long id,int page,int limit){
		QueryHql hql = this.newQueryHql();
		hql.andBy("merchantId", Condition.eq, id);
		List<BalancePO> balancePOs = this.findByHqlPage(hql, page, limit);
		if(balancePOs==null || balancePOs.size()<=0) return null;
		return balancePOs;
	}
	
	public int GetCountbyPage(long id){
		
		CountHql chql=this.newCountHql();
		chql.andBy("merchantId", Condition.eq, id);
		
		int allcount = this.countByHql(chql);
		return allcount;
	}
	
	public List<BalancePO> getSettleHistoryList(String sName,int page,int limit){
		QueryHql hql = this.newQueryHql();
		hql.andBy("merchantName", Condition.lk, sName);
		hql.orderBy("settleDate", true);
		List<BalancePO> balancePOs = this.findByHqlPage(hql, page, limit);
		if(balancePOs==null || balancePOs.size()<=0) return null;
		return balancePOs;
	}
	
	public List<BalancePO> getOneById(Long id){
		QueryHql hql = this.newQueryHql();
		hql.andBy("id", Condition.eq, id);
		List<BalancePO> balancePOs = this.findByHql(hql);
		if(balancePOs==null || balancePOs.size()<=0) return null;
		return balancePOs;
	}
	public List<BalancePO> getByToday() throws ParseException{
		QueryHql hql = this.newQueryHql();
		
		hql.andBy("settleDate", Condition.gt, DateUtil.parse(DateUtil.format(new Date(),"yyyy-MM-dd")+" 00:00:00","yyyy-MM-dd HH:mm:ss"));
		hql.andBy("settleDate", Condition.lt, DateUtil.parse(DateUtil.format(new Date(),"yyyy-MM-dd")+" 23:59:59","yyyy-MM-dd HH:mm:ss"));
		List<BalancePO> balancePOs = this.findByHql(hql);
		if(balancePOs==null || balancePOs.size()<=0) return null;
		return balancePOs;
	}
	
	public List<BalancePO> findAllSort(int pageNum,int pageSize){
		QueryHql hql=this.newQueryHql();
		hql.orderBy("settleDate", true);
		return this.findByHql(hql, pageNum, pageSize);
	}
	
}