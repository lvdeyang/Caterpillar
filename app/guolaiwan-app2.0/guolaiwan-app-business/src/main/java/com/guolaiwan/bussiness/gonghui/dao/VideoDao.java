package com.guolaiwan.bussiness.gonghui.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.guolaiwan.bussiness.gonghui.po.ClassesPo;
import com.guolaiwan.bussiness.gonghui.po.RecordPo;
import com.guolaiwan.bussiness.gonghui.po.VideoPo;

import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.QueryHql;

@Repository("com.guolaiwan.bussiness.gonghui.dao.VideoDao")
public class VideoDao extends AbstractBaseDao<VideoPo> {
	
	public List<VideoPo> findByPage(int pageNum, int pageSize) {
		QueryHql hql = this.newQueryHql();
		hql.orderBy("updateTime", true);
		List<VideoPo> videos = findByHqlPage(hql, pageNum, pageSize);
		if (videos == null || videos.size() <= 0)
			return null;
		return videos;
	}
	
	public List<VideoPo> findByCompanyTypePage(String companyType,int pageNum, int pageSize) {
		QueryHql hql = this.newQueryHql();
		hql.andBy("companyType",Condition.eq,companyType);
		hql.orderBy("updateTime", true);
		List<VideoPo> videos = findByHqlPage(hql, pageNum, pageSize);
		if (videos == null || videos.size() <= 0)
			return null;
		return videos;
	}
	public List<VideoPo> findByCompanyPage(String company,int pageNum, int pageSize) {
		QueryHql hql = this.newQueryHql();
		hql.andBy("company",Condition.eq,company);
		hql.orderBy("updateTime", true);
		List<VideoPo> videos = findByHqlPage(hql, pageNum, pageSize);
		if (videos == null || videos.size() <= 0)
			return null;
		return videos;
	}
	
	public List<VideoPo> findPassByPage(int pageNum, int pageSize) {
		QueryHql hql = this.newQueryHql();
		
		hql.andBy("passed",Condition.eq,1);
		//hql.orderBy("updateTime", true);
		hql.orderBy("aCount", true);
		List<VideoPo> videos = findByHqlPage(hql, pageNum, pageSize);
		if (videos == null || videos.size() <= 0)
			return null;
		return videos;
	}
	
	public List<VideoPo> findSendByUserId(Long userId) {
		QueryHql hql = this.newQueryHql();
		
		hql.andBy("send",Condition.eq,0);
		hql.andBy("userId",Condition.eq,userId);
		List<VideoPo> videos = findByHql(hql);
		if (videos == null || videos.size() <= 0)
			return null;
		return videos;
	}

}
