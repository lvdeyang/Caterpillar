package com.guolaiwan.bussiness.admin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.guolaiwan.bussiness.admin.enumeration.BookType;
import com.guolaiwan.bussiness.admin.enumeration.TableStatus;
import com.guolaiwan.bussiness.admin.po.TableStatusPO;

import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.QueryHql;

@Repository("com.guolaiwan.bussiness.admin.dao.TableStatusDAO")
public class TableStatusDAO extends AbstractBaseDao<TableStatusPO>{

	public TableStatusPO findBytidt(long tableId, String date, BookType type) {
		QueryHql hql = newQueryHql();
		hql.andBy("tableId", Condition.eq, tableId);
		hql.andBy("date", Condition.eq, date);
		hql.andBy("type", Condition.eq, type);
		hql.andBy("tableState", Condition.eq, "PAYSUCCESS");
		List<TableStatusPO> tableStatuList = findByHql(hql);
		if(tableStatuList==null || tableStatuList.size()<=0) return null;
		TableStatusPO tableStatusPO = tableStatuList.get(0);
		return tableStatusPO;
	}
}
