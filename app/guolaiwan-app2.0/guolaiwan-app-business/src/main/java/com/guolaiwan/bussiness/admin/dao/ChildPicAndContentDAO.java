package com.guolaiwan.bussiness.admin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.guolaiwan.bussiness.admin.po.ChildPicAndContentPO;

import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.QueryHql;

@Repository("com.guolaiwan.bussiness.admin.dao.ChildPicAndContentDAO")
public class ChildPicAndContentDAO extends AbstractBaseDao<ChildPicAndContentPO> {
	
	public List<ChildPicAndContentPO> getChildsByChildId(long voiceId , long childId){
		QueryHql hql = newQueryHql();
		hql.andBy("childId", Condition.eq, childId);
		hql.andBy("voiceId", Condition.eq, voiceId);
		List<ChildPicAndContentPO> list = findByHql(hql);
		return list;
	}	
	
}