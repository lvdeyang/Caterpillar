package com.guolaiwan.bussiness.admin.dao;

import org.springframework.stereotype.Repository;

import com.guolaiwan.bussiness.admin.po.RolePO;

import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.CountHql;
import pub.caterpillar.orm.hql.QueryHql;

@Repository("com.guolaiwan.bussiness.admin.dao.RoleDAO")
public class RoleDAO extends AbstractBaseDao<RolePO>{
	
	   //统计总数
		public int  GetCountByPage() {
			CountHql cHql=this.newCountHql();
			
			int allcount=this.countByHql(cHql);
			return allcount;
		}
		
		
		public RolePO getMerchantRole(){
			return this.findByField("roleName", "merchant").get(0);
		}
}
