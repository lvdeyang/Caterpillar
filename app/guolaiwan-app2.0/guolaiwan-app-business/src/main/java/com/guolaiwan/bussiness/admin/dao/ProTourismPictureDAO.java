package com.guolaiwan.bussiness.admin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.guolaiwan.bussiness.admin.po.ProTourismPicturePO;

import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.QueryHql;

@Repository("com.guolaiwan.bussiness.admin.dao.ProTourismPictureDAO")
public class ProTourismPictureDAO extends AbstractBaseDao<ProTourismPicturePO> {
	
	/**
	 * Liw
	 * 根据景区图片id获取景区图片表所有
	 * @param 页面传进来的景区图片id
	 * @return 景区图片表list
	 */
	public List<ProTourismPicturePO> getTourismPictureById(long id){
		QueryHql hql = this.newQueryHql();
		hql.andBy("id", Condition.eq, id);
		List<ProTourismPicturePO> list = findByHql(hql);
		return list;
	}
	
	
	
}