package com.guolaiwan.bussiness.admin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.guolaiwan.bussiness.admin.po.AdminPO;

import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.CountHql;
import pub.caterpillar.orm.hql.QueryHql;


@Repository("com.guolaiwan.bussiness.admin.dao.AdminDAO")
public class AdminDAO extends AbstractBaseDao<AdminPO> {
	public AdminPO getAdminByName(String username){
   	 QueryHql hql = this.newQueryHql();
   	 hql.andBy("username", Condition.eq, username);
   	 List<AdminPO> admins = findByHql(hql);
   	 if(admins==null || admins.size()<=0) return null;
   	 return admins.get(0);
    }
	
	public List<AdminPO> GetListbyPage(String username,int pageNum,int pageSize) {
		 QueryHql hql = this.newQueryHql();
		 hql.andBy("adminName", Condition.eq, username);
		 hql.orderBy("createdDate", true);
	   	 List<AdminPO> admins =findByHqlPage(hql,pageNum,pageSize);
	   	 if(admins==null || admins.size()<=0) return null;
	   	 return admins;
	}
	//统计总数
	public int  GetCountByPage(String username) {
		CountHql cHql=this.newCountHql();
		if(username!=null || username.length()>0)
		      cHql.andBy("adminName",Condition.eq,username);
		int allcount=this.countByHql(cHql);
		return allcount;
	}
}
