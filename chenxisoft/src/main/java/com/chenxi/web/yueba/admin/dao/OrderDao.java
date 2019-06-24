package com.chenxi.web.yueba.admin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.chenxi.web.classes.OrderStatus;
import com.chenxi.web.po.ArticlePo;
import com.chenxi.web.po.ProductPo;
import com.chenxi.web.yueba.admin.po.OrderPo;
import com.chenxi.web.yueba.admin.po.WorkerPo;

import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.CountHql;
import pub.caterpillar.orm.hql.QueryHql;

@Repository("OrderDao")
public class OrderDao extends AbstractBaseDao<OrderPo> {
	public int countCompleteByWorker(long workerId){
		CountHql hql=this.newCountHql();
		hql.andBy("workerId",Condition.eq,workerId);
		hql.andBy("orderStatus",Condition.eq,OrderStatus.COMPLETE);
		return countByHql(hql);
	}
}
