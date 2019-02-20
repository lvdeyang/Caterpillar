package com.guolaiwan.bussiness.user.dao;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.guolaiwan.bussiness.user.po.UserPO;


import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.QueryHql;

@Repository
public class UserDAO extends AbstractBaseDao<UserPO> {
	
	//通过用户名获取用户
     public UserPO getUserByName(String username){
    	 QueryHql hql=this.newQueryHql();
    	 hql.andBy("username", Condition.eq, username);
    	 List<UserPO> users = findByHql(hql);
    	 if(users==null || users.size()<=0) return null;
    	 return users.get(0);
     }
	
}
