package com.guolaiwan.bussiness.admin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.guolaiwan.bussiness.admin.po.PageLayoutPO;
import com.guolaiwan.bussiness.common.enumeration.BooleanType;

import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.QueryHql;

@Repository
public class PageLayoutDAO extends AbstractBaseDao<PageLayoutPO> {

	//获取所有布局
	public List<PageLayoutPO> getAllLayout(){
		QueryHql hql = this.newQueryHql();
		hql.orderBy("updateTime", true);
		return this.findByHql(hql);
	}
	
	//获取当前系统中生效的布局
	public PageLayoutPO getEnableLayout(){
		QueryHql hql = this.newQueryHql();
		hql.andBy("enable", Condition.eq, BooleanType.TRUE);
		List<PageLayoutPO> layouts = this.findByHql(hql);
		if(layouts==null || layouts.size()<=0){
			return null;
		}else{
			return layouts.get(0);
		}
	}
	
	//获取当前系统中生效的布局
	public PageLayoutPO getEnableLayoutExceptId(Long layoutId){
		QueryHql hql = this.newQueryHql();
		hql.andBy("id", Condition.ne, layoutId);
		hql.andBy("enable", Condition.eq, BooleanType.TRUE);
		List<PageLayoutPO> layouts = this.findByHql(hql);
		if(layouts==null || layouts.size()<=0){
			return null;
		}else{
			return layouts.get(0);
		}
	}
	
}
