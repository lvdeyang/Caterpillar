package com.guolaiwan.bussiness.chapman.source.dao;

import java.util.Collection;
import java.util.List;
import org.springframework.stereotype.Repository;

import com.guolaiwan.bussiness.chapman.source.enumeration.SourceFolderType;
import com.guolaiwan.bussiness.chapman.source.po.SourceFolderPO;
import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.QueryHql;

@Repository
public class SourceFolderDAO extends AbstractBaseDao<SourceFolderPO>{

	//获取一个文件夹的子文件夹
	public List<SourceFolderPO> getSubFolders(Long chapmanId, Long parentId){
		QueryHql hql = this.newQueryHql();
		hql.andBy("chapman.id", Condition.eq, chapmanId);
		hql.andBy("parent.id", Condition.eq, parentId);
		return this.findByHql(hql);
	}
	
	//获取一个商户的根路径
	public SourceFolderPO getRootFolder(Long chapmanId){
		QueryHql hql = this.newQueryHql();
		hql.andBy("chapman.id", Condition.eq, chapmanId);
		hql.andBy("type", Condition.eq, SourceFolderType.DEFAULT);
		List<SourceFolderPO> folders = this.findByHql(hql);
		if(folders==null || folders.size()<=0){
			return null;
		}else{
			return folders.get(0);
		}
	}
	
	//获取商户目录下特定名称的目录
	public SourceFolderPO getFolderByName(Long chapmanId, Long parentFolderId, String foldername){
		QueryHql hql = this.newQueryHql();
		hql.andBy("chapman.id", Condition.eq, chapmanId);
		hql.andBy("foldername", Condition.eq, foldername);
		hql.andBy("parent.id", Condition.eq, parentFolderId);
		List<SourceFolderPO> folders = this.findByHql(hql);
		if(folders==null || folders.size()<=0){
			return null;
		}else{
			return folders.get(0);
		}
	}
	
	//获取商户特定目录
	public List<SourceFolderPO> getFolders(Long chapmanId, Collection<Long> folderIds){
		if(folderIds==null || folderIds.size()<=0) return null;
		QueryHql hql = this.newQueryHql();
		hql.andBy("chapman.id", Condition.eq, chapmanId);
		hql.andBy("id", Condition.in, folderIds);
		return this.findByHql(hql);
	}
	
}
