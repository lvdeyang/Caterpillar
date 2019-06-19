package com.chenxi.web.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.chenxi.web.classes.Moudular;
import com.chenxi.web.po.OnlineClassesPo;

import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.CountHql;
import pub.caterpillar.orm.hql.QueryHql;

@Repository("OnlineClassesDao")
public class OnlineClassesDao extends AbstractBaseDao<OnlineClassesPo> {
    public List<OnlineClassesPo> findOnlineByClasses(long classId,int pageNum,int pageSize){
    	QueryHql hql=this.newQueryHql();
    	hql.andBy("classesId",Condition.eq,classId);
    	return findByHql(hql, pageNum, pageSize);
    }
    public int countOnlineByClasses(long classId){
    	CountHql hql=this.newCountHql();
    	hql.andBy("classesId",Condition.eq,classId);
    	return countByHql(hql);
    }
    public List<OnlineClassesPo> findOnlineBymodular(Moudular moudular,int pageNum,int pageSize){
    	QueryHql hql=this.newQueryHql();
    	hql.andBy("contentMouduler",Condition.eq,moudular);
    	return findByHql(hql, pageNum, pageSize);
    }
}
