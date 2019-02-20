package com.guolaiwan.bussiness.admin.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.guolaiwan.bussiness.admin.po.ReSpecialPO;
import com.guolaiwan.bussiness.admin.po.CityInfoPO;
import com.guolaiwan.bussiness.admin.po.ReSpecialPO;

import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.CountHql;
import pub.caterpillar.orm.hql.DeleteHql;
import pub.caterpillar.orm.hql.QueryHql;

@Repository("com.guolaiwan.bussiness.admin.dao.ReSpecialDAO")
public class ReSpecialDAO extends AbstractBaseDao<ReSpecialPO>{
	
	//获取可用特别推荐
	public List<ReSpecialPO> queryEnableById(Collection<Long> ids){
		if(ids==null || ids.size()<=0) return null;
		QueryHql hql = this.newQueryHql();
		hql.andBy("id", Condition.in, ids);
		hql.andBy("enable", Condition.eq, 1);
		return this.findByHql(hql);
	}
	
	//获取总公司特别推荐
	public List<ReSpecialPO> queryEnableByCompany(Long comId){
		QueryHql hql = this.newQueryHql();
		hql.andBy("comId", Condition.eq, comId);
		hql.andBy("enable", Condition.eq, 1);
		return this.findByHql(hql);
	}
	
	public ReSpecialPO getCarouselByName(String bkCode){
		QueryHql hql = this.newQueryHql();
		hql.andBy("Carousel", Condition.eq, bkCode);
		List<ReSpecialPO> Carousels = findByHql(hql);
		if(Carousels==null || Carousels.size()<=0) return null;
		return Carousels.get(0);
	}
	public List<ReSpecialPO> GetListbyPage(int pageNum,int pageSize) {
		QueryHql hql = this.newQueryHql();

		hql.orderBy("id", true);
		List<ReSpecialPO> Carousels =findByHqlPage(hql,pageNum,pageSize);
		if(Carousels==null || Carousels.size()<=0) return null;
		return Carousels;
	}

	//统计总数
	public int  getCountByPage() {
		CountHql cHql=this.newCountHql();
		int allcount=this.countByHql(cHql);
		return allcount;
	}

	//获取轮播图
	public List<ReSpecialPO> getList() {
		int pageNum =1;
		int pageSize =5;
		QueryHql hql = this.newQueryHql();
		hql.andBy("enable", Condition.eq, 1);
		List<ReSpecialPO> Carousels =findByHqlPage(hql,pageNum,pageSize);
		if(Carousels==null || Carousels.size()<=0) return null;
		return Carousels;
	}


	//获取所有公司下的轮播图
	public List<ReSpecialPO> findByCom(long comId,int pageNum,int pageSize){
		QueryHql hql = this.newQueryHql();
		hql.andBy("comId", Condition.eq, comId);
		hql.orderBy("enable", true);
		hql.orderBy("updateTime", true);
		List<ReSpecialPO> Carousels = findByHql(hql, pageNum, pageSize);
		return Carousels;
	}

	//获取所有公司下的轮播图的个数
	public int countByCom(long comId){
		CountHql cHql=this.newCountHql();
		cHql.andBy("comId", Condition.eq, comId);
		int count = countByHql(cHql);
		return count;
	}



}
