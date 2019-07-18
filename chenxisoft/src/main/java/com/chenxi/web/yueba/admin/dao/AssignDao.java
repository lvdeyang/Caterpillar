package com.chenxi.web.yueba.admin.dao;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.chenxi.web.yueba.admin.po.AssignPo;
import com.chenxi.web.yueba.admin.po.ComboPo;

import pub.caterpillar.commons.util.date.DateUtil;
import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.Hql;
import pub.caterpillar.orm.hql.QueryHql;

@Repository("AssignDao")
public class AssignDao extends AbstractBaseDao<AssignPo> {

	public List<AssignPo> findbyOrderIdAndToday(long orderId) throws ParseException{
		QueryHql hql=this.newQueryHql();
		hql.andBy("orderId",Condition.eq,orderId);
		Date start=DateUtil.parse(DateUtil.format(new Date(),"yyyy-MM-dd") +" 00:00:00","yyyy-MM-dd HH:mm:ss");
		Date end=DateUtil.parse(DateUtil.format(new Date(),"yyyy-MM-dd") +" 23:59:59","yyyy-MM-dd HH:mm:ss");
		hql.andBy("updateTime",Condition.le,end);
		hql.andBy("updateTime",Condition.ge,start);
		return this.findByHql(hql);
	}
	
	public List<AssignPo> findbyOrderIdAndPage(long orderId,int pageSize,int pageNum) throws ParseException{
		QueryHql hql=this.newQueryHql();
		hql.andBy("orderId",Condition.eq,orderId);
		hql.orderBy("updateTime",true);
		return this.findByHql(hql, pageNum, pageSize);
	}
	
}
