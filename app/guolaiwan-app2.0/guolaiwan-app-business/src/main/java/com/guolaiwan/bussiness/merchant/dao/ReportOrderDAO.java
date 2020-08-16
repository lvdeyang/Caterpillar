package com.guolaiwan.bussiness.merchant.dao;

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
import com.guolaiwan.bussiness.merchant.po.ReportOrderPO;

import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;
import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.CountHql;
import pub.caterpillar.orm.hql.QueryHql;


@Repository("com.guolaiwan.bussiness.merchant.dao.ReportOrderDAO")
public class ReportOrderDAO extends AbstractBaseDao<ReportOrderPO> {

	public List<ReportDTO> getSexData(int pageNum, int pageSize) {
		StringBufferWrapper sqlWrapper = new StringBufferWrapper().append(
				"select SUM(count) acount,sexString from t_sys_reportorder GROUP BY sex ORDER BY acount desc");
		
		SQLQuery query = getCurrentSession().createSQLQuery(sqlWrapper.toString())
				.addScalar("sexString", StandardBasicTypes.STRING).addScalar("acount", StandardBasicTypes.INTEGER);
		query.setResultTransformer(Transformers.aliasToBean(ReportDTO.class));

		// 分页
		int firstResult = (pageNum - 1) * pageSize;
		query.setFirstResult(firstResult);
		query.setMaxResults(pageSize);
		return query.list();
	}
	
	
	public List<ReportDTO> getageData(int pageNum, int pageSize) {
		StringBufferWrapper sqlWrapper = new StringBufferWrapper().append(
				"select SUM(count) acount,ageString from t_sys_reportorder GROUP BY ageString ORDER BY acount desc");
		
		SQLQuery query = getCurrentSession().createSQLQuery(sqlWrapper.toString())
				.addScalar("ageString", StandardBasicTypes.STRING).addScalar("acount", StandardBasicTypes.INTEGER);
		query.setResultTransformer(Transformers.aliasToBean(ReportDTO.class));

		// 分页
		int firstResult = (pageNum - 1) * pageSize;
		query.setFirstResult(firstResult);
		query.setMaxResults(pageSize);
		return query.list();
	}
	
	public List<ReportDTO> getregionData(int pageNum, int pageSize) {
		StringBufferWrapper sqlWrapper = new StringBufferWrapper().append(
				"select SUM(count) acount,region from t_sys_reportorder GROUP BY region ORDER BY acount desc");
		
		SQLQuery query = getCurrentSession().createSQLQuery(sqlWrapper.toString())
				.addScalar("region", StandardBasicTypes.STRING).addScalar("acount", StandardBasicTypes.INTEGER);
		query.setResultTransformer(Transformers.aliasToBean(ReportDTO.class));

		// 分页
		int firstResult = (pageNum - 1) * pageSize;
		query.setFirstResult(firstResult);
		query.setMaxResults(pageSize);
		return query.list();
	}
	
	public int GetCountByHour(Date start,Date end) {
		CountHql cHql = this.newCountHql();
		cHql.andBy("updateTime", Condition.ge, start);
		cHql.andBy("updateTime", Condition.le, start);
		int allcount = this.countByHql(cHql);
		return allcount;
	}
	
}