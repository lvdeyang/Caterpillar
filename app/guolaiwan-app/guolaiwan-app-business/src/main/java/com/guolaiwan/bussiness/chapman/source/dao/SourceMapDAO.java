package com.guolaiwan.bussiness.chapman.source.dao;

import java.util.Collection;
import java.util.List;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;
import com.guolaiwan.bussiness.chapman.enumeration.TargetType;
import com.guolaiwan.bussiness.chapman.source.dto.SourceMapDTO;
import com.guolaiwan.bussiness.chapman.source.enumeration.SourceMapType;
import com.guolaiwan.bussiness.chapman.source.enumeration.SourceType;
import com.guolaiwan.bussiness.chapman.source.po.SourceMapPO;
import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;
import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.QueryHql;

@Repository
public class SourceMapDAO extends AbstractBaseDao<SourceMapPO>{

	//获取产品明细的特定资源
	public List<SourceMapPO> getSourceMaps(Long detailId, Collection<Long> sourceMapIds){
		if(sourceMapIds==null || sourceMapIds.size()<=0) return null;
		QueryHql hql = this.newQueryHql();
		hql.andBy("targetId", Condition.eq, detailId);
		hql.andBy("targetType", Condition.eq, TargetType.PRODUCTDETAIL);
		hql.andBy("id", Condition.in, sourceMapIds);
		return this.findByHql(hql);
	}
	
	//获取一个产品明细的所有图片--重载
	public List<SourceMapDTO> getPictures(String productDetailId){
		return getPictures(Long.valueOf(productDetailId));
	}
	
	//获取一个产品明细的所有图片
	public List<SourceMapDTO> getPictures(Long productDetailId){
		StringBufferWrapper sqlWrapper = new StringBufferWrapper().append("select sm.id id, sm.updateTime updateTime, ")
																  .append("sm.uuid uuid, sm.type mapType, s.introduction introduction, ")
																  .append("s.path path, s.size size, s.type sourceType, ")
																  .append("s.file_id fileid, s.source_folder_id folderid, ")
																  .append("sf.foldername foldername from t_app_source s ")
																  .append("left JOIN t_app_source_map sm on s.id=sm.source_id ")
																  .append("LEFT JOIN t_app_source_folder sf on s.source_folder_id=sf.id ")
																  .append("where s.type='")
																  .append(SourceType.PICTURE.toString())
																  .append("' and sm.target_type='")
																  .append(TargetType.PRODUCTDETAIL.toString())
																  .append("' and sm.target_id=")
																  .append(productDetailId);
		SQLQuery query = getCurrentSession().createSQLQuery(sqlWrapper.toString())
											.addScalar("id", StandardBasicTypes.LONG)
											.addScalar("updateTime", StandardBasicTypes.TIMESTAMP)
											.addScalar("uuid")
											.addScalar("mapType")
											.addScalar("introduction")
											.addScalar("path")
											.addScalar("size")
											.addScalar("sourceType")
											.addScalar("fileid", StandardBasicTypes.LONG)
											.addScalar("folderid", StandardBasicTypes.LONG)
											.addScalar("foldername");
		query.setResultTransformer(Transformers.aliasToBean(SourceMapDTO.class));

		return query.list();
	}
	
	//获取产品详情图片
	public List<SourceMapDTO> getProductInfoPictures(Long productDetailId){
		StringBufferWrapper sqlWrapper = new StringBufferWrapper().append("SELECT s.path path, f.content image ")
																  .append("FROM t_app_source s ")
																  .append("LEFT JOIN t_app_source_map sm ON s.id=sm.source_id ")
																  .append("LEFT JOIN t_app_file f ON s.file_id=f.id ")
																  .append("WHERE s.type='")
																  .append(SourceType.PICTURE)
																  .append("' ")
																  .append("AND sm.target_type='")
																  .append(TargetType.PRODUCTDETAIL)
																  .append("' ")
																  .append("AND sm.type='")
																  .append(SourceMapType.INFO)
																  .append("' ")
																  .append("AND sm.target_id=")
																  .append(productDetailId)
																  .append(" ")
																  .append("ORDER BY sm.updateTime");
		SQLQuery query = getCurrentSession().createSQLQuery(sqlWrapper.toString())
											.addScalar("path")
											.addScalar("image", StandardBasicTypes.BINARY);
		query.setResultTransformer(Transformers.aliasToBean(SourceMapDTO.class));

		return query.list();
	}
	
}
