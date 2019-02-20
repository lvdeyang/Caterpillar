package com.guolaiwan.bussiness.distribute.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Component;

import com.guolaiwan.bussiness.distribute.po.RegionPo;

import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;
import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.CountHql;
import pub.caterpillar.orm.hql.QueryHql;

@Component
public class RegionDao extends AbstractBaseDao<RegionPo> {
	
	public List<RegionPo> queryAllFirstRegion(){
		QueryHql hql = this.newQueryHql();
		hql.andBy("parentId", Condition.eq, 0l);
		List<RegionPo> regions = this.findByHql(hql);
		if(regions==null || regions.size()<=0) return null;
		return regions;
	}
	public List<RegionPo> queryAllFirstRegion(int page,int limit){
		QueryHql hql = this.newQueryHql();
		hql.andBy("parentId", Condition.eq, 0l);
		List<RegionPo> regions = this.findByHqlPage(hql, page, limit);
		if(regions==null || regions.size()<=0) return null;
		return regions;
	}
	
	
	public List<RegionPo> queryRegionByParent(long parentId){
		QueryHql hql = this.newQueryHql();
		hql.andBy("parentId", Condition.eq, parentId);
		List<RegionPo> regions = this.findByHql(hql);
		if(regions==null || regions.size()<=0) return null;
		return regions;
	}
	public List<RegionPo> querylastRegionByParent(long parentId,int page,int limit){
		QueryHql hql = this.newQueryHql();
		hql.andBy("id", Condition.eq, parentId);
		List<RegionPo> regions = this.findByHqlPage(hql, page, limit);
		if(regions==null || regions.size()<=0) return null;
		return regions;
	}
	
	public List<RegionPo> querylastnRegionByParent(long parentId,int page,int limit){
		QueryHql hql = this.newQueryHql();
		hql.andBy("parentId", Condition.eq, parentId);
		List<RegionPo> regions = this.findByHqlPage(hql, page, limit);
		if(regions==null || regions.size()<=0) return null;
		return regions;
	}
	
	public List<RegionPo> querynextRegionByParent(long parentId,int page,int limit){
		QueryHql hql = this.newQueryHql();
		hql.andBy("parentId", Condition.eq, parentId);
		List<RegionPo> regions = this.findByHqlPage(hql, page, limit);
		if(regions==null || regions.size()<=0) return null;
		return regions;
	}
	
	//统计总数
	public int  GetCountByPage() {
		CountHql cHql=this.newCountHql();

		int allcount=this.countByHql(cHql);
		return allcount;
	}
	
	public int GetCountbyPage(long id){
		
		CountHql chql=this.newCountHql();
		chql.andBy("parentId", Condition.eq, id);
		
		int allcount = this.countByHql(chql);
		return allcount;
	}
	
	public Long getMaxId(){
		StringBufferWrapper sqlWrapper = new StringBufferWrapper()
				.append("select max(a.id) maxid from distribute_region a");
		SQLQuery query = getCurrentSession().createSQLQuery(sqlWrapper.toString()).addScalar("maxid",StandardBasicTypes.LONG);
		if(query.uniqueResult()==null) return 0l;
		long maxId = (long)query.uniqueResult();
		return maxId;
	}
}
