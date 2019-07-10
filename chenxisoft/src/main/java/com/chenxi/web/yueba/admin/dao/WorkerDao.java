package com.chenxi.web.yueba.admin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.chenxi.web.classes.WorkerStatus;
import com.chenxi.web.po.ArticlePo;
import com.chenxi.web.po.ProductPo;
import com.chenxi.web.yueba.admin.po.WorkerPo;

import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.QueryHql;

@Repository("WorkerDao")
public class WorkerDao extends AbstractBaseDao<WorkerPo> {
	public List<WorkerPo> findPassWorkersAndPage(int pageNum,int pageSize){
		QueryHql hql=this.newQueryHql();
		hql.andBy("status",Condition.eq,WorkerStatus.PASSED);
		hql.orderBy("sindex", false);
		return this.findByHql(hql, pageNum, pageSize);
	}
}
