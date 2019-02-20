package com.guolaiwan.bussiness.admin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.guolaiwan.bussiness.admin.po.ClassPO;
import com.guolaiwan.bussiness.admin.po.BewritePO;

import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.CountHql;
import pub.caterpillar.orm.hql.QueryHql;
@Repository("com.guolaiwan.bussiness.admin.dao.BewriteDAO")
public class BewriteDAO extends AbstractBaseDao<BewritePO>{
	public List<BewritePO> GetListbyPage(int pageNum,int pageSize) {
		QueryHql hql = this.newQueryHql();

		hql.orderBy("id", true);
		List<BewritePO> Bewrites =findByHqlPage(hql,pageNum,pageSize);
		if(Bewrites==null || Bewrites.size()<=0) return null;
		return Bewrites;
	}
	
	
	public List<BewritePO> getPagesByImg(long picId, int pageNum,int pageSize) {
		QueryHql hql = this.newQueryHql();
		hql.andBy("imgId", Condition.eq, picId);
		hql.orderBy("id", true);
		List<BewritePO> Bewrites =findByHqlPage(hql,pageNum,pageSize);
		if(Bewrites==null || Bewrites.size()<=0) return null;
		return Bewrites;
	}
	//统计总数
	public int  GetCountByPage() {
		CountHql cHql=this.newCountHql();

		int allcount=this.countByHql(cHql);
		return allcount;
	}
}
