package com.guolaiwan.bussiness.sec.dao;

import java.text.ParseException;
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
import com.guolaiwan.bussiness.admin.po.ColumnPO;
import com.guolaiwan.bussiness.admin.po.ModularPO;
import com.guolaiwan.bussiness.sec.po.SecUserPo;
import com.guolaiwan.bussiness.sec.po.SecUserPointPo;

import pub.caterpillar.commons.util.date.DateUtil;
import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;
import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.CountHql;
import pub.caterpillar.orm.hql.QueryHql;


@Repository("com.guolaiwan.bussiness.sec.dao.SecUserPointDAO")
public class SecUserPointDAO extends AbstractBaseDao<SecUserPointPo> {

	public List<SecUserPointPo> findbyUserAndPointTimeAndDate(long userId,long secPointTimeId,String date) throws ParseException{
		QueryHql hql=this.newQueryHql();
		hql.andBy("secUserId",Condition.eq,userId);
		hql.andBy("secPointTimeId",Condition.eq,secPointTimeId);
		hql.andBy("setTime",Condition.ge,DateUtil.parse(date+" 00:00:00", "yyyy-MM-dd HH:mm:ss"));
		hql.andBy("setTime",Condition.le,DateUtil.parse(date+" 23:59:59", "yyyy-MM-dd HH:mm:ss"));
		return this.findByHql(hql);
	}
}