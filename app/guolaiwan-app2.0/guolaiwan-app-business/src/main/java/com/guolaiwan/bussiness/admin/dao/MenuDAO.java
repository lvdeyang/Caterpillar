package com.guolaiwan.bussiness.admin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.guolaiwan.bussiness.admin.po.MenuPO;

import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.CountHql;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.QueryHql;

/**
 * Don't try so hard!
 * <p>Title: MenuDAO</p>
 * <p>Description: 权限DAO</p>
 * <p>Company: guolaiwan</p> 
 * @author Mr.Sun
 * @date 2017年11月20日 上午9:11:36
 */
@Repository("com.guolaiwan.bussiness.admin.dao.MenuDAO")
public class MenuDAO extends AbstractBaseDao<MenuPO> {

	//统计总数
	public int  GetCountByPage() {
		CountHql cHql=this.newCountHql();

		int allcount=this.countByHql(cHql);
		return allcount;
	}

	public List<MenuPO> GetListbyPage(int pageNum,int pageSize) {
		QueryHql hql = this.newQueryHql();
		List<MenuPO> menus =findByHqlPage(hql,pageNum,pageSize);
		if(menus==null || menus.size()<=0) return null;
		return menus;
	}

	//获取下拉框
	public List<MenuPO> getMenuByP(int pId){
		QueryHql hql = this.newQueryHql();
		hql.andBy("parentId", Condition.eq, pId);
		List<MenuPO> menus = findByHql(hql);
		return menus;
	}

	//父类获取并分页
	public List<MenuPO> getMenuByPPage(int pId,int pageNum,int pageSize){
		QueryHql hql = this.newQueryHql();
		hql.andBy("parentId", Condition.eq, pId);
		List<MenuPO> menus = findByHqlPage(hql,pageNum,pageSize);
		return menus;
	}

	//父类获取并分页
	public int getPCount(int pId){
		CountHql hql=this.newCountHql();
		hql.andBy("parentId", Condition.eq, pId);
		int pCount = countByHql(hql);
		return pCount;
	}
}
