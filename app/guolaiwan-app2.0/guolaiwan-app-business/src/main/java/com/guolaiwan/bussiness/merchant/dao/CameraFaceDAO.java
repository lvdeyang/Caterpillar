package com.guolaiwan.bussiness.merchant.dao;

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
import com.guolaiwan.bussiness.merchant.po.CameraFacePO;
import com.guolaiwan.bussiness.merchant.po.ReportOrderAllPO;
import com.guolaiwan.bussiness.merchant.po.ReportOrderPO;

import pub.caterpillar.commons.util.date.DateUtil;
import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;
import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.CountHql;
import pub.caterpillar.orm.hql.QueryHql;


@Repository("com.guolaiwan.bussiness.merchant.dao.CameraFaceDAO")
public class CameraFaceDAO extends AbstractBaseDao<CameraFacePO> {

	public List<CameraFacePO> findTodydata(Date today) throws ParseException {
		QueryHql cHql = this.newQueryHql();
		cHql.andBy("countDate", Condition.ge,DateUtil.parse(DateUtil.format(today,"yyyy-MM-dd")+" 00:00:00","yyyy-MM-dd HH:mm:ss"));
		cHql.andBy("countDate", Condition.le, DateUtil.parse(DateUtil.format(today,"yyyy-MM-dd")+" 23:59:59","yyyy-MM-dd HH:mm:ss"));
		int allcount = 0;
		List<CameraFacePO> reportOrderPOs=this.findByHql(cHql);
	
		return reportOrderPOs;
	}
	
}