package com.guolaiwan.bussiness.admin.dao.live;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.guolaiwan.bussiness.admin.enumeration.LiveType;
import com.guolaiwan.bussiness.admin.po.ActivityPO;
import com.guolaiwan.bussiness.admin.po.LivePO;
import com.guolaiwan.bussiness.admin.po.live.SubLivePO;

import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.DeleteHql;
import pub.caterpillar.orm.hql.QueryHql;

@Repository("com.guolaiwan.bussiness.admin.dao.live.SubLiveDao")
public class SubLiveDao extends AbstractBaseDao<SubLivePO> {
	
	public void deleteByLive(long liveId){
		DeleteHql hql = this.newDeleteHql();
		hql.andBy("livePO.id", Condition.eq, liveId);
		this.deleteByHql(hql);
	}
	
	/**
	 * 通过直播订单id:liverOrderId删除t_sys_sublive表中的数据
	 * @param orderId
	 */
	public void deleteByOrderId(long liverOrderId){
		DeleteHql hql = this.newDeleteHql();
		hql.andBy("liveOrderPO.id", Condition.eq, liverOrderId);
		this.deleteByHql(hql);
	}
	
	/**
	 * 通过liveId和cameraNumber
	 * 获取t_sys_sublive中对应机位的信息
	 * @param liveId       直播Id
	 * @param cameraNumber 机位编号
	 * @return SubLivePO   
	 */
	public SubLivePO getSubLivePO(long liveId,int cameraNumber){
		QueryHql hql = newQueryHql();
		hql.andBy("liveId", Condition.eq, liveId);
		hql.andBy("cameraNumber", Condition.eq, cameraNumber);
		List<SubLivePO> subLivePOList = findByHql(hql);
		if(subLivePOList == null || subLivePOList.size()<=0) return null;
		return subLivePOList.get(0);
	}
	
	/**
	 * 获取liveId对应的所有SubLivePO
	 * @param liveId       直播Id
	 * @return SubLivePO   
	 */
	public List<SubLivePO>  findSubLivePOsByLiveId(long liveId){
		QueryHql hql = newQueryHql();
		hql.andBy("liveId", Condition.eq, liveId);
		List<SubLivePO> subLivePOList = findByHql(hql);
		if(subLivePOList == null || subLivePOList.size()<=0) return null;
		return subLivePOList;
	}
	
	/**
	 * 获取id对应的所有SubLivePO
	 * @param id SubLivePO的id
	 * @return SubLivePO   
	 */
	public SubLivePO  findSubLivePOById(long id){
		QueryHql hql = newQueryHql();
		hql.andBy("id", Condition.eq, id);
		List<SubLivePO> subLivePOList = findByHql(hql);
		if(subLivePOList == null || subLivePOList.size()<=0) return null;
		return subLivePOList.get(0);
	}
	

}
