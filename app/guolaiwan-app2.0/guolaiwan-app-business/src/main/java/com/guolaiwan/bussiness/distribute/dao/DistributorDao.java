package com.guolaiwan.bussiness.distribute.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.guolaiwan.bussiness.admin.po.DistributorPO;
import com.guolaiwan.bussiness.distribute.classify.DistributorApplyStatus;
import com.guolaiwan.bussiness.distribute.classify.DistributorType;
import com.guolaiwan.bussiness.distribute.po.DistributeProduct;
import com.guolaiwan.bussiness.distribute.po.DistributorPo;

import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.CountHql;
import pub.caterpillar.orm.hql.QueryHql;

@Component
public class DistributorDao extends AbstractBaseDao<DistributorPo> {
	public List<DistributorPo> queryByUserId(Long userId) {
		QueryHql hql = this.newQueryHql();
		hql.andBy("userId", Condition.eq, userId);
		return this.findByHql(hql);
	}

	// 统计总条数
	public int getCountByPage() {
		CountHql cHql = this.newCountHql();

		int allcount = this.countByHql(cHql);
		return allcount;
	}

	// 分页查询
	public List<DistributorPo> getListByPageA(String[] names, Object[] values, int pageNum, int pageSize) {
		QueryHql hql = this.newQueryHql();
		for (int i = 0; i < names.length; i++) {
			hql.andBy(names[i], Condition.eq, values[i]);
		}
		hql.orderBy("updateTime", true);
		List<DistributorPo> distributorPOs = findByHqlPage(hql, pageNum, pageSize);
		if (distributorPOs == null || distributorPOs.size() <= 0)
			return null;
		return distributorPOs;
	}

	// 通用带参总数
	public int getCountByPageA(String[] names, Object[] values) {
		CountHql cHql = this.newCountHql();
		for (int i = 0; i < names.length; i++) {
			cHql.andBy(names[i], Condition.eq, values[i]);
		}
		int allcount = this.countByHql(cHql);
		return allcount;
	}

	// 获得所有分销商
	public int countByCom(long region) {
		CountHql chql = this.newCountHql();
		chql.andBy("region", Condition.eq, region);
		int count = countByHql(chql);
		return count;
	}

	// 获取某个公司下的所有产品
	public List<DistributorPo> findByCom(long comId, int pageNum, int pageSize) {
		//DistributorApplyStatus status = DistributorApplyStatus.valueOf("PASSED");
		QueryHql hql = this.newQueryHql();
		//hql.andBy("status", Condition.eq, status);
		hql.orderBy("updateTime", true);
		List<DistributorPo> products = findByHqlPage(hql, pageNum, pageSize);
		if (products == null || products.size() <= 0)
			return null;
		return products;
	}

	public List<DistributorPo> getDistributorById(long id) {
		QueryHql hql = this.newQueryHql();
		hql.andBy("id", Condition.eq, id);
		return this.findByHql(hql);
	}

}
