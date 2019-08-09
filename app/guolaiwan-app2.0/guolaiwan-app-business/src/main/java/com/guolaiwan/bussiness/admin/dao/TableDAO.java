package com.guolaiwan.bussiness.admin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.guolaiwan.bussiness.admin.enumeration.ShopAuditStateType;
import com.guolaiwan.bussiness.admin.po.MerchantPO;
import com.guolaiwan.bussiness.admin.po.TablePO;

import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.CountHql;
import pub.caterpillar.orm.hql.QueryHql;

@Repository("com.guolaiwan.bussiness.admin.dao.TableDAO")
public class TableDAO extends AbstractBaseDao<TablePO>{

	//通用带参总数
	public int  getCountByPageA(String[] names,Object[] values){
		CountHql cHql=this.newCountHql();
		for(int i=0;i<names.length;i++){
			cHql.andBy(names[i],  Condition.eq, values[i]);
		}
		int allcount=this.countByHql(cHql);
		return allcount;
	}
	
	//通过模块和类获取商品
	public List<TablePO> findByMerchantId(long merchantId){
		QueryHql hql = this.newQueryHql();
		hql.andBy("merchantId", Condition.eq, merchantId);
		hql.orderBy("tableNo", false); // 用户桌号
		List<TablePO> tables = findByHql(hql);
		if(tables==null || tables.size()<=0) return null;
		return tables;
	}
	//通过模块和类获取商品
	public List<TablePO> findByMerchantId(long merchantId,String tiers){
		QueryHql hql = this.newQueryHql();
		hql.andBy("merchantId", Condition.eq, merchantId);
		hql.andBy("tier", Condition.eq, tiers);
		hql.orderBy("tableNo", false); //  用户桌号
		List<TablePO> tables = findByHql(hql);
		if(tables==null || tables.size()<=0) return null;
		return tables;
	}
	
	//通过模块和类获取商品
	public List<TablePO> findByFeature(long merchantId,String tiers, String[] feature){
		QueryHql hql = this.newQueryHql();
		hql.andBy("merchantId", Condition.eq, merchantId);
		hql.andBy("tier", Condition.eq, tiers);
		for (int i=0;i<feature.length;i++){
			hql.andBy(feature[i], Condition.eq, Integer.parseInt("1"));
		}
		hql.orderBy("tableNo", false); //  用户桌号
		List<TablePO> tables = findByHql(hql);
		if(tables==null || tables.size()<=0) return null;
		return tables;
	}
	
	//搜索功能
	public List<TablePO> findSearch(long merchantId,String tiers){
		QueryHql hql = this.newQueryHql();
		hql.andBy("merchantId", Condition.eq, merchantId);
		hql.andBy("tablename", Condition.lk, tiers);
		/*hql.andBy("tableNo", Condition.lk, tiers);*/
		List<TablePO> tables = findByHql(hql);
		if(tables==null || tables.size()<=0) return null;
		return tables;
	}
	//搜索功能
	public int getfindSearch(long merchantId,String tiers){
		CountHql hql= this.newCountHql();
		hql.andBy("merchantId", Condition.eq, merchantId);
		hql.andBy("tablename", Condition.lk, tiers);
	/*	hql.andBy("tableNo", Condition.lk, tiers);*/
		int pCount = this.countByHql(hql);
		return pCount;
	}
	
	//通过模块和类获取商品
	public int ByMerchantId(long merchantId,String tiers,int room){
		CountHql hql= this.newCountHql();
		hql.andBy("merchantId", Condition.eq, merchantId);
		hql.andBy("tier", Condition.eq, tiers);
		hql.andBy("room", Condition.eq, room);
		int pCount = this.countByHql(hql);
		return pCount;
	}
	//通过模块和类获取商品
	public int ByMerchantId(long merchantId,String tiers,int room, String[] feature){
		CountHql hql= this.newCountHql();
		hql.andBy("merchantId", Condition.eq, merchantId);
		hql.andBy("tier", Condition.eq, tiers);
		hql.andBy("room", Condition.eq, room);
		for (int i=0;i<feature.length;i++){
			hql.andBy(feature[i], Condition.eq,Integer.parseInt("1"));
		}
		int pCount = this.countByHql(hql);
		return pCount;
	}
	
	
	public List<TablePO> getByFiels(String[] fields, String str) {
		QueryHql hql = newQueryHql();
		for (int i=0;i<fields.length;i++){
			hql.andBy(fields[i], Condition.eq, str);
		}
		List<TablePO> tables = findByHql(hql);
		return tables;
	}
	
	
}
