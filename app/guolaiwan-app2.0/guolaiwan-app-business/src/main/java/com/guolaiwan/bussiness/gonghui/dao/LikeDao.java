package com.guolaiwan.bussiness.gonghui.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.guolaiwan.bussiness.gonghui.po.ArticlePo;
import com.guolaiwan.bussiness.gonghui.po.LikePo;
import com.guolaiwan.bussiness.gonghui.po.OnlineClassesPo;

import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.QueryHql;
@Repository("com.guolaiwan.bussiness.gonghui.dao.LikeDao")
public class LikeDao extends AbstractBaseDao<LikePo> {

	public List<LikePo> findLikeByUserAndContent(long userId,long contentId){
    	QueryHql hql=this.newQueryHql();
    	hql.andBy("contentId",Condition.eq,contentId);
    	hql.andBy("userId",Condition.eq,userId);
    	return findByHql(hql);
    }
}
