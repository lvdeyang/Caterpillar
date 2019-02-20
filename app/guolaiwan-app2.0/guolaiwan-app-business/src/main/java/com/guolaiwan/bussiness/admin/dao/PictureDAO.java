package com.guolaiwan.bussiness.admin.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.guolaiwan.bussiness.admin.po.PicproRelationPO;
import com.guolaiwan.bussiness.admin.po.PicturePO;

import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.CountHql;
import pub.caterpillar.orm.hql.DeleteHql;
import pub.caterpillar.orm.hql.QueryHql;


@Repository("com.guolaiwan.bussiness.admin.dao.PictureDAO")
public class PictureDAO extends AbstractBaseDao<PicturePO> {
	public List<PicturePO> getPictureByPage(int pageNum,int pageSize){
   	 QueryHql hql = this.newQueryHql();
   	 hql.orderBy("updateTime", true);
     List<PicturePO> pictures= findByHqlPage(hql,pageNum,pageSize);
   	 if(pictures==null || pictures.size()<=0) return null;
   	 return pictures;
    }
	
	//统计总数
	public int  getCountByPage() {
		CountHql cHql=this.newCountHql();
		int allcount=this.countByHql(cHql);
		return allcount;
	}
	
	//
	public void deleteByUuid(String uuid){
	   	 DeleteHql hql = this.newDeleteHql();
	   	 hql.andBy("uuid",Condition.eq, uuid);
	     deleteByHql(hql);
	    }
	
	

	/**
	 * 获取图片对象通过子产品图片关联对象（App接口调用）
	 * @param childID 子产品Id
	 * @return
	 */
	public List<PicturePO> getPicsByPprs(
			List<PicproRelationPO> pprs
			){
		List<PicturePO> pictures = new ArrayList<PicturePO>();
		for (PicproRelationPO ppr : pprs) {
			PicturePO picture = this.getByField("id", ppr.getChildPicID());
			pictures.add(picture);
		}
		return pictures;
	}
}
