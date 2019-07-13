package com.chenxi.web.yueba.admin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.chenxi.web.yueba.admin.po.SeeRecordPo;

import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.QueryHql;

@Repository("SeeRecordDao")
public class SeeRecordDao extends AbstractBaseDao<SeeRecordPo> {
	public List<SeeRecordPo> findByUserId(long userId,int pageNum,int pageSize){
		QueryHql hql=this.newQueryHql();
		hql.andBy("userId",Condition.eq,userId);
		hql.orderBy("updateTime", true);
		return this.findByHql(hql, pageNum, pageSize);
	}
	public List<SeeRecordPo> findByWorkerId(long workerId,int pageNum,int pageSize){
		QueryHql hql=this.newQueryHql();
		hql.andBy("workerId",Condition.eq,workerId);
		hql.orderBy("updateTime", true);
		return this.findByHql(hql, pageNum, pageSize);
	}

}
