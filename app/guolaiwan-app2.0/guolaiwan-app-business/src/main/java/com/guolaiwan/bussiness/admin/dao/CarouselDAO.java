package com.guolaiwan.bussiness.admin.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.guolaiwan.bussiness.admin.po.CarouselPO;
import com.guolaiwan.bussiness.admin.po.CityInfoPO;

import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.CountHql;
import pub.caterpillar.orm.hql.DeleteHql;
import pub.caterpillar.orm.hql.QueryHql;

@Repository("com.guolaiwan.bussiness.admin.dao.CarouselDAO")
public class CarouselDAO extends AbstractBaseDao<CarouselPO>{
	
	//获取可用的轮播图
	public List<CarouselPO> queryEnableById(Collection<Long> ids){
		if(ids==null || ids.size()<=0) return null;
		QueryHql hql = this.newQueryHql();
		hql.andBy("id", Condition.in, ids);
		hql.andBy("enable", Condition.eq, 1);
		return this.findByHql(hql);
	}
	
	//获取总公司轮播图
	public List<CarouselPO> queryEnableByCompany(Long comId){
		QueryHql hql = this.newQueryHql();
		hql.andBy("enable", Condition.eq, 1);
		hql.andBy("comId", Condition.eq, comId);
		return this.findByHql(hql);
	}
	
	public CarouselPO getCarouselByName(String bkCode){
		QueryHql hql = this.newQueryHql();
		hql.andBy("Carousel", Condition.eq, bkCode);
		List<CarouselPO> Carousels = findByHql(hql);
		if(Carousels==null || Carousels.size()<=0) return null;
		return Carousels.get(0);
	}
	public List<CarouselPO> GetListbyPage(int pageNum,int pageSize) {
		QueryHql hql = this.newQueryHql();

		hql.orderBy("id", true);
		List<CarouselPO> Carousels =findByHqlPage(hql,pageNum,pageSize);
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
	public List<CarouselPO> getList() {
		int pageNum =1;
		int pageSize =5;
		QueryHql hql = this.newQueryHql();
		hql.andBy("enable", Condition.eq, 1);
		List<CarouselPO> Carousels =findByHqlPage(hql,pageNum,pageSize);
		if(Carousels==null || Carousels.size()<=0) return null;
		return Carousels;
	}


	//获取所有公司下的轮播图
	public List<CarouselPO> findByCom(long comId,int pageNum,int pageSize){
		QueryHql hql = this.newQueryHql();
		hql.andBy("comId", Condition.eq, comId);
		hql.orderBy("enable", true);
		hql.orderBy("updateTime", true);
		List<CarouselPO> Carousels = findByHql(hql, pageNum, pageSize);
		return Carousels;
	}

	//获取所有公司下的轮播图的个数
	public int countByCom(long comId){
		CountHql cHql=this.newCountHql();
		cHql.andBy("comId", Condition.eq, comId);
		int count = countByHql(cHql);
		return count;
	}
	//获取所有公司下的轮播图的个数
	public int countByComEn(long comId){
		CountHql cHql=this.newCountHql();
		cHql.andBy("comId", Condition.eq, comId);
		cHql.andBy("enable", Condition.eq, 1);
		int count = countByHql(cHql);
		return count;
	}

	public List<CarouselPO> getCarouselByComId(long comId){
		QueryHql hql = this.newQueryHql();
		hql.andBy("comId", Condition.eq, comId);
		hql.orderBy("sort", true);
		List<CarouselPO> list = findByHql(hql);
		return list;
	}

}
