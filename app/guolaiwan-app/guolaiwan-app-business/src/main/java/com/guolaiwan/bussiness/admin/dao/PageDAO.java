package com.guolaiwan.bussiness.admin.dao;

import java.util.Collection;
import java.util.List;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;
import com.guolaiwan.bussiness.admin.dto.PageDTO;
import com.guolaiwan.bussiness.admin.po.PagePO;
import com.guolaiwan.bussiness.chapman.product.dto.ProductDTO;

import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;
import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.QueryHql;

@Repository
public class PageDAO extends AbstractBaseDao<PagePO> {

	//获取布局下的所有页面
	public List<PageDTO> getPageByLayout(Long layoutId){
		StringBufferWrapper sqlWrapper = new StringBufferWrapper().append("SELECT p.id id, p.uuid uuid, p.updateTime updateTime, p.name name, p.type type, ")
																  .append("m.id menuid, m.name menuname ")
																  .append("FROM t_app_page p ")
																  .append("LEFT JOIN t_app_menu m ")
																  .append("ON p.menu_id=m.id ")
																  .append("WHERE p.page_layout_id=")
																  .append(layoutId);
		SQLQuery query = getCurrentSession().createSQLQuery(sqlWrapper.toString())
											.addScalar("id", StandardBasicTypes.LONG)
											.addScalar("updateTime", StandardBasicTypes.DATE)
											.addScalar("uuid")
											.addScalar("name")
											.addScalar("type")
											.addScalar("menuid", StandardBasicTypes.LONG)
											.addScalar("menuname");
		query.setResultTransformer(Transformers.aliasToBean(PageDTO.class));
		return query.list();
	}
	
	//获取布局下特定的页面
	public List<PagePO> getPageByLayout(Long layoutId, Collection<Long> pageIds){
		if(pageIds==null || pageIds.size()<=0) return null;
		QueryHql hql = this.newQueryHql();
		hql.andBy("pageLayout.id", Condition.eq, layoutId);
		hql.andBy("id", Condition.in, pageIds);
		return this.findByHql(hql);
	}
	
	//根据菜单获取生效布局下的页面
	public PagePO getPageByMenu(Long layoutId, Long menuId){
		QueryHql hql = this.newQueryHql();
		hql.andBy("pageLayout.id", Condition.eq, layoutId);
		hql.andBy("menu.id", Condition.eq, menuId);
		List<PagePO> pages = this.findByHql(hql);
		if(pages==null || pages.size()<=0) return null;
		return pages.get(0);
	}
	
}
