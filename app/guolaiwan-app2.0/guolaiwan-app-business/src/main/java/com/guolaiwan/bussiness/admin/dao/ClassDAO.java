package com.guolaiwan.bussiness.admin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.guolaiwan.bussiness.admin.po.CityInfoPO;
import com.guolaiwan.bussiness.admin.po.ClassPO;

import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.CountHql;
import pub.caterpillar.orm.hql.QueryHql;

@Repository("com.guolaiwan.bussiness.admin.dao.ClassDAO")
public class ClassDAO extends AbstractBaseDao<ClassPO>{
	public ClassPO getClassByName(String className){
	   	 QueryHql hql = this.newQueryHql();
	   	 hql.andBy("Class", Condition.eq, className);
	   	 List<ClassPO> Classs = findByHql(hql);
	   	 if(Classs==null || Classs.size()<=0) return null;
	   	 return Classs.get(0);
	    }
	public List<ClassPO> GetListbyPage(int pageNum,int pageSize) {
		 QueryHql hql = this.newQueryHql();
		
		 hql.orderBy("id", true);
	   	 List<ClassPO> Classs =findByHqlPage(hql,pageNum,pageSize);
	   	 if(Classs==null || Classs.size()<=0) return null;
	   	 return Classs;
	}
	//统计总数
	public int  GetCountByPage() {
		CountHql cHql=this.newCountHql();
		
		int allcount=this.countByHql(cHql);
		return allcount;
	}


}
