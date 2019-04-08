package com.guolaiwan.bussiness.admin.dao;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import com.guolaiwan.bussiness.admin.dto.MerchantDTO;
import com.guolaiwan.bussiness.admin.dto.ProductDTO;
import com.guolaiwan.bussiness.admin.po.ActivityPO;
import com.guolaiwan.bussiness.admin.po.AdminPO;
import com.guolaiwan.bussiness.admin.po.ModularPO;
import com.guolaiwan.bussiness.admin.po.UserOneDayBuyPO;

import pub.caterpillar.commons.util.date.DateUtil;
import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;
import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.CountHql;
import pub.caterpillar.orm.hql.QueryHql;


@Repository("com.guolaiwan.bussiness.admin.dao.UserOnedayBuyDAO")
public class UserOnedayBuyDAO extends AbstractBaseDao<UserOneDayBuyPO> {

	public List<UserOneDayBuyPO> findTodayBuy(long userId,long proId) throws ParseException{
		QueryHql hql=this.newQueryHql();
		hql.andBy("userId",Condition.eq,userId);
		hql.andBy("proId",Condition.eq,proId);
		hql.andBy("bookDate",Condition.ge,DateUtil.parse(DateUtil.format(new Date(),"yyyy-MM-dd")+" 00:00:00"));
		hql.andBy("bookDate",Condition.le,DateUtil.parse(DateUtil.format(new Date(),"yyyy-MM-dd")+" 23:59:59"));
	    return findByHql(hql);
	}
	
	
	public List<UserOneDayBuyPO> findDateBuy(long userId,long proId,Date date) throws ParseException{
		QueryHql hql=this.newQueryHql();
		hql.andBy("userId",Condition.eq,userId);
		hql.andBy("proId",Condition.eq,proId);
		hql.andBy("bookDate",Condition.ge,DateUtil.parse(DateUtil.format(date,"yyyy-MM-dd")+" 00:00:00"));
		hql.andBy("bookDate",Condition.le,DateUtil.parse(DateUtil.format(date,"yyyy-MM-dd")+" 23:59:59"));
	    return findByHql(hql);
	}
}