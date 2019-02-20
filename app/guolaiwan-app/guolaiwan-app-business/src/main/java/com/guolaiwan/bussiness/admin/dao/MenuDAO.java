package com.guolaiwan.bussiness.admin.dao;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import com.guolaiwan.bussiness.admin.po.MenuPO;
import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.QueryHql;

@Repository
public class MenuDAO extends AbstractBaseDao<MenuPO>{

	//获取所有的菜单按时间排序
	public List<MenuPO> getAllMenu(){
		QueryHql hql = this.newQueryHql();
		hql.orderBy("updateTime", false);
		return this.findByHql(hql);
	}
	
	//获取根菜单
	public List<MenuPO> getRootMenu(){
		String hql = "from MenuPO where parent.id is null";
		Session session = this.getCurrentSession();
		Query query = session.createQuery(hql);
		return query.list();
	}
	
	//获取子菜单
	public List<MenuPO> getSubMenu(Long menuId){
		QueryHql hql = this.newQueryHql();
		hql.andBy("parent.id", Condition.eq, menuId);
		return this.findByHql(hql);
	}
	
}
