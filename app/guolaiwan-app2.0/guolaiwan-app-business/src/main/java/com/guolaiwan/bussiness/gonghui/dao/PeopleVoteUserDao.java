package com.guolaiwan.bussiness.gonghui.dao;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.guolaiwan.bussiness.gonghui.po.ArticlePo;
import com.guolaiwan.bussiness.gonghui.po.PeopleVotePo;
import com.guolaiwan.bussiness.gonghui.po.PeopleVoteUserPo;

import pub.caterpillar.commons.util.date.DateUtil;
import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.CountHql;
import pub.caterpillar.orm.hql.QueryHql;
@Repository("PeopleVoteUserDao")
public class PeopleVoteUserDao extends AbstractBaseDao<PeopleVoteUserPo> {

	public int countUserToday(long userId) throws ParseException{
		CountHql countHql=this.newCountHql();
		countHql.andBy("userId", Condition.eq, userId);
		countHql.andBy("updateTime",Condition.ge,DateUtil.parse(DateUtil.format(new Date(),"yyyy-MM-dd")+" 00:00:00","yyyy-MM-dd HH:mm:ss"));
		countHql.andBy("updateTime",Condition.le,DateUtil.parse(DateUtil.format(new Date(),"yyyy-MM-dd")+" 23:59:59","yyyy-MM-dd HH:mm:ss"));
		return this.countByHql(countHql);
	}
	
	
	

}
