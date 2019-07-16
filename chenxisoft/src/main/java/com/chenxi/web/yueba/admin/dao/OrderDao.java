package com.chenxi.web.yueba.admin.dao;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.chenxi.web.classes.OrderStatus;
import com.chenxi.web.po.ArticlePo;
import com.chenxi.web.po.ProductPo;
import com.chenxi.web.yueba.admin.po.OrderPo;
import com.chenxi.web.yueba.admin.po.WorkerPo;

import pub.caterpillar.commons.util.date.DateUtil;
import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.CountHql;
import pub.caterpillar.orm.hql.QueryHql;

@Repository("OrderDao")
public class OrderDao extends AbstractBaseDao<OrderPo> {
	public int countCompleteByWorker(long workerId){
		CountHql hql=this.newCountHql();
		hql.andBy("workerId",Condition.eq,workerId);
		hql.andBy("orderStatus",Condition.eq,OrderStatus.COMPLETE);
		return countByHql(hql);
	}
	
	public OrderPo findMaxWorderOrder(long workerId){
		QueryHql hql=this.newQueryHql();
		hql.andBy("workerId",Condition.eq,workerId);
		hql.orderBy("fromDate", true);
		List<OrderPo> orderPos = this.findByHql(hql);
		if(orderPos.isEmpty())return null;
		return orderPos.get(0);
	}
	
	public List<OrderPo> findOrderByWorkerPage(Long workerId,int pageNum,int pageSize){
		QueryHql hql=this.newQueryHql();
		hql.andBy("workerId",Condition.eq,workerId);
		return this.findByHql(hql, pageNum, pageSize);
	}
	
	public List<OrderPo> findOrderByStatus(OrderStatus status,int pageNum,int pageSize){
		QueryHql hql=this.newQueryHql();
		hql.andBy("orderStatus",Condition.eq,status);
		return this.findByHql(hql, pageNum, pageSize);
	}
	
	public List<OrderPo> findOrderByorderDateAndWorkerId(String orderDate,long workerId) throws ParseException{
		Date oDate=DateUtil.parse(orderDate,"yyyy-MM-dd");
		Date startDate=DateUtil.addDay(oDate,-26);
		Date endDate=DateUtil.addDay(oDate,26);
		QueryHql hql=this.newQueryHql();
		hql.andBy("workerId",Condition.eq,workerId);
		hql.andBy("fromDate",Condition.le,endDate);
		hql.andBy("fromDate",Condition.ge,startDate);
		return this.findByHql(hql);
	}
	public List<OrderPo> findOrderByCurrAndWorkerId(String curr,long workerId) throws ParseException{
		QueryHql hql=this.newQueryHql();
		Date startDate=DateUtil.parse(DateUtil.format(DateUtil.addMonth(DateUtil.parse(curr,"yyyy-MM-dd"),-1),"yyyy-MM-dd")+" 00:00:00","yyyy-MM-dd HH:mm:ss");
		Date endDate=DateUtil.parse(DateUtil.format(DateUtil.addMonth(DateUtil.parse(curr,"yyyy-MM-dd"),1),"yyyy-MM-dd")+" 23:59:59","yyyy-MM-dd HH:mm:ss");
		
		Calendar ca1=Calendar.getInstance();
		Calendar ca2=Calendar.getInstance();
		ca1.setTime(startDate);
		ca2.setTime(endDate);
		
		
		hql.andBy("workerId",Condition.eq,workerId);
		hql.andBy("fromDate",Condition.ge,DateUtil.getMonthStartAndEndDate(ca1)[0]);
		hql.andBy("fromDate",Condition.le,DateUtil.getMonthStartAndEndDate(ca2)[1]);
		hql.orderBy("fromDate", false);
		return this.findByHql(hql);
	}
	
}
