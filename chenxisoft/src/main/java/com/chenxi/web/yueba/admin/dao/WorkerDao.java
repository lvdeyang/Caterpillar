package com.chenxi.web.yueba.admin.dao;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.chenxi.web.classes.WorkerStatus;
import com.chenxi.web.po.ArticlePo;
import com.chenxi.web.po.ProductPo;
import com.chenxi.web.yueba.admin.po.WorkerPo;

import pub.caterpillar.commons.util.date.DateUtil;
import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.QueryHql;

@Repository("WorkerDao")
public class WorkerDao extends AbstractBaseDao<WorkerPo> {
	public List<WorkerPo> findPassWorkersAndPage(int pageNum,int pageSize) throws ParseException{
		QueryHql hql=this.newQueryHql();
		hql.andBy("status",Condition.eq,WorkerStatus.PASSED);
		hql.orderBy("sindex", false);
		return this.findByHql(hql, pageNum, pageSize);
	}
	
	
	
	public List<WorkerPo> findNotPassWorkers(){
		QueryHql hql=this.newQueryHql();
		hql.andBy("status",Condition.eq,WorkerStatus.CHECKING);
		return this.findByHql(hql);
	}
}
