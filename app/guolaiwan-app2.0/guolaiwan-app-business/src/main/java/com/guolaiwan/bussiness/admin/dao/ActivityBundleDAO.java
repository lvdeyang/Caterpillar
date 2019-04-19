package com.guolaiwan.bussiness.admin.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import com.guolaiwan.bussiness.Parking.po.ParkingPositionPO;
import com.guolaiwan.bussiness.admin.dto.MerchantDTO;
import com.guolaiwan.bussiness.admin.dto.ProductDTO;
import com.guolaiwan.bussiness.admin.po.ActiveBundlePo;
import com.guolaiwan.bussiness.admin.po.ActivityPO;
import com.guolaiwan.bussiness.admin.po.AdminPO;
import com.guolaiwan.bussiness.admin.po.ModularPO;

import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;
import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.CountHql;
import pub.caterpillar.orm.hql.Hql;
import pub.caterpillar.orm.hql.QueryHql;


@Repository("com.guolaiwan.bussiness.admin.dao.ActivityBundleDAO")
public class ActivityBundleDAO extends AbstractBaseDao<ActiveBundlePo> {

	public List<ActiveBundlePo> queryByPage(int page,int pagesize,int comId){
		QueryHql queryHql=this.newQueryHql();
		queryHql.andBy("comId", Condition.eq, comId);
		List<ActiveBundlePo> products = findByHqlPage(queryHql, page, pagesize);
		if (products == null || products.size() <= 0)return null;
		
		return products;
	}
	
	
	
}