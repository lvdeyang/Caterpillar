package com.guolaiwan.bussiness.distribute.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Component;

import com.guolaiwan.bussiness.admin.po.CarouselPO;
import com.guolaiwan.bussiness.distribute.classify.DistributorType;
import com.guolaiwan.bussiness.distribute.classify.RecommendType;
import com.guolaiwan.bussiness.distribute.po.DistributeProduct;

import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.CountHql;
import pub.caterpillar.orm.hql.QueryHql;

@Component
public class DistributeProductDao extends AbstractBaseDao<DistributeProduct> {
	public List<DistributeProduct> queryByTorAndType(Long distributorId,DistributorType type){
		QueryHql hql = this.newQueryHql();
		hql.andBy("distributorId", Condition.eq, distributorId);
		hql.andBy("distributorType", Condition.eq, type);
		return this.findByHql(hql);
	}
	public List<DistributeProduct> queryOnlineByTorAndType(Long distributorId,DistributorType type){
		QueryHql hql = this.newQueryHql();
		hql.andBy("distributorId", Condition.eq, distributorId);
		hql.andBy("distributorType", Condition.eq, type);
		hql.andBy("online", Condition.eq, 1);
		return this.findByHql(hql);
	}
	
	public List<DistributeProduct> queryOnlineByDistributor(Long distributorId){
		QueryHql hql = this.newQueryHql();
		hql.andBy("distributorId", Condition.eq, distributorId);
		hql.andBy("online", Condition.eq, 1);
		return this.findByHql(hql);
	}
	public List<DistributeProduct> queryOnlineByRegionAndRecomm(Long regionId,RecommendType type){
		QueryHql hql = this.newQueryHql();
		hql.andBy("recommendType", Condition.eq, type);
		hql.andBy("regionId", Condition.eq, regionId);
		hql.andBy("online", Condition.eq, 1);
		return this.findByHql(hql);
	}
	public List<DistributeProduct> queryOnlineByRegion(Long regionId){
		QueryHql hql = this.newQueryHql();
		hql.andBy("regionId", Condition.eq, regionId);
		hql.andBy("online", Condition.eq, 1);
		return this.findByHql(hql);
	}
	
	public List<DistributeProduct> queryOnlineByMerchant(Long merchantId){
		QueryHql hql = this.newQueryHql();
		hql.andBy("distributorId", Condition.eq, merchantId);
		hql.andBy("distributorType", Condition.eq,DistributorType.PROVINCE);
		hql.andBy("online", Condition.eq, 1);
		return this.findByHql(hql);
	}
	
	// 获取某个公司下的所有产品数量
	public int countByCom(long comId) {
		CountHql chql = this.newCountHql();
		if (comId != 1l) {
			chql.andBy("regionId", Condition.eq, comId);
		}
		int count = countByHql(chql);
		return count;
	}
	public int countByMerchant(long merchantId) {
		CountHql chql = this.newCountHql();
		
		chql.andBy("distributorId", Condition.eq, merchantId);
		chql.andBy("distributorType", Condition.eq, DistributorType.PROVINCE);
		int count = countByHql(chql);
		return count;
	}

}
