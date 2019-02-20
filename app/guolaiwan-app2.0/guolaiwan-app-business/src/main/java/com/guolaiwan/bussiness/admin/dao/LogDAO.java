package com.guolaiwan.bussiness.admin.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.guolaiwan.bussiness.admin.po.LogPO;

import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.CountHql;
import pub.caterpillar.orm.hql.QueryHql;

/**
 * Don't try so hard!
 * <p>Title: MenuDAO</p>
 * <p>Description: 权限DAO</p>
 * <p>Company: guolaiwan</p> 
 * @author zichao
 * @date 2017年11月20日 上午9:11:36
 */
@Repository("com.guolaiwan.bussiness.admin.dao.LogDAO")
public class LogDAO extends AbstractBaseDao<LogPO> {

	//统计总数
	public int  getCountByPage() {
		CountHql cHql=this.newCountHql();
		int allcount=this.countByHql(cHql);
		return allcount;
	}
	
	//分页
	public List<LogPO> GetListbyPage(int pageNum,int pageSize) {
		 QueryHql hql = this.newQueryHql();
	   	 List<LogPO> menus =findByHqlPage(hql,pageNum,pageSize);
	   	 if(menus == null || menus.size() <= 0) return null;
	   	 return menus;
	}
	
}
