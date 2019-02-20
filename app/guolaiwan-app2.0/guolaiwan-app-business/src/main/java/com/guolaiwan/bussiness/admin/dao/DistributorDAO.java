package com.guolaiwan.bussiness.admin.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.guolaiwan.bussiness.admin.po.DistributorPO;

import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.CountHql;
import pub.caterpillar.orm.hql.QueryHql;

@Repository("com.guolaiwan.bussiness.admin.dao.DistributorDAO")
public class DistributorDAO extends AbstractBaseDao<DistributorPO>{

	// 统计总条数
	public int  getCountByPage() {
		CountHql cHql=this.newCountHql();

		int allcount=this.countByHql(cHql);
		return allcount;
	}

	// 分页查询
	public List<DistributorPO> getListByPageA(String[] names,Object[] values,int pageNum,int pageSize) {
		QueryHql hql = this.newQueryHql();
		for(int i=0;i<names.length;i++){
			hql.andBy(names[i],  Condition.eq, values[i]);
		}
		hql.orderBy("updateTime", true);
		List<DistributorPO> distributorPOs = findByHqlPage(hql,pageNum,pageSize);
		if(distributorPOs==null || distributorPOs.size()<=0) return null;
		return distributorPOs;
	}

	//后台检索
	public List<DistributorPO> getListByPageB(Map<String, Object> map,int pageNum,int pageSize) {
		QueryHql hql = this.newQueryHql();
		for (Map.Entry<String,Object> entry : map.entrySet()) {
			if(entry.getKey().equals("shopName")){
				hql.andBy(entry.getKey(), Condition.lk, entry.getValue());

			}else{
				hql.andBy(entry.getKey(), Condition.eq, entry.getValue());
			}
		}
		hql.orderBy("updateTime", true);
		List<DistributorPO> distributorPOs = findByHqlPage(hql,pageNum,pageSize);
		if(distributorPOs==null || distributorPOs.size()<=0) return null;
		return distributorPOs;
	}
	//后台检索(个数)
	public int CountByPageB(Map<String, Object> map) {
		CountHql cHql=this.newCountHql();
		for (Map.Entry<String,Object> entry : map.entrySet()) {
			if(entry.getKey().equals("distributorName")){
				cHql.andBy(entry.getKey(), Condition.lk, entry.getValue());

			}else{
				cHql.andBy(entry.getKey(), Condition.eq, entry.getValue());
			}
		}
		int count = countByHql(cHql);
		return count;
	}

	//通用带参总数
	public int  getCountByPageA(String[] names,Object[] values){
		CountHql cHql=this.newCountHql();
		for(int i=0;i<names.length;i++){
			cHql.andBy(names[i],  Condition.eq, values[i]);
		}
		int allcount=this.countByHql(cHql);
		return allcount;
	}
}
