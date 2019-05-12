package com.guolaiwan.bussiness.admin.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.guolaiwan.bussiness.admin.po.LiveGiftPO;
import com.guolaiwan.bussiness.admin.po.LiveTipGiftPO;

import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.CountHql;
import pub.caterpillar.orm.hql.QueryHql;

@Repository("com.guolaiwan.bussiness.admin.dao.LiveTipGiftDAO")
public class LiveTipGiftDAO extends AbstractBaseDao<LiveTipGiftPO>{
	
	
	public List<LiveTipGiftPO> GetListbyPage(int pageNum,int pageSize) {
		QueryHql hql = this.newQueryHql();
		hql.orderBy("id", true);
		List<LiveTipGiftPO> LiveTipGiftPO =findByHqlPage(hql, pageNum, pageSize);
		if(LiveTipGiftPO==null || LiveTipGiftPO.size()<=0) return null;
		return LiveTipGiftPO;
	}

	//统计总数
	public int  getCountByPage() {
		CountHql cHql=this.newCountHql();
		int allcount=this.countByHql(cHql);
		return allcount;
	}

	public List<LiveTipGiftPO> GetListbysort() {
		QueryHql hql = this.newQueryHql();
		hql.orderBy("sort", true);
		List<LiveTipGiftPO> LiveTipGiftPO =findByHql(hql);
		if(LiveTipGiftPO==null || LiveTipGiftPO.size()<=0) return null;
		return LiveTipGiftPO;
	}



}
