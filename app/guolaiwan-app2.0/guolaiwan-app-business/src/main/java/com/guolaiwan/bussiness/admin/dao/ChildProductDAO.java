package com.guolaiwan.bussiness.admin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.guolaiwan.bussiness.admin.po.ChildProductPO;

import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.CountHql;
import pub.caterpillar.orm.hql.QueryHql;
import pub.caterpillar.orm.hql.SubHql;


@Repository("com.guolaiwan.bussiness.admin.dao.ChildProductDAO")
public class ChildProductDAO extends AbstractBaseDao<ChildProductPO> {
	/**
	 * 获取某个产品下的子产品（App接口调用）
	 * @param productID 产品Id
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public List<ChildProductPO> getChildByProductId(
			long productID,
			int pageNum,
			int pageSize){
		QueryHql hql = this.newQueryHql();
		hql.andBy("productID", Condition.eq, productID);
		List<ChildProductPO> childs =findByHqlPage(hql,pageNum,pageSize);
		return childs;
	}
	/**
	 * 获取某个产品下的子产品（App接口调用）
	 * 算法支持,根据商品id查询所有的子产品集合
	 * @param productID 产品Id
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public List<ChildProductPO> getChildByProductId(
			long productID){
		QueryHql hql = this.newQueryHql();
		hql.andBy("productID", Condition.eq, productID);
		List<ChildProductPO> childs =findByHql(hql);
		return childs;
	}
	
	public List<ChildProductPO> getChildByProductAndLan(Long proId,Long lanId){
		QueryHql hql = this.newQueryHql();
		hql.andBy("productID", Condition.eq, proId);
		hql.andBy("lanId", Condition.eq, lanId);
		List<ChildProductPO> childs =findByHql(hql);
		return childs;
	}
	
	/*
	 * 根据商品id查询所有导览点
	 */
	public List<ChildProductPO> getChildByProAndLan(
			long productID){
		QueryHql hql = this.newQueryHql();
		hql.andBy("productID", Condition.eq, productID);
		List<ChildProductPO> childs =findByHql(hql);
		return childs;
	}
	
	public List<ChildProductPO> getChildByProAndRegion(
			long productID,long regionId){
		QueryHql hql = this.newQueryHql();
		hql.andBy("productID", Condition.eq, productID);
		hql.andBy("region", Condition.eq, regionId);
		
		List<ChildProductPO> childs =findByHql(hql);
		return childs;
	}
	
	/**
	 * Liw
	 * 根据导览点id,语言id,商品id查询一条信息
	 * @return 一条导览点信息
	 */
	public List<ChildProductPO> getChildByPidAndLanIdAndCid(long id , long pid , long lanid){
		QueryHql hql = this.newQueryHql();
		hql.andBy("id", Condition.eq, id);
		hql.andBy("productID", Condition.eq, pid);
		hql.andBy("lanId", Condition.eq, lanid);
		List<ChildProductPO> list = findByHql(hql);
		return list;
	}
	
	public Long addChildByUidCid(Long userId , Long childIds){
		SubHql hql = newSubHql();
		hql.andBy("id", Condition.eq, userId);
		
		return 0l;
	}
	
	public List<ChildProductPO> getChildsList(long id){
		QueryHql hql = newQueryHql();
		hql.andBy("id", Condition.eq, id);
		List<ChildProductPO> list = findByHql(hql);
		return list;
	}
	
	public List<ChildProductPO> getChildByProName(String name){
		QueryHql hql = newQueryHql();
		hql.andBy("childName", Condition.is, name);
		List<ChildProductPO> list = findByHql(hql);
		return list;
	}
	
	/**
	 * Liw
	 * 算法支持
	 * 获得主要的一条导览点的信息
	 * @param productId
	 * @return
	 */
	public List<ChildProductPO> getChildByIsCen(long productId){
		QueryHql hql = newQueryHql();
		hql.andBy("productID", Condition.eq, productId);
		hql.andBy("isCen", Condition.eq, 1);
		List<ChildProductPO> list = findByHql(hql);
		return list;
	}
	
	/**
	 * Liw
	 * 算法支持
	 * 根据id查询一条导览点信息
	 * @param id
	 * @return
	 */
	public List<ChildProductPO> getChildById(long id){
		QueryHql hql = newQueryHql();
		hql.andBy("id", Condition.eq, id);
		List<ChildProductPO> list = findByHql(hql);
		return list;
	}
	
	/**
	 * Liw
	 * 算法支持
	 * 根据经纬度获取一条信息
	 * @param Lo
	 * @param La
	 * @return
	 */
	public List<ChildProductPO> getIdByLoLa(String Lo , String La){
		QueryHql hql = newQueryHql();
		hql.andBy("childLongitude", Condition.is, Lo);
		hql.andBy("childLatitude", Condition.is, La);
		List<ChildProductPO> list = findByHql(hql);
		return list;
	}
	
	public List<ChildProductPO> getChildsByCIdIsCen(long productID	){
		QueryHql hql = this.newQueryHql();
		hql.andBy("productID", Condition.eq, productID);
		hql.andBy("isCen", Condition.eq, 1);
		List<ChildProductPO> list = findByHql(hql);
		return list;
	}
	
}