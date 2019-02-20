package com.guolaiwan.bussiness.admin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.guolaiwan.bussiness.admin.enumeration.ColumnType;
import com.guolaiwan.bussiness.admin.po.ColumnPO;

import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.QueryHql;

@Repository(value = "com.guolaiwan.bussiness.admin.dao.ColumnDAO")
public class ColumnDAO extends AbstractBaseDao<ColumnPO> {
	
	public ColumnPO getColumnById(long id){
		QueryHql hql = this.newQueryHql();
		hql.andBy("id", Condition.eq, id);
		List<ColumnPO> list = findByHql(hql);
		if (list.size()==0) {
			return null;
		}else {
			return list.get(0);
		}
	}
	
	public List<ColumnPO> getType(ColumnType ct){
		QueryHql hql = this.newQueryHql();
		hql.andBy("type", Condition.eq, ct);
		List<ColumnPO> list = findByHql(hql);
		return list;
	}
	
	public ColumnPO getColumnByMerchantId(long id){
		QueryHql hql = this.newQueryHql();
		hql.andBy("merchantId", Condition.eq, id);
		List<ColumnPO> list = findByHql(hql);
		if (list.size()==0) {
			return null;
		}else {
			return list.get(0);
		}
	}
	
	public List<ColumnPO> getColumnByCode(String code){
		QueryHql hql = this.newQueryHql();
		hql.andBy("code", Condition.eq, code);
		List<ColumnPO> list = findByHql(hql);
		return list;
	}
	
}