package com.guolaiwan.bussiness.admin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.guolaiwan.bussiness.admin.po.LiveMessagePO;
import com.guolaiwan.bussiness.admin.po.LiveProductPO;
import com.guolaiwan.bussiness.admin.po.OlChatMessagePO;

import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.DeleteHql;
import pub.caterpillar.orm.hql.QueryHql;

@Repository("com.guolaiwan.bussiness.admin.dao.OlChatMessageDAO")
public class OlChatMessageDAO extends AbstractBaseDao<OlChatMessagePO> {

	public List<OlChatMessagePO> findAll(final int page, final int pageSize) {
		QueryHql hql = newQueryHql();
		hql.orderBy("updateTime", true);
		return findByHql(hql, page, pageSize);
	}
	
	
	/**
	 * 在同一个商户的页面 获取没有发送的 对应着用户的信息
	 * 发送到页面展示
	 * @param merchantId
	 * @param touserId
	 * @return
	 */
	public List<OlChatMessagePO> findByFlag(long merchantId) {
		QueryHql hql = newQueryHql();
		hql.andBy("merchantId", Condition.eq, merchantId);
		hql.andBy("flag", Condition.eq, false);
		hql.orderBy("updateTime", true);
		return findByHql(hql);
	}
	
	
	
	
	
}
