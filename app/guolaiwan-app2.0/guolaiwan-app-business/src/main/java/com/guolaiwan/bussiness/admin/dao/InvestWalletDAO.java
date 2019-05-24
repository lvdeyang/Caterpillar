package com.guolaiwan.bussiness.admin.dao;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.guolaiwan.bussiness.admin.po.InvestWalletPO;
import com.guolaiwan.bussiness.admin.po.LiveGiftPO;
import com.guolaiwan.bussiness.admin.po.LiveTipGiftPO;
import com.guolaiwan.bussiness.admin.po.UserInfoPO;

import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.CountHql;
import pub.caterpillar.orm.hql.QueryHql;

@Repository("com.guolaiwan.bussiness.admin.dao.InvestWalletDAO")
public class InvestWalletDAO extends AbstractBaseDao<InvestWalletPO>{
	
	
	public List<InvestWalletPO> GetListbyPage(int pageNum,int pageSize) {
		QueryHql hql = this.newQueryHql();
		hql.orderBy("id", true);
		List<InvestWalletPO> InvestWalletPO =findByHqlPage(hql, pageNum, pageSize);
		if(InvestWalletPO==null || InvestWalletPO.size()<=0) return null;
		return InvestWalletPO;
	}

	//统计总数
	public int  getCountByPage() {
		CountHql cHql=this.newCountHql();
		int allcount=this.countByHql(cHql);
		return allcount;
	}

	public List<InvestWalletPO> GetListbysort() {
		QueryHql hql = this.newQueryHql();
		hql.orderBy("sort", true);
		List<InvestWalletPO> InvestWalletPO =findByHql(hql);
		if(InvestWalletPO==null || InvestWalletPO.size()<=0) return null;
		return InvestWalletPO;
	}

	public List<InvestWalletPO> GetListbyPage(int pageNum, int pageSize, String userid, String username) {
		QueryHql hql = this.newQueryHql();
		hql.andBy("userid", Condition.lk, userid);
		hql.andBy("username", Condition.lk, username);
		hql.orderBy("updateTime", true);
		List<InvestWalletPO> userinfos = findByHqlPage(hql, pageNum, pageSize);
		if (userinfos == null || userinfos.size() <= 0)
			return null;
		return userinfos;
	}
	
	// 统计总数
	public int countByUserId(String userid) {
		CountHql cHql = this.newCountHql();
		cHql.andBy("userid", Condition.lk, userid);
		int allcount = this.countByHql(cHql);
		return allcount;
	}


}
