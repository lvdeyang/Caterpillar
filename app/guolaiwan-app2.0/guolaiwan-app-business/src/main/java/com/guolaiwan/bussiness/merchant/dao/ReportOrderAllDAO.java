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
import com.guolaiwan.bussiness.merchant.po.ReportOrderAllPO;
import com.guolaiwan.bussiness.merchant.po.ReportOrderPO;

import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;
import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.CountHql;
import pub.caterpillar.orm.hql.QueryHql;


@Repository("com.guolaiwan.bussiness.merchant.dao.ReportOrderAllDAO")
public class ReportOrderAllDAO extends AbstractBaseDao<ReportOrderAllPO> {
	public int GetCountByHour(Date start,Date end) {
		QueryHql cHql = this.newQueryHql();
		cHql.andBy("updateTime", Condition.ge, start);
		cHql.andBy("updateTime", Condition.le, start);
		int allcount = 0;
		List<ReportOrderAllPO> reportOrderPOs=this.findByHql(cHql);
		for (ReportOrderAllPO reportOrderAllPO : reportOrderPOs) {
			allcount+=reportOrderAllPO.getCount();
		}
		return allcount;
	}
	
	public List<ReportDTO> getSexData(int pageNum, int pageSize) {
		StringBufferWrapper sqlWrapper = new StringBufferWrapper().append(
				"select SUM(count) acount,sexString from t_sys_reportorderall GROUP BY sex ORDER BY acount desc");
		
		SQLQuery query = getCurrentSession().createSQLQuery(sqlWrapper.toString())
				.addScalar("sexString", StandardBasicTypes.STRING).addScalar("acount", StandardBasicTypes.INTEGER);
		query.setResultTransformer(Transformers.aliasToBean(ReportDTO.class));

		// 分页
		int firstResult = (pageNum - 1) * pageSize;
		query.setFirstResult(firstResult);
		query.setMaxResults(pageSize);
		return query.list();
	}
	
}