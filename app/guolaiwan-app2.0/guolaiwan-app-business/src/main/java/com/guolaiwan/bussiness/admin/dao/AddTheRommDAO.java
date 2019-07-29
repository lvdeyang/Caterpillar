package com.guolaiwan.bussiness.admin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.guolaiwan.bussiness.admin.po.AddTheroomPO;

import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.QueryHql;

@Repository("com.guolaiwan.bussiness.admin.dao.AddTheRommDAO")
public class AddTheRommDAO extends AbstractBaseDao<AddTheroomPO> {
	
	
	public List<AddTheroomPO> findByPro(String merchantId) {
		QueryHql hql = this.newQueryHql();
		hql.andBy("merchantId", Condition.eq, merchantId);
		hql.orderBy("tier", false);
		hql.orderBy("name", false);
		List<AddTheroomPO> distributorProducts = findByHql(hql);
		return distributorProducts;
	}

	public List<AddTheroomPO> groupbytier() {
		// TODO Auto-generated method stub

		String sqlWrapper = "select * FROM t_sys_addtheroom GROUP BY tier order by id";

		List<AddTheroomPO> stus = getCurrentSession().createSQLQuery(sqlWrapper.toString())
				.addEntity(AddTheroomPO.class).list();

		return stus;
	}

	// 通过楼层去查没层的房间
	public List<AddTheroomPO> findByPro1(String number) {
		QueryHql hql = this.newQueryHql();
		hql.andBy("tier", Condition.eq, number);
		List<AddTheroomPO> distributorProducts = findByHql(hql);
		return distributorProducts;
	}
}
