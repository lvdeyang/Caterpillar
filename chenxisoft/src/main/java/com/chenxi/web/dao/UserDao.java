package com.chenxi.web.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.chenxi.web.po.UserPo;

import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.QueryHql;

@Repository("UserDao")
public class UserDao extends AbstractBaseDao<UserPo> {
	
	public List<UserPo> findUserByOpenId(String openId){
		QueryHql hql=this.newQueryHql();
		hql.andBy("openId",Condition.eq,openId);
		return this.findByHql(hql);
	}

}
