package com.guolaiwan.bussiness.admin.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.guolaiwan.bussiness.admin.po.LiveGiftPO;
import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.CountHql;
import pub.caterpillar.orm.hql.QueryHql;

@Repository("com.guolaiwan.bussiness.admin.dao.LiveGiftDAO")
public class LiveGiftDAO extends AbstractBaseDao<LiveGiftPO>{
	
	
	public List<LiveGiftPO> GetListbyPage(int pageNum,int pageSize) {
		QueryHql hql = this.newQueryHql();

		hql.orderBy("id", true);
		List<LiveGiftPO> liveGiftPO =findByHqlPage(hql,pageNum,pageSize);
		if(liveGiftPO==null || liveGiftPO.size()<=0) return null;
		return liveGiftPO;
	}

	//统计总数
	public int  getCountByPage() {
		CountHql cHql=this.newCountHql();
		int allcount=this.countByHql(cHql);
		return allcount;
	}

	public List<LiveGiftPO> GetListbysort() {
		QueryHql hql = this.newQueryHql();
		hql.orderBy("sort", true);
		List<LiveGiftPO> liveGiftPO =findByHql(hql);
		if(liveGiftPO==null || liveGiftPO.size()<=0) return null;
		return liveGiftPO;
	}



}
