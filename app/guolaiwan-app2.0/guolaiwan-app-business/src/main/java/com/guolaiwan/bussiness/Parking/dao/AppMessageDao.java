package com.guolaiwan.bussiness.Parking.dao;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.guolaiwan.bussiness.Parking.po.AppMessagePO;
import com.guolaiwan.bussiness.Parking.po.AttractionsParkingPO;
import com.guolaiwan.bussiness.Parking.po.CarPositionPO;
import com.guolaiwan.bussiness.admin.po.MerchantPO;

import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.CountHql;
import pub.caterpillar.orm.hql.QueryHql;

/**
 * 景区信息查询
 * 
 * @author Administrator
 *
 */
@Component
public class AppMessageDao extends AbstractBaseDao<AppMessagePO> {

	public AppMessagePO getMessage() {
		QueryHql hql = newQueryHql();
		List<AppMessagePO> list = findByHql(hql);
		if (list == null || list.size() == 0)
		return null;
		return list.get(0);
	}
	
}
