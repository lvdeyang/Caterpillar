package com.guolaiwan.bussiness.admin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.guolaiwan.bussiness.admin.po.LanPO;

import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.CountHql;
import pub.caterpillar.orm.hql.QueryHql;

@Repository("com.guolaiwan.bussiness.admin.dao.LanDao")
public class LanDAO extends AbstractBaseDao<LanPO> {
	
	/**
	 * Liw 根据页面传进来的语言id查询语言表全部
	 * 
	 * @param 页面传进来的语言id
	 * @return 语言表list
	 */
	public List<LanPO> getLanById(long id) {
		QueryHql hql = this.newQueryHql();
		hql.andBy("id", Condition.eq, id);
		List<LanPO> list = findByHql(hql);
		return list;
	}
	
	/**
	 * Liw
	 * 获取所有语言
	 */
	public List<LanPO> getAllLans(){
		List<LanPO> list = findAll();
		return list;
	}
	
	public List<LanPO> getLanByMerchantId(long merchantId){
		QueryHql hql = this.newQueryHql();
		hql.andBy("merchantId", Condition.eq, merchantId);
		List<LanPO> list = findByHql(hql);
		return list;
	}
	
}