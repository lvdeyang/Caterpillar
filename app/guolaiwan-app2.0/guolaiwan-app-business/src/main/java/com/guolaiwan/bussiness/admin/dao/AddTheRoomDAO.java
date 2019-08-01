package com.guolaiwan.bussiness.admin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.guolaiwan.bussiness.admin.po.AddTheRoomPO;

import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.QueryHql;

@Repository("com.guolaiwan.bussiness.admin.dao.AddTheRoomDAO")
public class AddTheRoomDAO extends AbstractBaseDao<AddTheRoomPO> {
	
	
	public List<AddTheRoomPO> findByPro(String merchantId) {
		QueryHql hql = this.newQueryHql();
		hql.andBy("merchantId", Condition.eq, merchantId);
		hql.orderBy("tier", false);
		hql.orderBy("name", false);
		List<AddTheRoomPO> distributorProducts = findByHql(hql);
		return distributorProducts;
	}

	public List<AddTheRoomPO> groupbytier() {
		// TODO Auto-generated method stub

		String sqlWrapper = "select * FROM t_sys_addtheroom GROUP BY tier order by id";

		List<AddTheRoomPO> stus = getCurrentSession().createSQLQuery(sqlWrapper.toString())
				.addEntity(AddTheRoomPO.class).list();

		return stus;
	}

	// 通过楼层去查没层的房间
	public List<AddTheRoomPO> findByPro1(String number) {
		QueryHql hql = this.newQueryHql();
		hql.andBy("tier", Condition.eq, number);
		List<AddTheRoomPO> distributorProducts = findByHql(hql);
		return distributorProducts;
	}
	
	/**
	 * 按照Mid和Tier查询所有的房间
	 * @param merchantId
	 * @param tier
	 * @return
	 */
	public List<AddTheRoomPO> findByMidTier(String merchantId,String tier,String identity) {
		QueryHql hql = this.newQueryHql();
		hql.andBy("merchantId", Condition.eq, merchantId);
		hql.andBy("tier", Condition.eq, tier);
		hql.andBy("identity", Condition.eq, identity);
		hql.orderBy("tier", false);
		hql.orderBy("name", false);
		List<AddTheRoomPO> distributorProducts = findByHql(hql);
		return distributorProducts;
	}
}
