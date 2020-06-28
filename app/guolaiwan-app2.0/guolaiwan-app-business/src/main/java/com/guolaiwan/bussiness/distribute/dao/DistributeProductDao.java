package com.guolaiwan.bussiness.distribute.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Component;

import com.guolaiwan.bussiness.admin.po.CarouselPO;
import com.guolaiwan.bussiness.distribute.classify.RecommendType;
import com.guolaiwan.bussiness.distribute.po.DistributeProduct;

import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.CountHql;
import pub.caterpillar.orm.hql.QueryHql;

@Component
public class DistributeProductDao extends AbstractBaseDao<DistributeProduct> {
	
	public List<DistributeProduct> queryAllByDistributor(Long distributorId){
		QueryHql hql = this.newQueryHql();
		hql.andBy("distributorId", Condition.eq, distributorId);
		return this.findByHql(hql);
	}
	public List<DistributeProduct> queryOnlineByDistributor(Long distributorId){
		QueryHql hql = this.newQueryHql();
		hql.andBy("distributorId", Condition.eq, distributorId);
		hql.andBy("online", Condition.eq, 1);
		return this.findByHql(hql);
	}
	
	public DistributeProduct queryOnlineByDisAndProId(long disId,long proId){
		QueryHql hql = this.newQueryHql();
		hql.andBy("product.id",Condition.eq,proId);
		hql.andBy("distributorId", Condition.eq, disId);
		hql.andBy("online", Condition.eq, 1);
		List<DistributeProduct> distributeProducts=this.findByHql(hql);
		if(!distributeProducts.isEmpty()){
			return distributeProducts.get(0);
		}
		return null;
	}
	
	public List<DistributeProduct> queryOnlineByRegionAndRecomm(Long regionId,long proRegionId,RecommendType type){
		QueryHql hql = this.newQueryHql();
		hql.andBy("recommendType", Condition.eq, type);
		hql.andBy("regionId", Condition.eq, regionId);
		hql.andBy("online", Condition.eq, 1);
		hql.andBy("proRegionId",Condition.eq,proRegionId);
		return this.findByHql(hql);
	}
	public List<DistributeProduct> queryOnlineByRegion(Long regionId){
		QueryHql hql = this.newQueryHql();
		hql.andBy("regionId", Condition.eq, regionId);
		hql.andBy("online", Condition.eq, 1);
		return this.findByHql(hql);
	}
	public List<DistributeProduct> queryAllFirstRegionPro(long regionId){
		QueryHql hql = this.newQueryHql();
		hql.andBy("regionId", Condition.eq, 0l);
		hql.andBy("product.comId", Condition.eq, regionId);
		return this.findByHql(hql);
	}
	
	public List<DistributeProduct> queryAllByMerchant(long merchantId){
		QueryHql hql = this.newQueryHql();
		hql.andBy("online", Condition.eq, 1);
		hql.andBy("product.productMerchantID",Condition.eq,merchantId);
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
	

	
	//按分公司查询
	public List<DistributeProduct> queryOnlineByCom(Long regionId,int count){
		QueryHql hql = this.newQueryHql();
		hql.andBy("regionId", Condition.eq, regionId);
		return this.findByHqlPage(hql, 1, count);
	}
	
	
}
