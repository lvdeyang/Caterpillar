package com.guolaiwan.bussiness.chapman.source.dao;

import java.util.Collection;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.guolaiwan.bussiness.chapman.source.enumeration.SourceType;
import com.guolaiwan.bussiness.chapman.source.po.SourcePO;
import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.QueryHql;

@Repository
public class SourceDAO extends AbstractBaseDao<SourcePO>{

	//获取一个目录下特定的图片
	public SourcePO getFolderPictureByName(Long folderId, String sourcename){
		QueryHql hql = this.newQueryHql();
		hql.andBy("sourceFolder.id", Condition.eq, folderId);
		hql.andBy("introduction", Condition.eq, sourcename);
		List<SourcePO> sources = this.findByHql(hql);
		if(sources==null || sources.size()<=0){
			return null;
		}else{
			return sources.get(0);
		}
	}
	
	//获取一个目录下所有图片
	public List<SourcePO> getFolderPictures(Long folderId){
		QueryHql hql = this.newQueryHql();
		hql.andBy("sourceFolder.id", Condition.eq, folderId);
		hql.andBy("type", Condition.eq, SourceType.PICTURE);
		System.out.println(hql);
		return this.findByHql(hql);
	}
	
	//获取商户的特定图片
	public List<SourcePO> getPictures(Long folderId, Collection<Long> pictureIds){
		if(pictureIds==null || pictureIds.size()<=0) return null;
		QueryHql hql = this.newQueryHql();
		hql.andBy("sourceFolder.id", Condition.eq, folderId);
		hql.andBy("id", Condition.in, pictureIds);
		hql.andBy("type", Condition.eq, SourceType.PICTURE);
		return this.findByHql(hql);
	}
	
	//获取图片
	public SourcePO getPicture(Long sourceId){
		QueryHql hql = this.newQueryHql();
		hql.andBy("id", Condition.eq, sourceId);
		hql.andBy("type", Condition.eq, SourceType.PICTURE);
		List<SourcePO> sources = this.findByHql(hql);
		if(sources==null || sources.size()<=0) return null;
		return sources.get(0);
	}
	
}
