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
import com.guolaiwan.bussiness.merchant.dto.ReportDTO;
import com.guolaiwan.bussiness.merchant.po.CameraFacePO;
import com.guolaiwan.bussiness.merchant.po.CarMessagePO;
import com.guolaiwan.bussiness.merchant.po.ReportOrderPO;

import pub.caterpillar.commons.util.date.DateUtil;
import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;
import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.CountHql;
import pub.caterpillar.orm.hql.QueryHql;


@Repository("com.guolaiwan.bussiness.merchant.dao.CarMessageDAO")
public class CarMessageDAO extends AbstractBaseDao<CarMessagePO> {

	public List<ReportDTO> getAllCarcount() {
		StringBufferWrapper sqlWrapper = new StringBufferWrapper().append(
				"select SUM(carNum) acount from t_sys_carmessage");
		
		SQLQuery query = getCurrentSession().createSQLQuery(sqlWrapper.toString()).
				addScalar("acount", StandardBasicTypes.INTEGER);
		query.setResultTransformer(Transformers.aliasToBean(ReportDTO.class));
		
		return query.list();
	}
	
	public List<ReportDTO> gettypeData(int pageNum, int pageSize) {
		StringBufferWrapper sqlWrapper = new StringBufferWrapper().append(
				"select SUM(carNum) acount,typeString from t_sys_carmessage GROUP BY type ORDER BY acount desc");
		
		SQLQuery query = getCurrentSession().createSQLQuery(sqlWrapper.toString())
				.addScalar("typeString", StandardBasicTypes.STRING).addScalar("acount", StandardBasicTypes.INTEGER);
		query.setResultTransformer(Transformers.aliasToBean(ReportDTO.class));

		// 分页
		int firstResult = (pageNum - 1) * pageSize;
		query.setFirstResult(firstResult);
		query.setMaxResults(pageSize);
		return query.list();
	}
	
	public List<ReportDTO> getregionData(int pageNum, int pageSize) {
		StringBufferWrapper sqlWrapper = new StringBufferWrapper().append(
				"select SUM(carNum) acount,region from t_sys_carmessage GROUP BY region ORDER BY acount desc");
		
		SQLQuery query = getCurrentSession().createSQLQuery(sqlWrapper.toString())
				.addScalar("region", StandardBasicTypes.STRING).addScalar("acount", StandardBasicTypes.INTEGER);
		query.setResultTransformer(Transformers.aliasToBean(ReportDTO.class));

		// 分页
		int firstResult = (pageNum - 1) * pageSize;
		query.setFirstResult(firstResult);
		query.setMaxResults(pageSize);
		return query.list();
	}
	
	public List<CarMessagePO> findTodydata(Date today) throws ParseException {
		QueryHql cHql = this.newQueryHql();
		cHql.andBy("updateTime", Condition.ge,DateUtil.parse(DateUtil.format(today,"yyyy-MM-dd")+" 00:00:00","yyyy-MM-dd HH:mm:ss"));
		cHql.andBy("updateTime", Condition.le, DateUtil.parse(DateUtil.format(today,"yyyy-MM-dd")+" 23:59:59","yyyy-MM-dd HH:mm:ss"));
		int allcount = 0;
		List<CarMessagePO> reportOrderPOs=this.findByHql(cHql);
	
		return reportOrderPOs;
	}
}