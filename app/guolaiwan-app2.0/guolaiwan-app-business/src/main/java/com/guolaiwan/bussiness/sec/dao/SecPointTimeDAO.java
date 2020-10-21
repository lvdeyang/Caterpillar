package com.guolaiwan.bussiness.sec.dao;

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
import com.guolaiwan.bussiness.admin.po.ColumnPO;
import com.guolaiwan.bussiness.admin.po.ModularPO;
import com.guolaiwan.bussiness.sec.po.SecPointPo;
import com.guolaiwan.bussiness.sec.po.SecPointTimePo;
import com.guolaiwan.bussiness.sec.po.SecUserPo;

import pub.caterpillar.commons.util.date.DateUtil;
import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;
import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.CountHql;
import pub.caterpillar.orm.hql.QueryHql;


@Repository("com.guolaiwan.bussiness.sec.dao.SecPointTimeDAO")
public class SecPointTimeDAO extends AbstractBaseDao<SecPointTimePo> {
	//根据当前查找是否有符合的打卡排期
	public List<SecPointTimePo> findbyTimeAndPoint(Date date,long pointId) throws ParseException{
		QueryHql hql=this.newQueryHql();
		Date time=DateUtil.parse("1970-01-01 "+DateUtil.format(date,"HH:mm:ss"),"yyyy-MM-dd HH:mm:ss");
		hql.andBy("setStartTime", Condition.le, time);
		hql.andBy("setEndTime",Condition.ge,time);
		hql.andBy("secPointId",Condition.eq,pointId);
		return this.findByHql(hql);
	}

}