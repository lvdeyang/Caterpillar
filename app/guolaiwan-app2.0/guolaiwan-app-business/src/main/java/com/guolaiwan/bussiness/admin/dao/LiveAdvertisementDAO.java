package com.guolaiwan.bussiness.admin.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.guolaiwan.bussiness.admin.po.LiveAdvertisementPO;

import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.CountHql;
import pub.caterpillar.orm.hql.QueryHql;

@Repository("com.guolaiwan.bussiness.admin.dao.LiveAdvertisementDAO")
public class LiveAdvertisementDAO extends AbstractBaseDao<LiveAdvertisementPO>{
	
	
	//获取可用的轮播图
	public List<LiveAdvertisementPO> queryEnableById(Collection<Long> ids){
		if(ids==null || ids.size()<=0) return null;
		QueryHql hql = this.newQueryHql();
		hql.andBy("id", Condition.in, ids);
		hql.andBy("enable", Condition.eq, 1);
		return this.findByHql(hql);
	}
	
	
	public List<LiveAdvertisementPO> GetListbyPage(int pageNum,int pageSize) {
		QueryHql hql = this.newQueryHql();

		hql.orderBy("id", true);
		List<LiveAdvertisementPO> LiveAdvertisements =findByHqlPage(hql,pageNum,pageSize);
		if(LiveAdvertisements==null || LiveAdvertisements.size()<=0) return null;
		return LiveAdvertisements;
	}

	//统计总数
	public int  getCountByPage() {
		CountHql cHql=this.newCountHql();
		int allcount=this.countByHql(cHql);
		return allcount;
	}

	//获取轮播图
	public List<LiveAdvertisementPO> getList() {
		int pageNum =1;
		int pageSize =5;
		QueryHql hql = this.newQueryHql();
		hql.andBy("enable", Condition.eq, 1);
		List<LiveAdvertisementPO> LiveAdvertisements =findByHqlPage(hql,pageNum,pageSize);
		if(LiveAdvertisements==null || LiveAdvertisements.size()<=0) return null;
		return LiveAdvertisements;
	}



	public List<LiveAdvertisementPO> getLiveAdvertisementByComId(long comId){
		QueryHql hql = this.newQueryHql();
		hql.andBy("comId", Condition.eq, comId);
		hql.andBy("enable",Condition.eq,1);
		hql.orderBy("sort", true);
		List<LiveAdvertisementPO> list = findByHql(hql);
		return list;
	}


	public int countByComEn(){
		CountHql cHql=this.newCountHql();
		cHql.andBy("enable", Condition.eq, 1);
		int count = countByHql(cHql);
		return count;
	}

}
